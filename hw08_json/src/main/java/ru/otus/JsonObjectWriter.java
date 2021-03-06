package ru.otus;

import javax.json.*;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

public class JsonObjectWriter {
    public String writeToJson(Object obj){
        try {
            JsonObject jsonObject = objectToJson(obj);
            return jsonObject.toString();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private JsonObject objectToJson(Object obj) throws Exception {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Field[] files = obj.getClass().getDeclaredFields();
        for (Field field: files){
            field.setAccessible(true);
            int mod = field.getModifiers();
            if (!(Modifier.isStatic(mod) || Modifier.isTransient(mod))){
                objectBuilder.add(field.getName(),elementToJson(field.get(obj),field.getType()));
            }
        }
        return objectBuilder.build();
    }

    private JsonValue elementToJson(Object element,Class<?> type) throws Exception {
        if (type.isPrimitive()){
            return simpleObjectToJson(element);
        }else if (type.isArray()){
            return arrayToJson(element);
        }else if (Collection.class.isAssignableFrom(type)){
            Collection<?>collection = (Collection<?>)element;
            return arrayToJson(collection.toArray());
        }else if (Map.class.isAssignableFrom(type)){
            return mapToJson(element);
        }else {
            return simpleObjectToJson(element);
        }

    }

    private JsonArray arrayToJson(Object obj) throws Exception {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        int length = Array.getLength(obj);

        for (int i = 0; i < length; i++) {
            Object element = Array.get(obj, i);
            arrayBuilder.add(elementToJson(element, element.getClass()));
        }
        return arrayBuilder.build();
    }

    private JsonValue simpleObjectToJson(Object obj) {
        if (obj instanceof Number) {
            Number number = (Number) obj;
            if (number instanceof Double || number instanceof Float) {
                return Json.createValue(number.doubleValue());
            } else if (number instanceof Long) {
                return Json.createValue(number.longValue());
            } else {
                return Json.createValue(number.intValue());
            }
        } else if (obj instanceof Boolean) {
            return obj.equals(true) ? JsonValue.TRUE : JsonValue.FALSE;
        } else {
            return Json.createValue(obj.toString());
        }
    }
    private JsonObject mapToJson(Object obj) throws Exception {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        Map<?, ?> map = (Map<?, ?>) obj;

        for (Object key : map.keySet()) {
            Object value = map.get(key);
            objectBuilder.add(key.toString(), elementToJson(value, value.getClass()));
        }
        return objectBuilder.build();
    }
}
