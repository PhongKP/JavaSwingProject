package Modal;

public class DTONguyenLieu {
    private String manguyenlieu ;
    private String tennguyenlieu ;
    private String mota ;
    private String soluong;
    public DTONguyenLieu(String manguyenlieu,String tennguyenlieu,String mota,String soluong){
         this.manguyenlieu = manguyenlieu;
         this.tennguyenlieu = tennguyenlieu;
         this.mota = mota ;
         this.soluong = soluong;
    }    
    public String getMaNguyenLieu(){return manguyenlieu;}
    public String getTenNguyenLieu(){return tennguyenlieu;}
    public String getMota(){return mota;}
    public String getSoLuong(){return soluong;}
}
