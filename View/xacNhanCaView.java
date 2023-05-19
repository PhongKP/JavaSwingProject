package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BUS.giaoCaBUS;
import Check.CheckLoi;
import Modal.taiKhoanModal;

public class xacNhanCaView extends JFrame{
	
	private static xacNhanCaView Instance;
	private JButton btn_accept;
	private JButton btn_cancel;
	private ArrayList<String> listmaca;
	private giaoCaBUS qlgcBUS;
	private JLabel label_resTienBD;
	private JComboBox<String> combobox_Maca;
	
	public xacNhanCaView ()
	{
		setTitle("Coffee");
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(null);
		
		qlgcBUS = new giaoCaBUS();
		listmaca = qlgcBUS.getAllMacaBymanv(LoginView.getInstance().getTk().getUserName());
		
		Color color_background = new Color(250, 240, 235);
		
		Font fontHeader = new Font("Arial",Font.BOLD,22);
		Font fontLabelNotBold = new Font("Arial",Font.PLAIN,16);
		Font fontLabelBold = new Font("Arial",Font.BOLD,16);
		
		JPanel panel_Header = new JPanel();
		panel_Header.setBackground(color_background);
		panel_Header.setBounds(0,0,600,100);
		panel_Header.setLayout(null);
		JLabel label_Header = new JLabel("Xác nhận ca",JLabel.CENTER);
		label_Header.setFont(fontHeader);
		label_Header.setBounds(230, 10, 150, 50);
		panel_Header.add(label_Header);
		
		JPanel panel_body = new JPanel();
		panel_body.setLayout(null);
		
		DefaultComboBoxModel model = new DefaultComboBoxModel();
		model.addAll(listmaca);
		
		JLabel label_maca = new JLabel("Mã ca: ");
		label_maca.setFont(fontLabelNotBold);
		label_maca.setBounds(150, 50, 100, 50);
		combobox_Maca = new JComboBox<String>();
		combobox_Maca.setFont(fontLabelBold);
		combobox_Maca.setBounds(250, 50, 200, 50);
		combobox_Maca.setModel(model);
		JLabel label_tienBD = new JLabel("Tiền ban đầu: ");
		label_tienBD.setFont(fontLabelNotBold);
		label_tienBD.setBounds(142, 150, 100, 50);
		label_resTienBD = new JLabel("");
		label_resTienBD.setFont(fontLabelBold);
		label_resTienBD.setBounds(250, 150, 150, 50);
		panel_body.add(label_maca);
		panel_body.add(combobox_Maca);
		panel_body.add(label_tienBD);
		panel_body.add(label_resTienBD);
		
		btn_accept = new JButton("Xác nhận");
		btn_accept.setBounds(140,250,120,60);
		btn_cancel = new JButton("Hủy");
		btn_cancel.setBounds(320,250,120,60);
		panel_body.add(btn_accept);
		panel_body.add(btn_cancel);
		
		
		btn_accept.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				show_XacNhanCa();
				xacNhanCaView.getInstance().dispose();
			}
		});
		
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				xacNhanCaView.getInstance().dispose();
				LoginView loginView = new LoginView();
				loginView.setVisible(true);
				LoginView.setInstance(loginView);
			}
		});
		
		panel_body.setBackground(color_background);
		panel_body.setBounds(0,40, 600, 400);
		
		setTienBD();
		
		this.add(panel_Header);
		this.add(panel_body);
		setVisible(false);
	}
	
	public static xacNhanCaView getInstance ()
	{
		if (Instance == null)
		{
			Instance = new xacNhanCaView();
		}
		return Instance;
	}
	
	public void display()
	{
		setVisible(true);
	}
	
	public void show_XacNhanCa ()
	{
		mainFrameView.getInstance().disable();
		mainFrameView.setDSCN(CheckLoi.getListOfRole(LoginView.getInstance().getTk().getUserName()));
		mainFrameView main = new mainFrameView();
		main.setVisible(true);
		mainFrameView.setInstance(main);
	}
	
	public void setTienBD ()
	{
		
		label_resTienBD.setText("1.000.000 VNĐ");
	}
	
	public String getSelectedMaca ()
	{
		return (String) combobox_Maca.getSelectedItem();
	}
	
//	public static void main(String[] args) {
//		new xacNhanCaView();
//	}
	
}
