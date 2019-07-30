package ru.otus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.dataset.UserDataSet;
import ru.otus.repostore.DBServiceRepositoreImpl;

@Controller
public class UserController {
    private final DBServiceRepositoreImpl serviceRepositore;

    public UserController(DBServiceRepositoreImpl serviceRepositore) {
        this.serviceRepositore = serviceRepositore;
    }

    @GetMapping("/user/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new UserDataSet());
        return "userCreate.html";
    }
}
