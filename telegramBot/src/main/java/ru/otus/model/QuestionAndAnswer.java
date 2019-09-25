package ru.otus.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "QuestionsAndAnswers")
public class QuestionAndAnswer {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";
    @Id
    private long id;

    private String question;

    private String answer;

    private String additionInfo;

    public QuestionAndAnswer() {
    }

    public QuestionAndAnswer(long id,String question, String answer, String info) {
        this.question = question;
        this.answer = answer;
        this.id = id;
        this.additionInfo = info;
    }

    public String getAdditionInfo() {
        return additionInfo;
    }

    public void setAdditionInfo(String additionInfo) {
        this.additionInfo = additionInfo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionAndAnswer{" +
                "id='" + id + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
