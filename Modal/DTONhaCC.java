package Modal;

public class DTONhaCC {
    private String manhacc ;
    private String tennhacc;
    private String diachi;
    private String sdt;
    private String email;
    public DTONhaCC(String manhacc,String tennhacc,String diachi,String sdt,String email){
        this.manhacc = manhacc;
        this.tennhacc = tennhacc;
        this.diachi = diachi;
        this.sdt = sdt ;
        this.email = email;
    }
    public String getMaNhaCC(){
        return manhacc;
    }
    public String getTenNhaCC(){
        return tennhacc;
    }
    public String getDiaChi(){
        return diachi;
    }
    public String getSDT(){
        return sdt;
    }
    public String getEmail(){
        return email;
    }
}
