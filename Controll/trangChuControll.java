package Controll;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.LoginView;
import View.forgotPass;
import View.giaoCaView;
import View.mainFrameView;
import View.trangChuView;

public class trangChuControll implements ActionListener {

	private trangChuView view;
	
	public trangChuControll (trangChuView view)
	{
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Đăng Xuất"))
		{
			mainFrameView.getInstance().disable();
			if (forgotPass.getInstance() != null)
			{
				forgotPass.getInstance().dispose();
			}
			LoginView loginView = new LoginView();
			loginView.setVisible(true);
			LoginView.setInstance(loginView);
		}
		else if (e.getActionCommand().equals("Đổi mật khẩu"))
		{
			forgotPass.getInstance().display();
		}
		else if (e.getActionCommand().equals("Giao Ca"))
		{
			giaoCaView.getInstance().display();
		}
	}

}
