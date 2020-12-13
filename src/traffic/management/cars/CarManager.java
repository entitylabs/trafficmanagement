package traffic.management.cars;

import java.util.ArrayList;
import java.util.List;

import traffic.management.constants.UiConstants;
import traffic.management.model.Position;
import traffic.management.util.ColorUtil;
import traffic.management.util.NumberUtil;

public final class CarManager {

	private List<Car> cars = new ArrayList<Car>();

	private static CarManager carManager = null;

	private static int CAR_HEIGHT = 8;
	private static int CAR_WIDTH = 10;

	private CarManager(List<Car> cars) {
		super();
		this.cars = cars;
		this.addRandomCars();

	}

	public static CarManager getInstance() {

		if (carManager == null) {
			carManager = new CarManager(new ArrayList<>());
		}

		return carManager;

	}

	private void addRandomCars() {

		int numberOfCars = NumberUtil.generateRandomNumberInRange(5, 20);

		for (int i = 0; i < numberOfCars; i++) {

			int randonCarYPosition = NumberUtil.generateRandomNumberInRange(UiConstants.ROAD_Y_POSITION + 2,
					UiConstants.ROAD_Y_POSITION + 35);
			int randomSpeed = NumberUtil.generateRandomNumberInRange(2, 7);
			Car car = new Car(CAR_WIDTH, CAR_HEIGHT, ColorUtil.getRandomColor(),
					new Position(UiConstants.ROAD_X_POSITION, randonCarYPosition), randomSpeed, false);

			this.cars.add(car);
		}

	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public void pauseCars(boolean isPaused) {

		if (cars != null) {

			cars.stream().forEach(car -> car.setPaused(isPaused));

		}

	}

}
