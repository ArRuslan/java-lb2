package ua.nure.jfm.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Dmytro Kolesnykov
 */
class ArrayImplStringTest extends Base {
	
	private Array<String> array = new ArrayImpl<>();

    ////////////////////////////////////////////////////////////////////
    // Container methods.
	////////////////////////////////////////////////////////////////////

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;[1, 2, 3]",
		"1				;[1]",
		"null 2 null 4	;[null, 2, null, 4]"
	})
	void testAdd(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		assertEquals(expected, array.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3",
		"1 2",
		"null null"
	})
	void testClear(String input) {
		Utils.fillInWithStrings(array, input);
		array.clear();
		assertEquals("[]", array.toString());
	}

	@Test
	void testClearEmpty() {
		array.clear();
		assertEquals("[]", array.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1			;1",
		"1 2		;2",
		"1 2 3 null	;4",
	})
	void testSize(String input, String expectedStr) {
		Utils.fillInWithStrings(array, input);
		int expected = Integer.parseInt(expectedStr);
		assertEquals(expected, array.size());
	}
	
    ////////////////////////////////////////////////////////////////////
    // Array methods.
	////////////////////////////////////////////////////////////////////

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3		;2	;3",
		"1 2 3		;1	;2",
		"1 2 3		;0	;1",
		"null null	;0	;null",
		"null null	;1	;null",
	})
	void testGet(String input, String indexStr, String expected) {
		Utils.fillInWithStrings(array, input);
		int index = Integer.parseInt(indexStr);
		assertEquals(expected, array.get(index));
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 2		;2		;1",
		"1 1 1		;1		;0",
		"1 2 3		;3		;2",
		"null null	;null	;0",
		"1 null		;null	;1",
	})
	void testIndexOf(String input, String xStr, String expectedStr) {
		Utils.fillInWithStrings(array, input);
		String x = xStr;
		int expected = Integer.parseInt(expectedStr);
		assertEquals(expected, array.indexOf(x));
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;0		;[2, 3]",
		"1 2 3			;1		;[1, 3]",
		"1 2 3			;2		;[1, 2]",
		"null 2	null	;0		;[2, null]",
		"1 null			;1		;[1]",
	})
	void testRemove(String input, String indexStr, String expected) {
		Utils.fillInWithStrings(array, input);
		int index = Utils.parseInt(indexStr);
		array.remove(index);
		assertEquals(expected, array.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;0	;7		;[7, 2, 3]",
		"1 2 3			;1	;8		;[1, 8, 3]",
		"1 2 3			;2	;null	;[1, 2, null]",
		"null 2 null	;2	;null	;[null, 2, null]",
		"null 2 null	;1	;null	;[null, null, null]",
	})
	void testSet(String input, String indexStr, String xStr, String expected) {
		Utils.fillInWithStrings(array, input);
		int index = Integer.parseInt(indexStr);
		array.set(index, xStr);
		assertEquals(expected, array.toString());
	}

    ////////////////////////////////////////////////////////////////////
    // Iterator methods.
	////////////////////////////////////////////////////////////////////

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3		;2",
		"null 3		;3",
		"1 null 3	;null",
		"null null	;null"
	})
	void testIteratorNext(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		Iterator<String> it = array.iterator();
		it.next();
		assertEquals(expected, it.next());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3		;true",
		"null 3		;false",
		"1 null		;false"
	})
	void testIteratorHasNext(String input, String expectedStr) {
		Utils.fillInWithStrings(array, input);
		Boolean expected = Utils.parseBoolean(expectedStr);
		Iterator<String> it = array.iterator();
		it.next();
		it.next();
		assertEquals(expected, it.hasNext());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;[3]",
		"null null null	;[null]",
		"null null		;[]",
	})
	void testIteratorRemove(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		Iterator<String> it = array.iterator();
		it.next();
		it.remove();
		it.next();
		it.remove();
		assertEquals(expected, array.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1",
		"1 2" ,
		"null null null",
	})
	void testIteratorRemoveShouldThrowException(String input) {
		Utils.fillInWithStrings(array, input);
		Iterator<String> it1 = array.iterator();
		assertThrows(IllegalStateException.class, () -> it1.remove());
		Iterator<String> it2 = array.iterator();
		it2.next();
		it2.remove();
		assertThrows(IllegalStateException.class, () -> it2.remove());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2",
		"null null",
	})
	void testIteratorNextShouldThrowsException(String input) {
		Utils.fillInWithStrings(array, input);
		Iterator<String> it = array.iterator();
		it.next();
		it.next();
		assertThrows(NoSuchElementException.class, () -> it.next());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1			;1",
		"1 2 		;12",
		"1 null 3	;1null3",
	})
	void testIteratorForEach(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		StringBuilder sb = new StringBuilder();
		for (String x : array) {
			sb.append(x);
		}
		String actual = sb.toString();
		assertEquals(expected, actual);
	}

    ////////////////////////////////////////////////////////////////////
    // Stream methods.
	////////////////////////////////////////////////////////////////////

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1				;",
		"1 2 			;2",
		"1 2 3 4		;24",
		"11 22 33 444 	;22444",
	})
	void testStreamFilter(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		if (expected == null) {
			expected = "";
		}
		StringBuilder sb = new StringBuilder();
		array.stream()
			.filter(x -> Integer.parseInt(x) % 2 == 0)
			.forEach(sb::append);
		assertEquals(expected, sb.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"a as asd asdf	;1234",
		"a asd asdf		;134",
		"a				;1",
	})
	void testStreamMap(String input, String expected) {
		Utils.fillInWithStrings(array, input);
		StringBuilder sb = new StringBuilder();
		array.stream()
			.map(String::length)
			.forEach(sb::append);
		assertEquals(expected, sb.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"a bc def ghij	;ABD",
		"a def 			;AD",
		"a 				;A",
	})
	void testStreamMapFilter(String input, String expected) {
		Array<String> array = new ArrayImpl<>();
		Utils.fillInWithStrings(array, input);
		StringBuilder sb = new StringBuilder();
		array.stream()
			.filter(s -> s.length() <= 3)
			.map(String::toUpperCase)
			.map(s -> s.charAt(0))
			.forEach(sb::append);
		assertEquals(expected, sb.toString());
	}
	
	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"a bc def ghij	;ABD",
		"a def 			;AD",
		"a 				;A",
	})
	void testStreamFilterWildcard(String input, String expected) {
		Array<CharSequence> array = new ArrayImpl<>();
		Utils.fillIn(array, input, s -> (CharSequence)s);
		StringBuilder sb = new StringBuilder();
		array.stream()
			.filter(s -> s.length() <= 3)
			.map(s -> s.charAt(0))
			.map(Object::toString)
			.map(String::toUpperCase)
			.forEach(sb::append);
		assertEquals(expected, sb.toString());
	}

}
