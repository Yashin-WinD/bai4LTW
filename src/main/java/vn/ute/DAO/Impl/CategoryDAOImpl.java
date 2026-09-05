package vn.ute.DAO.Impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import vn.ute.DAO.CategoryDAO;
import vn.ute.config.JPAConfig;
import vn.ute.model.Category;

public class CategoryDAOImpl implements CategoryDAO {

	@Override
	public void insert(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void edit(Category category) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			Category category = em.find(Category.class, id);
			if (category != null) {
				em.remove(category);
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (trans.isActive()) {
				trans.rollback();
			}
		} finally {
			em.close();
		}
	}

	@Override
	public Category get(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Category.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public Category get(String name) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c WHERE c.catename = :cateName";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			query.setParameter("cateName", name);
			List<Category> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category> getAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Category> search(String keyword) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			String jpql = "SELECT c FROM Category c WHERE c.catename LIKE :keyword";
			TypedQuery<Category> query = em.createQuery(jpql, Category.class);
			query.setParameter("keyword", "%" + keyword + "%");
			return query.getResultList();
		} finally {
			em.close();
		}
	}
}
