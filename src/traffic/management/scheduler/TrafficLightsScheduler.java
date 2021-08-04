package traffic.management.scheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;

import traffic.management.model.Position;
import traffic.management.trafficlights.TrafficLight;
import traffic.management.trafficlights.TrafficSignalStatus;
import traffic.management.util.NumberUtil;

public class TrafficLightsScheduler {
	

	private ScheduledExecutorService trafficScheduler;
	
	private List<TrafficLight> trafficLights;

	public void playTrafficLights(int numberOfTrafficLights, int minimumDelay, int maxiumumDelay) {
		
		this.trafficLights= new ArrayList<>();
		scheduleLights(numberOfTrafficLights, minimumDelay, maxiumumDelay);

	}

	private void scheduleLights(int numberOfTrafficLights, int minimumDelay, int maximunDelay) {

		int xCoordinate=100;
		int yCoordinate=150;
		this.trafficScheduler = Executors.newScheduledThreadPool(10);
		for (int i = 0; i < numberOfTrafficLights; i++) {

			int delay = NumberUtil.generateRandomNumberInRange(minimumDelay,maximunDelay);

			TrafficLight trafficLight = new TrafficLight(new Position(xCoordinate, yCoordinate), TrafficSignalStatus.GREEN);
			this.trafficLights.add(trafficLight);
			xCoordinate=xCoordinate+115;
			trafficLight.setName("Traffic light " + i);

			this.trafficScheduler.scheduleAtFixedRate(trafficLight, delay, delay, TimeUnit.SECONDS);

		}
	}
	
	public void stopScheduler() {
		
		this.trafficScheduler.shutdown();
	}

	public ScheduledExecutorService getTrafficScheduler() {
		return trafficScheduler;
	}

	public void setTrafficScheduler(ScheduledExecutorService trafficScheduler) {
		this.trafficScheduler = trafficScheduler;
	}

	public List<TrafficLight> getTrafficLights() {
		return trafficLights;
	}

	public void setTrafficLights(List<TrafficLight> trafficLights) {
		this.trafficLights = trafficLights;
	}
	
	
	public void pauseTrafficLights(boolean isPaused) {
		this.trafficLights.forEach(trafficLight->trafficLight.setPaused(isPaused));
	}
	
}
