package BUS;

import Modal.DTONguyenLieu;

public class BUSNguyenLieu {
    private static BUSNguyenLieu instance ;
    public static BUSNguyenLieu getInstance(){
        if(instance == null){
          instance = new BUSNguyenLieu();
        }    
        return instance;
    }
    public java.util.List<DTONguyenLieu> getAll(){
        return DAO.DAONguyenLieu.getInstance().getAll();
    }
    public boolean isExists(String manguyenlieu){
        return DAO.DAONguyenLieu.getInstance().isExists(manguyenlieu);
    }
    public DTONguyenLieu getNguyenLieuByID(String manguyenlieu){
        return DAO.DAONguyenLieu.getInstance().getNguyenLieuByID(manguyenlieu);
    }
    public boolean delete(String manguyenlieu){
        return DAO.DAONguyenLieu.getInstance().delete(manguyenlieu);
    }
    public boolean edit(DTONguyenLieu dto){
        return DAO.DAONguyenLieu.getInstance().edit(dto);
    }
    public boolean add(DTONguyenLieu dto){
        return DAO.DAONguyenLieu.getInstance().add(dto);
    }
}
