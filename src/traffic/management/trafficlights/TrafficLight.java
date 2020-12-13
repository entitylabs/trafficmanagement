package traffic.management.trafficlights;

import traffic.management.scheduler.observers.Subject;
import traffic.management.model.Position;
import traffic.management.scheduler.observers.Observer;
import java.util.*;

public class TrafficLight extends Subject implements Runnable {

	private String name;
	private Position position;
	private int counter = 0;
	private boolean isPaused;
	
	private TrafficSignalStatus currentStatus = TrafficSignalStatus.RED;

	public TrafficLight(Position position, TrafficSignalStatus currentStatus) {
		super(new ArrayList<Observer>());
		this.currentStatus = currentStatus;
		this.position = position;
		this.isPaused=false;
	}

	@Override
	public void run() {

		
		if(!isPaused) {
			
			counter++;

			if (counter % 4 == 0) {

				this.currentStatus = TrafficSignalStatus.GREEN;
			} else if (counter % 4 == 1) {
				this.currentStatus = TrafficSignalStatus.GREEN_TO_YELLOW;
			} else if (counter % 4 == 2) {
				this.currentStatus = TrafficSignalStatus.RED;
			} else if (counter % 4 == 3) {
				this.currentStatus = TrafficSignalStatus.RED_TO_YELLOW;
			}

			this.notifyAllObservers();
		}
		

	}

	public TrafficSignalStatus getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(TrafficSignalStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}

}
