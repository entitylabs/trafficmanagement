package traffic.management.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class TrafficManagementMainPanelGrid {
	
	private JPanel mainPanel;
	private int width;
	private int height;
	
	public TrafficManagementMainPanelGrid(int width, int height) {
		
		super();
		this.width = width;
		this.height = height;
	}
	
	
	public void createPanel() {
		
		GridLayout gridLayout = new GridLayout(3,0);
		
		mainPanel= new JPanel(gridLayout, true);
		
		JButton startButton = new JButton();
		startButton.setText("Start");
		startButton.setPreferredSize(new Dimension(10, 10));
		mainPanel.add("startButton", startButton);
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		mainPanel.add("trafficPanel", panel);
		JButton stopButton = new JButton();
		stopButton.setText("Stop");
		stopButton.setPreferredSize(new Dimension(10, 10));
		mainPanel.add("stopButton", stopButton);
		
	}


	public JPanel getMainPanel() {
		return mainPanel;
	}


	public void setMainPanel(JPanel mainPanel) {
		this.mainPanel = mainPanel;
	}


	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}
	
	
	
	

}
