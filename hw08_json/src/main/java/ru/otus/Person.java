package ru.otus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private int age;
    private boolean children = true;
    private final List<String> phoneNumbers = new ArrayList<>(Arrays.asList("1234", "911", "03", "02"));
    private final String[] cars = {"lada", "porche", "bmw"};

}
