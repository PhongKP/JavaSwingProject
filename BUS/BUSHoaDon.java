package BUS;

import java.sql.Timestamp;
import java.util.List;

import DAO.DAOHoaDon;
import Modal.DTOCTHD;
import Modal.DTOHoaDon;

public class BUSHoaDon {
	private static BUSHoaDon instance;

	public static BUSHoaDon getInstance() {
		if (instance == null) {
			instance = new BUSHoaDon();
		}
		return instance;
	}

	public List<DTOHoaDon> getAllHoaDonWaitConfirm() {
		return DAOHoaDon.getInstance().getAllHoaDonWaitConfirm();
	}

	public List<DTOHoaDon> getAllHoaDonByDateAndState(String date1, String date2, String state) {
		return DAOHoaDon.getInstance().getAllHoaDonByDateAndState(date1, date2, state);
	}

	public List<DTOCTHD> getCTHDByID(String id) {
		return DAOHoaDon.getInstance().getCTHDByID(id);
	}

	public DTOHoaDon getHoaDonByID(String id) {
		return DAOHoaDon.getInstance().getHoaDonByID(id);
	}

	public boolean confirmHoaDon(String id) {
		return DAOHoaDon.getInstance().confirmHoaDon(id);
	}

	public boolean recoverHoaDon(String id, String lydo) {
		return DAOHoaDon.getInstance().recoverHoaDon(id, lydo);
	}

	public String getTongTien(String trangthai, String mahd) {
		return DAOHoaDon.getInstance().getTongTienHD(trangthai, mahd);
	}

	public boolean isStateHoanThanh(String id) {
		return DAOHoaDon.getInstance().isStateHoanThanh(id);
	}

	public String getIDHDHT(Timestamp startTime, Timestamp endTime) {
		return DAOHoaDon.getInstance().getIDHDPresent(startTime, endTime);
	}

//    
	public boolean Cancel(String mahd) {
		return DAOHoaDon.getInstance().Cancel(mahd);
	}

	public boolean changeToWaitConfirm(String mahd) {
		return DAOHoaDon.getInstance().changeToWaitConfirm(mahd);
	}

	public boolean addHoaDon(DTOHoaDon dto) {
		return DAOHoaDon.getInstance().addHoaDon(dto);
	}

	public List<DTOHoaDon> getAllHoaDonWaitOrder() {
		return DAOHoaDon.getInstance().getAllHoaDonWaitOrder();
	}

	public String getNewMaHD() {
		return DAOHoaDon.getInstance().getNewMaHD();
	}

	public boolean addCTHD(DTOCTHD dto) {
		return DAOHoaDon.getInstance().addCTHD(dto);
	}

	public boolean updateHoaDon(DTOHoaDon dto) {
		return DAOHoaDon.getInstance().updateHoaDon(dto);
	}

	public boolean deleteCTHD(String mahd, String masp) {
		return DAOHoaDon.getInstance().deleteCTHD(mahd, masp);
	}

	public boolean updateTongTienHoaDon(String maHD, String tongGiaTien) {
		return DAOHoaDon.getInstance().updateTongTienHoaDon(maHD, tongGiaTien);
	}

	public double getAllThanhTien(String maHD) {
		return DAOHoaDon.getInstance().getAllThanhTien(maHD);
	}

	public boolean updateIDCustomer(String makh, String mahd) {
		return DAOHoaDon.getInstance().updateIDCustomer(makh, mahd);
	}

	public List<String> getAllIDHoaDon() {
		return DAOHoaDon.getInstance().getAllIDHoaDon();
	}

	public String getIDCustomerBySDT(String sdt) {
		return DAOHoaDon.getInstance().getIDCustomerBySDT(sdt);
	}

	public boolean updateTax(String tienthue, String mahd) {
		return DAOHoaDon.getInstance().updateTax(tienthue, mahd);
	}

	public boolean updateCustomerPay(String tienKhachDua, String mahd) {
		return DAOHoaDon.getInstance().updateCustomerPay(tienKhachDua, mahd);
	}

	public boolean updatePayTime(String payTime, String mahd) {
		return DAOHoaDon.getInstance().updatePayTime(payTime, mahd);
	}

	public String getQuantity(String mahd, String masp) {
		return DAOHoaDon.getInstance().getQuantity(mahd, masp);
	}

	public boolean updatePayType(String payType, String mahd) {
		return DAOHoaDon.getInstance().updatePayType(payType, mahd);
	}

	public boolean updateCTHD(String mahd, String masp, String soluong) {
		return DAOHoaDon.getInstance().updateCTHD(mahd, masp, soluong);
	}

	public boolean updateTongGia(String mahd, int tonggia) {
		return DAOHoaDon.getInstance().updateTongGia(mahd, tonggia);
	}

	public boolean updateThanhTienCTHD(int thanhtien, String mahd, String masp) {
		return DAOHoaDon.getInstance().updateCTHDThanhTien(thanhtien, mahd, masp);
	}

	public boolean delAllCTHD(String mahd) {
		return DAOHoaDon.getInstance().delAllCTHD(mahd);
	}
	
	public boolean updateGhiChu (String ghichu, String mahd)
	{
		return DAOHoaDon.getInstance().updateGhiChu(ghichu, mahd);
	}
	
}
