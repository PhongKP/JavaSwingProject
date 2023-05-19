package BUS;

import java.util.List;

import Modal.DTOTKSP;
import Modal.giaoCaModal;

public class BUSThongKe {
    private static BUSThongKe instance;

    public static BUSThongKe getInstance() {
        if (instance == null) {
            instance = new BUSThongKe();
        }
        return instance;
    }

    public List<String> getAllTypeProduct() {
        return DAO.DAOThongKe.getInstance().getAllTypeProduct();
    }

    public List<DTOTKSP> getTKSP(String date1, String date2, String theloai, String trangthai) {
        return DAO.DAOThongKe.getInstance().getTKSP(date1, date2, theloai, trangthai);
    }

    public List<String> getTKSPToday() {
        return DAO.DAOThongKe.getInstance().getTKSPToday();
    }

    public List<String> getTKSPSevenDay() {
        return DAO.DAOThongKe.getInstance().getTKSPSevenDay();
    }

    public List<String> getTKSPMonth() {
        return DAO.DAOThongKe.getInstance().getTKSPMonth();
    }

    public List<String> getTKSPOption(String date1, String date2) {
        return DAO.DAOThongKe.getInstance().getTKSPOption(date1, date2);
    }

    public List<giaoCaModal> getCaLamViecByDate(String date1, String date2) {
        return DAO.DAOThongKe.getInstance().getCaLamViecByDate(date1, date2);
    }
}
