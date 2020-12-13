package traffic.management.util;

import traffic.management.cars.Color;
import java.util.Random;
public class ColorUtil {

	private static Random random = new Random();
	
	public static Color getRandomColor() {
		
		return new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
	}
}
