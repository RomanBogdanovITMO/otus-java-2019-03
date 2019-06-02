package ru.otus;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Persone persone = new Persone("Ivan","Ivanov", 38);
        JsonObjectWriter jW = new JsonObjectWriter();
        Gson gson = new Gson();
//создаем строку с помощью JsonObjectWriter
        String strJson = jW.writeToJson(persone);
        System.out.println(strJson);
// создаем новый обьект используя полученнуюу строку в формате json.
        Persone persone1 = gson.fromJson(strJson,Persone.class);
        System.out.println(persone1.getFirstName());

        if (strJson.equals(gson.toJson(persone))){
            System.out.println("Object is serialised");
        }

    }
}
