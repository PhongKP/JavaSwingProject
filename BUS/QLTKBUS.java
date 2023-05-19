package BUS;

import java.util.ArrayList;

import DAO.QLTKDAO;
import Modal.taiKhoanModal;

public class QLTKBUS {
	private ArrayList<taiKhoanModal> dstk = new ArrayList<>();
	QLTKDAO qltkDAO = new QLTKDAO();
	
	public QLTKBUS ()
	{
		dstk = qltkDAO.readDB();
	}
	
	public String[] getHeader ()
	{
		return new String[]{"password","username","role"};
	}
	
	public taiKhoanModal getTK (String userName)
	{
		for (taiKhoanModal tk : dstk)
		{
			if (tk.getUserName().equals(userName))
			{
				return tk;
			}
		}
		return null;
	}
	
	public String getPasswordByUserName (String userName)
	{
		return qltkDAO.getPasswordByName(userName);
	}
	
	public Boolean add (taiKhoanModal tk)
	{
		Boolean ok = qltkDAO.add(tk);

        if (ok) {
            dstk.add(tk);
        }
        return ok;
	}
	
	public Boolean del (String userName)
	{
		Boolean ok = qltkDAO.del(userName);

        if (ok) {
            for (int i = (dstk.size() - 1); i >= 0; i--) {
                if (dstk.get(i).getUserName().equals(userName)) {
                    dstk.remove(i);
                }
            }
        }
        return ok;
	}
	
	public Boolean update (String userName, String pass, String role)
	{
		Boolean ok = qltkDAO.update(userName, pass, role);
		if (ok)
		{
			dstk.forEach((tk)->{
				if (tk.getUserName().equals(userName))
				{
					tk.setPassWord(pass);
					tk.setRole(role);
				}
			});
		}
		return ok;
	}
	
	public Boolean updatePassword (String password, String userName)
	{
		Boolean ok = qltkDAO.updatePassword(password,userName);
		if (ok)
		{
			dstk.forEach((tk)->{
				if (tk.getUserName().equals(userName))
				{
					tk.setPassWord(password);
				}
			});
		}
		return ok;
	}
	
	public ArrayList<taiKhoanModal> getDSTK ()
	{
		return dstk;
	}
}
