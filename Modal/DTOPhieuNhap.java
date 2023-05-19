package Modal;

public class DTOPhieuNhap {
    private String maphieunhap;
    private String ngaynhap;
    private String tonggia;
    private String manhacc;
    private String manv ;
    public DTOPhieuNhap(String maphieunhap,String ngaynhap,String tonggia,String manhacc,String manv){
        this.maphieunhap = maphieunhap;
        this.ngaynhap = ngaynhap;
        this.tonggia = tonggia;
        this.manhacc = manhacc;
        this.manv = manv;
    }
    public String getMaPN(){
        return maphieunhap;
    }
    public String getNgayNhap(){
        return ngaynhap;
    }
    public String getTongGia(){
        return tonggia;
    }
    public String getMaNhaCC(){
        return manhacc;
    }
    public String getMaNV(){
        return manv;
    }
}
