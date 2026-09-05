package vn.ute.service.Impl;

import java.io.File;
import java.util.List;
import vn.ute.DAO.CategoryDAO;
import vn.ute.DAO.Impl.CategoryDAOImpl;
import vn.ute.model.Category;
import vn.ute.service.Categoryservice;

public class CategoryserviceImpl implements Categoryservice {
	CategoryDAO categoryDAO = new CategoryDAOImpl();

	@Override
	public void insert(Category category) {
		categoryDAO.insert(category);
	}

	@Override
	public void delete(int id) {
		categoryDAO.delete(id);
	}

	@Override
	public Category get(int id) {
		return categoryDAO.get(id);
	}

	@Override
	public void edit(Category newCategory) {
		Category oldCategory = categoryDAO.get(newCategory.getId());
		oldCategory.setCatename(newCategory.getCatename());
		if (newCategory.getIcon() != null) {
// XOA ANH CU DI
			String fileName = oldCategory.getIcon();
			final String dir = "E:\\upload";
			File file = new File(dir + "/category" + fileName);
			if (file.exists()) {
				file.delete();
			}
			oldCategory.setIcon(newCategory.getIcon());
		}
		categoryDAO.edit(oldCategory);
	}

	@Override
	public Category get(String name) {
		return categoryDAO.get(name);
	}

	@Override
	public List<Category> getAll() {
		return categoryDAO.getAll();
	}

	@Override
	public List<Category> search(String catename) {
		return categoryDAO.search(catename);
	}
}