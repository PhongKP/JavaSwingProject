package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import ConnectionDB.connectionDB;
import Modal.DTOCTHD;
import Modal.DTOHoaDon;

public class DAOHoaDon {
	private static DAOHoaDon instance;

	public static DAOHoaDon getInstance() {
		if (instance == null) {
			instance = new DAOHoaDon();
		}
		return instance;
	}

	public List<DTOHoaDon> getAllHoaDonWaitConfirm() {
		List<DTOHoaDon> list = new ArrayList<>();
		String query = "SELECT mahd,thoigiantao,thoigianthanhtoan,tonggia,hoadon.manv,hoadon.makh,hoadon.trangthai,(SELECT tennv FROM nhanvien WHERE nhanvien.manv = hoadon.manv) AS tennv,(SELECT tenkh FROM khachhang WHERE khachhang.makh = hoadon.makh) AS tenkh,(SELECT sdt FROM khachhang WHERE khachhang.makh = hoadon.makh) AS sdt,loaithanhtoan,hoadon.ghichu,tienkhachdua,tienthue FROM hoadon WHERE hoadon.trangthai='Chờ xác nhận'";
		connectionDB connectDB = new connectionDB();
		ResultSet rs = connectDB.sqlQuery(query, null);
		try {
			if (rs != null) {
				while (rs.next()) {
					list.add(new DTOHoaDon(rs.getString("mahd"), rs.getString("thoigiantao"),
							rs.getString("thoigianthanhtoan"), rs.getString("tonggia"), rs.getString("manv"),
							rs.getString("makh"), rs.getString("trangthai"), rs.getString("tennv"),
							rs.getString("tenkh"), rs.getString("sdt"), rs.getString("loaithanhtoan"),
							rs.getString("ghichu"), rs.getString("tienkhachdua"), rs.getString("tienthue")));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connectDB.closeConnect();
		return list;

	}

	public List<DTOHoaDon> getAllHoaDonByDateAndState(String date1, String date2, String state) {
		List<DTOHoaDon> list = new ArrayList<>();

		String query = "SELECT hoadon.mahd, hoadon.thoigiantao, hoadon.thoigianthanhtoan, hoadon.tonggia, hoadon.manv, hoadon.makh, hoadon.trangthai, (SELECT tennv FROM nhanvien WHERE nhanvien.manv = hoadon.manv) AS tennv, (SELECT tenkh FROM khachhang WHERE khachhang.makh = hoadon.makh) AS tenkh, (SELECT sdt FROM khachhang WHERE khachhang.makh = hoadon.makh) AS sdt, hoadon.loaithanhtoan, hoadon.ghichu, hoadon.tienkhachdua,hoadon.tienthue FROM hoadon WHERE hoadon.thoigiantao BETWEEN ? AND ? ";

		connectionDB connectDB = new connectionDB();
		ResultSet rs;
		if (!state.equals("Tất cả")) {
			String query1 = "and hoadon.trangthai = ? ";
			rs = connectDB.sqlQuery(query + query1, new Object[] { date1, date2, state });
		} else {
			rs = connectDB.sqlQuery(query, new Object[] { date1, date2 });
		}

		try {
			if (rs != null) {
				while (rs.next()) {
					list.add(new DTOHoaDon(rs.getString("mahd"), rs.getString("thoigiantao"),
							rs.getString("thoigianthanhtoan"), rs.getString("tonggia"), rs.getString("manv"),
							rs.getString("makh"), rs.getString("trangthai"), rs.getString("tennv"),
							rs.getString("tenkh"), rs.getString("sdt"), rs.getString("loaithanhtoan"),
							rs.getString("ghichu"), rs.getString("tienkhachdua"), rs.getString("tienthue")));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connectDB.closeConnect();
		return list;
	}

	public DTOHoaDon getHoaDonByID(String id) {
		DTOHoaDon hoadon = null;
		String query = "Select hoadon.mahd,hoadon.thoigiantao,hoadon.thoigianthanhtoan,hoadon.tonggia,hoadon.manv,hoadon.makh,hoadon.trangthai,(SELECT tennv FROM nhanvien WHERE nhanvien.manv = hoadon.manv) AS tennv,(Select tenkh from khachhang where khachhang.makh = hoadon.makh) as tenkh,(Select sdt from khachhang where khachhang.makh = hoadon.makh) as sdt,hoadon.loaithanhtoan,hoadon.ghichu,hoadon.tienkhachdua,hoadon.tienthue from hoadon where mahd = ? ";
		connectionDB connectDB = new connectionDB();
		ResultSet rs = connectDB.sqlQuery(query, new Object[] { id });
		try {
			if (rs != null) {
				if (rs.next()) {
					hoadon = new DTOHoaDon(rs.getString("mahd"), rs.getString("thoigiantao"),
							rs.getString("thoigianthanhtoan"), rs.getString("tonggia"), rs.getString("manv"),
							rs.getString("makh"), rs.getString("trangthai"), rs.getString("tennv"),
							rs.getString("tenkh"), rs.getString("sdt"), rs.getString("loaithanhtoan"),
							rs.getString("ghichu"), rs.getString("tienkhachdua"), rs.getString("tienthue"));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connectDB.closeConnect();
		return hoadon;

	}

	public List<DTOCTHD> getCTHDByID(String id) {
		List<DTOCTHD> list = new ArrayList<>();
		String query = "Select mahd,cthd.masp,sanpham.tensp,sanpham.gia,cthd.soluong,cthd.thanhtien,cthd.ghichu from cthd,sanpham where mahd = ? and cthd.masp = sanpham.masp ";
		connectionDB connectDB = new connectionDB();
		ResultSet rs = connectDB.sqlQuery(query, new Object[] { id });
		try {
			if (rs != null) {
				while (rs.next()) {
					list.add(new DTOCTHD(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString("ghichu")));
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connectDB.closeConnect();
		return list;

	}

	public boolean confirmHoaDon(String id) {
		String query = "update hoadon set trangthai='Hoàn thành' where mahd= ? ";
		connectionDB connectDB = new connectionDB();
		boolean issuccess = connectDB.sqlUpdate(query, new Object[] { id });
		connectDB.closeConnect();
		return issuccess;
	}

	public boolean recoverHoaDon(String id, String lydo) {
		String query = "Update hoadon set trangthai = 'Chờ order' , ghichu = ? where mahd = ? ";
		connectionDB connectDB = new connectionDB();
		ResultSet rs = connectDB.sqlQuery("Select ghichu from hoadon where mahd = ?", new Object[] { id });
		String ghichu = "";
		try {
			if (rs.next()) {
				ghichu = rs.getString("ghichu");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		if (ghichu.trim().equals("")) {
			ghichu = lydo;
		} else {
			ghichu += "\n" + lydo;
		}
		boolean issuccess = connectDB.sqlUpdate(query, new Object[] { ghichu, id });
		connectDB.closeConnect();
		return issuccess;

	}

	public boolean isStateHoanThanh(String id) {
		String query = "Select * from hoadon where mahd= ? and trangthai = 'Hoàn thành'";
		connectionDB connectDB = new connectionDB();
		ResultSet rs = connectDB.sqlQuery(query, new Object[] { id });
		boolean issuccess = false;
		try {
			if (rs.next()) {
				issuccess = true;
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connectDB.closeConnect();
		return issuccess;
	}

	public String getTongTienHD(String trangthai, String mahd) {
		String result = "";
		String sql = "SELECT tonggia FROM hoadon WHERE trangthai = ? AND mahd = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = { trangthai, mahd };
		ResultSet res = conn.sqlQuery(sql, objs);
		try {
			if (res != null) {
				while (res.next()) {
					result += res.getString(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getIDHDPresent(Timestamp startTime, Timestamp endTime) {
		StringBuilder maHD = new StringBuilder();
		String sql = "SELECT mahd FROM hoadon WHERE thoigianthanhtoan >= ? AND thoigianthanhtoan <= ?";
		connectionDB conn = new connectionDB();
		Object[] objs = { startTime, endTime };
		ResultSet res = conn.sqlQuery(sql, objs);
		try {
			if (res != null) {
				while (res.next()) {
					maHD.append(res.getString("mahd"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maHD.toString();
	}

	public boolean Cancel(String mahd) {
		String query = "update hoadon set trangthai = 'Huỷ' where mahd = ?";
		connectionDB connect = new connectionDB();
		boolean issuccess = connect.sqlUpdate(query, new Object[] { mahd });
		connect.closeConnect();
		return issuccess;
	}

	public boolean changeToWaitConfirm(String mahd) {
		String query = "update hoadon set trangthai = 'Chờ xác nhận' where mahd = ?";
		connectionDB connect = new connectionDB();
		boolean issuccess = connect.sqlUpdate(query, new Object[] { mahd });
		connect.closeConnect();
		return issuccess;
	}

	public boolean addHoaDon(DTOHoaDon dto) {
		String query = "Insert into hoadon(mahd,thoigiantao,thoigianthanhtoan,manv,makh,trangthai,loaithanhtoan,ghichu,tienthue,tienkhachdua,tonggia) values(?,?,?,?,?,?,?,?,?,?,?)";
		connectionDB connect = new connectionDB();
		boolean issuccess = connect.sqlUpdate(query,
				new Object[] { dto.getMaHD(), dto.getThoiGianTao(), dto.getThoiGianThanhToan(), dto.getMaNV(),
						dto.getMaKH(), dto.getTrangThai(), dto.getLoaiThanhToan(), dto.getGhiChu(), dto.getTienThue(),
						dto.getTienKhachDua(), dto.getTongGia() });
		connect.closeConnect();
		return issuccess;
	}

	public List<DTOHoaDon> getAllHoaDonWaitOrder() {
		List<DTOHoaDon> list = new ArrayList<>();
		String query = "SELECT mahd,thoigiantao,thoigianthanhtoan,tonggia,hoadon.manv,hoadon.makh,hoadon.trangthai,(SELECT tennv FROM nhanvien WHERE nhanvien.manv = hoadon.manv) AS tennv,(SELECT tenkh FROM khachhang WHERE khachhang.makh = hoadon.makh) AS tenkh,(SELECT sdt FROM khachhang WHERE khachhang.makh = hoadon.makh) AS sdt,loaithanhtoan,hoadon.ghichu,tienkhachdua,tienthue FROM hoadon WHERE hoadon.trangthai='Chờ order'";
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, null);
		try {
			while (rs.next()) {
				list.add(new DTOHoaDon(rs.getString("mahd"), rs.getString("thoigiantao"),
						rs.getString("thoigianthanhtoan"), rs.getString("tonggia"), rs.getString("manv"),
						rs.getString("makh"), rs.getString("trangthai"), rs.getString("tennv"), rs.getString("tenkh"),
						rs.getString("sdt"), rs.getString("loaithanhtoan"), rs.getString("ghichu"),
						rs.getString("tienkhachdua"), rs.getString("tienthue")));
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connect.closeConnect();
		return list;
	}

	public String getNewMaHD() {
		String query = "select createHoaDonID() as mahd";
		String result = "";
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, null);
		try {
			if (rs.next()) {
				result = rs.getString("mahd");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connect.closeConnect();
		return result;
	}

	public boolean isExists(DTOCTHD dto) {
		boolean issuccess = false;
		String query = "select * from cthd where mahd = ? and masp = ?";
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, new Object[] { dto.getMaHD(), dto.getMaSP() });
		try {
			if (rs.next())
				issuccess = true;
		} catch (Exception ex) {
			System.out.println(ex);
		}
		connect.closeConnect();
		return issuccess;

	}

	public boolean addCTHD(DTOCTHD dto) {
		String query = "";
		boolean issuccess;
		connectionDB connect = new connectionDB();
		if (isExists(dto)) {
			query = "update cthd as c set soluong = c.soluong + ?  ,thanhtien = (select gia from sanpham where masp = c.masp ) * c.soluong where mahd = ? and masp = ? ";
			issuccess = connect.sqlUpdate(query, new Object[] { dto.getSoLuong(), dto.getMaHD(), dto.getMaSP() });
		} else {
			query = "insert into cthd (mahd,masp,soluong,thanhtien,ghichu) values(?,?,?,?,?)";
			issuccess = connect.sqlUpdate(query, new Object[] { dto.getMaHD(), dto.getMaSP(), dto.getSoLuong(),
					dto.getThanhTien(), dto.getGhiChu() });
		}

		connect.closeConnect();
		return issuccess;
	}

	public boolean updateHoaDon(DTOHoaDon dto) {
		String query = "update hoadon set thoigiantao = ?, thoigianthanhtoan = ? ,manv = ?,makh = ?,trangthai = ?,loaithanhtoan = ?,ghichu = ?,tienthue = ? ,tienkhachdua = ? ,tonggia = ? where mahd = ?";
		connectionDB connect = new connectionDB();
		boolean issuccess = connect.sqlUpdate(query,
				new Object[] { dto.getThoiGianTao(), dto.getThoiGianThanhToan(), dto.getMaNV(), dto.getMaKH(),
						dto.getTrangThai(), dto.getLoaiThanhToan(), dto.getGhiChu(), dto.getTienThue(),
						dto.getTienKhachDua(), dto.getTongGia(), dto.getMaHD() });
		connect.closeConnect();
		return issuccess;
	}

	public boolean deleteCTHD(String mahd, String masp) {
		String query = "delete from cthd where mahd = ? and masp = ?";
		connectionDB connect = new connectionDB();
		boolean issuccess = connect.sqlUpdate(query, new Object[] { mahd, masp });
		connect.closeConnect();
		return issuccess;

	}

	public boolean updateTongTienHoaDon(String maHD, String tongTienSP) {
		String query = "update hoadon set tonggia = ? where mahd = ? ";
		connectionDB connect = new connectionDB();
		boolean isscuess = connect.sqlUpdate(query, new Object[] { tongTienSP, maHD });
		connect.closeConnect();
		return isscuess;
	}

	public double getAllThanhTien(String maHD) {
		String query = "select coalesce(sum(thanhtien),0) from cthd where mahd = ?";
		connectionDB connect = new connectionDB();
		ResultSet rs = connect.sqlQuery(query, new Object[] { maHD });
		double sum = 0;
		try {
			if (rs.next()) {
				sum += Double.parseDouble(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		connect.closeConnect();
		return sum;
	}

	public boolean updateIDCustomer (String makh, String mahd)
	{
		String sql = "UPDATE hoadon SET makh = ? WHERE mahd =?";
		connectionDB conn = new connectionDB();
		Object[] objs = {makh,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public List<String> getAllIDHoaDon ()
	{
		String sql = "SELECT mahd FROM hoadon";
		connectionDB conn = new connectionDB();
		List<String> listIDHoaDon = new ArrayList<>();
		ResultSet re = conn.sqlQuery(sql, null);
		try {
			while (re.next())
			{
				listIDHoaDon.add(re.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return listIDHoaDon;
	}	
	
	public String getIDCustomerBySDT (String sdt)
	{
		String sql = "SELECT makh FROM khachhang WHERE sdt = ?";
		String res = "";
		connectionDB conn = new connectionDB();
		ResultSet re = conn.sqlQuery(sql, new Object[] {sdt});
		try {
			if (re.next())
			{
				res += re.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return res;
	}
	
	public boolean updateTax (String tienthue, String mahd)
	{
		String sql = "UPDATE hoadon SET tienthue = ? WHERE mahd =?";
		connectionDB conn = new connectionDB();
		Object[] objs = {tienthue,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updateCustomerPay (String customerPay, String mahd)
	{
		String sql = "UPDATE hoadon SET tienkhachdua = ? WHERE mahd =?";
		connectionDB conn = new connectionDB();
		Object[] objs = {customerPay,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updatePayTime (String payTime, String mahd)
	{
		String sql = "UPDATE hoadon SET thoigianthanhtoan = ? WHERE mahd =?";
		connectionDB conn = new connectionDB();
		Object[] objs = {payTime,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public String getQuantity (String mahd, String masp)
	{
		String sql = "SELECT soluong FROM cthd WHERE mahd = ? and masp = ?";
		String res = "";
		connectionDB conn = new connectionDB();
		ResultSet re = conn.sqlQuery(sql, new Object[] {mahd,masp});
		try {
			if (re.next())
			{
				res += re.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeConnect();
		return res;
	}
	
	public boolean updatePayType (String payType, String mahd)
	{
		String sql = "UPDATE hoadon SET loaithanhtoan = ? WHERE mahd =?";
		connectionDB conn = new connectionDB();
		Object[] objs = {payType,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updateCTHD (String mahd,String masp, String soluong)
	{
		String sql = "UPDATE cthd SET soluong = ? WHERE mahd = ? and masp = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = {soluong,mahd,masp};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updateTongGia (String mahd, int tonggia)
	{
		String sql = "UPDATE hoadon SET tonggia = ? WHERE mahd = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = {tonggia,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updateCTHDThanhTien (int thanhTien,String mahd, String masp)
	{
		String sql = "UPDATE cthd SET thanhtien = ? WHERE mahd = ? and masp = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = {thanhTien,mahd,masp};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean delAllCTHD (String mahd)
	{
		String sql = "DELETE FROM cthd WHERE mahd = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = {mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	public boolean updateGhiChu (String ghiChu,String mahd)
	{
		String sql = "UPDATE hoadon SET ghichu = ? WHERE mahd = ?";
		connectionDB conn = new connectionDB();
		Object[] objs = {ghiChu,mahd};
		boolean ok = conn.sqlUpdate(sql, objs);
		conn.closeConnect();
		return ok;
	}
	
	
}
