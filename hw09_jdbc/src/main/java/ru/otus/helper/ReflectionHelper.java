package ru.otus.helper;

import ru.otus.dao.MyId;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReflectionHelper {
    public static <T> T createInstance(ResultSet resultSet, Class<T> clazz) throws SQLException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {

        if (resultSet.next()) {
            T instance = clazz.getDeclaredConstructor().newInstance();
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                field.set(instance, resultSet.getObject(field.getName()));
            }
            return instance;
        }
        return null;
    }

    public static List<Object> getParamObgect(Object object) throws IllegalAccessException {

        List<Object> paramObjects = new ArrayList<>();

        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getAnnotation(MyId.class) == null) {
                paramObjects.add(field.get(object));
            }
        }
        return paramObjects;
    }
}
