package Modal;

public class QuyenModal {
	private String maQuyen;
	private String tenQuyen;
	
	public QuyenModal ()
	{
		
	}
	
	public QuyenModal (String maQuyen,String tenQuyen)
	{
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
	}

	public String getMaQuyen() {
		return maQuyen;
	}

	public void setMaQuyen(String maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}
	
}
