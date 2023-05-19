package DAO;

import java.util.ArrayList;

import ConnectionDB.connectionDB;
import Modal.DTONguyenLieu;

public class DAONguyenLieu {
    private static DAONguyenLieu instance;

    public static DAONguyenLieu getInstance() {
        if(instance==null){
            instance = new DAONguyenLieu();
            
        }
        return instance;
    }
    public java.util.List<DTONguyenLieu> getAll(){
        java.util.List<DTONguyenLieu> list = new ArrayList<>();
        String query = "Select * from nguyenlieu";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, null);
        try{
            while(rs.next()){
                list.add(new DTONguyenLieu(rs.getString("manguyenlieu"),rs.getString("tennguyenlieu"),rs.getString("mota"),rs.getString("soluong")));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return list ;
    }
    public boolean isExists(String manguyenlieu){
        String query = "Select * from nguyenlieu where manguyenlieu = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{manguyenlieu});
        boolean issuccess = false;
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
    public boolean delete(String manguyenlieu){
        String query = "delete from nguyenlieu where manguyenlieu =?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{manguyenlieu});
        connect.closeConnect();
        return issuccess;
    }
    public DTONguyenLieu getNguyenLieuByID(String manguyenlieu){
        DTONguyenLieu dto = null;
        String query = "Select * from nguyenlieu where manguyenlieu = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{manguyenlieu});
        try{
            if(rs.next()){
                dto = new DTONguyenLieu(rs.getString("manguyenlieu"),rs.getString("tennguyenlieu"),rs.getString("mota"),rs.getString("soluong"));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return dto;
    }
    public boolean edit(DTONguyenLieu dto){
        String query = "update nguyenlieu set tennguyenlieu = ? ,mota = ?,soluong = ? where manguyenlieu = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{dto.getTenNguyenLieu(),dto.getMota(),dto.getSoLuong(),dto.getMaNguyenLieu()});
        connect.closeConnect();
        return issuccess;
    }
    public boolean add(DTONguyenLieu dto){
        String query = "insert into nguyenlieu values(createNguyenLieuID(),?,?,?)";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{dto.getTenNguyenLieu(),dto.getMota(),dto.getSoLuong()});
       
        connect.closeConnect();
        return issuccess;
    }
}
