package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ConnectionDB.connectionDB;
import Modal.ProductModal;

public class ProductDAO {
	private connectionDB connection = null;

	public ProductDAO() {

	}

	public ArrayList<ProductModal> product_readDB() {
		connection = new connectionDB();
		ArrayList<ProductModal> productList = new ArrayList<ProductModal>();
		try {
			String query = "SELECT * FROM sanpham join theloai on theloai.maloai = sanpham.maloai";
			ResultSet resultSet = connection.sqlQuery(query, null);
			if (resultSet != null) {
				while (resultSet.next()) {
					String id = resultSet.getString("masp");
					String name = resultSet.getString("tensp");
					String type = resultSet.getString("tenloai");
					float price = resultSet.getFloat("gia");
					String state = resultSet.getString("trangthai");
					String amount = resultSet.getString("soluong");
					String image = resultSet.getString("hinhanh");
					String note = resultSet.getString("ghichu");
					productList.add(new ProductModal(id, name, type, price, state, note, image, amount));
				}
			}
		} catch (SQLException e) {
			System.out.println("Read fail");
		} finally {
			connection.closeConnect();
		}
		return productList;
	}
	public ArrayList<ProductModal> getProducts() {
		connection = new connectionDB();
		ArrayList<ProductModal> productList = new ArrayList<ProductModal>();
		try {
			String query = "SELECT * FROM sanpham ,theloai WHERE theloai.maloai = sanpham.maloai and sanpham.trangthai <> 'Ngừng bán'";
			ResultSet resultSet = connection.sqlQuery(query, null);
			if (resultSet != null) {
				while (resultSet.next()) {
					String id = resultSet.getString("masp");
					String name = resultSet.getString("tensp");
					String type = resultSet.getString("tenloai");
					float price = resultSet.getFloat("gia");
					String state = resultSet.getString("trangthai");
					String amount = resultSet.getString("soluong");
					String image = resultSet.getString("hinhanh");
					String note = resultSet.getString("ghichu");
					productList.add(new ProductModal(id, name, type, price, state, note, image, amount));
				}
			}
		} catch (SQLException e) {
			System.out.println("Read fail");
		} finally {
			connection.closeConnect();
		}
		return productList;
	}

	public Boolean add(ProductModal product) {
		connection = new connectionDB();

		String query = "insert into sanpham(masp,tensp,gia,soluong,maloai,hinhanh,trangthai,ghichu) values ( ?,?,?,?,  (select maloai from theloai where tenloai = ?) ,?,?,? )";
		boolean issuccess = connection.sqlUpdate(query,
				new Object[] { product.getProduct_id(), product.getProduct_name(), product.getProduct_price(),
						product.getProduct_amount(), product.getProduct_type(), product.getProduct_image(),
						product.getProduct_state(), product.getProduct_note()});

		connection.closeConnect();
		return issuccess;
	}

	public Boolean delete(String id) {
		connection = new connectionDB();
		Boolean ok = connection.sqlUpdate("DELETE FROM `sanpham` WHERE `sanpham`.`masp` = '" + id + "'", null);
		connection.closeConnect();
		return ok;
	}

	public Boolean update( String selectedId , String name, float price, int amount, String type, String image, String state,
			String note) {
		connection = new connectionDB();

		String query = "UPDATE sanpham, theloai " + "SET sanpham.tensp = '" + name
				+ "', " + "sanpham.gia = " + price + ", " + "sanpham.soluong = " + amount + ", "
				+ "sanpham.maloai = theloai.maloai, " + "sanpham.hinhanh = '" + image + "', " + "sanpham.trangthai = '"
				+ state + "', " + "sanpham.ghichu = '" + note + "' "
				+ "WHERE sanpham.maloai = theloai.maloai AND sanpham.masp = '" + selectedId + "'";

		Boolean check = connection.sqlUpdate(query, null);
		connection.closeConnect();
		return check;
	}


}
