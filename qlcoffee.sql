-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 18, 2023 at 03:56 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `qlcoffee`
--

DELIMITER $$
--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `calc_total` (`tienbandau` INT, `tiendoanhthu` INT) RETURNS INT(11)  BEGIN
   DECLARE tongtien int;
   SET tongtien = tienbandau + tiendoanhthu;
   RETURN tongtien;
END$$

CREATE DEFINER=`root`@`localhost` FUNCTION `createHoaDonID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin 
  declare maxid int ;
  set maxid = (Select max(cast(substring(mahd,3) as unsigned)) from hoadon);
  if maxid is null then 
      set maxid = 1 ;
   else
    set maxid  = maxid +1 ;
   end if ;
   return concat('HD',lpad(maxid,2,'0'));
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `createKhachHangID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin 
    declare maxid int;
    set maxid = (Select max(cast(substring(makh,3) as unsigned)) from khachhang);
    if maxid is null then 
       set maxid = 1 ;
    else 
       set maxid = maxid +1;
    end if; 
    return concat('KH',lpad(maxid,2,'0'));
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `createMaPNID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin
     declare maxid int;
     set maxid = (Select max(cast(substring(maphieunhap,3) as unsigned)) from phieunhap);
     if maxid is null then 
        set maxid =1 ;
     else 
        set maxid = maxid +1 ;
     end if;
     return concat('PN',lpad(maxid,2,'0'));
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `createNguyenlieuID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin 
    declare maxid int;
    set maxid = (Select max(cast(substring(manguyenlieu,3) as unsigned)) from nguyenlieu);
    if maxid is null then 
       set maxid = 1 ;
    else 
       set maxid = maxid +1;
    end if; 
    return concat('NL',lpad(maxid,2,'0'));
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `createNhaCCID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin 
    declare maxid int;
    set maxid = (Select max(cast(substring(manhacc,2) as unsigned)) from nhacc);
    if maxid is null then 
       set maxid = 1 ;
    else 
       set maxid = maxid +1;
    end if; 
    return concat('N',lpad(maxid,3,'0'));
end$$

CREATE DEFINER=`root`@`localhost` FUNCTION `getMaxPNID` () RETURNS CHAR(4) CHARSET utf8mb4 COLLATE utf8mb4_general_ci  begin
     declare maxid int;
     set maxid = (Select max(cast(substring(maphieunhap,3) as unsigned)) from phieunhap);
     return concat('PN',lpad(maxid,2,'0'));
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `calamviec`
--

CREATE TABLE `calamviec` (
  `maca` char(4) NOT NULL,
  `manv` char(4) DEFAULT NULL,
  `giobd` time DEFAULT NULL,
  `giokt` time DEFAULT NULL,
  `tienbandau` int(11) DEFAULT NULL,
  `tiendoanhthu` int(11) DEFAULT NULL,
  `tongtien` int(11) DEFAULT NULL,
  `ngay` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `calamviec`
--

INSERT INTO `calamviec` (`maca`, `manv`, `giobd`, `giokt`, `tienbandau`, `tiendoanhthu`, `tongtien`, `ngay`) VALUES
('CA01', 'NV02', '06:00:00', '08:00:00', 1000000, 0, 1000000, NULL),
('CA02', 'NV03', '08:00:00', '10:00:00', 1000000, 0, 1000000, NULL),
('CA03', 'NV02', '10:00:00', '12:00:00', 1000000, 0, 1000000, NULL),
('CA04', 'NV04', '12:00:00', '14:00:00', 1000000, 0, 1000000, NULL),
('CA05', 'NV03', '14:00:00', '16:00:00', 1000000, 0, 1000000, NULL),
('CA06', 'NV04', '16:00:00', '18:00:00', 1000000, 0, 1000000, NULL),
('CA07', 'NV09', '18:00:00', '20:00:00', 1000000, 0, 1000000, NULL),
('CA08', 'NV07', '20:00:00', '22:00:00', 1000000, 0, 1000000, NULL),
('CA09', 'NV07', '22:00:00', '24:00:00', 1000000, 0, 1000000, NULL),
('CA10', 'NV09', '00:00:00', '06:00:00', 1000000, 0, 1000000, NULL);

--
-- Triggers `calamviec`
--
DELIMITER $$
CREATE TRIGGER `check_calamviec_giobd_smaller_giokt_before_insert` BEFORE INSERT ON `calamviec` FOR EACH ROW BEGIN
    IF NEW.giobd > NEW.giokt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Giờ bắt đầu phải bé hơn giờ kết thúc';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_calamviec_giobd_smaller_giokt_before_update` BEFORE UPDATE ON `calamviec` FOR EACH ROW BEGIN
    IF NEW.giobd > NEW.giokt THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Giờ bắt đầu phải bé hơn giờ kết thúc';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chucnang`
--

CREATE TABLE `chucnang` (
  `machucnang` char(4) NOT NULL,
  `tenchucnang` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `chucnang`
--

INSERT INTO `chucnang` (`machucnang`, `tenchucnang`) VALUES
('CN01', 'Bán hàng'),
('CN02', 'Hóa đơn'),
('CN03', 'Sản phẩm'),
('CN04', 'Thống kê'),
('CN05', 'Nhân viên'),
('CN06', 'Phân quyền'),
('CN07', 'Quản lý quyền'),
('CN08', 'Nhà cung cấp'),
('CN09', 'Nguyên liệu');

-- --------------------------------------------------------

--
-- Table structure for table `ctchucnang`
--

CREATE TABLE `ctchucnang` (
  `machucnang` char(4) NOT NULL,
  `maquyen` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctchucnang`
--

INSERT INTO `ctchucnang` (`machucnang`, `maquyen`) VALUES
('CN01', 'Q001'),
('CN01', 'Q002'),
('CN02', 'Q001'),
('CN02', 'Q002'),
('CN03', 'Q001'),
('CN03', 'Q002'),
('CN04', 'Q001'),
('CN05', 'Q001'),
('CN06', 'Q001'),
('CN07', 'Q001'),
('CN08', 'Q001'),
('CN09', 'Q001');

-- --------------------------------------------------------

--
-- Table structure for table `cthd`
--

CREATE TABLE `cthd` (
  `soluong` int(11) DEFAULT NULL,
  `thanhtien` int(11) DEFAULT NULL,
  `mahd` char(4) NOT NULL,
  `masp` char(4) NOT NULL,
  `ghichu` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `cthd`
--

INSERT INTO `cthd` (`soluong`, `thanhtien`, `mahd`, `masp`, `ghichu`) VALUES
(2, 50000, 'HD01', 'SP02', ''),
(1, 30000, 'HD01', 'SP03', NULL),
(3, 90000, 'HD02', 'SP03', ''),
(5, 175000, 'HD02', 'SP04', ''),
(5, 150000, 'HD03', 'SP05', ''),
(2, 60000, 'HD04', 'SP03', NULL),
(1, 25000, 'HD05', 'SP02', NULL),
(1, 30000, 'HD05', 'SP03', NULL),
(2, 70000, 'HD05', 'SP06', NULL),
(1, 30000, 'HD06', 'SP03', NULL),
(1, 35000, 'HD06', 'SP06', NULL),
(1, 40000, 'HD06', 'SP07', NULL),
(1, 30000, 'HD07', 'SP03', NULL),
(1, 40000, 'HD07', 'SP07', NULL),
(1, 45000, 'HD07', 'SP08', NULL),
(1, 60000, 'HD08', 'SP03', NULL),
(1, 35000, 'HD08', 'SP06', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `ctphache`
--

CREATE TABLE `ctphache` (
  `donvi` int(11) DEFAULT NULL,
  `manguyenlieu` char(4) NOT NULL,
  `masp` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphache`
--

INSERT INTO `ctphache` (`donvi`, `manguyenlieu`, `masp`) VALUES
(30, 'NL01', 'SP09'),
(30, 'NL01', 'SP10'),
(30, 'NL01', 'SP11'),
(30, 'NL01', 'SP12'),
(30, 'NL01', 'SP13'),
(30, 'NL01', 'SP14'),
(100, 'NL02', 'SP01'),
(100, 'NL02', 'SP02'),
(100, 'NL02', 'SP03'),
(100, 'NL02', 'SP04'),
(100, 'NL02', 'SP05'),
(100, 'NL02', 'SP06'),
(100, 'NL02', 'SP07'),
(100, 'NL02', 'SP08'),
(70, 'NL02', 'SP11'),
(70, 'NL02', 'SP12'),
(50, 'NL03', 'SP03'),
(50, 'NL03', 'SP04'),
(100, 'NL03', 'SP05'),
(100, 'NL03', 'SP06'),
(100, 'NL04', 'SP07'),
(100, 'NL04', 'SP08'),
(30, 'NL04', 'SP11'),
(30, 'NL04', 'SP12'),
(30, 'NL04', 'SP13'),
(30, 'NL04', 'SP14'),
(100, 'NL05', 'SP09'),
(100, 'NL05', 'SP10'),
(70, 'NL05', 'SP13'),
(70, 'NL05', 'SP14'),
(100, 'NL05', 'SP15'),
(100, 'NL05', 'SP16'),
(30, 'NL06', 'SP09'),
(30, 'NL06', 'SP10'),
(30, 'NL07', 'SP09'),
(30, 'NL07', 'SP10'),
(30, 'NL08', 'SP13'),
(30, 'NL08', 'SP14');

--
-- Triggers `ctphache`
--
DELIMITER $$
CREATE TRIGGER `update_soluong_sanpham_after_insert_ctphache` AFTER INSERT ON `ctphache` FOR EACH ROW begin
   update sanpham set soluong = (select coalesce(floor(min(nguyenlieu.soluong/ctphache.donvi)),0) from ctphache,nguyenlieu where masp = new.masp and nguyenlieu.manguyenlieu = ctphache.manguyenlieu) where masp = new.masp;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_soluong_sanpham_after_update_ctphache` AFTER UPDATE ON `ctphache` FOR EACH ROW begin 
    update sanpham set soluong = (select coalesce(floor(min(nguyenlieu.soluong/ctphache.donvi)),0) from ctphache,nguyenlieu where masp = old.masp and nguyenlieu.manguyenlieu = ctphache.manguyenlieu) where masp = old.masp;
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_soluong_sanpham_before_delete_ctphache` BEFORE DELETE ON `ctphache` FOR EACH ROW begin 
   update sanpham set soluong = (select coalesce(floor(min(nguyenlieu.soluong/ctphache.donvi)),0) from ctphache,nguyenlieu where masp = old.masp and nguyenlieu.manguyenlieu = ctphache.manguyenlieu and nguyenlieu.manguyenlieu <> old.manguyenlieu ) where masp = old.masp ;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ctphieunhap`
--

CREATE TABLE `ctphieunhap` (
  `soluong` int(11) DEFAULT NULL,
  `dongia` int(11) DEFAULT NULL,
  `maphieunhap` char(4) NOT NULL,
  `manguyenlieu` char(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ctphieunhap`
--

INSERT INTO `ctphieunhap` (`soluong`, `dongia`, `maphieunhap`, `manguyenlieu`) VALUES
(1000, 150000, 'PN01', 'NL02'),
(1000, 200000, 'PN01', 'NL05'),
(1000, 100000, 'PN02', 'NL01'),
(1000, 80000, 'PN03', 'NL03'),
(1000, 60000, 'PN03', 'NL04'),
(1000, 60000, 'PN05', 'NL08'),
(1000, 60000, 'PN06', 'NL06'),
(1000, 60000, 'PN06', 'NL07');

--
-- Triggers `ctphieunhap`
--
DELIMITER $$
CREATE TRIGGER `update_soluong_nguyenlieu_after_insert_ctphieunhap` AFTER INSERT ON `ctphieunhap` FOR EACH ROW begin 
   update nguyenlieu set soluong = soluong + new.soluong where manguyenlieu = new.manguyenlieu ;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `mahd` char(4) NOT NULL,
  `thoigiantao` datetime DEFAULT NULL,
  `thoigianthanhtoan` datetime DEFAULT NULL,
  `manv` char(4) DEFAULT NULL,
  `makh` char(4) DEFAULT NULL,
  `trangthai` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `loaithanhtoan` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghichu` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `tienthue` int(11) DEFAULT NULL,
  `tienkhachdua` int(11) DEFAULT NULL,
  `tonggia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`mahd`, `thoigiantao`, `thoigianthanhtoan`, `manv`, `makh`, `trangthai`, `loaithanhtoan`, `ghichu`, `tienthue`, `tienkhachdua`, `tonggia`) VALUES
('HD01', '2022-01-01 08:00:00', '2022-01-01 10:00:00', 'NV01', 'KH01', 'Chờ order', 'Tiền mặt', 'Không bỏ đá', 0, 100000, 80000),
('HD02', '2022-01-01 10:00:00', '2022-01-01 12:00:00', 'NV02', 'KH02', 'Chờ order', 'Chuyển khoản', '', 0, 250000, 265000),
('HD03', '2022-01-01 12:00:00', '2022-01-01 14:00:00', 'NV03', 'KH03', 'Chờ xác nhận', 'Tiền mặt', 'Không bỏ cacao', 0, 150000, 150000),
('HD04', '2023-05-16 15:47:27', NULL, 'NV01', 'KH12', 'Chờ xác nhận', 'Tiền mặt', '', 6000, 70000, 66000),
('HD05', '2023-05-16 22:51:29', NULL, 'NV01', 'KH12', 'Hoàn thành', 'Tiền mặt', '', 12500, 200000, 137500),
('HD06', '2023-05-17 09:50:01', NULL, 'NV01', 'KH13', 'Chờ xác nhận', 'Tiền mặt', '', 10500, 115500, 115500),
('HD07', '2023-05-17 09:51:59', '2023-05-17 09:52:45', 'NV01', 'KH12', 'Chờ xác nhận', 'Tiền mặt', '', 11500, 126500, 126500),
('HD08', '2023-05-18 20:52:03', NULL, 'NV01', NULL, 'Chờ order', 'Tiền mặt', '', 9500, 0, 104500);

--
-- Triggers `hoadon`
--
DELIMITER $$
CREATE TRIGGER `update_soluong_nguyenlieu_after_update_Istate_hoan_thanhI_hoadon` AFTER UPDATE ON `hoadon` FOR EACH ROW begin 
   if new.trangthai = 'Hoàn thành' then
      update nguyenlieu as nl set soluong = soluong - (select coalesce(sum(ctphache.donvi*cthd.soluong),0) from cthd,ctphache where mahd = old.mahd and ctphache.masp = cthd.masp and ctphache.manguyenlieu = nl.manguyenlieu) where manguyenlieu in (select distinct manguyenlieu from cthd,ctphache where mahd = old.mahd and ctphache.masp = cthd.masp);
   end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `makh` char(4) NOT NULL,
  `tenkh` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sdt` char(10) DEFAULT NULL,
  `diachi` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`makh`, `tenkh`, `sdt`, `diachi`) VALUES
('KH01', 'Nguyễn Văn A', '0163456789', '123 Nguyễn Trãi'),
('KH02', 'Trần Thị B', '0927654321', '456 Lê Lợi'),
('KH03', 'Lê Thị C', '0123456789', '789 Trần Hưng Đạo'),
('KH04', 'Phạm Văn D', '0967654321', '321 Nguyễn Huệ'),
('KH05', 'Hoàng Thị E', '0123456779', '654 Lê Duẩn'),
('KH06', 'Nguyễn Văn F', '0957654321', '987 Trần Phú'),
('KH07', 'Trần Thị G', '0123458789', '246 Phạm Ngũ Lão'),
('KH08', 'Lê Văn H', '0947654311', '369 Lý Tự Trọng'),
('KH09', 'Phạm Thị I', '0123459789', '159 Nguyễn Du'),
('KH10', 'Hoàng Văn J', '0387654321', '753 Lê Thánh Tôn'),
('KH11', 'cu', '0948800938', ''),
('KH12', 'Phong', '0858212963', ''),
('KH13', 'Quan', '0778715603', '');

--
-- Triggers `khachhang`
--
DELIMITER $$
CREATE TRIGGER `check_khachhang_sdt_before_insert` BEFORE INSERT ON `khachhang` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_khachhang_sdt_before_update` BEFORE UPDATE ON `khachhang` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nguyenlieu`
--

CREATE TABLE `nguyenlieu` (
  `manguyenlieu` char(4) NOT NULL,
  `tennguyenlieu` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `mota` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nguyenlieu`
--

INSERT INTO `nguyenlieu` (`manguyenlieu`, `tennguyenlieu`, `mota`, `soluong`) VALUES
('NL01', 'Đường', 'đường trắng', 2000),
('NL02', 'Bột cà phê', 'Bột cà phê nguyên chất', 1600),
('NL03', 'Sữa đặc', 'Sữa đặc', 1750),
('NL04', 'Sữa tươi', 'Sữa tươi', 2000),
('NL05', 'Bột cà phê espresso', 'Bột cà phê espresso', 2000),
('NL06', 'Bột cacao', 'Bột cacao', 2000),
('NL07', 'Bột quế', 'Bột quế', 2000),
('NL08', 'Sốt chocolate', 'Sốt chocolate', 2000);

--
-- Triggers `nguyenlieu`
--
DELIMITER $$
CREATE TRIGGER `update_soluong_sanpham_after_update_nguyenlieu` AFTER UPDATE ON `nguyenlieu` FOR EACH ROW begin 
   update sanpham as sp set soluong =(select coalesce(floor(min(nguyenlieu.soluong/ctphache.donvi)),0) from ctphache,nguyenlieu where masp = sp.masp and nguyenlieu.manguyenlieu = ctphache.manguyenlieu) where masp in (select masp from ctphache where manguyenlieu = old.manguyenlieu);
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_soluong_sanpham_before_delete_nguyenlieu` BEFORE DELETE ON `nguyenlieu` FOR EACH ROW begin 
   update sanpham as sp set soluong =(select coalesce(floor(min(nguyenlieu.soluong/ctphache.donvi)),0) from ctphache,nguyenlieu where masp = sp.masp and nguyenlieu.manguyenlieu = ctphache.manguyenlieu and nguyenlieu.manguyenlieu <> old.manguyenlieu) where masp in (select masp from ctphache where manguyenlieu = old.manguyenlieu);
end
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `update_tongtien_phieunhap_before_delete_nguyenlieu` BEFORE DELETE ON `nguyenlieu` FOR EACH ROW begin 
   update phieunhap as p set tonggia =(select coalesce(sum(dongia),0) from ctphieunhap where maphieunhap = p.maphieunhap and manguyenlieu <> OLD.manguyenlieu ) where maphieunhap in (select maphieunhap from ctphieunhap where manguyenlieu = OLD.manguyenlieu);
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nhacc`
--

CREATE TABLE `nhacc` (
  `manhacc` char(4) NOT NULL,
  `tennhacc` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `diachi` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `sdt` char(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhacc`
--

INSERT INTO `nhacc` (`manhacc`, `tennhacc`, `diachi`, `sdt`, `email`) VALUES
('N001', 'Công ty TNHH Cà Phê Nguyên Chất', '123 Đường Trần Hưng Đạo, Quận 1, TP.HCM', '0123456789', 'nguyenchat@coffee.com'),
('N002', 'Công ty TNHH Đường Biên Hòa', '456 Đường Lê Duẩn, Quận 3, TP.HCM', '0987654321', 'duongbienhoa@sugar.com'),
('N003', 'Công ty TNHH Sữa Việt Nam', '789 Đường Nguyễn Trãi, Quận 5, TP.HCM', '0123987654', 'suavietnam@milk.com'),
('N004', 'Công ty TNHH Trà Xanh Việt Nam', '147 Đường Phạm Ngũ Lão, Quận 1, TP.HCM', '0987123456', 'traxanhvietnam@tea.com'),
('N005', 'Công ty TNHH Chocolate Việt Nam', '258 Đường Lý Tự Trọng, Quận 1, TP.HCM', '0123459876', 'chocolatevietnam@syrup.com'),
('N006', 'Công ty TNHH Bột Cacao Việt Nam', '369 Đường Nguyễn Thị Minh Khai, Quận 3, TP.HCM', '0987651234', 'botcacaovietnam@cocoa.com'),
('N007', 'Công ty TNHH Nước Ép Việt Nam', '741 Đường Nguyễn Văn Cừ, Quận 5, TP.HCM', '0123765498', 'nuocepvietnam@juice.com'),
('N010', 'Công ty TNHH Gia Vị Việt Nam', '147 Đường Nguyễn Thị Minh Khai, Quận 3, TP.HCM', '0987214365', 'giavivietnam@spice.com');

--
-- Triggers `nhacc`
--
DELIMITER $$
CREATE TRIGGER `check_nhacc_email_before_insert` BEFORE INSERT ON `nhacc` FOR EACH ROW BEGIN
    IF NEW.email NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Email không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhacc_email_before_update` BEFORE UPDATE ON `nhacc` FOR EACH ROW BEGIN
    IF NEW.email NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Email không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhacc_sdt_before_update` BEFORE UPDATE ON `nhacc` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhancc_sdt_before_insert` BEFORE INSERT ON `nhacc` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `manv` char(4) NOT NULL,
  `tenNV` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `gioitinh` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaysinh` datetime DEFAULT NULL,
  `quequan` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `CMND` char(12) DEFAULT NULL,
  `sdt` char(10) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `vaitro` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangthai` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghichu` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `hinhanh` mediumtext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`manv`, `tenNV`, `gioitinh`, `ngaysinh`, `quequan`, `CMND`, `sdt`, `email`, `vaitro`, `trangthai`, `ghichu`, `hinhanh`) VALUES
('NV01', 'Nguyễn Văn A', 'nam', '1990-01-01 00:00:00', 'Hà nội', '001122334455', '0123456789', 'nva@example.com', 'Quản lý', 'Đang làm', '', 'nguyenvana.png'),
('NV02', 'Trần Thị B', 'nữ', '1991-02-02 00:00:00', 'Hải Phòng', '112233445566', '0234567890', 'ttb@example.com', 'Nhân viên', 'Đang làm', '', 'tranthib.png'),
('NV03', 'Lê Văn C', 'nam', '1992-03-03 00:00:00', 'Đà Nẵng', '223344556677', '0345678901', 'lvc@example.com', 'Nhân viên', 'Đang làm', '', 'levanc.png'),
('NV04', 'Phạm Thị D', 'nữ', '1993-04-04 00:00:00', 'Huế', '334455667788', '0456789012', 'ptd@example.com', 'Nhân viên', 'Đang làm', '', 'phamthid.png'),
('NV05', 'Hoàng Văn E', 'nam', '1994-05-05 00:00:00', 'Nha Trang', '445566778899', '0567890123', 'hve@example.com', 'Nhân viên', 'Đang làm', '', 'hoangvane.png'),
('NV06', 'Do Thị F', 'nữ', '1995-06-06 00:00:00', 'Đà Lạt', '556677889900', '0678901234', 'dtf@example.com', 'Nhân viên', 'Đang làm', '', 'dothif.png'),
('NV07', 'Trường Văn G', 'nam', '1996-07-07 00:00:00', 'Cần Thơ', '667788990011', '0789012345', 'tvg@example.com', 'Nhân viên', 'Đang làm', '', 'truongvang.png'),
('NV08', 'Nguyễn Thị H', 'nữ', '1997-08-08 00:00:00', 'Vũng Tàu', '778899001122', '0890123456', 'nth@example.com', 'Nhân viên', 'Đang làm', '', 'nguyenthih.png'),
('NV09', 'Lê Thị I', 'nữ', '1998-09-09 00:00:00', 'Phan Thiết', '889900112233', '0901234567', 'lti@example.com', 'Nhân viên', 'Đang làm', '', 'lethii.png'),
('NV10', 'Phạm Văn J', 'nam', '1999-10-10 00:00:00', 'Quy Nhơn', '990011223344', '0975810314', 'pvj@example.com', 'Nhân viên', 'Đang làm', '', 'phamvanj.png');

--
-- Triggers `nhanvien`
--
DELIMITER $$
CREATE TRIGGER `check_gender_before_insert` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.gioitinh NOT IN ('nam', 'nữ') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Giới tính phải là nam hoặc nữ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_gender_before_update` BEFORE UPDATE ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.gioitinh NOT IN ('nam', 'nữ') THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Giới tính phải là nam hoặc nữ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhanvien_email_before_insert` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.email NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Email không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhanvien_email_before_update` BEFORE UPDATE ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.email NOT LIKE '%@%.%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Email không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhanvien_sdt_before_insert` BEFORE INSERT ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `check_nhanvien_sdt_before_update` BEFORE UPDATE ON `nhanvien` FOR EACH ROW BEGIN
    IF NEW.sdt NOT LIKE '0%' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'SDT không hợp lệ';
    END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `maphieunhap` char(4) NOT NULL,
  `ngaynhap` datetime DEFAULT NULL,
  `tonggia` int(11) DEFAULT NULL,
  `manhacc` char(4) DEFAULT NULL,
  `manv` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`maphieunhap`, `ngaynhap`, `tonggia`, `manhacc`, `manv`) VALUES
('PN01', '2021-01-01 00:00:00', 350000, 'N001', 'NV01'),
('PN02', '2021-01-01 00:00:00', 100000, 'N002', 'NV02'),
('PN03', '2021-01-01 00:00:00', 140000, 'N003', 'NV03'),
('PN05', '2021-01-01 00:00:00', 60000, 'N005', 'NV05'),
('PN06', '2021-01-01 00:00:00', 120000, 'N006', 'NV06');

-- --------------------------------------------------------

--
-- Table structure for table `quyen`
--

CREATE TABLE `quyen` (
  `maquyen` char(4) NOT NULL,
  `tenquyen` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `quyen`
--

INSERT INTO `quyen` (`maquyen`, `tenquyen`) VALUES
('Q001', 'admin'),
('Q002', 'Pha chế'),
('Q003', 'Thu ngân');

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `masp` char(4) NOT NULL,
  `tensp` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gia` int(11) DEFAULT NULL,
  `soluong` int(11) DEFAULT NULL,
  `maloai` char(4) DEFAULT NULL,
  `hinhanh` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `trangthai` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ghichu` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`masp`, `tensp`, `gia`, `soluong`, `maloai`, `hinhanh`, `trangthai`, `ghichu`) VALUES
('SP01', 'Cà phê đen nóng', 20000, 16, 'TL01', 'cafedennong.jpg', 'Đang bán', ''),
('SP02', 'Cà phê đen đá', 25000, 16, 'TL01', 'cafedenda.jpg', 'Đang bán', ''),
('SP03', 'Cà phê đen sữa nóng', 30000, 16, 'TL01', 'cafedensuanong.jpg', 'Đang bán', ''),
('SP04', 'Cà phê đen sữa đá', 35000, 16, 'TL01', 'cafedensuada.jpg', 'Đang bán', ''),
('SP05', 'Cà phê sữa nóng', 30000, 16, 'TL02', 'cafesuanong.jpg', 'Đang bán', ''),
('SP06', 'Cà phê sữa đá', 35000, 16, 'TL02', 'cafesuada.jpg', 'Đang bán', ''),
('SP07', 'Cà phê sữa tươi nóng', 40000, 16, 'TL02', 'cafesuatuoingong.jpg', 'Đang bán', ''),
('SP08', 'Cà phê sữa tươi đá', 45000, 16, 'TL02', 'cafesuatuoinda.jpg', 'Đang bán', ''),
('SP09', 'Cappuccino nóng', 40000, 20, 'TL03', 'cappuccinonong.jpg', 'Đang bán', ''),
('SP10', 'Cappuccino đá xay', 45000, 20, 'TL03', 'cappuccinodaxay.jpg', 'Đang bán', ''),
('SP11', 'Espresso nóng', 30000, 22, 'TL04', 'espressonong.jpg', 'Đang bán', ''),
('SP12', 'Espresso đá xay', 35000, 22, 'TL04', 'espressodaxay.jpg', 'Đang bán', ''),
('SP13', 'Mocha nóng', 40000, 28, 'TL06', 'mochanong.jpg', 'Đang bán', ''),
('SP14', 'Mocha đá xay', 45000, 28, 'TL06', 'mochadaxay.jpg', 'Đang bán', ''),
('SP15', 'Americano nóng', 30000, 20, 'TL07', 'americanonong.jpg', 'Đang bán', '');
INSERT INTO `sanpham` (`masp`, `tensp`, `gia`, `soluong`, `maloai`, `hinhanh`, `trangthai`, `ghichu`) VALUES
('SP16', 'Americano đá', 35000, 20, 'TL07', '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', 'Đang bán', '');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `password` varchar(1000) NOT NULL,
  `manv` char(4) NOT NULL,
  `maquyen` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`password`, `manv`, `maquyen`) VALUES
('admin', 'NV01', 'Q001'),
('123', 'NV02', 'Q002'),
('123', 'NV03', 'Q002'),
('123', 'NV04', 'Q003'),
('123', 'NV05', 'Q002'),
('123', 'NV06', 'Q002'),
('123', 'NV07', 'Q002'),
('123', 'NV08', 'Q002'),
('123', 'NV09', 'Q002'),
('123', 'NV10', 'Q002');

-- --------------------------------------------------------

--
-- Table structure for table `theloai`
--

CREATE TABLE `theloai` (
  `maloai` char(4) NOT NULL,
  `tenloai` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `theloai`
--

INSERT INTO `theloai` (`maloai`, `tenloai`) VALUES
('TL01', 'Cà phê đen'),
('TL02', 'Cà phê sữa'),
('TL03', 'Cappuccino'),
('TL04', 'Espresso'),
('TL06', 'Mocha'),
('TL07', 'Americano');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `calamviec`
--
ALTER TABLE `calamviec`
  ADD PRIMARY KEY (`maca`),
  ADD KEY `FK_calamviec_manv` (`manv`);

--
-- Indexes for table `chucnang`
--
ALTER TABLE `chucnang`
  ADD PRIMARY KEY (`machucnang`);

--
-- Indexes for table `ctchucnang`
--
ALTER TABLE `ctchucnang`
  ADD PRIMARY KEY (`machucnang`,`maquyen`),
  ADD KEY `FK_ctchucnang_maquyen` (`maquyen`);

--
-- Indexes for table `cthd`
--
ALTER TABLE `cthd`
  ADD PRIMARY KEY (`mahd`,`masp`),
  ADD KEY `FK_cthd_masp` (`masp`);

--
-- Indexes for table `ctphache`
--
ALTER TABLE `ctphache`
  ADD PRIMARY KEY (`manguyenlieu`,`masp`),
  ADD KEY `FK_ctphache_masp` (`masp`);

--
-- Indexes for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD PRIMARY KEY (`maphieunhap`,`manguyenlieu`),
  ADD KEY `FK_CTPN_manguyenlieu` (`manguyenlieu`);

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`mahd`),
  ADD KEY `FK_hoadon_manv` (`manv`),
  ADD KEY `FK_hoadon_makh` (`makh`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`makh`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Indexes for table `nguyenlieu`
--
ALTER TABLE `nguyenlieu`
  ADD PRIMARY KEY (`manguyenlieu`);

--
-- Indexes for table `nhacc`
--
ALTER TABLE `nhacc`
  ADD PRIMARY KEY (`manhacc`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`manv`),
  ADD UNIQUE KEY `CMND` (`CMND`),
  ADD UNIQUE KEY `sdt` (`sdt`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`maphieunhap`),
  ADD KEY `FK_PN_manhacc` (`manhacc`),
  ADD KEY `FK_PN_manv` (`manv`);

--
-- Indexes for table `quyen`
--
ALTER TABLE `quyen`
  ADD PRIMARY KEY (`maquyen`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`masp`),
  ADD KEY `FK_sanpham_maloai` (`maloai`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`manv`),
  ADD KEY `FK_taikhoan_maquyen` (`maquyen`);

--
-- Indexes for table `theloai`
--
ALTER TABLE `theloai`
  ADD PRIMARY KEY (`maloai`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `calamviec`
--
ALTER TABLE `calamviec`
  ADD CONSTRAINT `FK_calamviec_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE SET NULL;

--
-- Constraints for table `ctchucnang`
--
ALTER TABLE `ctchucnang`
  ADD CONSTRAINT `FK_ctchucnang_machucnang` FOREIGN KEY (`machucnang`) REFERENCES `chucnang` (`machucnang`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ctchucnang_maquyen` FOREIGN KEY (`maquyen`) REFERENCES `quyen` (`maquyen`) ON DELETE CASCADE;

--
-- Constraints for table `cthd`
--
ALTER TABLE `cthd`
  ADD CONSTRAINT `FK_cthd_mahd` FOREIGN KEY (`mahd`) REFERENCES `hoadon` (`mahd`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_cthd_masp` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE;

--
-- Constraints for table `ctphache`
--
ALTER TABLE `ctphache`
  ADD CONSTRAINT `FK_ctphache_manguyenlieu` FOREIGN KEY (`manguyenlieu`) REFERENCES `nguyenlieu` (`manguyenlieu`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_ctphache_masp` FOREIGN KEY (`masp`) REFERENCES `sanpham` (`masp`) ON DELETE CASCADE;

--
-- Constraints for table `ctphieunhap`
--
ALTER TABLE `ctphieunhap`
  ADD CONSTRAINT `FK_CTPN_manguyenlieu` FOREIGN KEY (`manguyenlieu`) REFERENCES `nguyenlieu` (`manguyenlieu`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CTPN_maphieunhap` FOREIGN KEY (`maphieunhap`) REFERENCES `phieunhap` (`maphieunhap`) ON DELETE CASCADE;

--
-- Constraints for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `FK_hoadon_makh` FOREIGN KEY (`makh`) REFERENCES `khachhang` (`makh`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_hoadon_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE SET NULL;

--
-- Constraints for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD CONSTRAINT `FK_PN_manhacc` FOREIGN KEY (`manhacc`) REFERENCES `nhacc` (`manhacc`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_PN_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE SET NULL;

--
-- Constraints for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD CONSTRAINT `FK_sanpham_maloai` FOREIGN KEY (`maloai`) REFERENCES `theloai` (`maloai`) ON DELETE SET NULL;

--
-- Constraints for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD CONSTRAINT `FK_taikhoan_manv` FOREIGN KEY (`manv`) REFERENCES `nhanvien` (`manv`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_taikhoan_maquyen` FOREIGN KEY (`maquyen`) REFERENCES `quyen` (`maquyen`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
