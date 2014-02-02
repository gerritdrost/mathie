package gerritdrost.com.apps.mathie.util;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class ReflectionUtilsTest {

	private SimpleObject simpleObject = new SimpleObject();
	private ExtendedObject extendedObject = new ExtendedObject();
	private DoubleExtendedObject doubleExtendedObject = new DoubleExtendedObject();
	private Object anonymousExtendedObject = new DoubleExtendedObject() {
		@SuppressWarnings("unused")
		public Object d1;
		@SuppressWarnings("unused")
		protected Object d2;
		@SuppressWarnings("unused")
		private Object d3;
		@SuppressWarnings("unused")
		Object d4;
	};

	@Before
	public void setup() {

	}

	@Test
	public void checkSimpleObject() {

		Collection<Field> simpleObjectFields = ReflectionUtils.getAllFields(simpleObject);

		assertTrue(simpleObjectFields.size() >= simpleObjectFieldNames.size());

		compare(simpleObjectFieldNames, simpleObjectFields);

	}

	@Test
	public void checkExtendedObject() {

		Collection<Field> extendedObjectFields = ReflectionUtils.getAllFields(extendedObject);

		assertTrue(extendedObjectFields.size() >= extendedObjectFieldNames.size());

		compare(extendedObjectFieldNames, extendedObjectFields);

	}

	@Test
	public void checkDoubleExtendedObject() {

		Collection<Field> doubleExtendedObjectFields = ReflectionUtils.getAllFields(doubleExtendedObject);

		assertTrue(doubleExtendedObjectFields.size() >= doubleExtendedObjectFieldNames.size());

		compare(doubleExtendedObjectFieldNames, doubleExtendedObjectFields);

	}

	@Test
	public void checkAnonymousExtendedObject() {

		Collection<Field> anonymousExtendedObjectFields = ReflectionUtils.getAllFields(anonymousExtendedObject);

		assertTrue(anonymousExtendedObjectFields.size() >= anonymousExtendedObjectFieldNames.size());

		compare(anonymousExtendedObjectFieldNames, anonymousExtendedObjectFields);

	}

	private static void compare(Collection<String> fieldNames, Collection<Field> fields) {

		for (String fieldName : fieldNames) {

			boolean foundOne = false;
			for (Field field : fields) {
				if (field.getName()
							.equals(fieldName)) {
					foundOne = true;
					break;
				}
			}

			assertTrue(foundOne);

		}
	}

	final Collection<String> simpleObjectFieldNames = Arrays.asList(new String[] { "a1", "a2", "a3", "a4" });

	public static class SimpleObject {
		public Object a1;
		protected Object a2;
		@SuppressWarnings("unused")
		private Object a3;
		Object a4;
	}

	final Collection<String> extendedObjectFieldNames = Arrays.asList(new String[] { "a1", "a2", "a3", "a4", "b1", "b2", "b3", "b4" });

	public static class ExtendedObject
			extends SimpleObject {
		public Object b1;
		protected Object b2;
		@SuppressWarnings("unused")
		private Object b3;
		Object b4;
	}

	final Collection<String> doubleExtendedObjectFieldNames = Arrays.asList(new String[] { "a1", "a2", "a3", "a4", "b1", "b2", "b3", "b4",
																							"c1", "c2", "c3", "c4" });

	public static class DoubleExtendedObject
			extends ExtendedObject {
		public Object c1;
		protected Object c2;
		@SuppressWarnings("unused")
		private Object c3;
		Object c4;
	}

	final Collection<String> anonymousExtendedObjectFieldNames = Arrays.asList(new String[] { "a1", "a2", "a3", "a4", "b1", "b2", "b3",
																								"b4", "c1", "c2", "c3", "c4", "d1", "d2",
																								"d3", "d4" });

}
