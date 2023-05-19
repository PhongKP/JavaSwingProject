package BUS;


import java.util.ArrayList;

import DAO.QLCNDAO;
import Modal.chucNangModal;

public class QLCNBUS {
	
	private ArrayList<chucNangModal> dscn = new ArrayList<>();
	QLCNDAO qlcnDAO = new QLCNDAO();
	
	public QLCNBUS ()
	{
		this.dscn = qlcnDAO.readDB();
	}
	
	public String getChucNang (String maChucNang)
	{
		for (chucNangModal cn : dscn)
		{
			if (cn.getMaChucNang().equals(maChucNang))
			{
				return cn.getTenChucNang();
			}
		}
		return null;
	}
	
	public String getMaChucNang (String tenCN)
	{
		for (chucNangModal cn : dscn)
		{
			if (cn.getTenChucNang().equals(tenCN))
			{
				return cn.getMaChucNang();
			}
		}
		return null;
	}
	
	public ArrayList<chucNangModal> getDSCN ()
	{
		return dscn;
	}
	
}
