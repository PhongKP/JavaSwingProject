package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;


import ConnectionDB.connectionDB;
import Modal.ctCNModal;

public class QLCTCNDAO {
	connectionDB qlctcnDAO;
	
	public QLCTCNDAO ()
	{
		qlctcnDAO = new connectionDB();
	}
	
	public ArrayList<ctCNModal> readDB ()
	{
		ArrayList<ctCNModal> dsctcn = new ArrayList<>();
		try {
            String qry = "SELECT * FROM ctchucnang";
            java.sql.ResultSet r = qlctcnDAO.sqlQuery(qry,null);
            if (r != null) {
                while (r.next()) {
                    String macn = r.getString("machucnang");
                    String maquyen = r.getString("maquyen");
                    dsctcn.add(new ctCNModal(macn, maquyen));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng ctchucnang");
        } finally {
            qlctcnDAO.closeConnect();
        }
        return dsctcn;
	}
	
	public boolean add (String machucnang, String maquyen)
	{
		Object[] objs = {machucnang,maquyen};
		boolean ok = qlctcnDAO.sqlUpdate("INSERT INTO ctchucnang(machucnang,maquyen) VALUES(?,?)", objs);
		qlctcnDAO.closeConnect();
		return ok;
	}
	
	public boolean checkPrimaryKey (String machucnang, String maquyen)
	{
		Boolean ok = false;
		Object[] objs = {machucnang,maquyen};
		ResultSet res = qlctcnDAO.sqlQuery("SELECT * FROM ctchucnang WHERE machucnang = ? and maquyen = ?", objs);
		try {
			if (res.next())
			{
				ok = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}
	
//	public boolean checkPrimaryKeyByFunctionID (String value)
//	{
//		Boolean ok = false;
//		ResultSet res = qlctcnDAO.sqlQuery("SELECT * FROM ctchucnang WHERE machucnang = ?", new Object[] {value});
//		try {
//			if (res.next())
//			{
//				ok = true;
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ok;
//	}
	
	public boolean del (String machucnang, String maquyen)
	{
		Object[] objs = {machucnang,maquyen};
		boolean ok = qlctcnDAO.sqlUpdate("DELETE FROM ctchucnang WHERE machucnang = ? and maquyen = ?",objs);
		qlctcnDAO.closeConnect();
		return ok;
	}
	
	public void close ()
	{
		qlctcnDAO.closeConnect();
	}
}
