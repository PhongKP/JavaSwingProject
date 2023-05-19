package BUS;

import java.util.Date;
import java.time.LocalTime;
import java.util.ArrayList;

import DAO.giaoCaDAO;
import Modal.giaoCaModal;

public class giaoCaBUS {
	
	private ArrayList<giaoCaModal> dsgc = new ArrayList<>();
	giaoCaDAO qlgcDAO = new giaoCaDAO();
	
	public giaoCaBUS ()
	{
		dsgc = qlgcDAO.readDB();
	}
	
	public ArrayList<String> getAllMacaBymanv (String manv)
	{
		return qlgcDAO.getListMacaBymanv(manv);
	}
	
	public String getGioBDFormat (String maca)
	{
		String res = qlgcDAO.getGioBDFormat(maca);
		if (res != null)
		{
			return res;
		}
		return null;
	}
	
	public String getGioKTFormat (String maca)
	{
		String res = qlgcDAO.getGioKTFormat(maca);
		if (res != null)
		{
			return res;
		}
		return null;
	}
	
	public String getTienDT (String maca)
	{
		String res = qlgcDAO.getTienDT(maca);
		if (res != null)
		{
			return res;
		}
		return null;
	}
	
	public String getTienBD (String maca)
	{
		String res = qlgcDAO.getTienBD(maca);
		if (res != null)
		{
			return res;
		}
		return null;
	}
	
	public String getTongTien (String maca)
	{
		String res = qlgcDAO.getTongTien(maca);
		if (res != null)
		{
			return res;
		}
		return null;
	}
	
	public boolean updateTienDT (String tiendoanhthu, String maca)
	{
		boolean success = qlgcDAO.updateTienDT(tiendoanhthu,maca);
		if (success)
		{
			for (giaoCaModal gc : dsgc)
			{
				if (gc.getMaCa().equals(maca))
				{
					gc.setTienDT(tiendoanhthu);
				}
			}
		}
		return success;
	}
	
	public LocalTime getStartTime (String maca)
	{
		return qlgcDAO.getStart_Time(maca);
	}
	
	public LocalTime getEndTime (String maca)
	{
		return qlgcDAO.getEnd_Time(maca);
	}
	
	public boolean updateNgay (Date ngay, String maca)
	{
		boolean ok = qlgcDAO.updateNgay(ngay, maca);
		if (ok)
		{
			for (giaoCaModal gc : dsgc)
			{
				if (gc.getMaCa().equals(maca))
				{
					gc.setNgay(String.valueOf(ngay));
				}
			}
		}
		return ok;
	}
	
	public ArrayList<giaoCaModal> getDSGC ()
	{
		return dsgc;
	}
}
