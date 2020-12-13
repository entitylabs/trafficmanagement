package traffic.management.scheduler.observers;

import java.util.List;

import traffic.management.trafficlights.TrafficLight;

public abstract class Subject {

	List<Observer> observers;


	public Subject(List<Observer> observers) {
		super();
		this.observers = observers;
	}

	public void addObserver(Observer observer) {
		this.observers.add(observer);
	}

	public boolean removeObserver(Observer observer) {

		return observers.contains(observer) ? observers.remove(observer) : false;
		

	}
	
	public void addObservers(List<Observer> observers) {
		this.observers.addAll(observers);
	}
	
	public void notifyAllObservers() {
		
		if(this instanceof TrafficLight) {
			
			TrafficLight currentSubject = (TrafficLight) this;
			this.observers.stream().forEach(observer-> observer.update(currentSubject));
		}
		
	}

}
