package vn.ute.DAO;

import java.util.List;
import vn.ute.model.Product;

public interface ProductDAO {
	void insert(Product product);

	void edit(Product product);

	void delete(int id);

	Product get(int id);

	List<Product> getAll();

	List<Product> findNewest(int limit);

	List<Product> findPage(int page, int pageSize);

	long count();
}
