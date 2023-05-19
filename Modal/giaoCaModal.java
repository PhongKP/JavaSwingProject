package Modal;

public class giaoCaModal {
	private String maCa;
	private String manv;
	private String gioBD;
	private String gioKT;
	private String tienBD;
	private String tienDT;
	private String tienTong;
	private String ngay;
	
	public giaoCaModal(String maCa, String manv, String gioBD, String gioKT, String tienBD, String tienDT,
			String tienTong,String ngay) {
		this.maCa = maCa;
		this.manv = manv;
		this.gioBD = gioBD;
		this.gioKT = gioKT;
		this.tienBD = tienBD;
		this.tienDT = tienDT;
		this.tienTong = tienTong;
		this.ngay = ngay;
	}
	
	public giaoCaModal ()
	{
		
	}

	public String getMaCa() {
		return maCa;
	}

	public void setMaCa(String maCa) {
		this.maCa = maCa;
	}

	public String getManv() {
		return manv;
	}

	public void setManv(String manv) {
		this.manv = manv;
	}

	public String getGioBD() {
		return gioBD;
	}

	public void setGioBD(String gioBD) {
		this.gioBD = gioBD;
	}

	public String getGioKT() {
		return gioKT;
	}

	public void setGioKT(String gioKT) {
		this.gioKT = gioKT;
	}

	public String getTienBD() {
		return tienBD;
	}

	public void setTienBD(String tienBD) {
		this.tienBD = tienBD;
	}

	public String getTienDT() {
		return tienDT;
	}

	public void setTienDT(String tienDT) {
		this.tienDT = tienDT;
	}

	public String getTienTong() {
		return tienTong;
	}

	public void setTienTong(String tienTong) {
		this.tienTong = tienTong;
	}
    public String getNgay(){
		return ngay;
	}
	public void setNgay(String ngay){
        this.ngay = ngay;
	}
	
	
}
