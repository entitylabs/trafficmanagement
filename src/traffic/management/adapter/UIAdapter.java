package traffic.management.adapter;

import java.util.List;

import traffic.management.cars.Car;
import traffic.management.cars.CarManager;
import traffic.management.scheduler.TrafficLightsScheduler;
import traffic.management.ui.TrafficManagementMainFrame;

public class UIAdapter {

	static TrafficLightsScheduler scheduler = new TrafficLightsScheduler();

	static TrafficManagementMainFrame trafficManagementFrame;
	
	static List<Car> cars;

	public static void createUIInstance() {
		
		CarManager carManager = CarManager.getInstance();

		cars = carManager.getCars();

		trafficManagementFrame = new TrafficManagementMainFrame(cars);

		trafficManagementFrame.setVisible(true);
	}

	
	public static void triggerTrafficScheduler(int numberOfTrafficLights) {

		if (null != scheduler.getTrafficScheduler()) {
			scheduler.stopScheduler();
		}

		scheduler.playTrafficLights(numberOfTrafficLights, 5, 10);

		for (int i = 0; i < scheduler.getTrafficLights().size(); i++) {
			scheduler.getTrafficLights().get(i).addObserver(trafficManagementFrame.getTrafficLightPanels().get(i));
			
			for(Car car: cars) {
				scheduler.getTrafficLights().get(i).addObserver(car);
				car.setRunning(true);
			}
			
		}
		
		trafficManagementFrame.updateTrafficmanagementPanel();
	}

	public static void pauseUI(boolean pauseTraffic) {
		if (null != scheduler.getTrafficScheduler()) {
			scheduler.pauseTrafficLights(pauseTraffic);
		}
	}
	
	
	public static void stopScheduler() {
		if (null != scheduler.getTrafficScheduler()) {
			scheduler.stopScheduler();
		}
	}
}
