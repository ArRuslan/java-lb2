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
class ListImplIntegerTest extends Base {
	
	private List<Integer> list = new ListImpl<>();

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
		Utils.fillInWithIntegers(list, input);
		assertEquals(expected, list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3",
		"1 2",
		"null null"
	})
	void testClear(String input) {
		Utils.fillInWithIntegers(list, input);
		list.clear();
		assertEquals("[]", list.toString());
	}

	@Test
	void testClearEmpty() {
		list.clear();
		assertEquals("[]", list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1			;1",
		"1 2		;2",
		"1 2 3 null	;4",
	})
	void testSize(String input, String expectedStr) {
		Utils.fillInWithIntegers(list, input);
		int expected = Integer.parseInt(expectedStr);
		assertEquals(expected, list.size());
	}
	
    ////////////////////////////////////////////////////////////////////
    // List methods.
	////////////////////////////////////////////////////////////////////

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 2		;1",
		"2 1 1		;2",
		"null 2 	;null",
	})
	void testGetFirst(String input, String expectedStr) {
		Utils.fillInWithIntegers(list, input);
		Integer expected = Utils.parseInt(expectedStr);
		assertEquals(expected, list.getFirst());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 2		;2",
		"2 1 1		;1",
		"1 null 	;null",
	})
	void testGetLast(String input, String expectedStr) {
		Utils.fillInWithIntegers(list, input);
		Integer expected = Utils.parseInt(expectedStr);
		assertEquals(expected, list.getLast());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3		;[2, 3]",
		"null 1 2	;[1, 2]",
		"null		;[]",
		"1			;[]",
	})
	void testRemoveFirst(String input, String expected) {
		Utils.fillInWithIntegers(list, input);
		list.removeFirst();
		assertEquals(expected, list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3		;[1, 2]",
		"null 2 3	;[null, 2]",
		"null		;[]",
		"1			;[]",
	})
	void testRemoveLast(String input, String expected) {
		Utils.fillInWithIntegers(list, input);
		list.removeLast();
		assertEquals(expected, list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;7		;[7, 1, 2, 3]",
		"1 2 3			;null	;[null, 1, 2, 3]",
		"null 2 null	;null	;[null, null, 2, null]",
	})
	void testAddFirst(String input, String xStr, String expected) {
		Utils.fillInWithIntegers(list, input);
		Integer x = Utils.parseInt(xStr);
		list.addFirst(x);
		assertEquals(expected, list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;7		;null",
		"1 2 3			;3		;3",
		"null 2 null	;null	;null",
	})
	void testSearch(String input, String xStr, String expectedStr) {
		Utils.fillInWithIntegers(list, input);
		Integer x = Utils.parseInt(xStr);
		Integer expected = Utils.parseInt(expectedStr);
		Integer actual = list.search(x);
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;7		;[1, 2, 3]	;false",
		"1 2 3			;2		;[1, 3]		;true",
		"1 2 3			;3		;[1, 2]		;true",
		"null 2 null	;null	;[2, null]	;true",
	})
	void testRemove(String input, String xStr, String expected, String isExistStr) {
		Utils.fillInWithIntegers(list, input);
		Integer x = Utils.parseInt(xStr);
		boolean isExistActual = list.remove(x);
		boolean isExistExpected = Utils.parseBoolean(isExistStr);
		assertEquals(expected, list.toString());
		assertEquals(isExistExpected, isExistActual);
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
	void testIteratorNext(String input, String expectedStr) {
		Utils.fillInWithIntegers(list, input);
		Iterator<Integer> it = list.iterator();
		Integer expected = Utils.parseInt(expectedStr);
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
		Utils.fillInWithIntegers(list, input);
		Boolean expected = Utils.parseBoolean(expectedStr);
		Iterator<Integer> it = list.iterator();
		it.next();
		it.next();
		assertEquals(expected, it.hasNext());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3			;[3]",
		"null null null	;[null]",
	})
	void testIteratorRemove(String input, String expected) {
		Utils.fillInWithIntegers(list, input);
		Iterator<Integer> it = list.iterator();
		it.next();
		it.remove();
		it.next();
		it.remove();
		assertEquals(expected, list.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1",
		"1 2" ,
		"null null null",
	})
	void testIteratorRemoveShouldThrowException(String input) {
		Utils.fillInWithIntegers(list, input);
		Iterator<Integer> it1 = list.iterator();
		assertThrows(IllegalStateException.class, () -> it1.remove());
		Iterator<Integer> it2 = list.iterator();
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
		Utils.fillInWithIntegers(list, input);
		Iterator<Integer> it = list.iterator();
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
		Utils.fillInWithIntegers(list, input);
		StringBuilder sb = new StringBuilder();
		for (Integer x : list) {
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
		"1			;",
		"1 2 		;2",
		"1 2 3 4	;24",
	})
	void testStreamFilter(String input, String expected) {
		Utils.fillInWithIntegers(list, input);
		if (expected == null) {
			expected = "";
		}
		StringBuilder sb = new StringBuilder();
		list.stream()
			.filter(x -> x % 2 == 0)
			.forEach(sb::append);
		assertEquals(expected, sb.toString());
	}

	@ParameterizedTest()
	@CsvSource(delimiter = ';',
		value = {
		"1 2 3 4	;++|++++|",
		"2 3 4 		;++|++++|",
		"2			;++|",
	})
	void testStreamFilterWildcard(String input, String expected) {
		Utils.fillIn(list, input, Integer::parseInt);
		StringBuilder sb = new StringBuilder();
		list.stream()
			.filter(x -> x % 2 == 0)
			.map(x -> "+".repeat(x))
			.forEach(x -> sb.append(x).append('|'));
		assertEquals(expected, sb.toString());
	}
	
}
