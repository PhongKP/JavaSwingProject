package Modal;

public class CustomerModel {
	private String makh;
	private String tenkh;
	private String sdt;
	private String diachi;

	public CustomerModel(String makh, String tenkh, String sdt, String diachi) {
		
		this.makh = makh;
		this.tenkh = tenkh;
		this.sdt = sdt;
		this.diachi = diachi;
	}

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getTenkh() {
		return tenkh;
	}

	public void setTenkh(String tenkh) {
		this.tenkh = tenkh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}
}
