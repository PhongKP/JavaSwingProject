package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Controll.mainFrameControll;

public class mainFrameView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JButton nhanVien;
	public JButton thongKe;
	public JButton sanPham;
	public JButton hoaDon;
	public JButton banHang;
	public JButton trangChu;
	public JButton quyen;
	public JButton qlQuyen;
	public JButton qlNcc;
	public Color colorleft;
	public JPanel panelright;
	private Color colorright;
	private JButton qlnl;
	public static boolean isUser;
	public static ArrayList<String> dsCN = new ArrayList<>();
	private static mainFrameView instance;

	public mainFrameView ()
	{
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		
		MouseListener msl = new mainFrameControll(this);
		ActionListener ac = new mainFrameControll(this);
		
		colorleft = new Color(139,69,19);
		colorright = new Color(222,188,153);
		URL logo_link = LoginView.class.getResource("/img/logo_left.png");
		URL iconHome_link = LoginView.class.getResource("/img/home.png");
		URL iconShopping_link = LoginView.class.getResource("/img/shopping-cart.png");
		URL iconBill_link = LoginView.class.getResource("/img/hoadon.png");
		URL iconStatistics_link = LoginView.class.getResource("/img/thongke.png");
		URL iconEmployee_link = LoginView.class.getResource("/img/nhanvien.png");
		URL iconProduct_link = LoginView.class.getResource("/img/market.png");
		URL iconRole_link = LoginView.class.getResource("/img/role.jpg");
		URL iconRoleManager_link = LoginView.class.getResource("/img/role_manager.jpg");
		URL iconProvide_link = LoginView.class.getResource("/img/khachHang.png");
		URL iconNguyenLieu_link = LoginView.class.getResource("/img/logo.png");
		Font font = new Font("Arial",Font.BOLD,17);
		
		JPanel panelleft = new JPanel();
		panelleft.setPreferredSize(new Dimension(300,JFrame.MAXIMIZED_VERT));
		panelleft.setBackground(colorleft);
		panelleft.setLayout(new BorderLayout());
		
		JPanel panelleft_top = new JPanel();
		panelleft_top.setPreferredSize(new Dimension(300,150));
		panelleft_top.setBackground(colorleft);
		
		JPanel panelleft_bottom = new JPanel();
		panelleft_bottom.setPreferredSize(new Dimension(300,JFrame.MAXIMIZED_VERT - 300 ));
		panelleft_bottom.setBackground(colorleft);
		
		panelright = new JPanel();
		panelright.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ-300,JFrame.MAXIMIZED_VERT));
		panelright.setBackground(colorright);
		panelright.setLayout(new BorderLayout());
		
		// Chèn Logo vào bên trái main
		ImageIcon imageIcon = new ImageIcon(
		new ImageIcon(logo_link).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
		JLabel label_logo_left = new JLabel();
		label_logo_left.setIcon(imageIcon);
		panelleft_top.add(label_logo_left);
		
		ImageIcon iconHome = new ImageIcon(
				new ImageIcon(iconHome_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		trangChu = new JButton("Trang Chủ");
		trangChu.setAlignmentX(Component.LEFT_ALIGNMENT);
		trangChu.setIcon(iconHome);
		trangChu.setPreferredSize(new Dimension(300,65));
		trangChu.setFont(font);
		trangChu.setBackground(colorleft);
		trangChu.setForeground(Color.white);
		trangChu.setFocusable(false);
		trangChu.addMouseListener(msl);
		trangChu.addActionListener(ac);
		trangChu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		trangChu.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		panelleft_bottom.add(trangChu);
		
		ImageIcon iconShopping = new ImageIcon(
				new ImageIcon(iconShopping_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		banHang = new JButton("Bán Hàng");
		banHang.setAlignmentX(Component.LEFT_ALIGNMENT);
		banHang.setIcon(iconShopping);
		banHang.setPreferredSize(new Dimension(300,65));
		banHang.setFont(font);
		banHang.setBackground(colorleft);
		banHang.setForeground(Color.white);
		banHang.setFocusable(false);
		banHang.setCursor(new Cursor(Cursor.HAND_CURSOR));
		banHang.addMouseListener(msl);
		banHang.addActionListener(ac);
		banHang.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		banHang.setVisible(false);
		banHang.setEnabled(false);
		panelleft_bottom.add(banHang);
		
		ImageIcon iconBill = new ImageIcon(
				new ImageIcon(iconBill_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		hoaDon = new JButton("Hóa Đơn");
		hoaDon.setAlignmentX(Component.LEFT_ALIGNMENT);
		hoaDon.setIcon(iconBill);
		hoaDon.setPreferredSize(new Dimension(300,65));
		hoaDon.setFont(font);
		hoaDon.setBackground(colorleft);
		hoaDon.setForeground(Color.white);
		hoaDon.setFocusable(false);
		hoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
		hoaDon.addMouseListener(msl);
		hoaDon.addActionListener(ac);
		hoaDon.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		hoaDon.setVisible(false);
		hoaDon.setEnabled(false);
		panelleft_bottom.add(hoaDon);
		
		ImageIcon iconProduct = new ImageIcon(
				new ImageIcon(iconProduct_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		sanPham = new JButton("Sản Phẩm");
		sanPham.setAlignmentX(Component.LEFT_ALIGNMENT);
		sanPham.setIcon(iconProduct);
		sanPham.setPreferredSize(new Dimension(300,65));
		sanPham.setFont(font);
		sanPham.setBackground(colorleft);
		sanPham.setForeground(Color.white);
		sanPham.setFocusable(false);
		sanPham.setCursor(new Cursor(Cursor.HAND_CURSOR));
		sanPham.addMouseListener(msl);
		sanPham.addActionListener(ac);
		sanPham.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		sanPham.setVisible(false);
		sanPham.setEnabled(false);
		panelleft_bottom.add(sanPham);
		
		ImageIcon iconthongKe = new ImageIcon(
				new ImageIcon(iconStatistics_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		thongKe = new JButton("Thống Kê");
		thongKe.setAlignmentX(Component.LEFT_ALIGNMENT);
		thongKe.setIcon(iconthongKe);
		thongKe.setPreferredSize(new Dimension(300,65));
		thongKe.setFont(font);
		thongKe.setBackground(colorleft);
		thongKe.setForeground(Color.white);
		thongKe.setFocusable(false);
		thongKe.setCursor(new Cursor(Cursor.HAND_CURSOR));
		thongKe.addMouseListener(msl);
		thongKe.addActionListener(ac);
		thongKe.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		thongKe.setVisible(false);
		thongKe.setEnabled(false);
		panelleft_bottom.add(thongKe);
		
		ImageIcon iconEmployee = new ImageIcon(
				new ImageIcon(iconEmployee_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		nhanVien = new JButton("Nhân Viên");
		nhanVien.setAlignmentX(Component.LEFT_ALIGNMENT);
		nhanVien.setIcon(iconEmployee);
		nhanVien.setPreferredSize(new Dimension(300,65));
		nhanVien.setFont(font);
		nhanVien.setBackground(colorleft);
		nhanVien.setForeground(Color.white);
		nhanVien.setFocusable(false);
		nhanVien.setCursor(new Cursor(Cursor.HAND_CURSOR));
		nhanVien.addMouseListener(msl);
		nhanVien.addActionListener(ac);
		nhanVien.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		nhanVien.setVisible(false);
		nhanVien.setEnabled(false);
		panelleft_bottom.add(nhanVien);
		
		ImageIcon iconRole = new ImageIcon(
				new ImageIcon(iconRole_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		quyen = new JButton("Tài Khoản");
		quyen.setAlignmentX(Component.LEFT_ALIGNMENT);
		quyen.setIcon(iconRole);
		quyen.setPreferredSize(new Dimension(300,65));
		quyen.setFont(font);
		quyen.setBackground(colorleft);
		quyen.setForeground(Color.white);
		quyen.setFocusable(false);
		quyen.setCursor(new Cursor(Cursor.HAND_CURSOR));
		quyen.addMouseListener(msl);
		quyen.addActionListener(ac);
		quyen.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		quyen.setVisible(false);
		quyen.setEnabled(false);
		panelleft_bottom.add(quyen);
		
		ImageIcon iconRole_Manager = new ImageIcon(
				new ImageIcon(iconRoleManager_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		qlQuyen = new JButton("Các Quyền");
		qlQuyen.setAlignmentX(Component.LEFT_ALIGNMENT);
		qlQuyen.setIcon(iconRole_Manager);
		qlQuyen.setPreferredSize(new Dimension(300,65));
		qlQuyen.setFont(font);
		qlQuyen.setBackground(colorleft);
		qlQuyen.setForeground(Color.white);
		qlQuyen.setFocusable(false);
		qlQuyen.setCursor(new Cursor(Cursor.HAND_CURSOR));
		qlQuyen.addMouseListener(msl);
		qlQuyen.addActionListener(ac);
		qlQuyen.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		qlQuyen.setVisible(false);
		qlQuyen.setEnabled(false);
		panelleft_bottom.add(qlQuyen);
		
		ImageIcon iconNhaCC = new ImageIcon(
				new ImageIcon(iconProvide_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		qlNcc = new JButton("Kho Hàng");
		qlNcc.setAlignmentX(Component.LEFT_ALIGNMENT);
		qlNcc.setIcon(iconNhaCC);
		qlNcc.setPreferredSize(new Dimension(300,65));
		qlNcc.setFont(font);
		qlNcc.setBackground(colorleft);
		qlNcc.setForeground(Color.white);
		qlNcc.setFocusable(false);
		qlNcc.setCursor(new Cursor(Cursor.HAND_CURSOR));
		qlNcc.addMouseListener(msl);
		qlNcc.addActionListener(ac);
		qlNcc.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		qlNcc.setVisible(false);
		qlNcc.setEnabled(false);
		panelleft_bottom.add(qlNcc);
		
		ImageIcon iconNguyenLieu = new ImageIcon(
				new ImageIcon(iconNguyenLieu_link).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		qlnl = new JButton("Nguyên Liệu");
		qlnl.setAlignmentX(Component.LEFT_ALIGNMENT);
		qlnl.setIcon(iconNguyenLieu);
		qlnl.setPreferredSize(new Dimension(300,80));
		qlnl.setFont(font);
		qlnl.setBackground(colorleft);
		qlnl.setForeground(Color.white);
		qlnl.setFocusable(false);
		qlnl.setCursor(new Cursor(Cursor.HAND_CURSOR));
		qlnl.addMouseListener(msl);
		qlnl.addActionListener(ac);
		qlnl.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		qlnl.setVisible(false);
		qlnl.setEnabled(false);
		panelleft_bottom.add(qlnl);
		
		panelleft.add(panelleft_top,BorderLayout.NORTH);
		panelleft.add(panelleft_bottom,BorderLayout.CENTER);
		this.add(panelleft,BorderLayout.WEST);
		this.add(panelright,BorderLayout.CENTER);
		this.setVisible(false);
		
		anChucNang();
	}
	
	public void setBG (JButton btn)
	{
		btn.setBackground(new Color(222,184,135));
	}
	
	public void setDefaultBG (JButton btn)
	{
		btn.setBackground(colorleft);
	}
	
	public void addPanel (JPanel panel)
	{
		panelright.add(panel, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	public static mainFrameView getInstance ()
	{
		if (instance == null)
		{
			instance = new mainFrameView();
		}
		return instance;
	}
	
	public void disable ()
	{
		this.setVisible(false);
	}
	
	public void hien() {
		this.setVisible(true);
	}
	
	public static void setIsUser (boolean value)
	{
		isUser = value;
	}
	
	public static void setDSCN (ArrayList<String> list)
	{
		dsCN = list;
	}
		
	public void anChucNang ()
	{
//		System.out.println(isUser);
		for (String s : dsCN)
		{
//			System.out.println(s);
			switch (s)
			{
				case "CN01" -> {
					banHang.setEnabled(true);
					banHang.setVisible(true);
				}
				case "CN02" -> {
					hoaDon.setEnabled(true);
					hoaDon.setVisible(true);
				}
				case "CN03" -> {
					sanPham.setEnabled(true);
					sanPham.setVisible(true);
				}
				case "CN04" -> {
					thongKe.setEnabled(true);
					thongKe.setVisible(true);
				}
				case "CN05" -> {
					nhanVien.setEnabled(true);
					nhanVien.setVisible(true);
				}
				case "CN06" ->
				{
					quyen.setEnabled(true);
					quyen.setVisible(true);
				}
				case "CN07" ->
				{
					qlQuyen.setEnabled(true);
					qlQuyen.setVisible(true);
				}
				case "CN08" ->
				{
					qlNcc.setEnabled(true);
					qlNcc.setVisible(true);
				}
				case "CN09" ->
				{
					qlnl.setEnabled(true);
					qlnl.setVisible(true);
				}
			}
		}
	}
	
	
	public static void setInstance (mainFrameView Instance)
	{
		mainFrameView.instance = Instance;
	}
	
//	public static void main(String[] args) {
//		new mainFrameView();
//	}
}
