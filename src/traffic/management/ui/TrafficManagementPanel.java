package traffic.management.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import traffic.management.cars.Car;
import traffic.management.constants.UiConstants;

public class TrafficManagementPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private List<Car> cars;
	private int numberOFTrafficLights;

	Timer timer;

	int curvePostion = 0;

	public TrafficManagementPanel(List<Car> cars, int numberOFTrafficLights) {
		super();
		this.cars = cars;
		this.numberOFTrafficLights = numberOFTrafficLights;
		timer = new Timer(100, this);
		timer.setInitialDelay(150);
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		curvePostion = curvePostion + 1;
		this.setBackground(Color.white);

		g.setColor(Color.lightGray);
		g.fill3DRect(UiConstants.ROAD_X_POSITION, UiConstants.ROAD_Y_POSITION,
				(int) getToolkit().getScreenSize().getWidth(), UiConstants.ROAD_WIDTH, true);

		g.setColor(Color.darkGray);
		g.drawRect(UiConstants.ROAD_X_POSITION, UiConstants.ROAD_Y_POSITION,
				(int) getToolkit().getScreenSize().getWidth(), UiConstants.ROAD_WIDTH);

		int xPosition = 30;
		for (int i = 0; i < 20; i++) {
			g.setColor(Color.white);
			g.fillRect(xPosition, UiConstants.ROAD_Y_POSITION + 15, 30, UiConstants.ROAD_WIDTH - 30);
			xPosition = xPosition + 120;
		}
		int traficLightXLocation = UiConstants.FIRST_TRAFIC_LIGHT_X_COORDINATE;
		int traficLightYLocation = UiConstants.FIRST_TRAFIC_LIGHT_Y_COORDINATE;

		int trafficLocationLabelStep = 140;

		for (int i = 0; i < numberOFTrafficLights; i++) {
			g.setColor(Color.darkGray);
			trafficLocationLabelStep = trafficLocationLabelStep - 15;
//			g.drawString("[X=" + traficLightXLocation + ",Y=," + traficLightYLocation + "]", traficLightXLocation - 40,
//					90);
			int crossingYPosition = 0;
			for (int j = 0; j < 13; j++) {
				if (j % 2 == 0) {
					g.setColor(Color.black);

				} else {
					g.setColor(Color.white);
				}

				g.fillRect(traficLightXLocation, traficLightYLocation + crossingYPosition, 13, 3);
				crossingYPosition = crossingYPosition + 3;
			}

			traficLightXLocation = traficLightXLocation + UiConstants.TRAFFICLIGHT_DISTANCE_APART;

		}

		int carLabelDistance = 0;

		for (Car car : this.cars) {

			car.moveCar();
			carLabelDistance = carLabelDistance + 15;
			Color carColor = new Color(car.getColor().getR(), car.getColor().getG(), car.getColor().getB());
			g.setColor(carColor);
			g.fill3DRect(car.getPosition().getxCoordinate(), car.getPosition().getyCoordinate(), car.getWidth(),
					car.getHeight(), true);
			g.setColor(Color.WHITE);
			g.fill3DRect(car.getPosition().getxCoordinate() + 2, car.getPosition().getyCoordinate() + 2,
					car.getWidth() - 5, car.getHeight() - 4, true);

			g.setColor(carColor);
			//g.drawString(car.toString(), car.getPosition().getxCoordinate(), 200 + carLabelDistance);

		}
		
		
		
		// draw train

		g.setColor(new Color(27, 27, 27));
		g.fill3DRect(0, 50, (int) getToolkit().getScreenSize().getWidth(), 2, true);
		g.fill3DRect(0, 65, (int) getToolkit().getScreenSize().getWidth(), 2, true);

		int xPositionOfTrack = 0;

		for (int i = 0; i < 200; i++) {
			g.drawLine(xPositionOfTrack, 48, xPositionOfTrack - 3, 70);
			g.drawLine(xPositionOfTrack + 1, 48, xPositionOfTrack - 2, 70);

			xPositionOfTrack = xPositionOfTrack + 5;

			if (i % 10 == 0) {
				g.fill3DRect(xPositionOfTrack, 15, 2, 25, true);

				g.fill3DRect(xPositionOfTrack - 5, 60, 2, 30, true);

				g.drawLine(xPositionOfTrack, 15, xPositionOfTrack - 5, 60);

			}

		}

		// wires
		g.drawLine(0, 40, (int) getToolkit().getScreenSize().getWidth(), 40);

		g.setColor(new Color(173, 216, 230));
		g.fillRect(0, 250, (int) getToolkit().getScreenSize().getWidth(), 30);

	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	public int getNumberOFTrafficLights() {
		return numberOFTrafficLights;
	}

	public void setNumberOFTrafficLights(int numberOFTrafficLights) {
		this.numberOFTrafficLights = numberOFTrafficLights;
	}

}
