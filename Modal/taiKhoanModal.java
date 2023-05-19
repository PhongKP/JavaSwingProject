package Modal;

public class taiKhoanModal {
	protected String userName;
	protected String passWord;
	protected String role;
	
	public taiKhoanModal ()
	{
		
	}
	

	public taiKhoanModal(String userName, String passWord,String role) {
		this.userName = userName;
		this.passWord = passWord;
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


}
