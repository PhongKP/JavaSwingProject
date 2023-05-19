package Modal;

public class DTOCongThuc {
    private String manguyenlieu;
    private String tennguyenlieu;
    private String soluong;
    public DTOCongThuc(String manguyenlieu,String tennguyenlieu,String soluong){
        this.manguyenlieu = manguyenlieu;
        this.tennguyenlieu = tennguyenlieu;
        this.soluong = soluong;
    }
    public String getMaNguyenLieu(){
        return manguyenlieu;
    }
    public String getTenNguyenLieu(){
        return tennguyenlieu;
    }
    public String getSoLuong(){
        return soluong;
    }
}
