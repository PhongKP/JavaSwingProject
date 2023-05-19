package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import ConnectionDB.connectionDB;
import Modal.chucNangModal;

public class QLCNDAO {
	connectionDB qlcnConnect;
	
	public QLCNDAO ()
	{
		qlcnConnect = new connectionDB();
	}
	
	public ArrayList<chucNangModal> readDB ()
	{
		ArrayList<chucNangModal> dscn = new ArrayList<>();
		try {
            String qry = "SELECT * FROM chucnang";
            java.sql.ResultSet r = qlcnConnect.sqlQuery(qry,null);
            if (r != null) {
                while (r.next()) {
                    String macn = r.getString("machucnang");
                    String tencn = r.getString("tenchucnang");
                    dscn.add(new chucNangModal(macn, tencn));
                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "-- ERROR! Lỗi đọc dữ liệu bảng chức năng");
        } finally {
            qlcnConnect.closeConnect();
        }
        return dscn;
		
	}
	
	public void close ()
	{
		qlcnConnect.closeConnect();
	}
}
