package ru.otus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//генерация ID
@Document(collection = "database_sequences")
@Data
@AllArgsConstructor
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

}
