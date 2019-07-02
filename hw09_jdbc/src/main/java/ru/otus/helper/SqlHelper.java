package ru.otus.helper;

import ru.otus.dao.MyId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SqlHelper {
    public static <T> String getSelectSqlQuery(Class<T> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> listFieldName = new ArrayList<>();
        String idColumnName = "";

        for (Field field : fields) {
            if (field.getAnnotation(MyId.class) != null) {
                idColumnName = field.getName();
            }
            field.setAccessible(true);
            listFieldName.add(field.getName());
        }

        String columnNames = String.join(",", listFieldName);
        String tableName = clazz.getSimpleName();

        return String.format("SELECT %s FROM %s WHERE %s=?", columnNames, tableName, idColumnName);
    }

    public static <T> String getInsertSqlQuery(Class<T> clazz) {
        List<String> listFieldName = new ArrayList<>();
        List<String> listFieldNameSimvol = new ArrayList<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
           // field.setAccessible(true);
            if (field.getAnnotation(MyId.class) == null) {
                field.setAccessible(true);
                listFieldName.add(field.getName());
            }
        }
        for (int i = 0; i < listFieldName.size(); i++) {
            String numberOfValues = "?";
            listFieldNameSimvol.add(numberOfValues);
        }
        String tableName = clazz.getSimpleName();
        String columnNames = String.join(",", listFieldName);
        String valuesCount = String.join(",", listFieldNameSimvol);

        return String.format("insert into %s (%s) values (%s)", tableName, columnNames, valuesCount);
    }
}
