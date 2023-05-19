package ConnectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class connectionDB {
	private String hostName = "localhost:3306";
	private String dbName = "qlcoffee";
	private String userName = "root";
	private String passWord = "";
	private Connection conn = null;
	private PreparedStatement pst = null;
	private String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName + "?useUnicode=true&characterEncoding=UTF-8";
	private ResultSet rst;
	
	public connectionDB ()
	{
//		checkDriver();
		setUpDB();
	}
	
	public connectionDB (String dbName)
	{
		checkDriver();
		this.dbName = dbName;
	}
	
	public Connection connectDB ()
	{
		Connection con = null;
		try {
			con = DriverManager.getConnection(connectionURL,userName,passWord);
//			System.out.println("Success");
//			System.out.println(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	private void checkDriver ()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Not found the driver for mysql");
			
		}
	}
	
	public Boolean checkConnect ()
	{
		if (conn == null || pst == null)
		{
			JOptionPane.showMessageDialog(null, "Error: Hasn't connect to the database");
			return false;
		}
		return true;
	}
	
	private void setUpDB()
	{
		try {
			conn = DriverManager.getConnection(connectionURL,userName,passWord);
			System.out.println("Success in connect to "+dbName);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Cannot connect to "+dbName);
		}
	}
	
	public ResultSet sqlQuery (String querry,Object[] objects)
	{
			try {
				if (conn == null || conn.isClosed())
				{
					conn = DriverManager.getConnection(connectionURL,userName,passWord);
				}
				pst = conn.prepareStatement(querry);
				if (objects != null)
				{
					for (int i=0;i<objects.length;i++)
					{
						pst.setObject(i+1, objects[i]);
					}
				}
				rst = pst.executeQuery();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error: Can't get data from "+dbName+"\n"+e.getLocalizedMessage());
				rst = null;
			}
		return rst;
		
	}
	
	public Boolean sqlUpdate (String sql,Object[] objects)
	{
			try {
				if (conn == null || conn.isClosed())
				{
					conn = DriverManager.getConnection(connectionURL,userName,passWord);
				}
				pst = conn.prepareStatement(sql);
				if (objects != null)
				{
					for (int i=0;i<objects.length;i++)
					{
						pst.setObject(i+1, objects[i]);
					}
				}
				pst.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Error: Can't update data into "+dbName+"\n"+e.getLocalizedMessage());
				return false;
			}
	}
	
	public void closeConnect ()
	{
		try {
			if (rst != null && !rst.isClosed()) {
                rst.close();
            }
            if (pst != null && !pst.isClosed()) {
                pst.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
            
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: Close connection to "+dbName+"\n"+e.getLocalizedMessage());
		}
	}
}
