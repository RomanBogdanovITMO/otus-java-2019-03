package ru.otus.servis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RequiredArgsConstructor
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;
    private final QuestionAndAnswerRepository repository;


    public long generateSequence(final String seqName) {
        log.info("execute method generateSequence(): {}", seqName);

        final DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

    //добовляем модель в бд
    public QuestionAndAnswer create(final String question, final String answer, final String info) {
        log.info("execute method create: {}, {}, {}", question, answer, info);

        return repository.save(QuestionAndAnswer.builder()
                .id(generateSequence(QuestionAndAnswer.SEQUENCE_NAME))
                .question(question)
                .answer(answer)
                .additionInfo(info)
                .build());
    }

    //выбираем все модели в бд
    public List<QuestionAndAnswer> getAll() {
        log.info("execute method getAll: ");

        return repository.findAll();
    }

    //находим нужный обьект по вопросу, изменяем и обновляем бд
    public QuestionAndAnswer update(final String question, final String answer, final String info) {
        log.info("execute method update: {}, {}, {}", question, answer, info);

        final QuestionAndAnswer questionAndAnswer = repository.findByQuestion(question);
        questionAndAnswer.setQuestion(question);
        questionAndAnswer.setAnswer(answer);
        questionAndAnswer.setAdditionInfo(info);

        return repository.save(questionAndAnswer);
    }

    //находим нужный обьект по ID, изменяем и обновляем бд.
    public QuestionAndAnswer updateById(final int id, final String question, final String answer, final String info) {
        log.info("execute method updateById: {}", id);

        final QuestionAndAnswer questionAndAnswer = repository.findById(id);
        questionAndAnswer.setQuestion(question);
        questionAndAnswer.setAnswer(answer);
        questionAndAnswer.setAdditionInfo(info);

        return repository.save(questionAndAnswer);
    }

    //удалить все модели в бд
    public void deleteAll() {
        log.info("execute method deleteAll: ");

        repository.deleteAll();
    }

    //удалить выбранную модель
    public void delete(int id) {
        log.info("execute method delete: ");

        final QuestionAndAnswer questionAndAnswer = repository.findById(id);
        repository.delete(questionAndAnswer);
    }
}
