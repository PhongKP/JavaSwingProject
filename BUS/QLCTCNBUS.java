package BUS;

import java.util.ArrayList;

import DAO.QLCTCNDAO;
import Modal.ctCNModal;

public class QLCTCNBUS {
	private ArrayList<ctCNModal> dsctcn = new ArrayList<>();
	QLCTCNDAO qlctcnDAO =  new QLCTCNDAO();
	
	public QLCTCNBUS ()
	{
		dsctcn = qlctcnDAO.readDB();
	}
	
	public ArrayList<String> getChucNang (String maQuyen)
	{
		ArrayList<String> dsCN = new ArrayList<>();
		for (ctCNModal ctcn : dsctcn)
		{
			if (ctcn.getMaQuyen().equals(maQuyen))
				dsCN.add(ctcn.getMaChucNang());
		}
		return dsCN;
	}
	
	public boolean add (String machucnang, String maquyen)
	{
		boolean ok = qlctcnDAO.add(machucnang, maquyen);
		if (ok)
		{
			dsctcn.add(new ctCNModal(machucnang,maquyen));
		}
		return ok;
	}
		
	public boolean checkKey (String machucnang,String maquyen)
	{
		boolean ok = qlctcnDAO.checkPrimaryKey(machucnang, maquyen);
		if (ok)
		{
			return true;
		}
		return false;
	}
	
	public boolean del (int Index)
	{
		boolean ok = false;
		for (int i=0;i<dsctcn.size();i++)
		{
			if (dsctcn.size() != 0)
			{
				if (i == Index)
				{
					if (qlctcnDAO.del(dsctcn.get(i).getMaChucNang(), dsctcn.get(i).getMaQuyen()))
					{
						dsctcn.remove(dsctcn.get(i));
						ok = true;
					}
				}
			}
		}
		return ok;
	}
	
	public ArrayList<ctCNModal> getDSCTCN ()
	{
		return dsctcn;
	}
}
