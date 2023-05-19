package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import BUS.QLTKBUS;
import Modal.taiKhoanModal;

public class forgotPass extends JFrame{
	
	taiKhoanModal tk;
	private static forgotPass Instance;
	private QLTKBUS qltkBUS;

    JPasswordField txMatKhauCu = new JPasswordField(15);
    JPasswordField txMatKhauMoi = new JPasswordField(15);
    JPasswordField txXacNhanMatKhau = new JPasswordField(15);

    JButton btnDongY = new CreateRoundButton("");
    JButton btnHuy = new CreateRoundButton("");
	private String mkcu;
	private String mkmoi;
	private String xnmk;

    public forgotPass() {
        setSize(300, 300);
        setTitle("Đổi mật khẩu");
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);
        
        tk = LoginView.getInstance().getTk();
        qltkBUS = new QLTKBUS();

        // input
        JPanel plInput = new JPanel();
        plInput.setLayout(null);
        plInput.setPreferredSize(new Dimension(300,200));
        txMatKhauCu.setBorder(BorderFactory.createTitledBorder("Mật khẩu cũ: "));
        txMatKhauMoi.setBorder(BorderFactory.createTitledBorder("Mật khẩu mới: "));
        txXacNhanMatKhau.setBorder(BorderFactory.createTitledBorder("Xác nhận mật khẩu: "));
//        txMatKhauCu.setPreferredSize(new Dimension(300,60));
//        txMatKhauMoi.setPreferredSize(new Dimension(300,60));
//        txXacNhanMatKhau.setPreferredSize(new Dimension(300,60));
        txMatKhauCu.setBounds(5, 25, 275, 45);
        txMatKhauMoi.setBounds(5, 85, 275, 45);
        txXacNhanMatKhau.setBounds(5, 150, 275, 45);
        plInput.setBackground(new Color(222,188,153));
        
        plInput.add(txMatKhauCu);
        plInput.add(txMatKhauMoi);
        plInput.add(txXacNhanMatKhau);


        // button
        JPanel plButton = new JPanel();
        plButton.setPreferredSize(new Dimension(300,60));
        btnDongY.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDongY.setBorder(null);
        btnDongY.setPreferredSize(new Dimension(50,50));
        btnDongY.setBackground(new Color(144,238,144));
        btnDongY.setForeground(new Color(144,238,144));
        btnHuy.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnHuy.setBorder(null);
        btnHuy.setPreferredSize(new Dimension(50,50));
        btnHuy.setBackground(new Color(255,69,0));
        btnHuy.setForeground(new Color(255,69,0));
        plButton.add(btnDongY);
        plButton.add(btnHuy);
        plButton.setBackground(new Color(222,188,153));

        btnHuy.setIcon(new ImageIcon(this.getClass().getResource("/img/icons8_cancel_30px_1.png")));
        btnDongY.setIcon(new ImageIcon(this.getClass().getResource("/img/icons8_ok_30px.png")));

        btnHuy.addActionListener((ae) -> {
            this.dispose();
        });
        btnDongY.addActionListener((ae) -> {
            if(checkPass()) {
//            	tk.setPassWord(txMatKhauMoi.getPassword().toString());
            	Boolean success = qltkBUS.updatePassword(mkmoi,tk.getUserName());
            	if (success)
            	{
            		this.dispose();
            	}
            }
        });

        this.add(plInput,BorderLayout.CENTER);
        this.add(plButton,BorderLayout.SOUTH);
        this.setVisible(false);
    }

    private Boolean checkPass() {
    	char [] oldPass = txMatKhauCu.getPassword();
        mkcu = new String(oldPass);
        char [] newPass = txMatKhauMoi.getPassword();
        mkmoi = new String(newPass);
        char [] vertifyOldPass = txXacNhanMatKhau.getPassword();
        xnmk = new String(vertifyOldPass);
        
        if (!mkcu.equals(tk.getPassWord())) {
//        	System.out.println(tk.getPassWord());
//        	System.out.println(mkcu);
            JOptionPane.showMessageDialog(txMatKhauCu, "Mật khẩu cũ không đúng!");
            txMatKhauCu.requestFocus();
            return false;

        } else if (mkmoi.equals("") || xnmk.equals("")) {
            JOptionPane.showMessageDialog(txMatKhauMoi, "Mật khẩu mới không được để trống!");
            txMatKhauMoi.requestFocus();
            return false;
            
        } else if (!mkmoi.equals(xnmk)) {
            JOptionPane.showMessageDialog(txXacNhanMatKhau, "Mật khẩu mới không khớp!");
            txXacNhanMatKhau.requestFocus();
            return false;
        }

        return true;
    }
    
    public static forgotPass getInstance ()
    {
    	if (Instance == null)
    	{
    		Instance = new forgotPass();
    	}
    	return Instance;
    }
    
    public void display ()
    {
    	this.setVisible(true);
    }
    
//    public static void main(String[] args) {
//		new forgotPass().display();;
//	}

}
