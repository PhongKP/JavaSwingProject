package View;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Vector;

import javax.lang.model.SourceVersion;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Div;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.toedter.calendar.JDateChooser;

import BUS.BUSHoaDon;
import BUS.BUSThongKe;
import Modal.DTOCTHD;
import Modal.DTOHoaDon;
import Modal.DTOTKSP;
import Modal.giaoCaModal;

public class thongKeView extends JPanel{
	
    DefaultTableModel tablemodelca;
    DefaultTableModel tablemodel1;
    JLabel kqhomnay;
    JLabel lbbanhomnay;
    JLabel lbhuyhomnay;

    JLabel kqsevenday;
    JLabel lbbansevenday;
    JLabel lbhuysevenday;
    JLabel kqmonth;
    JLabel lbbanmonth;
    JLabel lbhuymonth;
    JLabel kqoption;
    JLabel lbbanoption;
    JLabel lbhuyoption;
    JDateChooser datePicker;
    JDateChooser datePicker1;
    JDateChooser datePickerdtsp2;
    JDateChooser datePickerdtsp1;
    JDateChooser datePickerdowndtsp1;
    JDateChooser datePickerdowndtsp2;
    JDateChooser datePicker3;
    JDateChooser datePicker4;
    JComboBox combobox1;
    JComboBox combobox2;
    JPanel rightheader;
    JButton btnxuat;

    public void exportExcel() {
        java.util.Date selectedDate1 = datePicker.getDate();
        java.util.Date selectedDate2 = datePicker1.getDate();
        if (selectedDate1 == null || selectedDate2 == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
            return;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        java.util.List<String> list = BUSThongKe.getInstance().getTKSPOption(dateFormat.format(selectedDate1).toString(), dateFormat.format(selectedDate2).toString());
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.setColumnWidth(0, 18 * 256);
        sheet.setColumnWidth(1, 28 * 256);
        sheet.setColumnWidth(2, 22 * 256);
        sheet.setColumnWidth(3, 50 * 256);

        byte[] rgbbrown = new byte[]{(byte) 135, (byte) 206, (byte) 250}; 
        XSSFColor colorbrown = new XSSFColor(rgbbrown, null);

// Create a CellStyle object and set its fill pattern and fill foreground color
        XSSFCellStyle stylecolorbrown = (XSSFCellStyle) workbook.createCellStyle();
        stylecolorbrown.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        stylecolorbrown.setFillForegroundColor(colorbrown);

        byte[] rgbpink = new byte[]{(byte) 173, (byte) 216, (byte) 230};
        XSSFColor colorpink = new XSSFColor(rgbpink, null);

// Create a CellStyle object and set its fill pattern and fill foreground color
        XSSFCellStyle stylecolorpink = (XSSFCellStyle)workbook.createCellStyle();
        stylecolorpink.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        stylecolorpink.setFillForegroundColor(colorpink);

        Row row0 = sheet.createRow(0);
        Cell cell0ofrow0 = row0.createCell(0);
        cell0ofrow0.setCellValue("Doanh thu");
        cell0ofrow0.setCellStyle(stylecolorbrown);

        Cell cell1ofrow0 = row0.createCell(1);

        cell1ofrow0.setCellValue("Hoá đơn đã bán");
        cell1ofrow0.setCellStyle(stylecolorpink);

        Cell cell2ofrow0 = row0.createCell(2);
        cell2ofrow0.setCellValue("Hoá đơn huỷ");
        cell2ofrow0.setCellStyle(stylecolorbrown);
        Cell cell3ofrow0 = row0.createCell(3);
        cell3ofrow0.setCellValue("Ngày");
        cell3ofrow0.setCellStyle(stylecolorpink);
        
        
        Row row1 = sheet.createRow(1);
        Cell cell0ofrow1 = row1.createCell(0);
        cell0ofrow1.setCellValue(list.get(0));
        
        Cell cell1ofrow1 = row1.createCell(1);
        cell1ofrow1.setCellValue(list.get(1));
        
        Cell cell2ofrow1 = row1.createCell(2);
        cell2ofrow1.setCellValue(list.get(2));
       
        Cell cell3ofrow1 = row1.createCell(3);
        cell3ofrow1.setCellValue(dateFormat.format(selectedDate1).toString() + " -> " + dateFormat.format(selectedDate2).toString());
 
        FileOutputStream outputStream;
        try {
        	String filePath = "";
            JFileChooser choose=new JFileChooser();
            int x=choose.showOpenDialog(null);
            if(x==JFileChooser.APPROVE_OPTION){
                filePath=choose.getSelectedFile().getPath() + ".xlsx";
            }
            outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception ex) {
            System.out.println(ex);

        }

        JOptionPane.showMessageDialog(null, "Xuất thành công");
    }

    public thongKeView() {
        Init();
        loadDataTKSP();
        loadDataToday();
        loadDataSevenDay();
        loadDataMonth();
        loadDataOption();
        loadDataCaLamViec();
    }

    public void loadDataCaLamViec() {
        tablemodelca.setRowCount(0);
        java.util.Date selectedDate1 = datePicker3.getDate();
        java.util.Date selectedDate2 = datePicker4.getDate();
        if (selectedDate1 == null || selectedDate2 == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
            return;
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        java.util.List<giaoCaModal> list = BUSThongKe.getInstance().getCaLamViecByDate(dateFormat1.format(selectedDate1).toString(), dateFormat1.format(selectedDate2).toString());
        for (giaoCaModal x : list) {
            tablemodelca.addRow(new Object[]{x.getMaCa(), x.getManv(), x.getGioBD(), x.getGioKT(), x.getTienBD(), x.getTienDT(), x.getTienTong(), x.getNgay()});
        }
    }

    public void loadDataTKSP() {
        tablemodel1.setRowCount(0);
        java.util.Date selectedDate1 = (java.util.Date) datePickerdowndtsp1.getDate();
        java.util.Date selectedDate2 = (java.util.Date) datePickerdowndtsp2.getDate();
        if (selectedDate1 == null || selectedDate2 == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
            return;
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        java.util.List<DTOTKSP> list = BUSThongKe.getInstance().getTKSP(
                dateFormat1.format(selectedDate1),
                dateFormat1.format(selectedDate2),
                combobox1.getItemAt(combobox1.getSelectedIndex()).toString(),
                combobox2.getItemAt(combobox2.getSelectedIndex()).toString()
        );

        for (DTOTKSP o : list) {
            tablemodel1.addRow(new Object[]{o.getMaSP(), o.getTenSP(), o.getSoLuong(), o.getTrangThai()});
        }
    }

    public void loadDataToday() {
        java.util.List<String> list = BUSThongKe.getInstance().getTKSPToday();

        kqhomnay.setText(list.get(0));
        lbbanhomnay.setText("Hoá đơn bán:" + list.get(1));
        lbhuyhomnay.setText("Hoá đơn huỷ:" + list.get(2));

    }

    public void loadDataSevenDay() {
        java.util.List<String> list = BUSThongKe.getInstance().getTKSPSevenDay();

        kqsevenday.setText(list.get(0));
        lbbansevenday.setText("Hoá đơn bán:" + list.get(1));
        lbhuysevenday.setText("Hoá đơn huỷ:" + list.get(2));

    }

    public void loadDataMonth() {
        java.util.List<String> list = BUSThongKe.getInstance().getTKSPMonth();

        kqmonth.setText(list.get(0));
        lbbanmonth.setText("Hoá đơn bán:" + list.get(1));
        lbhuymonth.setText("Hoá đơn huỷ:" + list.get(2));

    }

    public void loadDataOption() {
        java.util.Date selectedDate1 = (java.util.Date) datePickerdtsp1.getDate();
        java.util.Date selectedDate2 = (java.util.Date) datePickerdtsp2.getDate();
        if (selectedDate1 == null || selectedDate2 == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
            return;
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        java.util.List<String> list = BUSThongKe.getInstance().getTKSPOption(dateFormat1.format(selectedDate1), dateFormat1.format(selectedDate2));
        kqoption.setText(list.get(0));
        lbbanoption.setText("Hoá đơn bán:" + list.get(1));
        lbhuyoption.setText("Hoá đơn huỷ:" + list.get(2));
    }

    public void Init() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        LocalDateTime lastyear = now.minusYears(1);

        //////////////////////////////////////////
        setLayout(new BorderLayout(0, 15));
        setBackground(new Color(250, 238, 232));
        JPanel header = new JPanel();
        header.setBackground(new Color(250, 238, 232));
        header.setPreferredSize(new Dimension(0, 60));
        header.setLayout(new GridLayout(1, 2));
        JPanel leftheader = new JPanel();
        leftheader.setBackground(new Color(250, 238, 232));
        leftheader.setLayout(new GridLayout(1, 3, 10, 0));
        JButton btndoanhthusp = new JButton("Doanh thu-Sản phẩm");
        btndoanhthusp.setFocusable(false);
        btndoanhthusp.setBackground(new Color(141, 109, 99));
        btndoanhthusp.setForeground(Color.WHITE);
        btndoanhthusp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

        JButton btngiaoca = new JButton("Giao ca");
        btngiaoca.setFocusable(false);
        btngiaoca.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
        btngiaoca.setBackground(new Color(190, 155, 146));
        btngiaoca.setForeground(Color.WHITE);
        leftheader.add(btndoanhthusp);
        leftheader.add(btngiaoca);
        JPanel temp1 = new JPanel();
        temp1.setBackground(new Color(250, 238, 232));
        leftheader.add(temp1);

        rightheader = new JPanel();
        rightheader.setBackground(new Color(250, 238, 232));
        rightheader.setLayout(new GridLayout(1, 2, 10, 0));
        JPanel pnldate = new JPanel();
        pnldate.setBackground(new Color(250, 238, 232));
        pnldate.setLayout(new GridLayout(2, 2, 5, 5));
        JLabel lbtungay = new JLabel("Từ");
        lbtungay.setHorizontalAlignment(JLabel.RIGHT);
        JLabel lbdenngay = new JLabel("Đến");
        lbdenngay.setHorizontalAlignment(JLabel.RIGHT);
        java.sql.Timestamp timestampxuat1 = java.sql.Timestamp.valueOf(now);
        datePicker = new JDateChooser();
        datePicker.setDate(new Date(timestampxuat1.getTime()));
        java.sql.Timestamp timestampxuat2 = java.sql.Timestamp.valueOf(tomorrow);
        datePicker1 = new JDateChooser();
        datePicker1.setDate(new Date(timestampxuat2.getTime()));

        pnldate.add(lbtungay);
        pnldate.add(datePicker);

        pnldate.add(lbdenngay);
        pnldate.add(datePicker1);

        btnxuat = new JButton("Xuất TK");
        btnxuat.setBackground(Color.WHITE);
        btnxuat.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnxuat.setFocusable(false);
        btnxuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportExcel();

            }

        });
        rightheader.add(pnldate);
        rightheader.add(btnxuat);

        header.add(leftheader);
        header.add(rightheader);

        JPanel center = new JPanel();
        center.setBackground(new Color(250, 238, 232));
        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        center.setLayout(new BorderLayout(0, 0));
        JPanel upcenter = new JPanel();
        upcenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        upcenter.setBackground(new Color(250, 238, 232));
        upcenter.setPreferredSize(new Dimension(0, 36));
        upcenter.setLayout(new GridLayout(1, 2, 10, 0));
        JPanel leftupcenter = new JPanel();
        leftupcenter.setBackground(new Color(250, 238, 232));
        leftupcenter.setLayout(new GridLayout(1, 5, 5, 0));
        JLabel lbtungay2 = new JLabel("Từ ngày:");
        lbtungay2.setHorizontalAlignment(JLabel.CENTER);
        JLabel lbdenngay2 = new JLabel("Đến ngày:");
        lbdenngay2.setHorizontalAlignment(JLabel.CENTER);

        datePicker3 = new JDateChooser();

        java.sql.Timestamp timestamp1 = java.sql.Timestamp.valueOf(now);

        datePicker3.setDate(new java.sql.Date(timestamp1.getTime()));

        datePicker4 = new JDateChooser();
        java.sql.Timestamp timestamp2 = java.sql.Timestamp.valueOf(tomorrow);
        datePicker4.setDate(new java.sql.Date(timestamp2.getTime()));

        JButton btnloc2 = new JButton("Lọc");
        btnloc2.setFocusable(false);
        btnloc2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataCaLamViec();
            }

        });

        btnloc2.setBackground(Color.WHITE);

        leftupcenter.add(lbtungay2);
        leftupcenter.add(datePicker3);
        leftupcenter.add(lbdenngay2);
        leftupcenter.add(datePicker4);
        leftupcenter.add(btnloc2);

        JPanel rightupcenter = new JPanel();
        rightupcenter.setBackground(new Color(250, 238, 232));
        upcenter.add(leftupcenter);
        upcenter.add(rightupcenter);

        JPanel downcenter = new JPanel();
        downcenter.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        downcenter.setBackground(new Color(250, 238, 232));
        downcenter.setLayout(new GridLayout(1, 1));
        String columnca[] = {"Mã Ca", "Mã nhân viên", "TG bắt đầu", "TG kết thúc", "Tiền ban đầu", "Tiền doanh thu", "Tổng tiền ca"};

        tablemodelca = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablemodelca.setColumnIdentifiers(columnca);
        JTable table = new JTable(tablemodelca);
        table.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setBackground(new Color(242, 242, 243));
        JScrollPane sp = new JScrollPane(table);

        downcenter.add(sp);

        center.add(upcenter, BorderLayout.NORTH);
        center.add(downcenter, BorderLayout.CENTER);
///////////////////////////////////////////////////////////////////////////////////////
        JPanel doanhthuspcenter = new JPanel();
        doanhthuspcenter.setLayout(new GridLayout(2, 1, 0, 5));
        doanhthuspcenter.setBackground(new Color(250, 238, 232));
        JPanel updtsp = new JPanel();
        updtsp.setBackground(new Color(250, 238, 232));
        updtsp.setLayout(new BorderLayout(0, 10));
        updtsp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JPanel northupdtsp = new JPanel();
        northupdtsp.setBackground(new Color(250, 238, 232));
        northupdtsp.setLayout(new GridLayout(1, 4, 20, 0));
        JPanel homnay = new JPanel();
        homnay.setLayout(new GridLayout(4, 1));
        homnay.setBackground(new Color(141, 109, 99));

        JLabel lbhomnay = new JLabel("Hôm nay");
        lbhomnay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        lbhomnay.setHorizontalAlignment(JLabel.CENTER);
        lbhomnay.setForeground(Color.WHITE);
        kqhomnay = new JLabel("118.000đ");
        kqhomnay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kqhomnay.setHorizontalAlignment(JLabel.CENTER);
        kqhomnay.setForeground(Color.WHITE);
        lbbanhomnay = new JLabel("Hoá đơn bán:0");
        lbbanhomnay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbbanhomnay.setForeground(Color.WHITE);
        lbbanhomnay.setHorizontalAlignment(JLabel.CENTER);

        lbhuyhomnay = new JLabel("Hoá đơn huỷ:0");
        lbhuyhomnay.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbhuyhomnay.setForeground(Color.WHITE);
        lbhuyhomnay.setHorizontalAlignment(JLabel.CENTER);
        homnay.add(lbhomnay);
        homnay.add(kqhomnay);
        homnay.add(lbbanhomnay);
        homnay.add(lbhuyhomnay);

        JPanel sevenday = new JPanel();
        sevenday.setLayout(new GridLayout(4, 1));
        sevenday.setBackground(new Color(141, 109, 99));
        JLabel lbsevenday = new JLabel("7 ngày");
        lbsevenday.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        lbsevenday.setForeground(Color.WHITE);
        lbsevenday.setHorizontalAlignment(JLabel.CENTER);
        kqsevenday = new JLabel("118.000đ");
        kqsevenday.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kqsevenday.setHorizontalAlignment(JLabel.CENTER);
        kqsevenday.setForeground(Color.WHITE);
        lbbansevenday = new JLabel("Hoá đơn bán:0");
        lbbansevenday.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbbansevenday.setHorizontalAlignment(JLabel.CENTER);
        lbbansevenday.setForeground(Color.WHITE);
        lbhuysevenday = new JLabel("Hoá đơn huỷ:");
        lbhuysevenday.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbhuysevenday.setHorizontalAlignment(JLabel.CENTER);
        lbhuysevenday.setForeground(Color.WHITE);

        sevenday.add(lbsevenday);
        sevenday.add(kqsevenday);
        sevenday.add(lbbansevenday);
        sevenday.add(lbhuysevenday);

        JPanel month = new JPanel();
        month.setBackground(new Color(141, 109, 99));
        month.setLayout(new GridLayout(4, 1));
        JLabel lbmonth = new JLabel("Tháng");
        lbmonth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        lbmonth.setHorizontalAlignment(JLabel.CENTER);
        lbmonth.setForeground(Color.WHITE);
        kqmonth = new JLabel("118.000đ");
        kqmonth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kqmonth.setHorizontalAlignment(JLabel.CENTER);
        kqmonth.setForeground(Color.WHITE);
        lbbanmonth = new JLabel("Hoá đơn bán:0");
        lbbanmonth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbbanmonth.setHorizontalAlignment(JLabel.CENTER);
        lbbanmonth.setForeground(Color.WHITE);
        lbhuymonth = new JLabel("Hoá đơn huỷ:");
        lbhuymonth.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbhuymonth.setHorizontalAlignment(JLabel.CENTER);
        lbhuymonth.setForeground(Color.WHITE);

        month.add(lbmonth);
        month.add(kqmonth);
        month.add(lbbanmonth);
        month.add(lbhuymonth);

        JPanel option = new JPanel();
        option.setBackground(new Color(141, 109, 99));
        option.setLayout(new GridLayout(4, 1));
        JLabel lboption = new JLabel("Tuỳ chọn");
        lboption.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
        lboption.setHorizontalAlignment(JLabel.CENTER);
        lboption.setForeground(Color.WHITE);
        kqoption = new JLabel("118.000đ");
        kqoption.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kqoption.setHorizontalAlignment(JLabel.CENTER);
        kqoption.setForeground(Color.WHITE);
        lbbanoption = new JLabel("Hoá đơn bán:0");
        lbbanoption.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbbanoption.setHorizontalAlignment(JLabel.CENTER);
        lbbanoption.setForeground(Color.WHITE);
        lbhuyoption = new JLabel("Hoá đơn huỷ:");
        lbhuyoption.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        lbhuyoption.setHorizontalAlignment(JLabel.CENTER);
        lbhuyoption.setForeground(Color.WHITE);
        option.add(lboption);
        option.add(kqoption);
        option.add(lbbanoption);
        option.add(lbhuyoption);

        northupdtsp.add(homnay);
        northupdtsp.add(sevenday);
        northupdtsp.add(month);
        northupdtsp.add(option);
        JPanel centerupdtsp = new JPanel();
        centerupdtsp.setBackground(new Color(250, 238, 232));
        centerupdtsp.setPreferredSize(new Dimension(0, 55));
        centerupdtsp.setLayout(new GridLayout(1, 4, 20, 0));

        JPanel fourcenterupdtsp = new JPanel();
        fourcenterupdtsp.setBackground(new Color(250, 238, 232));
        fourcenterupdtsp.setLayout(new BorderLayout(5, 0));

        JPanel left4centerupdtsp = new JPanel();
        left4centerupdtsp.setBackground(new Color(250, 238, 232));
        left4centerupdtsp.setLayout(new GridLayout(2, 1, 0, 3));
        left4centerupdtsp.setPreferredSize(new Dimension(30, 0));
        JLabel tungaydtsp = new JLabel("Từ:");
        JLabel denngaydtsp = new JLabel("Đến:");
        left4centerupdtsp.add(tungaydtsp);
        left4centerupdtsp.add(denngaydtsp);

        JPanel center4centerupdtsp = new JPanel();
        center4centerupdtsp.setLayout(new GridLayout(2, 1, 0, 3));

        datePickerdtsp1 = new JDateChooser();
        java.sql.Timestamp timestampdtsp1 = java.sql.Timestamp.valueOf(lastyear);
        datePickerdtsp1.setDate(new Date(timestampdtsp1.getTime()));

        datePickerdtsp2 = new JDateChooser();
        java.sql.Timestamp timestampdtsp2 = java.sql.Timestamp.valueOf(now);
        datePickerdtsp2.setDate(new Date(timestampdtsp2.getTime()));

        center4centerupdtsp.add(datePickerdtsp1);
        center4centerupdtsp.add(datePickerdtsp2);

        JButton btnchondtsp = new JButton("Chọn");
        btnchondtsp.setBackground(Color.WHITE);
        btnchondtsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataOption();
            }

        });

        fourcenterupdtsp.add(left4centerupdtsp, BorderLayout.WEST);
        fourcenterupdtsp.add(center4centerupdtsp, BorderLayout.CENTER);
        fourcenterupdtsp.add(btnchondtsp, BorderLayout.EAST);
        JPanel temppn1 = new JPanel();
        temppn1.setBackground(new Color(250, 238, 232));
        JPanel temppn2 = new JPanel();
        temppn2.setBackground(new Color(250, 238, 232));
        JPanel temppn3 = new JPanel();
        temppn3.setBackground(new Color(250, 238, 232));

        centerupdtsp.add(temppn1);
        centerupdtsp.add(temppn2);
        centerupdtsp.add(temppn3);
        centerupdtsp.add(fourcenterupdtsp);

        updtsp.add(northupdtsp, BorderLayout.CENTER);
        updtsp.add(centerupdtsp, BorderLayout.SOUTH);

        ////////////////////////////
        JPanel downdtsp = new JPanel();
        downdtsp.setBackground(new Color(250, 238, 232));
        downdtsp.setLayout(new BorderLayout(10, 10));
        downdtsp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JLabel lbtkspdtsp = new JLabel("Thống kê sản phẩm");
        lbtkspdtsp.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));

        tablemodel1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] tkspcolumn = {"Mã SP", "Tên SP", "Số lượng", "Trạng thái"};
        tablemodel1.setColumnIdentifiers(tkspcolumn);

        JTable table1 = new JTable();

        table1.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table1.setModel(tablemodel1);
        JScrollPane scrollpane1 = new JScrollPane(table1);

        JPanel rightdowndtsp = new JPanel();

        rightdowndtsp.setBackground(new Color(250, 238, 232));
        rightdowndtsp.setLayout(new BorderLayout(0, 5));
        rightdowndtsp.setPreferredSize(new Dimension(300, 0));
        JPanel northrightdowndtsp = new JPanel();
        northrightdowndtsp.setBackground(new Color(250, 238, 232));
        northrightdowndtsp.setLayout(new GridLayout(4, 2, 10, 2));
        JLabel lbtungayrightdowndtsp = new JLabel("Từ ngày:");
        JLabel lbdenngayrightdowndtsp = new JLabel("Đến ngày:");
        JLabel lbloaispdtsp = new JLabel("Loại sản phẩm:");
        JLabel lbtrangthaidtsp = new JLabel("Trạng thái:");

        datePickerdowndtsp1 = new JDateChooser();
        java.sql.Timestamp timestampdowndtsp1 = java.sql.Timestamp.valueOf(now);

        datePickerdowndtsp1.setDate(new Date(timestampdowndtsp1.getTime()));

        datePickerdowndtsp2 = new JDateChooser();
        java.sql.Timestamp timestampdowndtsp2 = java.sql.Timestamp.valueOf(tomorrow);
        datePickerdowndtsp2.setDate(new Date(timestampdowndtsp2.getTime()));

        java.util.Vector v1 = new java.util.Vector();
        v1.add(0, "Tất cả");
        java.util.List<String> list1 = BUSThongKe.getInstance().getAllTypeProduct();
        for (int i = 0; i < list1.size(); i++) {
            v1.add(i + 1, list1.get(i));
        }
        combobox1 = new JComboBox(v1);

        java.util.Vector v2 = new java.util.Vector();
        v2.add(
                0, "Tất cả");
        v2.add(1, "Đang bán");
        v2.add(2, "Ngừng bán");
        combobox2 = new JComboBox(v2);

        northrightdowndtsp.add(lbtungayrightdowndtsp);

        northrightdowndtsp.add(lbdenngayrightdowndtsp);
        northrightdowndtsp.add(datePickerdowndtsp1);
        northrightdowndtsp.add(datePickerdowndtsp2);
        northrightdowndtsp.add(lbloaispdtsp);
        northrightdowndtsp.add(lbtrangthaidtsp);
        northrightdowndtsp.add(combobox1);

        northrightdowndtsp.add(combobox2);

        JPanel centerrightdowndtsp = new JPanel();
        centerrightdowndtsp.setBackground(new Color(250, 238, 232));
        centerrightdowndtsp.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton btnlocdtsp = new JButton("Lọc");
        btnlocdtsp.setBackground(Color.WHITE);
        btnlocdtsp.setFocusable(false);
        btnlocdtsp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadDataTKSP();

            }

        });
        centerrightdowndtsp.add(btnlocdtsp);

        rightdowndtsp.add(northrightdowndtsp, BorderLayout.NORTH);
        rightdowndtsp.add(centerrightdowndtsp, BorderLayout.CENTER);

        downdtsp.add(lbtkspdtsp, BorderLayout.NORTH);
        downdtsp.add(scrollpane1, BorderLayout.CENTER);
        downdtsp.add(rightdowndtsp, BorderLayout.EAST);

        doanhthuspcenter.add(updtsp);
        doanhthuspcenter.add(downdtsp);

        add(header, BorderLayout.NORTH);
        add(doanhthuspcenter, BorderLayout.CENTER);

        btndoanhthusp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(center);
                remove(doanhthuspcenter);
                add(doanhthuspcenter, BorderLayout.CENTER);
                rightheader.setVisible(true);
                loadDataTKSP();
                loadDataToday();
                loadDataSevenDay();
                loadDataMonth();
                loadDataOption();
                loadDataCaLamViec();
                revalidate();
                repaint();
            }

        });
        btngiaoca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                remove(center);
                remove(doanhthuspcenter);
                add(center, BorderLayout.CENTER);
                rightheader.setVisible(false);
                loadDataTKSP();
                loadDataToday();
                loadDataSevenDay();
                loadDataMonth();
                loadDataOption();
                loadDataCaLamViec();
                revalidate();
                repaint();
            }

        });
    }

}
