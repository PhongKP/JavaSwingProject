package DAO;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ConnectionDB.connectionDB;
import Modal.DTOTKSP;
import Modal.giaoCaModal;

public class DAOThongKe {
    private static DAOThongKe instance;

    public static DAOThongKe getInstance() {
        if (instance == null) {
            instance = new DAOThongKe();
        }
        return instance;
    }

    public java.util.List<String> getAllTypeProduct() {
        java.util.List<String> list = new java.util.ArrayList<>();
        String query = "Select tenloai from theloai";
        connectionDB connectDB = new connectionDB();
        java.sql.ResultSet rs = connectDB.sqlQuery(query, null);
        try {
            while (rs.next()) {
                list.add(rs.getString("tenloai"));
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public java.util.List<DTOTKSP> getTKSP(String date1, String date2, String theloai, String trangthai) {
        java.util.List<DTOTKSP> list = new java.util.ArrayList<>();
        String query = "Select sanpham.masp,sanpham.tensp,sum(cthd.soluong) as soluong,sanpham.trangthai from cthd ,sanpham ,hoadon,theloai where theloai.maloai = sanpham.maloai and cthd.masp = sanpham.masp and cthd.mahd =hoadon.mahd and hoadon.trangthai='Hoàn thành' and hoadon.thoigiantao between ? and ? ";

        String query1 = "group by sanpham.masp,sanpham.tensp,sanpham.trangthai";
        String query2 = "";
        String query3 = "";
        if (!theloai.equals("Tất cả")) {
            query2 = "and theloai.tenloai ='" + theloai + "' ";
        }
        if (!trangthai.equals("Tất cả")) {
            query3 = "and sanpham.trangthai ='" + trangthai + "' ";
        }
        connectionDB connectDB = new connectionDB();
        java.sql.ResultSet rs = connectDB.sqlQuery(query + query2 + query3 + query1, new Object[]{date1, date2});
        try {
            while (rs.next()) {
                list.add(new DTOTKSP(rs.getString("masp"), rs.getString("tensp"), rs.getString("soluong"), rs.getString("trangthai")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public java.util.List<String> getTKSPToday() {
        java.util.List<String> list = new java.util.ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd 00:00:00");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        String query = "Select count(mahd) as hoadondaban,ifnull(sum(tonggia),0) as tonggia  from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Hoàn thành'";
        String query1 = "Select count(mahd) as hoadonhuy from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Huỷ'";
        connectionDB connectDB = new connectionDB();
        java.sql.ResultSet rs = connectDB.sqlQuery(query, new Object[]{dtf.format(now), dtf.format(tomorrow)});
        java.sql.ResultSet rs1 = connectDB.sqlQuery(query1, new Object[]{dtf.format(now), dtf.format(tomorrow)});
        try {
            if (rs.next()) {
                list.add(rs.getString("tonggia"));
                list.add(rs.getString("hoadondaban"));
            }
            if (rs1.next()) {
                list.add(rs1.getString("hoadonhuy"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public List<String> getTKSPSevenDay() {
        List<String> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd 00:00:00");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastweek = now.minusWeeks(1);
        String query = "Select count(mahd) as hoadondaban,ifnull(sum(tonggia),0) as tonggia  from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Hoàn thành'";
        String query1 = "Select count(mahd) as hoadonhuy from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Huỷ'";
        connectionDB connectDB = new connectionDB();
        ResultSet rs = connectDB.sqlQuery(query, new Object[]{dtf.format(lastweek), dtf.format(now)});
        ResultSet rs1 = connectDB.sqlQuery(query1, new Object[]{dtf.format(lastweek), dtf.format(now)});
        try {
            if (rs.next()) {
                list.add(rs.getString("tonggia"));
                list.add(rs.getString("hoadondaban"));
            }
            if (rs1.next()) {
                list.add(rs1.getString("hoadonhuy"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public List<String> getTKSPMonth() {
        List<String> list = new ArrayList<>();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd 00:00:00");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastmonth = now.minusMonths(1);
        String query = "Select count(mahd) as hoadondaban,ifnull(sum(tonggia),0) as tonggia  from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Hoàn thành'";
        String query1 = "Select count(mahd) as hoadonhuy from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Huỷ'";
        connectionDB connectDB = new connectionDB();
        ResultSet rs = connectDB.sqlQuery(query, new Object[]{dtf.format(lastmonth), dtf.format(now)});
        ResultSet rs1 = connectDB.sqlQuery(query1, new Object[]{dtf.format(lastmonth), dtf.format(now)});
        try {
            if (rs.next()) {
                list.add(rs.getString("tonggia"));
                list.add(rs.getString("hoadondaban"));
            }
            if (rs1.next()) {
                list.add(rs1.getString("hoadonhuy"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public List<String> getTKSPOption(String date1, String date2) {
        List<String> list = new ArrayList<>();
        String query = "Select count(mahd) as hoadondaban,ifnull(sum(tonggia),0) as tonggia  from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Hoàn thành'";
        String query1 = "Select count(mahd) as hoadonhuy from hoadon where thoigiantao between ? and ? and hoadon.trangthai = 'Huỷ'";
        connectionDB connectDB = new connectionDB();
        ResultSet rs = connectDB.sqlQuery(query, new Object[]{date1, date2});
        ResultSet rs1 = connectDB.sqlQuery(query1, new Object[]{date1, date2});
        try {
            if (rs.next()) {
                list.add(rs.getString("tonggia"));
                list.add(rs.getString("hoadondaban"));
            }
            if (rs1.next()) {
                list.add(rs1.getString("hoadonhuy"));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;
    }

    public List<giaoCaModal> getCaLamViecByDate(String date1, String date2) {
        List<giaoCaModal> list = new ArrayList<>();
        String query = "Select * from calamviec where ngay between ? and ?";

        connectionDB connectDB = new connectionDB();
        ResultSet rs = connectDB.sqlQuery(query, new Object[]{date1, date2});
        try {
            while (rs.next()) {
                 list.add(new giaoCaModal(rs.getString("maca"), rs.getString("manv"), rs.getString("giobd"), rs.getString("giokt"), rs.getString("tienbandau"), rs.getString("tiendoanhthu"), rs.getString("tongtien"),rs.getString("ngay")));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        connectDB.closeConnect();
        return list;

    }
}
