package ru.otus.servis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.otus.model.DatabaseSequence;
import ru.otus.model.QuestionAndAnswer;
import ru.otus.repository.QuestionAndAnswerRepository;

import java.util.List;
import java.util.Objects;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {
    private MongoOperations mongoOperations;
    @Autowired
    private QuestionAndAnswerRepository repository;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

    //добовляем модель в бд
    public QuestionAndAnswer create(String question, String answer, String info) {
        return repository.save(new QuestionAndAnswer(generateSequence(QuestionAndAnswer.SEQUENCE_NAME), question, answer, info));
    }

    //выбираем все модели в бд
    public List<QuestionAndAnswer> getAll() {
        return repository.findAll();
    }

    //находим нужный обьект по вопросу, изменяем и обновляем бд
    public QuestionAndAnswer update(String question, String answer, String info) {
        QuestionAndAnswer questionAndAnswer = repository.findByQuestion(question);
        questionAndAnswer.setQuestion(question);
        questionAndAnswer.setAnswer(answer);
        questionAndAnswer.setAdditionInfo(info);
        return repository.save(questionAndAnswer);
    }

    //находим нужный обьект по ID, изменяем и обновляем бд.
    public QuestionAndAnswer updateById(int id, String question, String answer, String info) {
        QuestionAndAnswer questionAndAnswer = repository.findById(id);
        questionAndAnswer.setQuestion(question);
        questionAndAnswer.setAnswer(answer);
        questionAndAnswer.setAdditionInfo(info);
        return repository.save(questionAndAnswer);
    }

    //удалить все модели в бд
    public void deleteAll() {
        repository.deleteAll();
    }

    //удалить выбранную модель
    public void delete(int id) {
        QuestionAndAnswer questionAndAnswer = repository.findById(id);
        repository.delete(questionAndAnswer);
    }
}
