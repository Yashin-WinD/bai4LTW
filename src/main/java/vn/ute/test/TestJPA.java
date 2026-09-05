package vn.ute.test;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import vn.ute.config.JPAConfig;
import vn.ute.model.Category;

/**
 * Chạy class này (Run As → Java Application) để kiểm tra cấu hình JPA.
 */
public class TestJPA {
	public static void main(String[] args) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			System.out.println("=== Test ket noi JPA ===");
			List<Category> list = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();
			System.out.println("So luong category: " + list.size());
			list.forEach(System.out::println);

			System.out.println("=== Test persist (rollback, khong luu DB) ===");
			trans.begin();
			Category sample = new Category();
			sample.setCatename("JPA-Test");
			sample.setIcon("jpa-test.png");
			em.persist(sample);
			em.flush();
			System.out.println("Persist OK, cate_id tam: " + sample.getId());
			trans.rollback();
			System.out.println("Rollback OK. Cau hinh JPA thanh cong.");
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
			System.err.println("Test JPA that bai. Kiem tra persistence.xml va MySQL.");
		} finally {
			em.close();
			JPAConfig.shutdown();
		}
	}
}
