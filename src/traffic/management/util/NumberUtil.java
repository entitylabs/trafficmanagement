package traffic.management.util;

import java.util.Random;
public class NumberUtil {

	private static Random randomNumberGenerator= new Random();
	
	public static int generateRandomNumberInRange(int minimum, int maximum) {
		
		return randomNumberGenerator.nextInt(maximum - minimum) + minimum;
	}
}
