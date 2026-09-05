package vn.ute.DAO.Impl;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import vn.ute.DAO.UserDAO;
import vn.ute.config.JPAConfig;
import vn.ute.model.User;

public class UserDAOImpl implements UserDAO {

	@Override
	public User get(String username) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username", User.class);
			query.setParameter("username", username);
			List<User> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public User findByEmail(String email) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
			query.setParameter("email", email);
			List<User> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public User findByUsernameOrEmail(String key) {
		EntityManager em = JPAConfig.getEntityManager();
		try {
			TypedQuery<User> query = em.createQuery(
					"SELECT u FROM User u WHERE u.userName = :key OR u.email = :key", User.class);
			query.setParameter("key", key);
			List<User> result = query.getResultList();
			return result.isEmpty() ? null : result.get(0);
		} finally {
			em.close();
		}
	}

	@Override
	public void insert(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.persist(user);
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
	public void update(User user) {
		EntityManager em = JPAConfig.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		try {
			trans.begin();
			em.merge(user);
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
	public boolean existsByUsername(String username) {
		return get(username) != null;
	}

	@Override
	public boolean existsByEmail(String email) {
		return findByEmail(email) != null;
	}
}
