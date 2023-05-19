package Modal;

public class ctCNModal {
	private String maChucNang;
	private String maQuyen;
	
	public ctCNModal ()
	{
		
	}
	
	public ctCNModal (String maChucNang, String maQuyen)
	{
		this.maChucNang = maChucNang;
		this.maQuyen = maQuyen;
	}

	public String getMaChucNang() {
		return maChucNang;
	}

	public void setMaChucNang(String maChucNang) {
		this.maChucNang = maChucNang;
	}

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}
	
	
}
