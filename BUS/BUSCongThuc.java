package BUS;

import Modal.DTOCongThuc;
import Modal.ProductModal;
//import Modal.ProductModal;

public class BUSCongThuc {
    private static BUSCongThuc instance ;
    public static BUSCongThuc getInstance(){
        if(instance==null){
            instance = new BUSCongThuc();
        }
        return instance;
    }
    public java.util.List<ProductModal>  getAllSanPham(){
        return DAO.DAOCongThuc.getInstance().getAllSanPham();
    }
    public boolean isExistsSanPham(String masp){
        return DAO.DAOCongThuc.getInstance().isExistsSanPham(masp);
    }
    public java.util.List<DTOCongThuc> getAllNguyenLieuByMaSP(String masp){
        return DAO.DAOCongThuc.getInstance().getAllNguyenLieuByMaSP(masp);
    }
    public boolean isExistsNguyenLieuInSP(String masp,String manguyenlieu){
        
        return DAO.DAOCongThuc.getInstance().isExistsNguyenLieuInSP(masp, manguyenlieu);
    }
    public boolean delete(String masp ,String manguyenlieu){
        return DAO.DAOCongThuc.getInstance().delete(masp, manguyenlieu);
             
    }
    public boolean add(String masp,String manguyenlieu,String soluong){
        return DAO.DAOCongThuc.getInstance().add(masp, manguyenlieu, soluong);
    }
}
