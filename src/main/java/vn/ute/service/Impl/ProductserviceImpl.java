package vn.ute.service.Impl;

import java.util.List;

import vn.ute.DAO.ProductDAO;
import vn.ute.DAO.Impl.ProductDAOImpl;
import vn.ute.model.Product;
import vn.ute.service.Productservice;
import vn.ute.util.UploadUtil;

public class ProductserviceImpl implements Productservice {
	ProductDAO productDAO = new ProductDAOImpl();

	@Override
	public void insert(Product product) {
		productDAO.insert(product);
	}

	@Override
	public void delete(int id) {
		Product old = productDAO.get(id);
		if (old != null) {
			UploadUtil.delete(old.getImage());
			productDAO.delete(id);
		}
	}

	@Override
	public Product get(int id) {
		return productDAO.get(id);
	}

	@Override
	public void edit(Product newProduct) {
		Product old = productDAO.get(newProduct.getId());
		if (old == null) {
			return;
		}
		old.setName(newProduct.getName());
		old.setDescription(newProduct.getDescription());
		old.setPrice(newProduct.getPrice());
		old.setAmount(newProduct.getAmount());
		old.setCategory(newProduct.getCategory());
		if (newProduct.getImage() != null) {
			UploadUtil.delete(old.getImage());
			old.setImage(newProduct.getImage());
		}
		productDAO.edit(old);
	}

	@Override
	public List<Product> getAll() {
		return productDAO.getAll();
	}

	@Override
	public List<Product> findNewest(int limit) {
		return productDAO.findNewest(limit);
	}

	@Override
	public List<Product> findPage(int page, int pageSize) {
		return productDAO.findPage(page, pageSize);
	}

	@Override
	public long count() {
		return productDAO.count();
	}
}
