package View;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import Check.CheckLoi;
import Modal.taiKhoanModal;

public class LoginView extends JFrame{
	/**
	 * 
	 */
	private static LoginView instance ;
	public static LoginView getInstance() {
		if (instance==null) {
			instance = new LoginView();
		}
		return instance;
	}
	
	 
	private static final long serialVersionUID = 1L;
	private Container container;
	private JLabel userLabel;
	private JLabel passwordLabel;
	private JTextField userTextField;
	private JPasswordField passwordField;
	private JButton loginButton;	
	private JButton cancelButton;
	private JCheckBox showPassword;
	private Color originalButton = new Color(220, 220, 220);
	private taiKhoanModal tk;
	
	public LoginView(){
		//
		container = getContentPane();
		container.setLayout(null);
		URL logo_link = LoginView.class.getResource("/img/login_logo.png");
		Font font = new Font("Arial",Font.BOLD,12);
		
		// Logo
		JLabel label_logo = new JLabel();
		ImageIcon imageIcon = new ImageIcon(
		new ImageIcon(logo_link).getImage().getScaledInstance(160, 140, Image.SCALE_SMOOTH));
		label_logo.setIcon(imageIcon);
		showPassword = new JCheckBox("Hiển thị mật khẩu");
		userLabel = new JLabel("Tên đăng nhập");
		passwordLabel = new JLabel("Mật khẩu");
		userTextField = new JTextField();
		passwordField = new JPasswordField();
		loginButton = new JButton("Đăng nhập");
		cancelButton = new JButton("Thoát");
		
		Font fontItalic = showPassword.getFont();
		Map<TextAttribute, Object> attributes = new HashMap<>(fontItalic.getAttributes());
		attributes.put(TextAttribute.UNDERLINE,TextAttribute.UNDERLINE_ON);
		label_logo.setBounds(220, 22, 160, 140);
		userLabel.setBounds(165, 170, 100, 25);
		userLabel.setFont(font);
		userTextField.setBounds(265, 170, 150, 25);
		passwordLabel.setBounds(165, 205, 100, 25);
		passwordLabel.setFont(font);
		passwordField.setBounds(265, 205, 150, 25);
		loginButton.setBounds(165, 275, 110, 30);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setBorder(null);
		cancelButton.setBounds(305, 275, 110, 30);
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.setBorder(null);
		showPassword.setBounds(292,235,300,30);
		showPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		showPassword.setFont(font);
		showPassword.setBackground(new Color(153,179,157));
		loginButton.setBackground(originalButton);
		loginButton.setFocusable(false);
		loginButton.setForeground(Color.RED);
		cancelButton.setBackground(originalButton);
		cancelButton.setFocusable(false);

		container.add(label_logo);
		container.add(userLabel);
		container.add(passwordLabel);
		container.add(userTextField);
		container.add(passwordField);
		container.add(loginButton);
		container.add(cancelButton);
		container.add(showPassword);

		
		showPassword.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1)
					passwordField.setEchoChar((char)0);
				else
					passwordField.setEchoChar('*');
			}
		});
		
		getRootPane().setDefaultButton(loginButton);
			
		addActionEvent();
		init();
	}

	public void init() {
		this.setTitle("Đăng nhập");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);
		container.setBackground(new Color(153, 179, 157));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public void addActionEvent() {
		
		
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == loginButton)
				{
					if (userTextField.getText().length() == 0 && passwordField.getPassword().length == 0)
					{
						JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 507: Tài khoản và mật khẩu không được để trống",
																			   "Error",
																			   JOptionPane.ERROR_MESSAGE);
					}
					else if (userTextField.getText().length() == 0)
					{
						JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 505: Tài khoản không được để trống",
																			   "Error",
																			   JOptionPane.ERROR_MESSAGE);
					}
					else if (userTextField.getText().length() > 6)
					{
						JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 508: Độ dài của tài khoản không được quá 6 kí tự",
																			   "Error",
																			   JOptionPane.ERROR_MESSAGE);
					}
					else if (passwordField.getPassword().length == 0)
					{
						JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 506: Mật khẩu không được để trống",
																				"Error",
																				JOptionPane.ERROR_MESSAGE);
					}
					else if (userTextField.getText().length() > 0 && passwordField.getPassword().length > 0)
					{
						char [] passArray = passwordField.getPassword();
						String pass = new String(passArray);
						String userName = userTextField.getText();
						userName = userName.substring(0,1).toUpperCase() + userName.substring(1);
						if (CheckLoi.checkUsername(userTextField.getText()) && CheckLoi.checkPassword(pass,userName) && !CheckLoi.checkRole(userTextField.getText()))
						{
							tk = new taiKhoanModal(userName,pass,CheckLoi.getTenQuyen(userName));
//							mainFrameView.getInstance().disable();
//							mainFrameView.setDSCN(CheckLoi.getListOfRole(userName));
//							mainFrameView main = new mainFrameView();
//							main.setVisible(true);
//							mainFrameView.setInstance(main);
							LoginView.getInstance().dispose();
							xacNhanCaView.getInstance().display();
						}
						else if (CheckLoi.checkUsername(userTextField.getText()) && CheckLoi.checkPassword(pass,userName) && CheckLoi.checkRole(userTextField.getText()))
						{
							tk = new taiKhoanModal(userName,pass,CheckLoi.getTenQuyen(userName));
							mainFrameView.getInstance().disable();
							mainFrameView.setDSCN(CheckLoi.getListOfRole(userName));
							mainFrameView main = new mainFrameView();
							main.setVisible(true);
							mainFrameView.setInstance(main);
							LoginView.getInstance().dispose();
						}
						else
						{
							JOptionPane.showMessageDialog(LoginView.getInstance(),
										"Error 404: Username or Password is invalid",
										"Error",
										JOptionPane.ERROR_MESSAGE);
						}
					}
				}
			}
		});
		
		loginButton.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent e) {
				loginButton.setBackground(new Color(16, 78, 139));
				loginButton.setForeground(Color.red);
			}

			public void mouseEntered(MouseEvent e) {
				loginButton.setBackground(new Color(16, 78, 139));
				loginButton.setForeground(Color.red);
			}

			public void mousePressed(MouseEvent e) {
				loginButton.setBackground(new Color(16, 78, 139));
				loginButton.setForeground(Color.red);
				
				if (userTextField.getText().length() == 0 && passwordField.getPassword().length == 0)
				{
					JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 507: Tài khoản và mật khẩu không được để trống",
																		   "Error",
																		   JOptionPane.ERROR_MESSAGE);
				}
				else if (userTextField.getText().length() == 0)
				{
					JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 505: Tài khoản không được để trống",
																		   "Error",
																		   JOptionPane.ERROR_MESSAGE);
				}
				else if (userTextField.getText().length() > 6)
				{
					JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 508: Độ dài của tài khoản không được quá 6 kí tự",
																		   "Error",
																		   JOptionPane.ERROR_MESSAGE);
				}
				else if (passwordField.getPassword().length == 0)
				{
					JOptionPane.showMessageDialog(LoginView.getInstance(), "Error 506: Mật khẩu không được để trống",
																			"Error",
																			JOptionPane.ERROR_MESSAGE);
				}
				else if (userTextField.getText().length() > 0 && passwordField.getPassword().length > 0)
				{
					char [] passArray = passwordField.getPassword();
					String pass = new String(passArray);
					String userName = userTextField.getText();
					userName = userName.substring(0,1).toUpperCase() + userName.substring(1);
					if (CheckLoi.checkUsername(userTextField.getText()) && CheckLoi.checkPassword(pass,userName) && !CheckLoi.checkRole(userTextField.getText()))
					{
						tk = new taiKhoanModal(userName,pass,CheckLoi.getTenQuyen(userName));
//						mainFrameView.getInstance().disable();
//						mainFrameView.setDSCN(CheckLoi.getListOfRole(userName));
//						mainFrameView main = new mainFrameView();
//						main.setVisible(true);
//						mainFrameView.setInstance(main);
						LoginView.getInstance().dispose();
						xacNhanCaView.getInstance().display();
					}
					else if (CheckLoi.checkUsername(userTextField.getText()) && CheckLoi.checkPassword(pass,userName) && CheckLoi.checkRole(userTextField.getText()))
					{
						tk = new taiKhoanModal(userName,pass,CheckLoi.getTenQuyen(userName));
						mainFrameView.getInstance().disable();
						mainFrameView.setDSCN(CheckLoi.getListOfRole(userName));
						mainFrameView main = new mainFrameView();
						main.setVisible(true);
						mainFrameView.setInstance(main);
						LoginView.getInstance().dispose();
					}
					else
					{
						JOptionPane.showMessageDialog(LoginView.getInstance(),
									"Error 404: User name or password is invalid",
									"Error",
									JOptionPane.ERROR_MESSAGE);
					}
				}
			}	

			@Override
			public void mouseExited(MouseEvent e) {
				loginButton.setBackground(originalButton); // button text color
				loginButton.setForeground(Color.red);
			}

			public void mouseReleased(MouseEvent e) {
				loginButton.setBackground(new Color(16, 78, 139));
				loginButton.setForeground(Color.red);
			}
		});

		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setForeground(Color.red);
			}

			public void mouseEntered(MouseEvent e) {
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setForeground(Color.WHITE);
			}

			public void mousePressed(MouseEvent e) {
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setForeground(Color.red);
				System.exit(0);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				cancelButton.setBackground(originalButton); // button text color
				cancelButton.setForeground(Color.RED);
			}

			public void mouseReleased(MouseEvent e) {
				cancelButton.setBackground(new Color(128, 0, 0));
				cancelButton.setForeground(Color.red);
			}
		});
		
	}
	
	public static void setInstance (LoginView loginview)
	{
		instance = loginview; 
	}
	
	public taiKhoanModal getTk ()
	{
		return tk;
	}
}
