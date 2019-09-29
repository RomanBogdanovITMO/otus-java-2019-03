package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.QuestionAndAnswer;


@Repository
public interface QuestionAndAnswerRepository extends MongoRepository<QuestionAndAnswer, Integer> {
    public QuestionAndAnswer findByQuestion(String question);

    public QuestionAndAnswer findById(int id);

}
