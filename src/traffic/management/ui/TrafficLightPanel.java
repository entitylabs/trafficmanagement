package traffic.management.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import traffic.management.scheduler.observers.Observer;
import traffic.management.trafficlights.TrafficLight;
import traffic.management.trafficlights.TrafficSignalStatus;

public class TrafficLightPanel implements Observer {

	private JPanel redLight;
	private JPanel yellowLight;
	private JPanel greenLight;
	private JPanel trafficLightContainer;

	public TrafficLightPanel() {

		this.trafficLightContainer = new JPanel();
		this.redLight = new JPanel();
		this.yellowLight = new JPanel();
		this.greenLight = new JPanel();

		trafficLightContainer.setBackground(Color.BLACK);
		trafficLightContainer.setBorder(BorderFactory.createBevelBorder(0));

		GroupLayout trafficLightContainerLayout = new GroupLayout(trafficLightContainer);
		trafficLightContainer.setLayout(trafficLightContainerLayout);

		setHorizontalLayout(trafficLightContainerLayout);
		setVerticalLayout(trafficLightContainerLayout);

	}

	private void setHorizontalLayout(GroupLayout trafficLightContainerLayout) {
		trafficLightContainerLayout
				.setHorizontalGroup(trafficLightContainerLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
						.addComponent(redLight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(yellowLight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(greenLight, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE));
	}

	private void setVerticalLayout(GroupLayout trafficLightContainerLayout) {
		trafficLightContainerLayout
				.setVerticalGroup(
						trafficLightContainerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(
										trafficLightContainerLayout.createSequentialGroup()
												.addComponent(redLight, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(yellowLight, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(greenLight, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addGap(0, 11, Short.MAX_VALUE)));
	}

	public JPanel getRedLight() {
		return redLight;
	}

	public void setRedLight(JPanel redLight) {
		this.redLight = redLight;
	}

	public JPanel getYellowLight() {
		return yellowLight;
	}

	public void setYellowLight(JPanel yellowLight) {
		this.yellowLight = yellowLight;
	}

	public JPanel getGreenLight() {
		return greenLight;
	}

	public void setGreenLight(JPanel greenLight) {
		this.greenLight = greenLight;
	}

	public JPanel getTrafficLightContainer() {
		return trafficLightContainer;
	}

	public void setTrafficLightContainer(JPanel trafficLightContainer) {
		this.trafficLightContainer = trafficLightContainer;
	}

	@Override
	public void update(TrafficLight trafficLight) {

		this.makeAllBlank();

		if (trafficLight.getCurrentStatus().equals(TrafficSignalStatus.RED)) {
			redLight.setBackground(Color.RED);
		}

		else if (trafficLight.getCurrentStatus().equals(TrafficSignalStatus.GREEN)) {
			greenLight.setBackground(Color.GREEN);
		}

		else if (trafficLight.getCurrentStatus().equals(TrafficSignalStatus.GREEN_TO_YELLOW)
				|| trafficLight.getCurrentStatus().equals(TrafficSignalStatus.RED_TO_YELLOW)) {
			yellowLight.setBackground(Color.YELLOW);
		}

	}

	private void makeAllBlank() {

		redLight.setBackground(Color.WHITE);
		greenLight.setBackground(Color.WHITE);
		yellowLight.setBackground(Color.WHITE);

	}

}
