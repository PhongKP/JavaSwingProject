package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectionDB.connectionDB;
import Modal.taiKhoanModal;

public class QLTKDAO {
	
	connectionDB qltkconnect;
	
	public QLTKDAO ()
	{
		qltkconnect = new connectionDB();
	}
	
	public ArrayList<taiKhoanModal> readDB ()
	{
		ArrayList<taiKhoanModal> dstk = new ArrayList<>();
		try {
			String sql = "SELECT * FROM taikhoan";
			ResultSet res = qltkconnect.sqlQuery(sql, null);
			if (res != null)
			{
				while (res.next())
				{
					String tenUser = res.getString("manv");
					String pass = res.getString("password");
					String role = res.getString("maquyen");
					dstk.add(new taiKhoanModal(tenUser,pass,role));
				}
			}
		} catch (SQLException sx) {
			JOptionPane.showMessageDialog(null,"Error: can't read the account database");
		} finally {
			qltkconnect.closeConnect();
		}
		return dstk;
	}
	
	public String getPasswordByName (String userName)
	{
		String pass = "";
		String sql = "SELECT password FROM taikhoan WHERE manv = ?";
		ResultSet res = qltkconnect.sqlQuery(sql, new Object[]{userName});
		try {
			if (res != null)
			{
				if (res.next())
				{
					pass = res.getString("password");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		qltkconnect.closeConnect();
		return pass;
	}
	
	public Boolean add (taiKhoanModal tk)
	{
		Boolean ok = qltkconnect.sqlUpdate("INSERT INTO `taikhoan` (`password`, `manv`, `maquyen`) VALUES ('"
                + tk.getPassWord() + "', '" + tk.getUserName() + "', '" + tk.getUserName() + "');",null);
		qltkconnect.closeConnect();
		return ok;
	}
	
	public Boolean del (String userName)
	{
		Boolean ok = qltkconnect.sqlUpdate("DELETE FROM `taikhoan` WHERE `taikhoan`.`TenTaiKhoan` = '" + userName + "'", null);
		qltkconnect.closeConnect();
		return ok;
	}
	
	public Boolean update (String userName,String pass,String role)
	{
		Boolean ok = qltkconnect.sqlUpdate("Update taikhoan Set password='" + pass 
                + "',maquyen='" + role + "' where manv='" + userName + "'",null);
        qltkconnect.closeConnect();
        return ok;
	}
	
	public Boolean updatePassword (String password, String userName)
	{
		Object[] objs = {password,userName};
		Boolean ok = qltkconnect.sqlUpdate("UPDATE taikhoan SET password = ? WHERE manv = ?",objs);
		qltkconnect.closeConnect();
		return ok;
	}
	
	public void close ()
	{
		qltkconnect.closeConnect();
	}
	
}
