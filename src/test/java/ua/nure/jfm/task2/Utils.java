package ua.nure.jfm.task2;

import java.util.Arrays;
import java.util.function.Function;

/**
 * @author Dmytro Kolesnykov
 */
public class Utils {
	
	public static <T extends Container<Integer>> void fillInWithIntegers(T container, String input) {
		fillIn(container, input, Utils::parseInt);
	}

	public static <T extends Container<String>> void fillInWithStrings(T container, String input) {
		fillIn(container, input, Function.identity());
	}

	public static <E, T extends Container<E>> void fillIn(T container, String input, Function<String, E> func) {
		Arrays.stream(input.split("\\s+"))
			.map(func)
			.forEach(container::add);
	}

	public static Integer parseInt(String str) {
		return "null".equals(str) ? null : Integer.valueOf(str);
	}

	public static Boolean parseBoolean(String str) {
		return "null".equals(str) ? null : Boolean.valueOf(str);
	}
	
}
