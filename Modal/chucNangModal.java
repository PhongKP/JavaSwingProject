package Modal;

public class chucNangModal {
	private String maChucNang;
	private String tenChucNang;
	
	public chucNangModal ()
	{
		
	}
	
	public chucNangModal (String maChucNang, String tenChucNang)
	{
		this.maChucNang = maChucNang;
		this.tenChucNang = tenChucNang;
	}

	public String getMaChucNang() {
		return maChucNang;
	}

	public void setMaChucNang(String maChucNang) {
		this.maChucNang = maChucNang;
	}

	public String getTenChucNang() {
		return tenChucNang;
	}

	public void setTenChucNang(String tenChucNang) {
		this.tenChucNang = tenChucNang;
	}
	
}
