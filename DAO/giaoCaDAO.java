package DAO;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import ConnectionDB.connectionDB;
import Modal.giaoCaModal;

public class giaoCaDAO {
	
	connectionDB qlgcConnect;
	
	public giaoCaDAO ()
	{
		qlgcConnect = new connectionDB();
	}
	
	public ArrayList<giaoCaModal> readDB ()
	{
		ArrayList<giaoCaModal> dsgc = new ArrayList<>();
		try {
			String sql = "SELECT * FROM calamviec";
			ResultSet res = qlgcConnect.sqlQuery(sql, null);
			if (res != null)
			{
				while (res.next())
				{
					String maCa = res.getString(1);
					String manv = res.getString(2);
					String gioBD = res.getString(3);
					String gioKT = res.getString(4);
					String tienBD = res.getString(5);
					String tienKT = res.getString(6);
					String tienTong = res.getString(7);
					String ngay = res.getString(8);
					dsgc.add(new giaoCaModal(maCa,manv,gioBD,gioKT,tienBD,tienKT,tienTong,ngay));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error: can't read the ca lam viec database");
		} finally {
			qlgcConnect.closeConnect();
		}
		return dsgc;
	}
	
	public ArrayList<String> getListMacaBymanv (String manv)
	{
		ArrayList<String> listmaca = new ArrayList<>();
		String sql = "SELECT maca FROM calamviec WHERE manv = ?";
		ResultSet res = qlgcConnect.sqlQuery(sql, new Object[] {manv});
		try {
			if (res != null)
			{
				while (res.next())
				{
					listmaca.add(res.getString("maca"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return listmaca;
	}
	
	public String getGioBDFormat (String maca)
	{
		String res = "";
		String sql  = "SELECT giobd AS start_time FROM calamviec WHERE maca = ?";
		ResultSet result = qlgcConnect.sqlQuery(sql, new Object[] {maca});
		try {
			if (result != null)
			{
				while (result.next())
				{
					res += result.getString("start_time");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return res;
	}
	
	public String getGioKTFormat (String maca)
	{
		String res = "";
		String sql  = "SELECT giokt AS end_time FROM calamviec WHERE maca = ?";
		ResultSet result = qlgcConnect.sqlQuery(sql, new Object[] {maca});
		try {
			if (result != null)
			{
				while (result.next())
				{
					res += result.getString("end_time");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return res;
	}
	
	public String getTienDT (String maca)
	{
		String res = "";
		String sql = "SELECT tiendoanhthu FROM calamviec WHERE maca = ?";
		ResultSet re = qlgcConnect.sqlQuery(sql, new Object[]{maca});
		try {
			if (re != null)
			{
				while (re.next())
				{
					res += re.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return res;
	}
	
	public String getTienBD (String maca)
	{
		String res = "";
		String sql = "SELECT tienbandau FROM calamviec WHERE maca = ?";
		ResultSet re = qlgcConnect.sqlQuery(sql, new Object[]{maca});
		try {
			if (re != null)
			{
				while (re.next())
				{
					res += re.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return res;
	}
	
	public String getTongTien (String maca)
	{
		String res = "";
		String sql = "SELECT tongtien FROM calamviec WHERE maca = ?";
		ResultSet re = qlgcConnect.sqlQuery(sql, new Object[]{maca});
		try {
			if (re != null)
			{
				while (re.next())
				{
					res += re.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return res;
	}
	
	
	public boolean updateTienDT (String tiendoanhthu, String maca)
	{
		String sql = "UPDATE calamviec SET tiendoanhthu = ? WHERE maca = ?";
		boolean ok = qlgcConnect.sqlUpdate(sql, new Object[] {tiendoanhthu,maca});
		qlgcConnect.closeConnect();
		return ok;
	}
	
	public LocalTime getStart_Time (String maca)
	{
		LocalTime start_time = null;
		String sql = "SELECT * FROM calamviec WHERE maca = ?";
		ResultSet res = qlgcConnect.sqlQuery(sql, new Object[] {maca});
		try {
			if (res != null)
			{
				while (res.next())
				{
					start_time = res.getTime("giobd").toLocalTime();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return start_time;
	}
	
	public LocalTime getEnd_Time (String maca)
	{
		LocalTime end_Time = null;
		String sql = "SELECT * FROM calamviec WHERE maca = ?";
		ResultSet res = qlgcConnect.sqlQuery(sql, new Object[] {maca});
		try {
			if (res != null)
			{
				while (res.next())
				{
					end_Time = res.getTime("giobd").toLocalTime();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		qlgcConnect.closeConnect();
		return end_Time;
	}
	
	public boolean updateNgay (Date ngay, String maca)
	{
		String sql = "UPDATE calamviec SET ngay = ? WHERE maca = ?";
		boolean ok = qlgcConnect.sqlUpdate(sql, new Object[] {ngay,maca});
		qlgcConnect.closeConnect();
		return ok;
	}
	
	public void close ()
	{
		qlgcConnect.closeConnect();
	}
}
