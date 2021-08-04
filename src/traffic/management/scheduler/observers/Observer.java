package traffic.management.scheduler.observers;

import traffic.management.trafficlights.TrafficLight;

public interface Observer {

	public void update(TrafficLight trafficLight);
}
