package View;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BUS.BUSHoaDon;
import BUS.QLTKBUS;
import BUS.employeeBUS;
import BUS.giaoCaBUS;
import Modal.EmployeeModal;
import Modal.giaoCaModal;
import Modal.taiKhoanModal;

public class giaoCaView extends JFrame{
	
	private static giaoCaView Instance;
	private ArrayList<giaoCaModal> dsgc;
	private giaoCaBUS qlgcBUS;
	private BUSHoaDon qlhdBUS;
	private hoaDonView viewHD;
	
	public static giaoCaView getInstance()
	{
		if (Instance == null)
		{
			Instance = new giaoCaView();
		}
		return Instance;
	}

	private JButton cancelBtn;
	private JLabel label_maCa_res;
	private JLabel label_TGBD_res;
	private JLabel label_TGKT_res;
	private JLabel label_tienBD_res;
	private JLabel label_tienDT_res;
	private JLabel label_tongTien_res;
	private NumberFormat format_money = NumberFormat.getInstance();
	private Locale local_VN = new Locale("vi","VN");
	private JButton comfirmBtn;
	
	public void display ()
	{
		this.setVisible(true);
	}
	
	public giaoCaView ()
	{
		setSize(600,500);
		setTitle("Giao ca");
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout());
		
		qlgcBUS = new giaoCaBUS();
		dsgc = qlgcBUS.getDSGC();
		qlhdBUS = new BUSHoaDon();
		viewHD = new hoaDonView();
		
		format_money.setCurrency(Currency.getInstance(local_VN));
		
		JPanel panelHeader = new JPanel();
		panelHeader.setPreferredSize(new Dimension(600,35));
		
		JPanel panelTop = new JPanel();
		panelTop.setLayout(null);
		panelTop.setPreferredSize(new Dimension(600,400));
		
		JPanel panelBottom = new JPanel();
		panelBottom.setLayout(new FlowLayout());
		panelBottom.setPreferredSize(new Dimension(600,100));
		
		Font fontHeader = new Font("Arial",Font.BOLD,30);
		Font fontLabel = new Font("Arial",Font.PLAIN,14);
		Font fontLabelBold = new Font("Arial",Font.BOLD,14);
		
		JLabel header = new JLabel("GIAO CA",JLabel.CENTER);
		header.setFont(fontHeader);
		panelHeader.add(header);
		
		JLabel label_maCa = new JLabel("Mã ca: ");
		label_maCa.setFont(fontLabel);
		label_maCa.setBounds(100, 50, 150, 30);
		label_maCa_res = new JLabel("");
		label_maCa_res.setFont(fontLabelBold);
		label_maCa_res.setBounds(350, 50, 150, 30);
		JLabel label_TGBD = new JLabel("Thời gian bắt đầu: ");
		label_TGBD.setFont(fontLabel);
		label_TGBD.setBounds(100, 100, 150, 30);
		label_TGBD_res = new JLabel("");
		label_TGBD_res.setFont(fontLabelBold);
		label_TGBD_res.setBounds(350, 100, 150, 30);
		JLabel label_TGKT = new JLabel("Thời gian kết thúc: ");
		label_TGKT.setFont(fontLabel);
		label_TGKT.setBounds(100, 150, 150, 30);
		label_TGKT_res = new JLabel("");
		label_TGKT_res.setFont(fontLabelBold);
		label_TGKT_res.setBounds(350, 150, 150, 30);
		JLabel label_tienBD = new JLabel("Tiền ban đầu: ");
		label_tienBD.setFont(fontLabel);
		label_tienBD.setBounds(100, 200, 150, 30);
		label_tienBD_res = new JLabel("");
		label_tienBD_res.setFont(fontLabelBold);
		label_tienBD_res.setBounds(350, 200, 150, 30);
		JLabel label_tienDT = new JLabel("Tiền doanh thu: ");
		label_tienDT.setFont(fontLabel);
		label_tienDT.setBounds(100, 250, 150, 30);
		label_tienDT_res = new JLabel("0 VNĐ");
		label_tienDT_res.setFont(fontLabelBold);
		label_tienDT_res.setBounds(350, 250, 150, 30);
		JLabel label_tongTien = new JLabel("Tổng tiền: ");
		label_tongTien.setFont(fontLabel);
		label_tongTien.setBounds(100, 300, 150, 30);
		label_tongTien_res = new JLabel("");
		label_tongTien_res.setFont(fontLabelBold);
		label_tongTien_res.setBounds(350, 300, 150, 30);
		panelTop.add(label_maCa);
		panelTop.add(label_maCa_res);
		panelTop.add(label_TGBD);
		panelTop.add(label_TGBD_res);
		panelTop.add(label_TGKT);
		panelTop.add(label_TGKT_res);
		panelTop.add(label_tienBD);
		panelTop.add(label_tienBD_res);
		panelTop.add(label_tienDT);
		panelTop.add(label_tienDT_res);
		panelTop.add(label_tongTien);
		panelTop.add(label_tongTien_res);
		
		comfirmBtn = new JButton("Kết ca");
		comfirmBtn.setPreferredSize(new Dimension(100,40));
		comfirmBtn.setFocusable(false);
		comfirmBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelBtn = new JButton("Hủy");
		cancelBtn.setPreferredSize(new Dimension(100,40));
		cancelBtn.setFocusable(false);
		cancelBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panelBottom.add(comfirmBtn);
		panelBottom.add(cancelBtn);
		
		comfirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String maca = xacNhanCaView.getInstance().getSelectedMaca();
				Date currenDate = new Date();
				boolean success = qlgcBUS.updateNgay(currenDate,maca);
				if (success)
				{
					updateTienDT();
					giaoCaView.getInstance().dispose();
					mainFrameView.getInstance().dispose();
					LoginView loginView = new LoginView();
					loginView.setVisible(true);
					LoginView.setInstance(loginView);
				}
				else
				{
					JOptionPane.showMessageDialog(giaoCaView.getInstance(),
												  "Error 700: Lỗi không thể reset ca",
												  "Error",
												  JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		cancelBtn.addActionListener((e)->{
			this.dispose();
		});
		
		setLabelCa();
		setLabelTGBD();
		setLabelTGKT();
		setLabelTienBD();
		updateTienDT();
		setLabelTongTien();
		
		this.add(panelHeader,BorderLayout.NORTH);
		this.add(panelTop,BorderLayout.CENTER);
		this.add(panelBottom,BorderLayout.SOUTH);
		setVisible(false);
	}
	
	public void setLabelCa ()
	{
		String maca = xacNhanCaView.getInstance().getSelectedMaca();
		label_maCa_res.setText(maca);
	}
	
	public void setLabelTGBD ()
	{
		String maca = xacNhanCaView.getInstance().getSelectedMaca();
		String gioBD = qlgcBUS.getGioBDFormat(maca);
		label_TGBD_res.setText(gioBD);
	}
	
	public void setLabelTGKT ()
	{
		String maca = xacNhanCaView.getInstance().getSelectedMaca();
		String gioKT = qlgcBUS.getGioKTFormat(maca);
		label_TGKT_res.setText(gioKT);
	}
	
	public void setLabelTienDT (String money)
	{
//		String manv = LoginView.getInstance().getTk().getUserName();
//		String tienDTNotFormat = qlgcBUS.getTienDT(manv);
//		Double numberOftienDT = Double.valueOf(tienDTNotFormat);
//		String tienDT = format_money.format(numberOftienDT);
		if (money != null)
		{
			Double numberOftienDT = Double.valueOf(money);
			String tienDT = format_money.format(numberOftienDT);
			label_tienDT_res.setText(tienDT);
		}
		else
		{
			label_tienDT_res.setText("0 VNĐ");
		}
	}
	
	public void setLabelTienBD ()
	{
//		String manv = LoginView.getInstance().getTk().getUserName();
//		String tienBDNotFormat = qlgcBUS.getTienBD(manv);
//		Double numberOftienBD = Double.valueOf(tienBDNotFormat);
//		String tienBD = format_money.format(numberOftienBD);
		label_tienBD_res.setText("1.000.000 VNĐ");
	}
	
	public void setLabelTongTien ()
	{
		String maca = xacNhanCaView.getInstance().getSelectedMaca();
		String tongTienNotFormat = qlgcBUS.getTongTien(maca);
		Double numberOfTongTien = Double.valueOf(tongTienNotFormat);
		String tongTien = format_money.format(numberOfTongTien);
//		System.out.println(tienBD);
		label_tongTien_res.setText(tongTien+" VNĐ");
	}
	
	
	public void updateTienDT ()
	{
//		String mahd = viewHD.getmaHDHT();
//		boolean trangthai = qlhdBUS.isStateHoanThanh(mahd);
//		if (trangthai)
//		{
//			String maca = getMaca();
//			String tienDT = qlhdBUS.getTongTien(maca);
//			qlgcBUS.updateTienDT(tienDT, maca);
//			setLabelTienDT();
//		}
		String maca = xacNhanCaView.getInstance().getSelectedMaca();
		Timestamp startTime = Timestamp.valueOf(qlgcBUS.getStartTime(maca).atDate(LocalDate.now()));
		Timestamp endTime = Timestamp.valueOf(qlgcBUS.getEndTime(maca).atDate(LocalDate.now()));
		String mahd = qlhdBUS.getIDHDHT(startTime, endTime);
		System.out.println(mahd);
		boolean state = qlhdBUS.isStateHoanThanh(mahd);
		System.out.println(state);
		if (state)
		{
			String tienDT = qlhdBUS.getTongTien("Hoàn thành",mahd);
			qlgcBUS.updateTienDT(tienDT,maca);
			setLabelTienDT(tienDT);
		}
	}
}
