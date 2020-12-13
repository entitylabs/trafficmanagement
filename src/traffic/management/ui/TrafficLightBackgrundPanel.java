package traffic.management.ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TrafficLightBackgrundPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.setColor(Color.white);
		g.fillRect(0, 50, 5, 5);
		g.fillRect(10, 50, 5, 5);
	}

}
