package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ConnectionDB.connectionDB;
import Modal.CustomerModel;

public class CustomerDAO {
	public static CustomerDAO instance;
	public static CustomerDAO getInstance() {
		if(instance == null) {
			instance = new CustomerDAO();
		}
		return instance ;
	}
	public List<CustomerModel>getAllCustomer(){
		String query = "Select * from khachhang";
		List<CustomerModel> list = new ArrayList<>();
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, null);
		try {
			while(rs.next()) {
				list.add(new CustomerModel(rs.getString("makh"),rs.getString("tenkh"),rs.getString("sdt"),rs.getString("diachi")));
			}
		}catch(Exception ex) {
			System.out.println(ex);
		}
		connect.closeConnect();
		return list;
		
	}
	public CustomerModel getCustomerByID(String makh) {
		String query = "Select * from khachhang where makh = ?";
		CustomerModel customer = null;
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, new Object[] {makh});
		try {
			if(rs.next()) {
				customer = new CustomerModel(rs.getString("makh"),rs.getString("tenkh"),rs.getString("sdt"),rs.getString("diachi"));
			}
			
		}catch(Exception ex) {
			System.out.println(ex);
		}
		connect.closeConnect();
		return customer;
		
	}
	
	public boolean update (String maKH, String tenkh, String sdt, String diachi)
	{
		String querry = "UPDATE khachhang SET tenkh = ?,sdt = ?,diachi = ? WHERE maKH = ?";
		Object [] objs = {tenkh,sdt,diachi,maKH};
		connectionDB conn = new connectionDB();
		boolean ok = conn.sqlUpdate(querry, objs);
		conn.closeConnect();
		if (ok)
			return true;
		return false;
	}
	
	public boolean add (String maKH, String tenkh, String sdt, String diachi)
	{
		String querry = "INSERT INTO khachhang(makh,tenkh,sdt,diachi) VALUES(?,?,?,?)";
		Object [] objs = {maKH,tenkh,sdt,diachi};
		connectionDB conn = new connectionDB();
		boolean ok = conn.sqlUpdate(querry, objs);
		conn.closeConnect();
		if (ok)
			return true;
		return false;
	}
	
	public String getAutoIncresementID ()
	{
		String sql = "SELECT createKhachHangID()";
		String re = "";
		connectionDB conn = new connectionDB();
		try {
			ResultSet res = conn.sqlQuery(sql, null);
			if (res.next())
			{
				re += res.getNString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return re;
	}
	
	public List<String> getAllSDT ()
	{
		String query = "Select sdt from khachhang";
		List<String> list = new ArrayList<>();
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, null);
		try {
			while(rs.next()) {
				list.add(rs.getString(1));
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		connect.closeConnect();
		return list;
	}
	
	public String getNameBySDT (String sdt)
	{
		String sql = "SELECT tenkh FROM khachhang WHERE sdt = ?";
		String re = "";
		connectionDB conn = new connectionDB();
		try {
			ResultSet res = conn.sqlQuery(sql, new Object[]{sdt});
			if (res.next())
			{
				re += res.getNString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return re;
	}
	
	public String getIDBySDT (String sdt)
	{
		String sql = "SELECT makh FROM khachhang WHERE sdt = ?";
		String re = "";
		connectionDB conn = new connectionDB();
		try {
			ResultSet res = conn.sqlQuery(sql, new Object[]{sdt});
			if (res.next())
			{
				re += res.getNString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return re;
	}
}
