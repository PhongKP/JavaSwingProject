package DAO;

import java.util.ArrayList;

import ConnectionDB.connectionDB;
import Modal.DTOCongThuc;
import Modal.ProductModal;
//import Modal.ProductModal;

public class DAOCongThuc {
    private static DAOCongThuc instance;

    public static DAOCongThuc getInstance() {
        if (instance == null) {
            instance = new DAOCongThuc();
        }
        return instance;

    }

    public java.util.List<ProductModal> getAllSanPham() {
        java.util.List<ProductModal> list = new ArrayList<ProductModal>();
        String query = "select masp,tensp,trangthai,ghichu from sanpham";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, null);
        try {
            while (rs.next()) {
                list.add(new ProductModal(rs.getString("masp"), rs.getString("tensp"), null, Float.valueOf(0), rs.getString("trangthai"),rs.getString("ghichu"),null,null));

            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        connect.closeConnect();
        return list;
    }

    public boolean isExistsSanPham(String masp) {
        String query = "Select * from sanpham where masp = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{masp});
        boolean issuccess = false;
        try {
            if (rs.next()) {
                issuccess = true;
            }
        } catch (Exception ex) {
            System.out.println(ex);

        }
        connect.closeConnect();
        return issuccess;
    }
    public java.util.List<DTOCongThuc> getAllNguyenLieuByMaSP(String masp){
         java.util.List<DTOCongThuc> list = new ArrayList<DTOCongThuc>();
         String query = "Select nguyenlieu.manguyenlieu as manguyenlieu,nguyenlieu.tennguyenlieu as tennguyenlieu,ctphache.donvi as donvi from ctphache,nguyenlieu where ctphache.masp = ? and ctphache.manguyenlieu = nguyenlieu.manguyenlieu ";
         connectionDB connect = new connectionDB();
         java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{masp});
         try{
             while(rs.next()){
                 list.add(new DTOCongThuc(rs.getString("manguyenlieu"),rs.getString("tennguyenlieu"),rs.getString("donvi")));
             }    
         }catch(Exception ex){
             System.out.println(ex);
         }
         connect.closeConnect();
         return list;
    }
    public boolean isExistsNguyenLieuInSP(String masp,String manguyenlieu){
        String query = "Select * from ctphache where masp = ? and manguyenlieu = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = false;
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{masp,manguyenlieu});
        try{
            if(rs.next()){
                issuccess = true;
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return issuccess;
    }
    public boolean delete(String masp,String manguyenlieu){
        String query = "delete from ctphache where masp = ? and manguyenlieu = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{masp,manguyenlieu});
        connect.closeConnect();
        return issuccess;
        
    }
    public boolean add(String masp,String manguyenlieu,String soluong){
        connectionDB connect = new connectionDB();
         boolean issuccess;
        if(isExistsNguyenLieuInSP(masp,manguyenlieu)){
            String query = "update ctphache set donvi = donvi + ? where masp = ? and manguyenlieu =?";
            issuccess = connect.sqlUpdate(query, new Object[]{soluong,masp,manguyenlieu});
            
        }else{
            String query = "insert into ctphache(masp,manguyenlieu,donvi) values(?,?,?)";
            issuccess = connect.sqlUpdate(query, new Object[]{masp,manguyenlieu,soluong});
        }
        connect.closeConnect();
        return issuccess;
    }
}
