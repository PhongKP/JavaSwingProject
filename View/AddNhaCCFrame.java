package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddNhaCCFrame extends JFrame {
	   private static AddNhaCCFrame instance;

	    public static AddNhaCCFrame getInstance() {
	        if (instance != null) {
	            instance.dispose();
	        }
	        instance = new AddNhaCCFrame();
	        return instance;
	    }

	    public AddNhaCCFrame() {
	        Init();

	    }

	    public void Init() {
	        Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
	        setIconImage(icon);
	        setBackground(new Color(250, 238, 232));
	        setResizable(false);
	        setLayout(new GridLayout(1, 1));
	        setVisible(true);
	        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        setSize(600, 436);
	        setLocationRelativeTo(null);
	        JPanel mainpanel = new JPanel();
	        mainpanel.setBackground(new Color(250, 238, 232));
	        mainpanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
	        mainpanel.setLayout(new GridLayout(0, 1));
	     

	        JLabel tennhacc = new JLabel("Tên nhà CC:");
	        JTextField txttennhacc = new JTextField();
	        JLabel diachi = new JLabel("Địa chỉ:");
	        JTextField txtdiachi = new JTextField();
	        JLabel sdt = new JLabel("SĐT:");
	        JTextField txtsdt = new JTextField();
	        JLabel email = new JLabel("Email");
	        JTextField txtemail = new JTextField();
	        JPanel panelbutton = new JPanel();
	        panelbutton.setBackground(new Color(250, 238, 232));
	        panelbutton.setLayout(new FlowLayout(FlowLayout.CENTER));
	        JButton btnedit = new JButton("Thêm nhà cung cấp");
	        btnedit.setPreferredSize(new Dimension(200, 40));
	        panelbutton.add(btnedit);
	        btnedit.setBackground(Color.WHITE);
	        btnedit.setFocusable(false);
//	        btnedit.setMinimumSize(new Dimension(200, 20));
//	        btnedit.setMaximumSize(new Dimension(200, 20));
//	        btnedit.setPreferredSize(new Dimension(200, 20));
	        btnedit.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (txttennhacc.getText().trim().equals("") || txtdiachi.getText().trim().equals("") || txtsdt.getText().trim().equals("") || txtemail.getText().trim().equals("")) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
	                    return;
	                }
	                if (txtsdt.getText().length() != 10) {
	                    JOptionPane.showMessageDialog(null, "SDT phải 10 số");
	                    return;
	                }
	                try {
	                    Integer.parseInt(txtsdt.getText().trim());
	                } catch (Exception ex) {
	                    JOptionPane.showMessageDialog(null, "SDT ko hợp lệ");
	                    return;
	                }
	                if (txtsdt.getText().charAt(0) != '0') {
	                    JOptionPane.showMessageDialog(null, "SDT phải bắt đầu bằng số 0");
	                    return;
	                }
	                
	                if (BUS.BUSNhaCC.getInstance().isExistsPhone(txtsdt.getText().trim())) {
	                    JOptionPane.showMessageDialog(null, "Đã tồn tại sdt");
	                    return;
	                }
	                if (!BUS.BUSNhaCC.getInstance().add(new Modal.DTONhaCC(null, txttennhacc.getText().trim(), txtdiachi.getText().trim(), txtsdt.getText().trim(), txtemail.getText().trim()))) {
	                    JOptionPane.showMessageDialog(null, "Thêm thất bại");
	                    return;
	                }
	                nhaCCView.getInstance().loadData();
	                dispose();

	            }
	        });

	 
	        mainpanel.add(tennhacc);
	        mainpanel.add(txttennhacc);
	        mainpanel.add(diachi);
	        mainpanel.add(txtdiachi);
	        mainpanel.add(sdt);
	        mainpanel.add(txtsdt);
	        mainpanel.add(email);
	        mainpanel.add(txtemail);
	        mainpanel.add(panelbutton);
	        setTitle("Thêm nhà cung cấp");
	        add(mainpanel);

	    }
}
