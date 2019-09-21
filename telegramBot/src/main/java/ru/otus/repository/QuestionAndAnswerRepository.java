package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.model.QuestionAndAnswer;

public interface QuestionAndAnswerRepository extends MongoRepository<QuestionAndAnswer,Integer> {

}
