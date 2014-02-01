package gerritdrost.com.apps.mathie.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ReflectionUtils {

	public static Collection<Field> getAllFields(Object object) {
		return getAllFields(object.getClass());
	}

	public static Collection<Field> getAllFields(Class<?> clazz) {
		Set<Field> fieldList = new HashSet<Field>();

		Stack<Class<?>> classes = new Stack<Class<?>>();

		classes.push(clazz);

		while (!classes.isEmpty()) {

			Class<?> currentClass = classes.pop();

			Class<?> superClass = currentClass.getSuperclass();

			if (superClass != null)
				classes.push(superClass);

			for (Field field : currentClass.getDeclaredFields())
				fieldList.add(field);
		}

		return fieldList;
	}
}
