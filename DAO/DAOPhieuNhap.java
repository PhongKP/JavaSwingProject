package DAO;

import java.util.ArrayList;

import ConnectionDB.connectionDB;
import Modal.DTOCTPN;
import Modal.DTOPhieuNhap;

public class DAOPhieuNhap {
    private static DAOPhieuNhap instance;
    public static DAOPhieuNhap getInstance(){
        if(instance == null){
            instance = new DAOPhieuNhap();
        }
        return instance;
    }
    public DTOPhieuNhap getPhieuNhapByID(String maphieunhap){
        DTOPhieuNhap dto = null;
        String query = "select * from phieunhap where maphieunhap = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{maphieunhap});
        try{
            if(rs.next()){
                dto = new DTOPhieuNhap(rs.getString("maphieunhap"),rs.getString("ngaynhap"),rs.getString("tonggia"),rs.getString("manhacc"),rs.getString("manv"));
                
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return dto;
    }
    public java.util.List<DTOPhieuNhap> getAll(){
        java.util.List<DTOPhieuNhap> list = new ArrayList<>();
        String query = "Select * from phieunhap";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, null);
        try{
            while(rs.next()){
                list.add(new DTOPhieuNhap(rs.getString("maphieunhap"),rs.getString("ngaynhap"),rs.getString("tonggia"),rs.getString("manhacc"),rs.getString("manv")));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return list;
    }
    public java.util.List<DTOCTPN> getAllCTPNByMaPN(String maphieunhap){
        java.util.List<DTOCTPN> list = new ArrayList<>();
        String query = "Select * from ctphieunhap where maphieunhap = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{maphieunhap});
        try{
            while(rs.next()){
                list.add(new DTOCTPN(rs.getString("manguyenlieu"),rs.getString("soluong"),rs.getString("dongia")));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return list;
    }
    public boolean delete(String maphieunhap){
        String query = "delete from phieunhap where maphieunhap = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{maphieunhap});
        connect.closeConnect();
        return issuccess;
    }
    public boolean add(DTOCTPN[] listitem,DTOPhieuNhap phieunhap){
        String query = "insert into phieunhap(maphieunhap,ngaynhap,tonggia,manhacc,manv) values(createMaPNID(),?,?,?,?)";
        connectionDB connect = new connectionDB();
        if(!connect.sqlUpdate(query, new Object[]{phieunhap.getNgayNhap(),phieunhap.getTongGia(),phieunhap.getMaNhaCC(),phieunhap.getMaNV()})){
            return false;          
        }
        query = "insert into ctphieunhap(maphieunhap,manguyenlieu,soluong,dongia) values(getMaxPNID(),?,?,?)";
        for(DTOCTPN item:listitem){
            if(!connect.sqlUpdate(query, new Object[]{item.getMaNguyenLieu(),item.getSoLuong(),item.getDonGia()}))
                return false;
        }
        return true;
    }
}
