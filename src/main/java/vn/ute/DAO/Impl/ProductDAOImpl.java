package vn.ute.DAO.Impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import vn.ute.DAO.ProductDAO;
import vn.ute.config.JPAConfig;
import vn.ute.model.Product;

public class ProductDAOImpl implements ProductDAO {

	@Override
	public void insert(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(product);
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
	public void edit(Product product) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(product);
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
			Product product = em.find(Product.class, id);
			if (product != null) {
				em.remove(product);
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
	public Product get(int id) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.find(Product.class, id);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> getAll() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery(
					"SELECT p FROM Product p ORDER BY p.createdDate DESC", Product.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findNewest(int limit) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery(
					"SELECT p FROM Product p ORDER BY p.createdDate DESC", Product.class);
			query.setMaxResults(limit);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public List<Product> findPage(int page, int pageSize) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<Product> query = em.createQuery(
					"SELECT p FROM Product p ORDER BY p.createdDate DESC", Product.class);
			query.setFirstResult((page - 1) * pageSize);
			query.setMaxResults(pageSize);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

	@Override
	public long count() {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			return em.createQuery("SELECT COUNT(p) FROM Product p", Long.class).getSingleResult();
		} finally {
			em.close();
		}
	}
}
