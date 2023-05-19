package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
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
import Modal.DTOCTHD;
import Modal.DTOHoaDon;

public class hoaDonView extends JPanel{
    JTabbedPane tabbedPane;

    Object columnNameHoaDon[] = {"Mã hoá đơn", "Tên nhân viên", "Ngày tạo", "Tổng tiền", "Trạng thái"};

    Object columnNameCTHD[] = {"Tên sản phẩm", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú"};

    JPanel panel1;
    JPanel panel2;
    JPanel left1;
    JPanel center;
    JPanel header;
    JLabel lbhoadon;
    JPanel footer;
    JButton btnconfirm;
    JPanel right2;
    JPanel header2;
    JLabel title2;
    JPanel center2;
    JPanel center2a;
    JPanel north1;

    JPanel center2b;
    JPanel north2;
    JPanel north2up;
    JPanel north2down;
    JLabel lbtrangthai;
    JLabel kqtrangthai;
    JLabel lbtongtiensp;
    JLabel kqtongtiensp;
    JLabel lbchiphi;
    JLabel kqchiphi;
    JLabel lbtongtien;
    JLabel kqtongtien;

    JLabel lbmahoadon;
    JLabel lbkqmahoadon;
    JLabel lbmakhachhang;
    JLabel lbkqmakhachhang;
    JLabel lbsdt;
    JLabel lbkqsdt;
    JLabel lbthoigiantao;
    JLabel lbkqthoigiantao;
    JLabel lbthoigianthanhtoan;
    JLabel lbkqthoigianthanhtoan;
    JLabel lbloai;
    JLabel lbkqloai;
    JLabel lbghichu;
    JTextArea ghichu;
    JPanel tren;
    JPanel tren1;
    JPanel atren1;
    JPanel upatren1;
    JLabel lbmahoadontren1;
    JTextField txtmahoadon;
    JButton btnfindmahoadon;
    JPanel btren1;
    JLabel lbtungay2;
    JPanel ctren1;
    JPanel upctren1;
    JLabel lbdenngay2;
    JPanel dtren1;
    JPanel updtren1;
    JLabel lbuptren1trangthai;
    JComboBox combobox;
    JButton btnloc;
    JPanel tren2;
    JPanel duoi;
    JLabel titleduoi1;
    JPanel duoi2;
    JPanel leftduoi2;
    JPanel aleftduoi2;
    JPanel upaleftduoi2;
    JLabel lbmahoadon2;
    JLabel kqmahoadon2;/////////////
    JLabel lbmakhachhang2;
    JLabel kqmakhachhang2;
    JLabel lbsdt2;
    JLabel kqsdt2;
    JLabel lbthoigiantao2;
    JLabel kqthoigiantao2;
    JLabel lbtgthanhtoan2;
    JLabel kqtgthanhtoan2;
    JLabel lbloaithanhtoan2;
    JLabel kqloaithanhtoan2;
    JLabel lbghichu2;
    JPanel downaleftduoi2;
    JTextArea ghichu2;
    JPanel bleftduoi2;
    JPanel upbleftduoi2;
    JPanel aupbleftduoi2;
    JPanel bupbleftduoi2;
    JPanel downbleftduoi2;

    JPanel duoi1;
    JLabel lbtrangthai2;
    JLabel kqtrangthai2;
    JLabel lbtongtiensp2;
    JLabel kqtongtiensp2;
    JLabel lbchiphikhac2;
    JLabel kqchiphikhac2;
    JLabel lbtongtienhd2;
    JLabel kqtongtienhd2;

    JPanel rightduoi2;
    JPanel uprightduoi2;
    JPanel downrightduoi2;
    JButton btnkhoiphuc;
    JPanel upbtren1;
    DefaultTableModel tablemodel1;
    JTable table1;
    JScrollPane scrollpane1;
    DefaultTableModel tablemodel2;
    JTable table2;
    JScrollPane scrollpane2;
    DefaultTableModel tablemodel3;
    JTable table3;
    JScrollPane scrollpane3;
    DefaultTableModel tablemodel4;
    JTable table4;
    JScrollPane scrollpane4;
    JDateChooser datePicker;
    JDateChooser datePicker2;

    public void printPDF(String mahd,String strdate,String strnamekh,String strmakh,String strmanv,String strnamenv,String[][] array ,String strtongtiensp,String strtienthue,String strtonggia,String strtienkhachdua,String strtienthoi) {
        try {
        	String filePath = "";
            JFileChooser choose=new JFileChooser();
            int x=choose.showOpenDialog(null);
            if(x==JFileChooser.APPROVE_OPTION){
                filePath=choose.getSelectedFile().getPath() + ".pdf";
            }
            PdfWriter writer = new PdfWriter(filePath);
            PdfDocument pdf = new PdfDocument(writer);
            Document doc = new Document(pdf);
            pdf.addNewPage();
            ImageData imagedata = ImageDataFactory.create("src/img/logo.png");
            com.itextpdf.layout.element.Image image = new com.itextpdf.layout.element.Image(imagedata);
            float size = (float) (pdf.getPage(1).getPageSize().getWidth() * 0.25);
            image.setWidth(size);
            image.setHeight(size);
            float paddingtop = 5;
            image.setFixedPosition((float) (size * 1.5), pdf.getPage(1).getPageSize().getHeight() - size - paddingtop);
            doc.add(image);
            PdfFont font = PdfFontFactory.createFont("src/andika-basic/AndikaNewBasic-R.ttf", PdfEncodings.IDENTITY_H, true);
            Paragraph mahoadon = new Paragraph("Mã hoá đơn:" + mahd);
            float paddingleft = 40;
            float heighttext = 24;
            mahoadon.setFont(font);

            float line1bottom = pdf.getPage(1).getPageSize().getHeight() - size - paddingtop - heighttext;
            mahoadon.setFixedPosition(1, paddingleft, line1bottom, 1000);
            doc.add(mahoadon);
            Paragraph thoigiantao = new Paragraph("Ngày thanh toán:"+strdate);
            thoigiantao.setFont(font);
            float line2bottom = line1bottom - heighttext;
            thoigiantao.setFixedPosition(1, paddingleft, line2bottom, 1000);
            doc.add(thoigiantao);
            Paragraph khachhang = new Paragraph("Khách hàng:("+strmakh+")"+strnamekh);
            khachhang.setFont(font);
            float line3bottom = line2bottom - heighttext;
            khachhang.setFixedPosition(1, paddingleft, line3bottom, 1000);
            doc.add(khachhang);
            Paragraph nhanvien = new Paragraph("Nhân viên thanh toán:("+strmanv+")"+strnamenv);
            nhanvien.setFont(font);
            float line4bottom = line3bottom - heighttext;
            nhanvien.setFixedPosition(1, paddingleft, line4bottom, 1000);
            doc.add(nhanvien);
            LineSeparator line1 = new LineSeparator(new SolidLine());
            line1.setWidth(UnitValue.createPercentValue(100));
            Div div1 = new Div();
            div1.add(line1);
            div1.setMarginTop(pdf.getPage(1).getPageSize().getHeight() - line4bottom - heighttext - 10);
            doc.add(div1);
            float paddingright = pdf.getPage(1).getPageSize().getWidth() - paddingleft;
            float column1 = paddingleft + 5;
            float column5 = paddingright - 5;
            float column2 = column1 + (float) ((column5 - column1) * 0.2) - 45;
            float column3 = column2 + (float) ((column5 - column1) * 0.2) + 100;
            float column4 = column3 + (float) ((column5 - column1) * 0.2);
            column5 = column4 + (float) ((column5 - column1) * 0.2 - 50);
            Paragraph masp = new Paragraph("Mã SP");
            masp.setFont(font);
            float line5bottom = line4bottom - heighttext - 5;
            masp.setFixedPosition(1, column1, line5bottom, (float) ((paddingright - column1) * 0.2) - 45);
            masp.setBold();
            doc.add(masp);
            Paragraph tensp = new Paragraph("Tên SP");
            tensp.setFont(font);

            tensp.setFixedPosition(1, column2, line5bottom, (float) ((paddingright - column1) * 0.2) + 100);
            tensp.setBold();
            doc.add(tensp);
            Paragraph dongia = new Paragraph("Đơn giá");
            dongia.setFont(font);
            dongia.setFixedPosition(1, column3, line5bottom, (float) ((paddingright - column1) * 0.2));
            dongia.setBold();
            dongia.setTextAlignment(TextAlignment.RIGHT);
            doc.add(dongia);
            Paragraph soluong = new Paragraph("SL");
            soluong.setTextAlignment(TextAlignment.RIGHT);
            soluong.setFont(font);
            soluong.setFixedPosition(1, column4, line5bottom, (float) ((paddingright - column1) * 0.2) - 50);
            soluong.setBold();
            doc.add(soluong);
            Paragraph thanhtien = new Paragraph("Thành tiền");
            thanhtien.setFont(font);
            thanhtien.setFixedPosition(1, column5, line5bottom, (float) ((paddingright - column1) * 0.2));
            thanhtien.setBold();
            thanhtien.setTextAlignment(TextAlignment.RIGHT);
            doc.add(thanhtien);
            LineSeparator line2 = new LineSeparator(new SolidLine());
            line2.setWidth(UnitValue.createPercentValue(100));
            Div div2 = new Div();
            div2.add(line2);
            div2.setMarginTop(heighttext);
            doc.add(div2);

            float newline = line5bottom;
            int currentpage = 1;
            for(int i = 0; i < array.length; i++) {
                newline -= heighttext;
                if (newline < 10) {
                    pdf.addNewPage();
                    currentpage++;
                    newline = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
                }

                Paragraph item1 = new Paragraph(array[i][0]);
                item1.setFont(font);
                item1.setFixedPosition(currentpage, column1, newline, (float) ((paddingright - column1) * 0.2) - 45);
                Paragraph item2 = new Paragraph(array[i][1]);
                item2.setFont(font);
                item2.setFixedPosition(currentpage, column2, newline, (float) ((paddingright - column1) * 0.2) + 100);
                Paragraph item3 = new Paragraph(array[i][2]);
                item3.setFont(font);
                item3.setFixedPosition(currentpage, column3, newline, (float) ((paddingright - column1) * 0.2));
                item3.setTextAlignment(TextAlignment.RIGHT);
                Paragraph item4 = new Paragraph(array[i][3]);
                item4.setFont(font);
                item4.setTextAlignment(TextAlignment.RIGHT);
                item4.setFixedPosition(currentpage, column4, newline, (float) ((paddingright - column1) * 0.2) - 50);
                Paragraph item5 = new Paragraph(array[i][4]);
                item5.setFont(font);
                item5.setFixedPosition(currentpage, column5, newline, (float) ((paddingright - column1) * 0.2));
                item5.setTextAlignment(TextAlignment.RIGHT);
                doc.add(item1);
                doc.add(item2);
                doc.add(item3);
                doc.add(item4);
                doc.add(item5);

            }

            Paragraph tongtiensp = new Paragraph("Tổng tiền SP:");
            tongtiensp.setFont(font);
            float line6bottom = newline - heighttext;
            if (line6bottom < 10) {
                pdf.addNewPage();
                currentpage++;
                line6bottom = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
            }
            tongtiensp.setBold();
            tongtiensp.setFixedPosition(currentpage, column1, line6bottom, (float) ((paddingright - column1) * 0.4));
            doc.add(tongtiensp);

            Paragraph txttongtiensp = new Paragraph(strtongtiensp);
            txttongtiensp.setFont(font);
            txttongtiensp.setFixedPosition(currentpage, column5, line6bottom, (float) ((paddingright - column1) * 0.2));
            txttongtiensp.setTextAlignment(TextAlignment.RIGHT);
            txttongtiensp.setBold();
            doc.add(txttongtiensp);

            Paragraph tienthue = new Paragraph("Tiền thuế:");
            tienthue.setFont(font);
            float line7bottom = line6bottom - heighttext;
            if (line7bottom < 10) {
                pdf.addNewPage();
                currentpage++;
                line7bottom = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
            }
            tienthue.setFixedPosition(currentpage, column1, line7bottom, (float) ((paddingright - column1) * 0.4));
            tienthue.setBold();
            doc.add(tienthue);

            Paragraph txttienthue = new Paragraph(strtienthue);
            txttienthue.setFont(font);
            txttienthue.setFixedPosition(currentpage, column5, line7bottom, (float) ((paddingright - column1) * 0.2));
            txttienthue.setTextAlignment(TextAlignment.RIGHT);
            txttienthue.setBold();
            doc.add(txttienthue);

            Paragraph tonggia = new Paragraph("Tổng tiền:");
            tonggia.setFont(font);
            float line8bottom = line7bottom - heighttext;
            if (line8bottom < 10) {
                pdf.addNewPage();
                currentpage++;
                line8bottom = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
            }
            tonggia.setFixedPosition(currentpage, column1, line8bottom, (float) ((paddingright - column1) * 0.4));
            tonggia.setBold();
            doc.add(tonggia);

            Paragraph txttonggia = new Paragraph(strtonggia);
            txttonggia.setFont(font);
            txttonggia.setFixedPosition(currentpage, column5, line8bottom, (float) ((paddingright - column1) * 0.2));
            txttonggia.setTextAlignment(TextAlignment.RIGHT);
            txttonggia.setBold();
            doc.add(txttonggia);

            Paragraph tienkhachdua = new Paragraph("Tiền khách đưa:");
            tienkhachdua.setFont(font);
            float line9bottom = line8bottom - heighttext;
            if (line9bottom < 10) {
                pdf.addNewPage();
                currentpage++;
                line9bottom = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
            }
            tienkhachdua.setFixedPosition(currentpage, column1, line9bottom, (float) ((paddingright - column1) * 0.4));
            tienkhachdua.setBold();
            doc.add(tienkhachdua);

            Paragraph txttienkhachdua = new Paragraph(strtienkhachdua);
            txttienkhachdua.setFont(font);
            txttienkhachdua.setFixedPosition(currentpage, column5, line9bottom, (float) ((paddingright - column1) * 0.2));
            txttienkhachdua.setTextAlignment(TextAlignment.RIGHT);
            txttienkhachdua.setBold();
            doc.add(txttienkhachdua);

            Paragraph tienthoi = new Paragraph("Tiền thối:");
            tienthoi.setFont(font);
            float line10bottom = line9bottom - heighttext;
            if (line10bottom < 10) {
                pdf.addNewPage();
                currentpage++;
                line10bottom = pdf.getPage(currentpage).getPageSize().getHeight() - 40;
            }
            tienthoi.setFixedPosition(currentpage, column1, line10bottom, (float) ((paddingright - column1) * 0.4));
            tienthoi.setBold();
            doc.add(tienthoi);

            Paragraph txttienthoi = new Paragraph(strtienthoi);
            txttienthoi.setFont(font);
            txttienthoi.setFixedPosition(currentpage, column5, line10bottom, (float) ((paddingright - column1) * 0.2));
            txttienthoi.setTextAlignment(TextAlignment.RIGHT);
            txttienthoi.setBold();
            doc.add(txttienthoi);

            doc.close();
            pdf.close();
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public hoaDonView() {
        Init();
        loadData();
        loadData3();
    }

    public void loadDaTaCTHD(int row) {
        tablemodel2.setRowCount(0);
        List<DTOCTHD> list = BUSHoaDon.getInstance().getCTHDByID(table1.getValueAt(row, 0).toString());
        for (DTOCTHD o : list) {
            tablemodel2.addRow(new Object[]{
                o.getTenSP(),
                o.getDonGia(),
                o.getSoLuong(),
                o.getThanhTien(),
                o.getGhiChu()
            }
            );
        }
        DTOHoaDon dtohd = BUSHoaDon.getInstance().getHoaDonByID(tablemodel1.getValueAt(row, 0).toString());
        lbkqmahoadon.setText(dtohd.getMaHD());
        lbkqmakhachhang.setText(dtohd.getTenKH());
        lbkqsdt.setText(dtohd.getSDT());
        lbkqthoigiantao.setText(dtohd.getThoiGianTao());
        lbkqthoigianthanhtoan.setText(dtohd.getThoiGianThanhToan());
        lbkqloai.setText(dtohd.getLoaiThanhToan());
        ghichu.setText(dtohd.getGhiChu());
        kqtrangthai.setText(dtohd.getTrangThai());

        kqtongtiensp.setText("" + (Float.parseFloat(dtohd.getTongGia()) - Float.parseFloat(dtohd.getTienThue())));
        kqtongtien.setText(dtohd.getTongGia());
        kqchiphi.setText(dtohd.getTienThue());

    }

    public void loadDaTaCTHD3(int row) {
        tablemodel4.setRowCount(0);
        List<DTOCTHD> list = BUSHoaDon.getInstance().getCTHDByID(table3.getValueAt(row, 0).toString());
        for (DTOCTHD o : list) {
            tablemodel4.addRow(new Object[]{
                o.getTenSP(),
                o.getDonGia(),
                o.getSoLuong(),
                o.getThanhTien(),
                o.getGhiChu()
            }
            );
        }
        DTOHoaDon dtohd = BUSHoaDon.getInstance().getHoaDonByID(tablemodel3.getValueAt(row, 0).toString());
        kqmahoadon2.setText(dtohd.getMaHD());
        kqmakhachhang2.setText(dtohd.getTenKH());
        kqsdt2.setText(dtohd.getSDT());
        kqthoigiantao2.setText(dtohd.getThoiGianTao());
        kqtgthanhtoan2.setText(dtohd.getThoiGianThanhToan());
        kqloaithanhtoan2.setText(dtohd.getLoaiThanhToan());
        ghichu2.setText(dtohd.getGhiChu());
        kqtrangthai2.setText(dtohd.getTrangThai());

        kqtongtiensp2.setText("" + (Float.parseFloat(dtohd.getTongGia()) - Float.parseFloat(dtohd.getTienThue())));
        kqtongtienhd2.setText(dtohd.getTongGia());
        kqchiphikhac2.setText(dtohd.getTienThue());

    }

    public void loadData() {
        tablemodel1.setRowCount(0);
        tablemodel2.setRowCount(0);
        List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonWaitConfirm();
        for (DTOHoaDon dto : list) {

            tablemodel1.addRow(new Object[]{dto.getMaHD(), dto.getTenNV(), dto.getThoiGianTao(), dto.getTongGia(), dto.getTrangThai()});
        }
        lbkqmahoadon.setText("");
        lbkqmakhachhang.setText("");
        lbkqsdt.setText("");
        lbkqthoigiantao.setText("");
        lbkqthoigianthanhtoan.setText("");
        lbkqloai.setText("");
        ghichu.setText("");
        kqtrangthai.setText("");

        kqtongtiensp.setText("");
        kqtongtien.setText("");
        kqchiphi.setText("");

    }

    public void loadData3() {
        tablemodel3.setRowCount(0);
        tablemodel4.setRowCount(0);
        java.util.Date selectedDate1 = datePicker.getDate();
        java.util.Date selectedDate2 = datePicker2.getDate();
        if (selectedDate1 == null || selectedDate2 == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày");
            return;
        }
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        List<DTOHoaDon> list = BUSHoaDon.getInstance().getAllHoaDonByDateAndState(dateFormat1.format(selectedDate1).toString(), dateFormat1.format(selectedDate2).toString(), combobox.getItemAt(combobox.getSelectedIndex()).toString());
        for (DTOHoaDon dto : list) {
            tablemodel3.addRow(new Object[]{dto.getMaHD(), dto.getTenNV(), dto.getThoiGianTao(), dto.getTongGia(), dto.getTrangThai()});
        }
        kqmahoadon2.setText("");
        kqmakhachhang2.setText("");
        kqsdt2.setText("");
        kqthoigiantao2.setText("");
        kqtgthanhtoan2.setText("");
        kqloaithanhtoan2.setText("");
        ghichu2.setText("");
        kqtrangthai2.setText("");

        kqtongtiensp2.setText("");
        kqtongtienhd2.setText("");
        kqchiphikhac2.setText("");

    }

    public void Init() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        setBackground(new Color(250, 238, 232));
        setLayout(new GridLayout(1, 1));
        tabbedPane = new JTabbedPane();

        panel1 = new JPanel();

        panel1.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel1.setLayout(
                new GridLayout(1, 2, 10, 0));
        panel1.setBackground(
                new Color(250, 238, 232));
        panel2 = new JPanel();

        panel2.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        tabbedPane.addTab(
                "HĐ pha chế", null, panel1, "");
        tabbedPane.addTab(
                "Lịch sử HĐ", null, panel2, "");

        add(tabbedPane);

        left1 = new JPanel();

        left1.setBackground(
                new Color(250, 238, 232));
        left1.setLayout(
                new BorderLayout(0, 10));

        center = new JPanel();

        center.setBackground(
                new Color(250, 238, 232));
        center.setLayout(
                new GridLayout(1, 1));

        tablemodel1 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodel1.setColumnIdentifiers(columnNameHoaDon);
        table1 = new JTable();

        table1.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        table1.setModel(tablemodel1);

        table1.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e
            ) {
                int row = table1.getSelectedRow();
                loadDaTaCTHD(row);
            }
        }
        );
        scrollpane1 = new JScrollPane(table1);

        center.add(scrollpane1);

        header = new JPanel();

        header.setPreferredSize(
                new Dimension(0, 60));
        header.setLayout(
                new FlowLayout(FlowLayout.CENTER));
        header.setBackground(
                new Color(250, 238, 232));
        lbhoadon = new JLabel("Hoá đơn");

        lbhoadon.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 30));
        lbhoadon.setHorizontalAlignment(JLabel.CENTER);

        lbhoadon.setVerticalAlignment(JLabel.CENTER);

        header.add(lbhoadon);
        footer = new JPanel();

        footer.setPreferredSize(
                new Dimension(0, 100));
        footer.setBackground(
                new Color(250, 238, 232));

        btnconfirm = new JButton("Xác nhận HĐ");
        btnconfirm.setFocusable(false);
        btnconfirm.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 17));
        btnconfirm.setPreferredSize(
                new Dimension(150, 50));
        btnconfirm.setBackground(Color.WHITE);

        btnconfirm.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (table1.getSelectedRow() == -1) {
                    return;
                }
                
                
                try {
					if (BUSHoaDon.getInstance().confirmHoaDon(table1.getValueAt(table1.getSelectedRow(), 0).toString()) == false) {
					    return;
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
                loadData();
            }
        }
        );

        footer.add(btnconfirm, BorderLayout.CENTER);

        left1.add(header, BorderLayout.NORTH);

        left1.add(center, BorderLayout.CENTER);

        left1.add(footer, BorderLayout.SOUTH);
        right2 = new JPanel();

        right2.setBackground(
                new Color(250, 238, 232));
        right2.setLayout(
                new BorderLayout(0, 5));

        header2 = new JPanel();

        header2.setPreferredSize(
                new Dimension(0, 200));
        header2.setLayout(
                new BorderLayout(0, 10));
        header2.setBackground(
                new Color(250, 238, 232));

        title2 = new JLabel("Hóa đơn chi tiết");

        title2.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 30));
        title2.setHorizontalAlignment(JLabel.CENTER);

        title2.setVerticalAlignment(JLabel.CENTER);

        title2.setPreferredSize(
                new Dimension(0, 60));

        /////////////////////////////////////////////////////////////////////////
        tablemodel2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodel2.setColumnIdentifiers(columnNameCTHD);
        table2 = new JTable();

        table2.setModel(tablemodel2);
        scrollpane2 = new JScrollPane(table2);

        header2.add(title2, BorderLayout.NORTH);
        //ADD TAble to center of header2

        header2.add(scrollpane2, BorderLayout.CENTER);

        JPanel content = new JPanel();

        content.setLayout(
                new BorderLayout(20, 0));

        center2 = new JPanel();

        center2.setBackground(
                new Color(250, 238, 232));
        center2.setPreferredSize(
                new Dimension(0, 300));
        center2.setLayout(
                new GridLayout(1, 2, 20, 0));

        center2a = new JPanel();

        center2a.setBackground(
                new Color(250, 238, 232));
        center2a.setLayout(
                new BorderLayout());
        center2a.setPreferredSize(
                new Dimension(350, 0));

        north1 = new JPanel();

        north1.setPreferredSize(
                new Dimension(0, 300));
        north1.setLayout(
                new GridLayout(7, 2));
        north1.setBackground(
                new Color(250, 238, 232));

        center2a.add(north1, BorderLayout.NORTH);

        center2b = new JPanel();

        center2b.setBackground(
                new Color(250, 238, 232));
        center2b.setLayout(
                new BorderLayout());

        north2 = new JPanel();

        north2.setBackground(
                new Color(250, 238, 232));
        north2.setPreferredSize(
                new Dimension(0, 300));
        north2.setLayout(
                new GridLayout(2, 1));

        north2up = new JPanel();

        north2up.setLayout(
                new GridLayout(2, 1));
        north2up.setBackground(
                new Color(250, 238, 232));

        north2down = new JPanel();

        north2down.setLayout(
                new GridLayout(4, 2));
        north2down.setBackground(
                new Color(250, 238, 232));

        north2.add(north2up);

        north2.add(north2down);

        lbtrangthai = new JLabel("Trạng thái:");

        lbtrangthai.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 18));
        lbtrangthai.setHorizontalAlignment(JLabel.LEFT);

        kqtrangthai = new JLabel("Chờ xác nhận");

        kqtrangthai.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 30));
        kqtrangthai.setHorizontalAlignment(JLabel.LEFT);

        kqtrangthai.setVerticalAlignment(JLabel.TOP);

        north2up.add(lbtrangthai);

        north2up.add(kqtrangthai);

        lbtongtiensp = new JLabel("Tổng tiền SP:");

        lbtongtiensp.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        lbtongtiensp.setHorizontalAlignment(JLabel.LEFT);

        kqtongtiensp = new JLabel("20000đ");

        kqtongtiensp.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        kqtongtiensp.setHorizontalAlignment(JLabel.RIGHT);

        lbchiphi = new JLabel("Tiền thuế:");

        lbchiphi.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        lbchiphi.setHorizontalAlignment(JLabel.LEFT);

        kqchiphi = new JLabel("0đ");

        kqchiphi.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        kqchiphi.setHorizontalAlignment(JLabel.RIGHT);

        lbtongtien = new JLabel("Tổng tiền HĐ:");

        lbtongtien.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        lbtongtien.setHorizontalAlignment(JLabel.LEFT);

        kqtongtien = new JLabel("20000đ");

        kqtongtien.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 13));
        kqtongtien.setHorizontalAlignment(JLabel.RIGHT);

        north2down.add(lbtongtiensp);

        north2down.add(kqtongtiensp);

        north2down.add(lbchiphi);

        north2down.add(kqchiphi);

        north2down.add(lbtongtien);

        north2down.add(kqtongtien);

        center2b.add(north2, BorderLayout.NORTH);

        lbmahoadon = new JLabel("Mã hoá đơn:");

        lbmahoadon.setHorizontalAlignment(JLabel.LEFT);

        lbkqmahoadon = new JLabel("HD002");

        lbkqmahoadon.setHorizontalAlignment(JLabel.LEFT);
        lbmakhachhang = new JLabel("Khách hàng:");

        lbmakhachhang.setHorizontalAlignment(JLabel.LEFT);

        lbkqmakhachhang = new JLabel("Trần Hoàng Nam");

        lbkqmakhachhang.setHorizontalAlignment(JLabel.LEFT);

        lbsdt = new JLabel("SDT:");

        lbsdt.setHorizontalAlignment(JLabel.LEFT);

        lbkqsdt = new JLabel("0975810314");

        lbkqsdt.setHorizontalAlignment(JLabel.LEFT);

        lbthoigiantao = new JLabel("Thời gian tạo:");

        lbthoigiantao.setHorizontalAlignment(JLabel.LEFT);

        lbkqthoigiantao = new JLabel("25-2-2003");

        lbkqthoigiantao.setHorizontalAlignment(JLabel.LEFT);

        lbthoigianthanhtoan = new JLabel("Thời gian thanh toán:");

        lbthoigianthanhtoan.setHorizontalAlignment(JLabel.LEFT);

        lbkqthoigianthanhtoan = new JLabel("23-2-2003");

        lbkqthoigianthanhtoan.setHorizontalAlignment(JLabel.LEFT);

        lbloai = new JLabel("Loại thanh toán:");

        lbloai.setHorizontalAlignment(JLabel.LEFT);

        lbkqloai = new JLabel("Tiền mặt");

        lbkqloai.setHorizontalAlignment(JLabel.LEFT);

        lbghichu = new JLabel("Ghi chú:");

        lbghichu.setHorizontalAlignment(JLabel.LEFT);

        north1.add(lbmahoadon);

        north1.add(lbkqmahoadon);

        north1.add(lbmakhachhang);

        north1.add(lbkqmakhachhang);

        north1.add(lbsdt);

        north1.add(lbkqsdt);

        north1.add(lbthoigiantao);

        north1.add(lbkqthoigiantao);

        north1.add(lbthoigianthanhtoan);

        north1.add(lbkqthoigianthanhtoan);

        north1.add(lbloai);

        north1.add(lbkqloai);

        north1.add(lbghichu);
        ghichu = new JTextArea();
        ghichu.setEditable(false);
        ghichu.setPreferredSize(
                new Dimension(300, 100));
        ghichu.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        ghichu.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));

        content.add(center2, BorderLayout.NORTH);

        content.add(ghichu, BorderLayout.CENTER);

        right2.add(header2, BorderLayout.NORTH);

        right2.add(content, BorderLayout.CENTER);

        center2.add(center2a);

        center2.add(center2b);

        panel1.add(left1);

        panel1.add(right2);
        /////////////////////////////////////////////////////////////////////////////////////

        panel2.setLayout(
                new GridLayout(2, 1));
        tren = new JPanel();

        tren.setBackground(
                new Color(250, 238, 232));
        tren.setLayout(
                new BorderLayout(0, 20));
        tren1 = new JPanel();

        tren1.setBackground(
                new Color(250, 238, 232));
        tren1.setLayout(
                new GridLayout(1, 4, 10, 0));
        atren1 = new JPanel();

        atren1.setLayout(
                new BorderLayout(5, 0));
        atren1.setBackground(
                new Color(250, 238, 232));
        atren1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        upatren1 = new JPanel();

        upatren1.setBackground(
                new Color(250, 238, 232));
        upatren1.setPreferredSize(
                new Dimension(0, 30));
        upatren1.setLayout(
                new GridLayout(1, 1));

        lbmahoadontren1 = new JLabel("Mã hóa đơn:");

        lbmahoadontren1.setVerticalAlignment(JLabel.CENTER);

        lbmahoadontren1.setHorizontalAlignment(JLabel.LEFT);

        lbmahoadontren1.setPreferredSize(
                new Dimension(100, 10));
        upatren1.add(lbmahoadontren1);
        txtmahoadon = new JTextField(7);
        btnfindmahoadon = new JButton("Tìm");
        btnfindmahoadon.setFocusable(false);
        btnfindmahoadon.setPreferredSize(
                new Dimension(80, 0));
        btnfindmahoadon.setBackground(Color.WHITE);
        btnfindmahoadon.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                if (!txtmahoadon.getText().trim().equals("")) {

                    DTOHoaDon dto = BUSHoaDon.getInstance().getHoaDonByID(txtmahoadon.getText());
                    tablemodel3.setRowCount(0);
                    tablemodel4.setRowCount(0);
                    if (dto != null) {

                        tablemodel3.addRow(new Object[]{dto.getMaHD(), dto.getTenNV(), dto.getThoiGianTao(), dto.getTongGia(), dto.getTrangThai()});
                    } else {
                        JOptionPane.showMessageDialog(null, "Ko tìm thấy ID hoá đơn");
                    }
                    kqmahoadon2.setText("");
                    kqmakhachhang2.setText("");
                    kqsdt2.setText("");
                    kqthoigiantao2.setText("");
                    kqtgthanhtoan2.setText("");
                    kqloaithanhtoan2.setText("");
                    ghichu2.setText("");
                    kqtrangthai2.setText("");

                    kqtongtiensp2.setText("");
                    kqtongtienhd2.setText("");
                    kqchiphikhac2.setText("");
                }

            }
        }
        );
        atren1.add(upatren1, BorderLayout.NORTH);

        atren1.add(txtmahoadon, BorderLayout.CENTER);

        atren1.add(btnfindmahoadon, BorderLayout.EAST);

        btren1 = new JPanel();

        btren1.setBackground(
                new Color(250, 238, 232));
        btren1.setLayout(
                new BorderLayout(5, 0));
        btren1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        upbtren1 = new JPanel();

        upbtren1.setBackground(
                new Color(250, 238, 232));
        upbtren1.setLayout(
                new GridLayout(1, 1));
        upbtren1.setPreferredSize(
                new Dimension(0, 30));
        lbtungay2 = new JLabel("Từ ngày:");

        lbtungay2.setHorizontalAlignment(JLabel.LEFT);

        lbtungay2.setVerticalAlignment(JLabel.CENTER);

        upbtren1.add(lbtungay2);

        datePicker = new JDateChooser();
        Timestamp timestamp = Timestamp.valueOf(now);

        datePicker.setDate(new Date(timestamp.getTime()));

        btren1.add(upbtren1, BorderLayout.NORTH);

        btren1.add(datePicker, BorderLayout.CENTER);

        ctren1 = new JPanel();

        ctren1.setBackground(
                new Color(250, 238, 232));
        ctren1.setLayout(
                new BorderLayout(5, 1));
        ctren1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        upctren1 = new JPanel();

        upctren1.setBackground(
                new Color(250, 238, 232));
        upctren1.setPreferredSize(
                new Dimension(0, 30));
        upctren1.setLayout(
                new GridLayout(1, 1));
        lbdenngay2 = new JLabel("Đến ngày:");

        lbdenngay2.setPreferredSize(
                new Dimension(100, 10));
        lbdenngay2.setVerticalAlignment(JLabel.CENTER);

        lbdenngay2.setHorizontalAlignment(JLabel.LEFT);

        upctren1.add(lbdenngay2);

        datePicker2 = new JDateChooser();
        Timestamp timestamp2 = Timestamp.valueOf(tomorrow);

        datePicker2.setDate(new Date(timestamp2.getTime()));
        ctren1.add(upctren1, BorderLayout.NORTH);

        ctren1.add(datePicker2, BorderLayout.CENTER);
        dtren1 = new JPanel();

        dtren1.setBackground(
                new Color(250, 238, 232));
        dtren1.setLayout(
                new BorderLayout(5, 1));
        dtren1.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 30));
        updtren1 = new JPanel();

        updtren1.setBackground(
                new Color(250, 238, 232));
        updtren1.setPreferredSize(
                new Dimension(0, 30));
        updtren1.setLayout(
                new GridLayout(1, 1));
        lbuptren1trangthai = new JLabel("Trạng thái:");

        lbuptren1trangthai.setHorizontalAlignment(JLabel.LEFT);

        lbuptren1trangthai.setVerticalAlignment(JLabel.CENTER);

        lbuptren1trangthai.setPreferredSize(
                new Dimension(100, 10));
        Vector v = new Vector();
        v.add(0, "Tất cả");
        v.add(1, "Chờ order");
        v.add(2, "Hoàn thành");
        v.add(3, "Chờ xác nhận");
        v.add(4, "Huỷ");
        combobox = new JComboBox(v);
        btnloc = new JButton("Lọc");
        btnloc.setFocusable(false);
        btnloc.setBackground(Color.WHITE);
        btnloc.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                loadData3();
            }
        });

        updtren1.add(lbuptren1trangthai);

        dtren1.add(updtren1, BorderLayout.NORTH);

        dtren1.add(combobox, BorderLayout.CENTER);

        dtren1.add(btnloc, BorderLayout.EAST);

        tren1.add(atren1);

        tren1.add(btren1);

        tren1.add(ctren1);

        tren1.add(dtren1);

////////////////////////////////////////////////////
        tablemodel3 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodel3.setColumnIdentifiers(columnNameHoaDon);
        table3 = new JTable();

        table3.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table3.setModel(tablemodel3);

        table3.addMouseListener(
                new MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e
            ) {
                int row = table3.getSelectedRow();
                loadDaTaCTHD3(row);
            }
        }
        );
        scrollpane3 = new JScrollPane(table3);

        tren2 = new JPanel();

        tren2.setLayout(
                new GridLayout(1, 1));
        tren2.setBackground(
                new Color(250, 238, 232));
        tren2.add(scrollpane3);

        tren.add(tren1, BorderLayout.NORTH);

        tren.add(tren2, BorderLayout.CENTER);
        duoi = new JPanel();

        duoi.setBackground(
                new Color(250, 238, 232));
        duoi.setLayout(
                new BorderLayout());
        duoi1 = new JPanel();

        duoi1.setBackground(
                new Color(250, 238, 232));
        duoi1.setPreferredSize(
                new Dimension(0, 60));
        duoi1.setLayout(
                new BorderLayout());
        duoi1.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 10));
        titleduoi1 = new JLabel("Chi tiết hóa đơn");

        titleduoi1.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 30));
        titleduoi1.setHorizontalAlignment(JLabel.CENTER);

        duoi2 = new JPanel();

        duoi2.setBackground(
                new Color(250, 238, 232));
        duoi2.setLayout(
                new GridLayout(1, 2, 20, 0));

        JPanel content2 = new JPanel();
        content2.setLayout(new BorderLayout(10, 0));

        leftduoi2 = new JPanel();

        leftduoi2.setBackground(
                new Color(250, 238, 232));
        leftduoi2.setLayout(
                new GridLayout(1, 2, 30, 0));
        aleftduoi2 = new JPanel();

        aleftduoi2.setBackground(
                new Color(250, 238, 232));
        aleftduoi2.setLayout(
                new BorderLayout(0, 10));
        upaleftduoi2 = new JPanel();

        upaleftduoi2.setBackground(
                new Color(250, 238, 232));
        upaleftduoi2.setPreferredSize(
                new Dimension(0, 180));//////////////////////////////////
        upaleftduoi2.setLayout(
                new GridLayout(7, 2, 10, 10));
        lbmahoadon2 = new JLabel("Mã HĐ:");

        kqmahoadon2 = new JLabel("HD001");
        lbmakhachhang2 = new JLabel("Khách hàng:");
        kqmakhachhang2 = new JLabel("KH001");
        lbsdt2 = new JLabel("SĐT:");
        kqsdt2 = new JLabel("0975810314");
        lbthoigiantao2 = new JLabel("TG Tạo:");
        kqthoigiantao2 = new JLabel("20-3-2003");
        lbtgthanhtoan2 = new JLabel("TG thanh toán:");
        kqtgthanhtoan2 = new JLabel("20-3-2003");
        lbloaithanhtoan2 = new JLabel("Loại thanh toán:");
        kqloaithanhtoan2 = new JLabel("Tiền mặt");
        lbghichu2 = new JLabel("Ghi chú:");
        downaleftduoi2 = new JPanel();

        downaleftduoi2.setBackground(
                new Color(250, 238, 232));
        downaleftduoi2.setLayout(
                new GridLayout(1, 1));
        ghichu2 = new JTextArea();
        ghichu2.setEditable(false);
        ghichu2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 15));
        ghichu2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        bleftduoi2 = new JPanel();

        bleftduoi2.setBackground(
                new Color(250, 238, 232));
        bleftduoi2.setLayout(
                new BorderLayout(0, 10));

        upbleftduoi2 = new JPanel();

        upbleftduoi2.setBackground(
                new Color(250, 238, 232));
        upbleftduoi2.setPreferredSize(
                new Dimension(0, 180));
        upbleftduoi2.setLayout(
                new GridLayout(2, 1, 0, 10));
        aupbleftduoi2 = new JPanel();

        aupbleftduoi2.setBackground(
                new Color(250, 238, 232));
        aupbleftduoi2.setLayout(
                new GridLayout(2, 1));

        bupbleftduoi2 = new JPanel();

        bupbleftduoi2.setLayout(
                new GridLayout(4, 2, 10, 10));
        bupbleftduoi2.setBackground(
                new Color(250, 238, 232));

        downbleftduoi2 = new JPanel();

        downbleftduoi2.setBackground(
                new Color(250, 238, 232));
        downbleftduoi2.setLayout(
                new GridLayout(1, 1));

        lbtrangthai2 = new JLabel("Trạng thái:");

        lbtrangthai.setHorizontalAlignment(JLabel.LEFT);
        kqtrangthai2 = new JLabel("X");

        kqtrangthai2.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 25));
        kqtrangthai2.setHorizontalAlignment(JLabel.LEFT);

        lbtongtiensp2 = new JLabel("Tổng tiền SP:");

        lbtongtiensp2.setHorizontalAlignment(JLabel.LEFT);
        kqtongtiensp2 = new JLabel("20000đ");

        kqtongtiensp2.setHorizontalAlignment(JLabel.RIGHT);
        lbchiphikhac2 = new JLabel("Tiền thuế:");

        lbchiphikhac2.setHorizontalAlignment(JLabel.LEFT);
        kqchiphikhac2 = new JLabel("0đ");

        kqchiphikhac2.setHorizontalAlignment(JLabel.RIGHT);
        lbtongtienhd2 = new JLabel("Tổng tiền HĐ:");

        lbtongtienhd2.setHorizontalAlignment(JLabel.LEFT);
        kqtongtienhd2 = new JLabel("20000đ");

        kqtongtienhd2.setHorizontalAlignment(JLabel.RIGHT);

        rightduoi2 = new JPanel();

        rightduoi2.setBackground(
                new Color(250, 238, 232));
        rightduoi2.setLayout(
                new GridLayout(2, 1, 0, 10));

        uprightduoi2 = new JPanel();

        uprightduoi2.setBackground(
                new Color(250, 238, 232));
        uprightduoi2.setLayout(
                new GridLayout(1, 1));
        String column2[]
                = {"Hoá đơn", "Tên SP", "Giá", "Số lượng"};

        tablemodel4 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table4 = new JTable();

        table4.setModel(tablemodel4);
        table4.getSelectionModel()
                .setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablemodel4.setColumnIdentifiers(columnNameCTHD);

        scrollpane4 = new JScrollPane(table4);

        uprightduoi2.add(scrollpane4);

        downrightduoi2 = new JPanel();

        downrightduoi2.setBackground(
                new Color(250, 238, 232));
        downrightduoi2.setLayout(
                new FlowLayout(FlowLayout.CENTER));

        btnkhoiphuc = new JButton("Khôi phục HĐ");
        btnkhoiphuc.setFocusable(false);
        btnkhoiphuc.setFont(
                new Font(Font.SANS_SERIF, Font.BOLD, 20));
        btnkhoiphuc.setBackground(Color.WHITE);
        btnkhoiphuc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int row = table3.getSelectedRow();
                if (row == -1) {
                    return;
                }
                if (BUSHoaDon.getInstance().isStateHoanThanh(tablemodel3.getValueAt(row, 0).toString())) {
                    String lydo = null;
                    lydo = JOptionPane.showInputDialog("Lý do");
                    if (lydo == null || lydo.trim().equals("")) {
                        return;
                    }
                    BUSHoaDon.getInstance().recoverHoaDon(tablemodel3.getValueAt(row, 0).toString(), lydo);
                    loadData3();

                }

            }

        });
        
        JButton inhoadon = new JButton("In hoá đơn");
        inhoadon.setPreferredSize(new Dimension(100, 100));
        inhoadon.setBackground(Color.WHITE);
        inhoadon.setFocusable(false);
        inhoadon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table3.getSelectedRow();
                if (row == -1) {
                    return;
                }
                if (BUSHoaDon.getInstance().isStateHoanThanh(tablemodel3.getValueAt(row, 0).toString())) {
                    Modal.DTOHoaDon bill = BUS.BUSHoaDon.getInstance().getHoaDonByID(tablemodel3.getValueAt(table3.getSelectedRow(), 0).toString());
                    java.util.List<Modal.DTOCTHD> cthd = BUS.BUSHoaDon.getInstance().getCTHDByID(tablemodel3.getValueAt(table3.getSelectedRow(), 0).toString());
                    String [][] array = new String[cthd.size()][];
                    for(int i =0 ;i <cthd.size();i++){
                        Modal.DTOCTHD dto = cthd.get(i);
                        array[i] = new String []{dto.getMaSP(),dto.getTenSP(),dto.getDonGia(),dto.getSoLuong(),dto.getThanhTien()};
                    }
                    printPDF(bill.getMaHD(),bill.getThoiGianThanhToan(),bill.getTenKH(),bill.getMaKH(),bill.getMaNV(),bill.getTenNV(),array,""+(Integer.parseInt(bill.getTongGia())-Integer.parseInt(bill.getTienThue())),bill.getTienThue(),bill.getTongGia(),bill.getTienKhachDua(),""+(Integer.parseInt(bill.getTienKhachDua())-Integer.parseInt(bill.getTongGia())));
                    JOptionPane.showMessageDialog(null, "In thành công");
                }
            }

        });
        duoi1.add(titleduoi1, BorderLayout.CENTER);
        duoi1.add(inhoadon, BorderLayout.EAST);
        aleftduoi2.add(upaleftduoi2, BorderLayout.NORTH);

        aleftduoi2.add(downaleftduoi2, BorderLayout.CENTER);

        upbleftduoi2.add(aupbleftduoi2);

        upbleftduoi2.add(bupbleftduoi2);

        aupbleftduoi2.add(lbtrangthai2);

        aupbleftduoi2.add(kqtrangthai2);

        bupbleftduoi2.add(lbtongtiensp2);

        bupbleftduoi2.add(kqtongtiensp2);

        bupbleftduoi2.add(lbchiphikhac2);

        bupbleftduoi2.add(kqchiphikhac2);

        bupbleftduoi2.add(lbtongtienhd2);

        bupbleftduoi2.add(kqtongtienhd2);

        bleftduoi2.add(upbleftduoi2, BorderLayout.NORTH);

        bleftduoi2.add(downbleftduoi2, BorderLayout.CENTER);

        leftduoi2.add(aleftduoi2);

        leftduoi2.add(bleftduoi2);
        content2.add(leftduoi2, BorderLayout.NORTH);

        content2.add(ghichu2);
        downrightduoi2.add(btnkhoiphuc);

        upaleftduoi2.add(lbmahoadon2);

        upaleftduoi2.add(kqmahoadon2);

        upaleftduoi2.add(lbmakhachhang2);

        upaleftduoi2.add(kqmakhachhang2);

        upaleftduoi2.add(lbsdt2);

        upaleftduoi2.add(kqsdt2);

        upaleftduoi2.add(lbthoigiantao2);

        upaleftduoi2.add(kqthoigiantao2);

        upaleftduoi2.add(lbtgthanhtoan2);

        upaleftduoi2.add(kqtgthanhtoan2);

        upaleftduoi2.add(lbloaithanhtoan2);

        upaleftduoi2.add(kqloaithanhtoan2);

        upaleftduoi2.add(lbghichu2);

        rightduoi2.add(uprightduoi2);

        rightduoi2.add(downrightduoi2);

        duoi2.add(content2);

        duoi2.add(rightduoi2);

        ////////////////
        duoi.add(duoi1, BorderLayout.NORTH);

        duoi.add(duoi2, BorderLayout.CENTER);

        panel2.add(tren);

        panel2.add(duoi);

        ///////////////////
        tabbedPane.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                loadData();
                loadData3();
            }
        });

    }
}

