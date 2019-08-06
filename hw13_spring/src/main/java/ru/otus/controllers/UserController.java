package ru.otus.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.repostore.DBServiceRepositoreImpl;

import java.util.List;

@Controller
public class UserController {
    private final DBServiceRepositoreImpl serviceRepositore;

    public UserController(DBServiceRepositoreImpl serviceRepositore) {
        this.serviceRepositore = serviceRepositore;
    }

    @GetMapping("/user/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new UserDataSet());
        model.addAttribute("address", new AddressDataSet());
        model.addAttribute("phone", new PhoneDataSet());
        return "userCreate";
    }

    @PostMapping("user/save")
    public RedirectView userSave(@ModelAttribute UserDataSet userDataSet){
        serviceRepositore.create(userDataSet);
        return new RedirectView("/user/list", true);
    }

    @GetMapping({"/", "/user/list"})
    public String userList(Model model) {
        List<UserDataSet> users = serviceRepositore.allUsers();
        model.addAttribute("users", users);
        return "userList.html";
    }
}
