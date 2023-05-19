package View;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
//import javax.swing.UIManager;


public class LoadingView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6467723846945871884L;
	private JLabel loadingLabel;
	private JProgressBar progressBar;
	private JLabel loadingNameLabel;
	private static LoadingView Instance;
	
	public LoadingView ()
	{
		setSize(600,400);
		setLocationRelativeTo(null);
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		JPanel panel_top = new JPanel();
		panel_top.setLayout(null);
		panel_top.setBackground(new Color(153, 179, 157));
		
		URL iconLoading_link = this.getClass().getResource("/img/login_logo.png");
		ImageIcon imageIcon = new ImageIcon(
				new ImageIcon(iconLoading_link).getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH));
		loadingLabel = new JLabel();
		loadingLabel.setIcon(imageIcon);
		loadingLabel.setBounds(212,120,200,140);
		loadingNameLabel = new JLabel("Loading ...");
		loadingNameLabel.setBounds(287,200,200,140);
		panel_top.add(loadingLabel);
		panel_top.add(loadingNameLabel);
		this.add(panel_top,BorderLayout.CENTER);
		
		JPanel panel_bottom = new JPanel();
		progressBar = new JProgressBar(0,100);
		progressBar.setStringPainted(true);
//		UIManager.put("progressBar.Background",Color.BLUE);
//		UIManager.put("progressBar.selectionBackground",Color.ORANGE);
//		UIManager.put("progressBar.selectionForeground",Color.WHITE);
		progressBar.setUI(new customLoadingBackground());
		progressBar.setPreferredSize(new Dimension(460,20));
		panel_bottom.setBackground(new Color(153, 179, 157));
		panel_bottom.add(progressBar);
		add(panel_bottom,BorderLayout.SOUTH);
		
		
		new Thread(new Runnable() {
			public void run() {
				for (int i=0;i<=100;i++)
				{
					try {
						if (i == 100)
						{
							dispose();
							LoginView.getInstance();
						}
						Thread.sleep(50);
					} catch (InterruptedException e) {
						JOptionPane.showMessageDialog(LoadingView.getInstance(),"Error 600: Không thể tải dữ liệu lên","Error",JOptionPane.ERROR_MESSAGE);
					}
					progressBar.setValue(i);
				}
			}
		}).start();	
		setVisible(true);
		
	}
	
	public static LoadingView getInstance ()
	{
		if (Instance == null)
		{
			Instance = new LoadingView();
		}
		return Instance;
	}
	
	
//	public static void main(String[] args) {
//		new LoadingView();
//	}
}
