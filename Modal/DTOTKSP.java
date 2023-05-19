package Modal;

public class DTOTKSP {
	 private String masp;
	 private String tensp;
	 private String soluong;
	 private String trangthai;

	 public DTOTKSP(String masp, String tensp, String soluong, String trangthai) {
	        this.masp = masp;
	        this.tensp = tensp;
	        this.soluong = soluong;
	        this.trangthai = trangthai;
	 }

	    public String getMaSP() {
	        return masp;
	    }

	    public String getTenSP() {
	        return tensp;
	    }

	    public String getSoLuong() {
	        return soluong;

	    }

	    public String getTrangThai() {
	        return trangthai;
	    }
}
