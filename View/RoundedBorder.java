package View;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.border.Border;

public class RoundedBorder implements Border {
	private final Color color;
	private final int radius;

	public RoundedBorder(Color color, int radius) {
		this.color = color;
		this.radius = radius;
	}
	
	public RoundedBorder(int radius) {
		this.radius = radius;
		this.color = null;
	}

	public Insets getBorderInsets(Component c) {
		return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
	}

	public boolean isBorderOpaque() {
		return false;
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(color);
		g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
		g2d.dispose();
	}
}
