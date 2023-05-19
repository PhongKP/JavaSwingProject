package BUS;

import java.util.ArrayList;

import DAO.QLQUYENDAO;
import Modal.QuyenModal;

public class QLQUYENBUS {
	
	private ArrayList<QuyenModal> dsq = new ArrayList<>();
	QLQUYENDAO qlqDAO = new QLQUYENDAO();
	
	public QLQUYENBUS ()
	{
		dsq = qlqDAO.readDB();
	}
	
	public String getQuyen (String maQuyen)
	{
		for (QuyenModal q : dsq)
		{
			if (q.getMaQuyen().equals(maQuyen))
			{
				return q.getTenQuyen();
			}
		}
		return null;
	}
	
	public boolean add (String maquyen, String tenquyen)
	{
		boolean ok = qlqDAO.add(maquyen, tenquyen);
		if (ok)
		{
			dsq.add(new QuyenModal(maquyen,tenquyen));
		}
		return ok;
	}
	
	public boolean del (int Index)
	{
		boolean ok = false;
		for (int i=0;i<dsq.size();i++)
		{
			if (dsq.size() != 0)
			{
				if (i == Index)
				{
					if(qlqDAO.del(dsq.get(i).getMaQuyen()));
					{
						ok = true;
						dsq.remove(dsq.get(i));
					}
				}
			}
		}
		return ok;
	}
	
	public boolean update (String maquyen,String tenquyen)
	{
		boolean ok = qlqDAO.update(maquyen, tenquyen);
		if (ok)
		{
			dsq.forEach(q->{
				if (q.getMaQuyen().equals(maquyen))
				{
					q.setTenQuyen(tenquyen);
				}
			});
		}
		return ok;
	}
	
	public boolean checkIdRole(String maquyen)
	{
		boolean ok = qlqDAO.checkPrimaryKeyByIdRole(maquyen);
		if (ok)
		{
			return true;
		}
		return false;
	}
	
//	public boolean checkRoleName (String tenquyen)
//	{
//		boolean ok = qlqDAO.checkPrimaryKeyByRoleName(tenquyen);
//		if (ok)
//		{
//			return true;
//		}
//		return false;
//	}
	
	public ArrayList<QuyenModal> getDSQ ()
	{
		return dsq;
	}
	
}
