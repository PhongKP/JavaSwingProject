package Modal;

public class DTOCTPN {
    private String manguyenlieu;
    private String soluong;
    private String dongia;
    public DTOCTPN(String manguyenlieu,String soluong,String dongia){
        this.manguyenlieu = manguyenlieu;
        this.soluong = soluong;
        this.dongia = dongia;
    }
    public String getMaNguyenLieu(){
        return manguyenlieu;
    }
    public String getSoLuong(){
        return soluong;
    }
    public String getDonGia(){
        return dongia;
    }
}
