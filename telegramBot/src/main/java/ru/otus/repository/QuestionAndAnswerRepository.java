package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.otus.model.QuestionAndAnswer;


@Repository
public interface QuestionAndAnswerRepository extends MongoRepository<QuestionAndAnswer, Integer> {

    QuestionAndAnswer findByQuestion(final String question);

    QuestionAndAnswer findById(final int id);

}
