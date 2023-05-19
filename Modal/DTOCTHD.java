package Modal;

public class DTOCTHD {
    private String mahd;
    private String masp;
    private String tensp;
    private String soluong;
    private String dongia;
    private String thanhtien;
    private String ghichu;

    public DTOCTHD(String mahd, String masp, String tensp, String dongia, String soluong, String thanhtien, String ghichu) {
        this.mahd = mahd;
        this.masp = masp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.ghichu = ghichu;
        this.thanhtien = thanhtien;
    }

    public String getMaHD() {
        return mahd;
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

    public String getDonGia() {
        return dongia;
    }

    public String getThanhTien() {
        return thanhtien;
    }

    public String getGhiChu() {
        return ghichu;
    }
}
