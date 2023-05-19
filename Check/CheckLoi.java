package Check;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import BUS.CustomerBUS;
import BUS.QLCTCNBUS;
import BUS.QLQUYENBUS;
import BUS.QLTKBUS;
import Modal.QuyenModal;
//import Modal.ctCNModal;
import Modal.taiKhoanModal;

public class CheckLoi {
	
	private static QLTKBUS qltkBUS = new QLTKBUS();
	private static QLQUYENBUS qlqBUS = new QLQUYENBUS();
	private static QLCTCNBUS qlctcnBUS = new QLCTCNBUS();
	private static ArrayList<taiKhoanModal> dstk = qltkBUS.getDSTK();
//	private static ArrayList<ctCNModal> dsctcn = qlctcnBUS.getDSCTCN();
	private static ArrayList<QuyenModal> dsq = qlqBUS.getDSQ();
	
	public static boolean checkUsername (String userName)
	{
		boolean flag = false;
		for (taiKhoanModal tk: dstk)
		{
			if (tk.getUserName().equals(userName))
			{
				flag = true;
			}
		}
		return flag;
	}
	
	public static boolean checkPassword (String userPass,String userName)
	{
		boolean flag = false;
			for (taiKhoanModal tk: dstk)
			{
				if (tk.getUserName().equals(userName))
				{
					if (tk.getPassWord().equals(userPass))
					{
						flag = true;
					}
				}
			}
		return flag;
	}
	
	public static boolean checkRole (String userName)
	{
		boolean ok = false;
		for (taiKhoanModal tk : dstk)
		{
			if (tk.getUserName().equals(userName))
			{
				if (qlqBUS.getQuyen(tk.getRole()).equals("admin"))
				{
					ok = true;
				}
			}
		}
		return ok;
	}
	
	public static ArrayList<String> getListOfRole (String userName)
	{
		ArrayList<String> dsCN = new ArrayList<>();
		for (taiKhoanModal tk : dstk)
		{
			if (tk.getUserName().equals(userName))
			{
				dsCN = qlctcnBUS.getChucNang(tk.getRole());
			}
		}
		return dsCN;
	}
	
	public static String getTenQuyen (String userName)
	{
		for (taiKhoanModal tk : dstk)
		{
			if (tk.getUserName().equals(userName))
			{
				for (QuyenModal q : dsq)
				{
					if (tk.getRole().equals(q.getMaQuyen()))
						return q.getTenQuyen();
				}
			}
		}
		return null;
	}
	
	public static boolean isProductId(String id) {
		String regex = "^SP\\d{1,2}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.find() ? true : false;
	}

	public static boolean isDigit(String number) {
		boolean allDigits = true;
		for (int i = 0; i < number.length(); i++) {
			if (!Character.isDigit(number.charAt(i))) {
				allDigits = false;
				break;
			}
		}
		return allDigits;
	}
	
	public static boolean isAmount(String amout) {
		return isDigit(amout);
	}
	
	public static boolean isEmployeeId(String id) {
		String regex = "^NV\\d{1,2}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(id);
		return matcher.find() ? true : false;
	}
	
	public static boolean isCMND(String cmnd) {
		return cmnd.length() <= 12 && isDigit(cmnd) ? true : false;
	}

	public static boolean isPhoneNumber(String phoneNumber) {
		return phoneNumber.length() <= 10 && isDigit(phoneNumber) ? true : false;
	}

	public static boolean isEmail(String email) {
		String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(email);
		return matcher.find() ? true : false;
	}
	
	public static boolean checkForgotPassword (String oldPass,String userName)
	{
		String currentPass = qltkBUS.getPasswordByUserName(userName);
		return currentPass.equals(oldPass);
	}
	
	public static boolean checkBase64(String str) {
		try {
			Base64.getDecoder().decode(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public static boolean isBase64(String str) {
		try {
			Base64.getDecoder().decode(str);
			return true;
		} catch (IllegalArgumentException e) {
			return false;
		}
	}
	
	public static boolean checkExistSDT (String sdt)
	{
		List<String> listSDT = CustomerBUS.getInstance().getAllSDT();
		for (String item : listSDT)
		{
			if (item.equals(sdt))
			{
				return true;
			}
		}
		return false;
	}
	
}
