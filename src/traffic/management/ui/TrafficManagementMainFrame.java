package traffic.management.ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import traffic.management.adapter.UIAdapter;
import traffic.management.cars.Car;
import traffic.management.cars.CarManager;
import traffic.management.constants.UiConstants;

public class TrafficManagementMainFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 1L;
	private javax.swing.JButton startButton;
	private javax.swing.JButton pauseButton;
	private javax.swing.JComboBox<String> trafficLightLists;
	private javax.swing.JLabel statisticsLabel;
	private javax.swing.JLabel totalNumberOfTrafficLightsPanel;
	private TrafficManagementPanel trafficManagementPanel;
	private int numberOfTrafficLights;
	private List<Car> cars;
	private List<TrafficLightPanel> trafficLightPanels;

	private boolean isPaused = false;
	private boolean start = false;

	public TrafficManagementMainFrame(List<Car> cars) {
		this.cars = cars;
		initComponents();

	}

	private void initComponents() {

		this.numberOfTrafficLights = 0;
		trafficManagementPanel = new TrafficManagementPanel(this.cars, 0);
		statisticsLabel = new javax.swing.JLabel();
		startButton = new javax.swing.JButton();
		totalNumberOfTrafficLightsPanel = new javax.swing.JLabel();
		pauseButton = new javax.swing.JButton();
		String[] values = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		trafficLightLists = new javax.swing.JComboBox<>(values);

		this.trafficLightPanels = new ArrayList<>();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		setTextForFields();

		addTrafficLightsCountActionListener();
		addStartButtonActionListener();
		addPauseButtonActionListener();
		this.updateTrafficmanagementPanel();
	}

	private void setTextForFields() {

		startButton.setText(UiConstants.START);
		totalNumberOfTrafficLightsPanel.setText("Traffic Lights on highway");
		pauseButton.setText(UiConstants.PAUSE);

	}

	private void addTrafficLightsCountActionListener() {

		trafficLightLists.addActionListener(event -> {

			numberOfTrafficLights = Integer.parseInt(trafficLightLists.getSelectedItem().toString());
			this.trafficManagementPanel.setNumberOFTrafficLights(numberOfTrafficLights);

		});

	}

	private void addPauseButtonActionListener() {

		pauseButton.addActionListener(event -> {
			isPaused = !isPaused;

			if (isPaused) {
				pauseButton.setText(UiConstants.RESUME);
			} else {
				pauseButton.setText(UiConstants.PAUSE);
			}
			UIAdapter.pauseUI(isPaused);
			CarManager.getInstance().pauseCars(isPaused);

		});

	}

	private void addStartButtonActionListener() {

		startButton.addActionListener(event -> {

			if (numberOfTrafficLights <= 0) {
				this.statisticsLabel.setText(UiConstants.SELECT_TRAFFIC_LIGHTS_IN_THE_DROPDOWN);
			} else {
				start = !start;

				if (this.start) {

					if (numberOfTrafficLights > 0) {
						startButton.setText(UiConstants.STOP);
						this.trafficLightPanels.clear();

						for (int i = 0; i < numberOfTrafficLights; i++) {

							this.trafficLightPanels.add(new TrafficLightPanel());
						}
						UIAdapter.triggerTrafficScheduler(numberOfTrafficLights);
					} 

				} else {
					System.exit(0);
				}
			}

		});

	}

	public void updateTrafficmanagementPanel() {

		javax.swing.GroupLayout traficLightPanelLayout = new javax.swing.GroupLayout(trafficManagementPanel);
		trafficManagementPanel.setLayout(traficLightPanelLayout);
		SequentialGroup horizontalGroup = traficLightPanelLayout.createSequentialGroup().addGap(100, 100, 100);
		ParallelGroup verticalGroup = traficLightPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false);

		createTrafficLightLayouts(horizontalGroup, verticalGroup);

		populateTraficLightsOnPanel(traficLightPanelLayout, horizontalGroup, verticalGroup);

		updateStatistcisLabelOnFrame();

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		addComponentsToHorizontalGroup(layout);
		addComponentToVerticalGroup(layout);

		pack();

		UiConstants.MAX_SCREEN_WIDTH = this.getWidth();
		//this.setSize((int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight()-100);
	}

	private void addComponentToVerticalGroup(javax.swing.GroupLayout layout) {
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(2, 2, 2)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(startButton)
								.addComponent(trafficLightLists, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(totalNumberOfTrafficLightsPanel).addComponent(pauseButton))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(trafficManagementPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(statisticsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
								javax.swing.GroupLayout.PREFERRED_SIZE)));
	}

	private void addComponentsToHorizontalGroup(javax.swing.GroupLayout layout) {
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(trafficManagementPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(statisticsLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(startButton).addGap(18, 18, 18)
						.addComponent(totalNumberOfTrafficLightsPanel).addGap(18, 18, 18)
						.addComponent(trafficLightLists, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(pauseButton).addContainerGap()));
	}

	private void updateStatistcisLabelOnFrame() {
		statisticsLabel.setBackground(new java.awt.Color(102, 102, 102));
		statisticsLabel.setText("");
	}

	private void populateTraficLightsOnPanel(javax.swing.GroupLayout traficLightPanelLayout,
			SequentialGroup horizontalGroup, ParallelGroup verticalGroup) {
		traficLightPanelLayout.setHorizontalGroup(
				traficLightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(traficLightPanelLayout.createSequentialGroup().addGroup(horizontalGroup)
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		traficLightPanelLayout
				.setVerticalGroup(traficLightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(traficLightPanelLayout.createSequentialGroup().addGap(100, 100, 100)
								.addGroup(verticalGroup).addContainerGap(190, Short.MAX_VALUE)));
	}

	private void createTrafficLightLayouts(SequentialGroup horizontalGroup, ParallelGroup verticalGroup) {
		this.trafficLightPanels.forEach(element -> {
			horizontalGroup
					.addComponent(element.getTrafficLightContainer(), javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE)
					.addGap(100, 100, 100);

			verticalGroup.addComponent(element.getTrafficLightContainer(), javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);

		});
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public javax.swing.JButton getStartButton() {
		return startButton;
	}

	public void setStartButton(javax.swing.JButton startButton) {
		this.startButton = startButton;
	}

	public javax.swing.JButton getStopButton() {
		return pauseButton;
	}

	public void setStopButton(javax.swing.JButton stopButton) {
		this.pauseButton = stopButton;
	}

	public javax.swing.JComboBox<String> getTrafficLightLists() {
		return trafficLightLists;
	}

	public void setTrafficLightLists(javax.swing.JComboBox<String> trafficLightLists) {
		this.trafficLightLists = trafficLightLists;
	}

	public javax.swing.JLabel getStatisticsLabel() {
		return statisticsLabel;
	}

	public void setStatisticsLabel(javax.swing.JLabel statisticsLabel) {
		this.statisticsLabel = statisticsLabel;
	}

	public javax.swing.JLabel getTotalNumberOFTrafficLightsPanel() {
		return totalNumberOfTrafficLightsPanel;
	}

	public void setTotalNumberOFTrafficLightsPanel(javax.swing.JLabel totalNumberOFTrafficLightsPanel) {
		this.totalNumberOfTrafficLightsPanel = totalNumberOFTrafficLightsPanel;
	}

	public javax.swing.JPanel getTrafficManagementPanel() {
		return trafficManagementPanel;
	}

	public void setTrafficManagementPanel(TrafficManagementPanel trafficManagementPanel) {
		this.trafficManagementPanel = trafficManagementPanel;
	}

	public int getNumberOfTrafficLights() {
		return numberOfTrafficLights;
	}

	public void setNumberOfTrafficLights(int numberOfTrafficLights) {
		this.numberOfTrafficLights = numberOfTrafficLights;
	}

	public List<TrafficLightPanel> getTrafficLightPanels() {
		return trafficLightPanels;
	}

	public void setTrafficLightPanels(List<TrafficLightPanel> trafficLightPanels) {
		this.trafficLightPanels = trafficLightPanels;
	}

	public javax.swing.JLabel getTotalNumberOfTrafficLightsPanel() {
		return totalNumberOfTrafficLightsPanel;
	}

	public void setTotalNumberOfTrafficLightsPanel(javax.swing.JLabel totalNumberOfTrafficLightsPanel) {
		this.totalNumberOfTrafficLightsPanel = totalNumberOfTrafficLightsPanel;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

}
