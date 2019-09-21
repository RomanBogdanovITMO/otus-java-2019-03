package ru.otus;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.telegram.telegrambots.TelegramBotsApi;
import ru.otus.model.QuestionAndAnswer;
import ru.otus.repository.QuestionAndAnswerRepository;

@EnableMongoRepositories(basePackageClasses = QuestionAndAnswerRepository.class)
@Configuration
public class ApplicationConfiguration {


    @Bean
    public TelegramBotsApi telegramBotsApi(){
        return new TelegramBotsApi();
    }

    @Bean
    CommandLineRunner commandLineRunner(QuestionAndAnswerRepository andAnswerRepository) {
        return strings -> {
            andAnswerRepository.save(new QuestionAndAnswer(0,"/start","выберите команду:-/помощь-/выбрать сумку",""));
            andAnswerRepository.save(new QuestionAndAnswer(1,"/помощь","-/номер технической поддержки -/другое",""));
            andAnswerRepository.save(new QuestionAndAnswer(2,"/выбрать сумку","размер:-/большая -/средняя -/маленькая-/показать все",""));
            andAnswerRepository.save(new QuestionAndAnswer(3,"/большая","добавлено","1"));
            andAnswerRepository.save(new QuestionAndAnswer(4,"/средняя","добавлено","2"));
            andAnswerRepository.save(new QuestionAndAnswer(5,"/маленькая","добавлено","3"));
            andAnswerRepository.save(new QuestionAndAnswer(6,"/показать все","добавлено","all"));
            andAnswerRepository.save(new QuestionAndAnswer(7,"/просмотр","весь осортимент","пожалуйста выберите сумку"));

        };
    }

}
