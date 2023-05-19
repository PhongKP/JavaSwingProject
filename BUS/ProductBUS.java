package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionDB.connectionDB;
import DAO.ProductDAO;
import Modal.ProductModal;

public class ProductBUS {
	private ArrayList<ProductModal> productList = null;
	private ProductDAO productDAO = null;
	private ArrayList<ProductModal> list = null; // get products trạng thái đang bán 

	public ProductBUS() {
		productList = new ArrayList<ProductModal>();
		productDAO = new ProductDAO();
		productList = productDAO.product_readDB();
	}

	public void show() {
		productList.forEach((product) -> {
			System.out.println(product.getProduct_id() + " ");
			System.out.println(product.getProduct_name() + " ");
			System.out.println(product.getProduct_type() + " ");
			System.out.println(product.getProduct_price() + " ");
			System.out.println(product.getProduct_state() + " ");
		});
	}

	public String[] getHeader() {
		return new String[] { "Mã SP", "Tên SP", "Loại SP", "Đơn giá", "Trạng thái" };
	}

	public void readDB() {
		productList = productDAO.product_readDB();
	}

	// Method for developer
	public ArrayList<ProductModal> readDB_() {
		productList = productDAO.product_readDB();
		return productList;
	}

	public ProductModal getProducts(String id) {
		for (ProductModal product : productList)
			if (id.equalsIgnoreCase(product.getProduct_id()))
				return product;
		return null;
	}

	public Boolean add(ProductModal product) {
		boolean check = productDAO.add(product);

		if (check)
			productList.add(product);
		return check;
	}

	public Boolean add(String id, String name, String type, String price, String state, String recipe, String image,
			String amount) {
		ProductModal product = new ProductModal();
		product.setProduct_id(id);
		product.setProduct_name(name);
		product.setProduct_type(type);
		product.setProduct_price(Float.parseFloat(price));
		product.setProduct_state(state);
		product.setProduct_amount(amount);
		return add(product);
	}

	public ArrayList<ProductModal> getProducts() {
		list = productDAO.getProducts();
		return list;
	}

	public Boolean update( String selectedId, String name, float price, int amount, String type, String image, String state,
			String note) {
		Boolean check = productDAO.update( selectedId, name, price, amount, type, image, state, note);

		if (check) {
			productList.forEach((product) -> {
				
					product.setProduct_name(name);
					product.setProduct_price(price);
					product.setProduct_amount(String.valueOf(amount));
					product.setProduct_type(type);
					product.setProduct_image(image);
					product.setProduct_state(state);
					product.setProduct_note(note);
				
			});
		}

		return check;
	}

	public ProductModal findById(String id) throws SQLException {
		String sql = "SELECT * FROM sanpham JOIN theloai ON sanpham.maloai = theloai.maloai WHERE masp = ?";
		connectionDB con = new connectionDB();
		Connection connection = con.connectDB();
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, id);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			ProductModal product = new ProductModal();
			product.setProduct_id(id);
			product.setProduct_name(resultSet.getString("tensp"));
			product.setProduct_price(resultSet.getFloat("gia"));
			product.setProduct_amount(resultSet.getString("soluong"));
			product.setProduct_type(resultSet.getString("tenloai"));
			product.setProduct_image(resultSet.getString("hinhanh"));
			product.setProduct_state(resultSet.getString("trangthai"));
			product.setProduct_note(resultSet.getString("ghichu"));

			con.closeConnect();
			connection.close();
			statement.close();
			resultSet.close();
			return product;
		} else
			return null;
	}
}
