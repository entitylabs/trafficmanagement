package traffic.management.cars; //import the sun.audio package

import java.util.HashSet;
import java.util.Set;

import traffic.management.constants.UiConstants;
import traffic.management.model.Position;
import traffic.management.scheduler.observers.Observer;
import traffic.management.trafficlights.TrafficLight;
import traffic.management.trafficlights.TrafficSignalStatus;
import traffic.management.util.NumberUtil;

public class Car implements Observer {

	private int width;
	private int height;
	private Color color;
	private Position position;
	private int pixelPerSecondSpeed;
	private boolean isRunning;

	private boolean isPaused;

	private Set<TrafficLight> trafficLights;

	public Car(int width, int height, Color color, Position position, int pixelPerSecondSpeed, boolean isRunning) {

		super();
		this.width = width;
		this.height = height;
		this.color = color;
		this.position = position;
		this.pixelPerSecondSpeed = pixelPerSecondSpeed;
		this.isRunning = isRunning;
		this.trafficLights = new HashSet<TrafficLight>();
		this.isPaused = false;
	}

	public void moveCar() {

		if (!isPaused) {

			if (this.position.getxCoordinate() >= UiConstants.MAX_SCREEN_WIDTH) {
				this.position.setxCoordinate(0);
			}
			trafficLights.stream().forEach(element -> this.checkStatus(element));

			if (this.isRunning) {
				this.getPosition().setxCoordinate(this.getPosition().getxCoordinate() + this.pixelPerSecondSpeed);
			} else {
				this.setPixelPerSecondSpeed(NumberUtil.generateRandomNumberInRange(2, 7));
			}

		}

	}

	private void checkStatus(TrafficLight light) {

		if (light.getCurrentStatus().equals(TrafficSignalStatus.RED)
				|| light.getCurrentStatus().equals(TrafficSignalStatus.RED_TO_YELLOW)) {

			if ((light.getPosition().getxCoordinate() - this.position.getxCoordinate() >= 14)
					&& (light.getPosition().getxCoordinate()
							- this.position.getxCoordinate() <= this.pixelPerSecondSpeed + 14)) {
				this.isRunning = false;
			}
		} else if (light.getCurrentStatus().equals(TrafficSignalStatus.GREEN)
				|| light.getCurrentStatus().equals(TrafficSignalStatus.GREEN_TO_YELLOW)) {

			if ((light.getPosition().getxCoordinate() - this.position.getxCoordinate() >= 14)
					&& (light.getPosition().getxCoordinate()
							- this.position.getxCoordinate() <= this.pixelPerSecondSpeed + 14)) {
				this.isRunning = true;
			}

		}
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

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getPixelPerSecondSpeed() {
		return pixelPerSecondSpeed;
	}

	public void setPixelPerSecondSpeed(int pixelPerSecondSpeed) {
		this.pixelPerSecondSpeed = pixelPerSecondSpeed;
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
	@Override
	public void update(TrafficLight trafficLight) {

		trafficLights.add(trafficLight);

	}
	
	@Override
	public String toString() {
		int speed = isRunning ? pixelPerSecondSpeed : 0;
		return "[Position=" + position + ", Speed=" + speed + "pixel/sec]";
	}

}
