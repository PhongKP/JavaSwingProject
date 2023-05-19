package BUS;

import java.util.List;

import DAO.CustomerDAO;
import Modal.CustomerModel;

public class CustomerBUS {
	public static CustomerBUS instance ;
	public static CustomerBUS getInstance() {
		if(instance == null) {
			instance = new CustomerBUS();
		}
		return instance;
	}
	public List<CustomerModel> getAllCustomer(){
		return CustomerDAO.getInstance().getAllCustomer();
	}
	public CustomerModel getCustomerByID(String makh) {
		return CustomerDAO.getInstance().getCustomerByID(makh);
	}
	
	public boolean update (String maKH, String tenkh, String sdt, String diachi)
	{
		return CustomerDAO.getInstance().update(maKH, tenkh, sdt, diachi);
	}
	
	public boolean add (String maKH, String tenkh, String sdt, String diachi)
	{
		return CustomerDAO.getInstance().add(maKH, tenkh, sdt, diachi);
	}
	
	public String getAutoIncresementID ()
	{
		return CustomerDAO.getInstance().getAutoIncresementID();
	}
	
	public List<String> getAllSDT ()
	{
		return CustomerDAO.getInstance().getAllSDT();
	}
	
	public String getNameBySDT (String sdt)
	{
		return CustomerDAO.getInstance().getNameBySDT(sdt);
	}
	
	public String getIDBySDT (String sdt)
	{
		return CustomerDAO.getInstance().getIDBySDT(sdt);
	}
}
