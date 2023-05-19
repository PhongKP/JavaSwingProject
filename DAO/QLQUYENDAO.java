package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectionDB.connectionDB;
import Modal.QuyenModal;

public class QLQUYENDAO {
	connectionDB qlqConnect;
	
	public QLQUYENDAO ()
	{
		qlqConnect = new connectionDB();
	}
	
	public ArrayList<QuyenModal> readDB ()
	{
        ArrayList<QuyenModal> dsq = new ArrayList<>();
        try {
            String qry = "SELECT * FROM quyen";
            java.sql.ResultSet r = qlqConnect.sqlQuery(qry,null);
            if (r != null) {
                while (r.next()) {
                    String maq = r.getString("MaQuyen");
                    String tenq = r.getString("TenQuyen");
                    dsq.add(new QuyenModal(maq, tenq));
                }
            }

        } catch (java.sql.SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng phân quyền");
        } finally {
            qlqConnect.closeConnect();
        }
        return dsq;
	}
	
	public boolean add (String maquyen, String tenquyen)
	{
		Object[] objs = {maquyen,tenquyen};
		boolean ok = qlqConnect.sqlUpdate("INSERT INTO quyen(maquyen,tenquyen) VALUES(?,?)", objs);
		qlqConnect.closeConnect();
		return ok;
	}
	
	public boolean del (String maquyen)
	{
		boolean ok = qlqConnect.sqlUpdate("DELETE FROM quyen WHERE maquyen = ?", new Object[] {maquyen});
		qlqConnect.closeConnect();
		return ok;
	}
	
	public boolean update (String maquyen, String tenquyen)
	{
		Object[] objs = {tenquyen,maquyen};
		boolean ok = qlqConnect.sqlUpdate("UPDATE quyen SET tenquyen = ? WHERE maquyen = ?", objs);
		qlqConnect.closeConnect();
		return ok;
	}
	
	public boolean checkPrimaryKeyByIdRole (String value)
	{
		Boolean ok = false;
		ResultSet res = qlqConnect.sqlQuery("SELECT * FROM quyen WHERE maquyen = ?",new Object[] {value});
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
	
//	public boolean checkPrimaryKeyByRoleName (String value)
//	{
//		Boolean ok = false;
//		Object[] objs = {value};
//		ResultSet res = qlqConnect.sqlQuery("SELECT * FROM quyen WHERE tenquyen = ?", objs);
//		if (res != null)
//		{
//			ok = true;
//		}
//		return ok;
//	}
	
	public void close ()
	{
		qlqConnect.closeConnect();
	}
}
