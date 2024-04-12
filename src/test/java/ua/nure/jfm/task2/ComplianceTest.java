package ua.nure.jfm.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import spoon.Launcher;
import spoon.SpoonAPI;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

/**
 * @author Dmytro Kolesnykov
 */
//@Disabled("This test is used as a trigger to fail all the other tests")
class ComplianceTest {

	// Assign this option to false to skip the compliance test
	// Note, during testing at the stand this option will be turned on!!!
	// private static final boolean TURN_TEST_COMPLIANCE_ON = false;
	private static final boolean TURN_TEST_COMPLIANCE_ON = true;

	public static final boolean MAKE_ALL_TESTS_FAILED;

	public static final Throwable CAUSE;

	private static final Object EOL = System.lineSeparator();
	
	static {
		L: {
			try {
				if (TURN_TEST_COMPLIANCE_ON) {
					initSpoon();
					startCompianceTests();
				}
			} catch (ReflectiveOperationException ex) {
				MAKE_ALL_TESTS_FAILED = true;
				CAUSE = ex.getCause();
				break L;
			}
			MAKE_ALL_TESTS_FAILED = false;
			CAUSE = null;
		}
	}

	private static SpoonAPI spoon;
	
	private static void initSpoon() {
		spoon = new Launcher();
		spoon.addInputResource("src/main/java/");
		spoon.buildModel();
	}
	
	private static void startCompianceTests() throws ReflectiveOperationException {
		ComplianceTest cTest = new ComplianceTest();
		for (Method m : ComplianceTest.class.getDeclaredMethods()) {
			if (Modifier.isPrivate(m.getModifiers())) {
				continue;
			}
			Test[] ar = m.getAnnotationsByType(Test.class);
			if (ar.length > 0 && m.getAnnotationsByType(Test.class)[0] != null) {
				m.invoke(cTest);
			}
		}
	}

	///////////////////////////////////////////////////

	@Test
	void appShouldNotUseForbiddenAPI() throws IOException, URISyntaxException {
		URL url = getClass().getResource("forbidden-api-regex.txt");
		String regex = Files.readString(Path.of(url.toURI()));
		Pattern forbiddenAPIRegex = Pattern.compile(regex.toString());
		StringBuilder errorMessage = new StringBuilder();
		for (CtType<?> ctType : spoon.getModel().getAllTypes()) {
			List<String> forbiddenAPI = ctType.getElements(new TypeFilter<>(CtTypeReference.class))
				.stream()
				.distinct()
				.filter(r -> forbiddenAPIRegex.matcher(r.toString()).matches())
				.map(CtTypeReference::getQualifiedName)
				.toList();
			if (!forbiddenAPI.isEmpty()) {
				errorMessage.append(EOL)
					.append(ctType.getQualifiedName()).append(": ")
					.append(forbiddenAPI);
			}
		}
		if (!errorMessage.isEmpty()) {
			fail(() -> "Using of this API is forbidden: " + errorMessage);
		}
	}

	@Test
	void shouldBeAppropriateNumberOfPackagesAndClasses() throws IOException, URISyntaxException {
		URL url = getClass().getResource("list-of-types.txt");
		String expected = Files.readString(Path.of(url.toURI()));
		// '\n' character is used for clarity in error message 
		String actual = spoon.getModel().getAllPackages().stream()
			.filter(p -> p.getTypes().size() != 0)
			.map(p -> p.getTypes().stream()
					.map(CtType::getQualifiedName)
					.sorted()
					.collect(Collectors.joining("\n")))
			.collect(Collectors.joining("\n"));
		assertEquals('\n' + expected, '\n' + actual);
	}

}
