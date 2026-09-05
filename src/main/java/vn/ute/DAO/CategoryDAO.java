package vn.ute.DAO;

import java.util.List;
import vn.ute.model.Category;

public interface CategoryDAO {
	void insert(Category category);

	void edit(Category category);

	void delete(int id);

	Category get(int id);

	Category get(String name);

	List<Category> getAll();

	List<Category> search(String keyword);

}