package test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import View.LoadingView;

public class test {
	
	public static void main(String[] args) throws UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(new FlatMacLightLaf());
        UIManager.put("Button.arc", 600);
		LoadingView.getInstance();
	}
}
