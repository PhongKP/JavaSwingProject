package BUS;

import Modal.DTOCTPN;
import Modal.DTOPhieuNhap;

public class BUSPhieuNhap {
    private static BUSPhieuNhap instance ;
    public static BUSPhieuNhap getInstance(){
        if(instance == null){
            instance = new BUSPhieuNhap();
        }
        return instance;
    }
    public java.util.List<DTOPhieuNhap> getAll(){
        return DAO.DAOPhieuNhap.getInstance().getAll();
    }
    public DTOPhieuNhap getPhieuNhapByID(String maphieunhap){
        return DAO.DAOPhieuNhap.getInstance().getPhieuNhapByID(maphieunhap);
    }
    public java.util.List<DTOCTPN> getAllCTPNByMaPN(String maphieunhap){
        return DAO.DAOPhieuNhap.getInstance().getAllCTPNByMaPN(maphieunhap);
    }
    public boolean delete(String maphieunhap){
        return DAO.DAOPhieuNhap.getInstance().delete(maphieunhap);
    }
    public boolean add(DTOCTPN[] listitem ,DTOPhieuNhap phieunhap){
        return DAO.DAOPhieuNhap.getInstance().add(listitem, phieunhap);
    }
}
