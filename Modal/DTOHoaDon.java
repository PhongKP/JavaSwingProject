package Modal;

public class DTOHoaDon {
    private String mahd ;
    private String thoigiantao;
    private String thoigianthanhtoan;
    private String tonggia;
    private String manv;
    private String makh;
    private String trangthai;
    private String tennhanvien;
    private String tenkhachhang;
    private String sdtkhachhang;
    private String loaithanhtoan;
    private String ghichu ;
    private String tienkhachdua;
    private String tienthue;
    public DTOHoaDon(String mahd,String thoigiantao,String thoigianthanhtoan,String tonggia,String manv,String makh,String trangthai,String tennhanvien,String tenkhachhang,String sdtkhachhang,String loaithanhtoan,String ghichu,String tienkhachdua,String tienthue){
        this.mahd = mahd ;
        this.thoigiantao = thoigiantao;
        this.thoigianthanhtoan = thoigianthanhtoan;
        this.tonggia = tonggia;
        this.manv = manv ;
        this.makh = makh ;
        this.trangthai = trangthai;
        this.tennhanvien = tennhanvien;
        this.tenkhachhang = tenkhachhang;
        this.sdtkhachhang = sdtkhachhang;
        this.loaithanhtoan = loaithanhtoan;
        this.ghichu = ghichu;
        this.tienkhachdua = tienkhachdua;
        this.tienthue = tienthue;
        
    }
    public String getMaHD(){
        return mahd;
    }
    public String getThoiGianTao(){
        return thoigiantao;
    }
    public String getTongGia(){
        return tonggia;
    }
    public String getMaNV(){
        return manv;
    }
    public String getMaKH(){
        return makh;
    }
    public String getTrangThai(){
        return trangthai;
    }
    public String getTenNV(){
        return tennhanvien;
    }
    public String getTenKH(){
        return tenkhachhang;
    }
    public String getSDT(){
        return sdtkhachhang;
    }
    public String getThoiGianThanhToan(){
        return thoigianthanhtoan;
    }
    public String getLoaiThanhToan(){
        return loaithanhtoan;
    }
    public String getGhiChu(){
        return ghichu;
    }
    public String getTienKhachDua(){
        return tienkhachdua;
    }
    public String getTienThue(){
        return tienthue;
    }

}
