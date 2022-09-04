package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.model.QuestionAndAnswer;
import ru.otus.servis.SequenceGeneratorService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class QuestionAndAnswerController {


    private final SequenceGeneratorService service;

    @RequestMapping("/create")
    public String create(@RequestParam String question, @RequestParam String answer, @RequestParam String info) {
        final QuestionAndAnswer andAnswer = service.create(question, answer, info);
        return andAnswer.toString();
    }

    @RequestMapping("/getAll")
    public List<QuestionAndAnswer> getAll() {
        return service.getAll();
    }

    @RequestMapping("/update")
    public String update(@RequestParam int id, @RequestParam String question, @RequestParam String answer, @RequestParam String info) {
        final QuestionAndAnswer questionAndAnswer = service.updateById(id, question, answer, info);
        return questionAndAnswer.toString();
    }

    @RequestMapping("/deleteAll")
    public String deleteAll() {
        service.deleteAll();
        return "DeleteAll: " + service.getAll().toString();
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam int id) {
        service.delete(id);
        return "Delete: " + id;
    }
}
