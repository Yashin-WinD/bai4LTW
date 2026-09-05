package vn.ute.DAO;

import vn.ute.model.User;

public interface UserDAO {
	User get(String username);

	User findByEmail(String email);

	User findByUsernameOrEmail(String key);

	void insert(User user);

	void update(User user);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);
}
