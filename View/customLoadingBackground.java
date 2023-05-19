package View;

import java.awt.Color;

import javax.swing.plaf.basic.BasicProgressBarUI;

public class customLoadingBackground extends BasicProgressBarUI {
	protected Color getSelectionBackground ()
	{
		return Color.ORANGE;
	}
	
}
