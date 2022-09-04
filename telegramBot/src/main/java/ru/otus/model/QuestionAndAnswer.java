package ru.otus.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;


@Document(collection = "QuestionsAndAnswers")
@Data
@Builder
@AllArgsConstructor
public class QuestionAndAnswer {

    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long id;

    @NotBlank
    private String question;

    @NotBlank
    private String answer;

    @NotBlank
    private String additionInfo;

}
