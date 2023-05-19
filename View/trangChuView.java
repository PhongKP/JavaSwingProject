package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BUS.employeeBUS;
import Controll.trangChuControll;
import Modal.EmployeeModal;
import Modal.taiKhoanModal;

public class trangChuView extends JPanel implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton buttonLogOut;
	private JLabel labelTime;
	private JButton buttonMK;
	private JLabel labelTenUser;
	private JLabel labelTenChucVu;
	private JButton buttonGiaoCa;
	private static trangChuView Instance;
	private employeeBUS qlnvBUS;
	private ArrayList<EmployeeModal> dsnv;

	public trangChuView ()
	{
		qlnvBUS = new employeeBUS();
		dsnv = qlnvBUS.getEmployees();
		
		this.setPreferredSize(new Dimension(JFrame.MAXIMIZED_HORIZ - 300,JFrame.MAXIMIZED_VERT - 40));
		this.setLayout(new BorderLayout());
		
		Color color = new Color(222,188,153);
		Color colorHeader = new Color(139,69,19);
		Font font = new Font("Arial", Font.BOLD, 14);
		
		ActionListener ac = new trangChuControll(this);
		
		URL logo_link = LoginView.class.getResource("/img/logo_trangChu.png");
		ImageIcon imageIcon = new ImageIcon(
		new ImageIcon(logo_link).getImage().getScaledInstance(800, 400, Image.SCALE_SMOOTH));
		
		JPanel panelBgHead = new JPanel();
		panelBgHead.setBackground(color);
		panelBgHead.setLayout(null);
		
		JPanel panelHeader = new JPanel();
		panelHeader.setLayout(null);
		buttonGiaoCa = new JButton("Giao Ca");
		buttonGiaoCa.setBounds(30,5,140,30);
		buttonGiaoCa.setFocusable(false);
		buttonGiaoCa.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonGiaoCa.setBackground(Color.WHITE);
		buttonGiaoCa.setBorder(null);
		buttonGiaoCa.setVisible(false);
		buttonGiaoCa.setEnabled(false);
		buttonGiaoCa.addActionListener(ac);
		buttonMK = new JButton("Đổi mật khẩu");
		buttonMK.setBounds(200,5,140,30);
		buttonMK.setFocusable(false);
		buttonMK.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonMK.setBackground(Color.WHITE);
		buttonMK.setBorder(null);
		buttonMK.addActionListener(ac);
		buttonLogOut = new JButton("Đăng Xuất");
		buttonLogOut.setBounds(370,5,140,30);
		buttonLogOut.setFocusable(false);
		buttonLogOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
		buttonLogOut.setBackground(Color.WHITE);
		buttonLogOut.setBorder(null);
		buttonLogOut.addActionListener(ac);
		labelTenUser = new JLabel("");
		labelTenUser.setBounds(550,5,400,30);
		labelTenUser.setFont(font);
		labelTenChucVu = new JLabel("");
		labelTenChucVu.setBounds(624,5,150,30);
		labelTenChucVu.setFont(font);
		labelTime = new JLabel("");
		labelTime.setBounds(1070, 5, 200, 30);
		labelTime.setFont(font);
		panelHeader.setBackground(colorHeader);
		panelHeader.setBounds(15, 0, 1400, 40);
		panelHeader.add(buttonGiaoCa);
		panelHeader.add(buttonMK);
		panelHeader.add(buttonLogOut);
		panelHeader.add(labelTenUser);
		panelHeader.add(labelTenChucVu);
		panelHeader.add(labelTime);
		
		panelBgHead.add(panelHeader);
		
		JPanel panelSouth = new JPanel();
		panelSouth.setPreferredSize(new Dimension(300,750));
		panelSouth.setBackground(color);
		panelSouth.setLayout(null);
		JLabel labelImage = new JLabel();
		labelImage.setBounds(260, 200, 600, 300);
		labelImage.setIcon(imageIcon);
		panelSouth.add(labelImage);
		
		Thread thread = new Thread(this);
		thread.start();
		
		taiKhoanModal tk = LoginView.getInstance().getTk();
		if (tk != null)
		{
			setInfor(getTenUser(tk.getUserName()), tk.getRole());
		}
		
		checkGiaoCa();
		
		this.add(panelBgHead,BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		
	}
	
	public static trangChuView getInstance ()
	{
		if (Instance == null)
		{
			Instance = new trangChuView();
		}
		return Instance;
	}

	@Override
	public void run() {
		while (true)
		{
			long curTime = System.currentTimeMillis();
			String curTimeStr = String.format("%tT %td-%tm-%tY", curTime,curTime,curTime,curTime);
			labelTime.setText(curTimeStr);
//			System.out.println(curTimeStr);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void setInfor (String userName, String role)
	{
		this.labelTenUser.setText(userName+"-");
		this.labelTenChucVu.setText(" "+role);
	}
	
	public String getTenUser (String manv)
	{
		for (EmployeeModal nv : dsnv)
		{
			if (nv.getEmployee_id().equals(manv))
			{
				return nv.getEmployee_name();
			}
		}
		return null;
	}
	
	public void checkGiaoCa ()
	{
		String role = LoginView.getInstance().getTk().getRole();
		taiKhoanModal tk = LoginView.getInstance().getTk();
		if (!role.equals("admin"))
		{
			buttonGiaoCa.setVisible(true);
			buttonGiaoCa.setEnabled(true);
		}
		else
		{
			buttonMK.setBounds(30,5,140,30);
			buttonLogOut.setBounds(200,5,140,30);
			labelTenUser.setBounds(360,5,400,30);
			labelTenChucVu.setBounds(464,5,150,30);
		}
	}
	
//	public static void main(String[] args) {
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		trangChuView view = new trangChuView();
//		frame.add(view);
//		frame.setVisible(true);
//	}
	
}
