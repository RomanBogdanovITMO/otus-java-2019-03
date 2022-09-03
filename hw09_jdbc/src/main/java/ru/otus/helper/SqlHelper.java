package ru.otus.helper;

import ru.otus.dao.MyId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper {
    public static <T> String getSelectSqlQuery(final Class<T> clazz) {
        final Field[] fields = clazz.getDeclaredFields();
        final List<String> listFieldName = new ArrayList<>();
        String idColumnName = "";

        for (Field field : fields) {
            if (field.getAnnotation(MyId.class) != null) {
                idColumnName = field.getName();
            }
            field.setAccessible(true);
            listFieldName.add(field.getName());
        }

        final String columnNames = String.join(",", listFieldName);
        final String tableName = clazz.getSimpleName();

        return String.format("SELECT %s FROM %s WHERE %s=?", columnNames, tableName, idColumnName);
    }

    public static <T> String getInsertSqlQuery(final Class<T> clazz) {
        final List<String> listFieldName = new ArrayList<>();
        final List<String> listFieldNameSimvol = new ArrayList<>();

        final Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            // field.setAccessible(true);
            if (field.getAnnotation(MyId.class) == null) {
                field.setAccessible(true);
                listFieldName.add(field.getName());
            }
        }
        for (int i = 0; i < listFieldName.size(); i++) {
            final String numberOfValues = "?";
            listFieldNameSimvol.add(numberOfValues);
        }
        final String tableName = clazz.getSimpleName();
        final String columnNames = String.join(",", listFieldName);
        final String valuesCount = String.join(",", listFieldNameSimvol);

        return String.format("insert into %s (%s) values (%s)", tableName, columnNames, valuesCount);
    }
}
