package ua.nure.jfm.task2;

import org.junit.jupiter.api.Assertions;

/**
 * @author Dmytro Kolesnykov
 */
class Base {
	
	{
		if (ComplianceTest.MAKE_ALL_TESTS_FAILED) {
			Assertions.fail("Compliance tests have not been passed", ComplianceTest.CAUSE);
		}
	}

}
