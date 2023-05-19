package DAO;

import java.util.ArrayList;

import ConnectionDB.connectionDB;

public class DAONhaCC {
    private static DAONhaCC instance ;
    public static DAONhaCC getInstance(){
        if(instance==null){
            instance = new DAONhaCC();
        }
        return instance;
    }
    public java.util.List<Modal.DTONhaCC>getAllNhaCC(){
       java.util.List<Modal.DTONhaCC> list = new ArrayList<Modal.DTONhaCC>();
       connectionDB connect = new connectionDB();
       String query = "Select * from nhacc";
       java.sql.ResultSet rs = connect.sqlQuery(query, null);
       try{
           while(rs.next()){
               list.add(new Modal.DTONhaCC(rs.getString("manhacc"),rs.getString("tennhacc"),rs.getString("diachi"),rs.getString("sdt"),rs.getString("email")));
           }
       }catch(Exception ex){
           System.out.println(ex);
       }
       connect.closeConnect();
       return list ;
    }
    public boolean checkIfExistsPhone(String manhacc,String phone){
        String query = "Select * from nhacc where manhacc <> ? and sdt = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = false;
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{manhacc,phone});
        
        try{
             if (rs.next()){
                 issuccess = true;
             }     
             
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return issuccess;
        
    }
    public boolean edit(Modal.DTONhaCC dto){
        String query = "update nhacc set tennhacc = ? ,diachi = ?,sdt =? ,email = ? where manhacc = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{dto.getTenNhaCC(),dto.getDiaChi(),dto.getSDT(),dto.getEmail(),dto.getMaNhaCC()});
        connect.closeConnect();
        return issuccess;
    }
    public boolean delete(String manhacc){
        String query = "Delete from nhacc where manhacc = ?";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query,new Object[]{manhacc});
        connect.closeConnect();
        return issuccess;
    }
    public boolean isMaNhaCCExists(String manhacc){
        String query = "Select * from nhacc where manhacc = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs =  connect.sqlQuery(query,new Object[]{manhacc});
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
    public boolean isExistsPhone(String phone){
        String query = "Select * from nhacc where sdt = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query,new Object[]{phone});
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
    public java.util.List<Modal.DTONhaCC> getNhaCCByIDOrName(String like){
        java.util.List<Modal.DTONhaCC> list = new ArrayList<>();
        String query = "Select * from nhacc where manhacc like ? or tennhacc like ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query, new Object[]{"%"+like+"%","%"+like+"%"});
        try{
            while(rs.next()){
                list.add(new Modal.DTONhaCC(rs.getString("manhacc"),rs.getString("tennhacc"),rs.getString("diachi"),rs.getString("sdt"),rs.getString("email")));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return list;
    }
    public boolean add(Modal.DTONhaCC dto){
        String query = "insert into nhacc(manhacc,tennhacc,diachi,sdt,email) values(createNhaCCID() ,? ,? ,?,?)";
        connectionDB connect = new connectionDB();
        boolean issuccess = connect.sqlUpdate(query, new Object[]{dto.getTenNhaCC(),dto.getDiaChi(),dto.getSDT(),dto.getEmail()});
        connect.closeConnect();
        return issuccess;
    }
    public Modal.DTONhaCC getNhaCCByID(String manhacc){
        Modal.DTONhaCC dto=null ;
        String query = "Select * from nhacc where manhacc = ?";
        connectionDB connect = new connectionDB();
        java.sql.ResultSet rs = connect.sqlQuery(query,new Object[]{manhacc});
        try{
            if(rs.next()){
                dto = new Modal.DTONhaCC(rs.getString("manhacc"),rs.getString("tennhacc"),rs.getString("diachi"),rs.getString("sdt"),rs.getString("email"));
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        connect.closeConnect();
        return dto;
                
    }
}
