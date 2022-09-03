package ru.otus.helper;

import ru.otus.dao.MyId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {
    public static <T> T createInstance(final ResultSet resultSet, final Class<T> clazz) throws SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        if (resultSet.next()) {
            final T instance = clazz.getDeclaredConstructor().newInstance();
            final Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                field.set(instance, resultSet.getObject(field.getName()));
            }
            return instance;
        }
        return null;
    }

    public static List<Object> getParamObject(final Object object) throws IllegalAccessException {

        final List<Object> paramObjects = new ArrayList<>();

        final Class<?> clazz = object.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(MyId.class) == null) {
                paramObjects.add(field.get(object));
            }
        }
        return paramObjects;
    }
}
