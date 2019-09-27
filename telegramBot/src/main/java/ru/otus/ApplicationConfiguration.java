package ru.otus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.otus.model.QuestionAndAnswer;
import ru.otus.servis.SequenceGeneratorService;
import ru.otus.repository.QuestionAndAnswerRepository;

@EnableMongoRepositories(basePackageClasses = QuestionAndAnswerRepository.class)
@Configuration
public class ApplicationConfiguration {

    @Bean
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi();
    }

    @Bean
    CommandLineRunner commandLineRunner(QuestionAndAnswerRepository andAnswerRepository, SequenceGeneratorService service) {
        return strings -> {

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "/start", "выберите команду: #1-помощь #2-выбрать сумку", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "1", "#3-номер технической поддержки #4-другое", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "3", "скоро будет", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "4", "ваше предложение в рассширении функционала Bota", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "/помощь", "#3-номер технической поддержки #4-другое", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "2", "#5-по размеру #6-по цвету", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "5", "размер:#7-большая #8-средняя #9-маленькая #10-показать все", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "6", "цвет:#7б - большая белая #8б - средняя белая #9б - маленькая белая" +
                    "#7ч - большая черная #8ч - средняя черная #9ч - маленькая черная" +
                    "#7ц - большая цветная #8ц - средняя цветная #9ц - маленькая цветная", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "7ч", "added", "big_black_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "8ч", "added", "medium_black_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "9ч", "added", "small_black_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "7ц", "added", "big_color_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "8ц", "added", "medium_color_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "9ц", "added", "small_color_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "7б", "added", "big_white_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "8б", "added", "medium_white_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "9б", "added", "small_white_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "/выбрать сумку", "5-по размеру #6-по цвету", ""));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "7", "added", "big_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "8", "added", "medium_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "9", "added", "small_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "10", "added", "all_bag"));

            andAnswerRepository.save(new QuestionAndAnswer(service.generateSequence(QuestionAndAnswer.SEQUENCE_NAME),
                    "/показать все", "added", "all_bag"));
        };
    }

}
