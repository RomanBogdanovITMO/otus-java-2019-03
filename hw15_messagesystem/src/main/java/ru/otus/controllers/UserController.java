package ru.otus.controllers;


/*import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.dataset.UserDataSet;
import ru.otus.repostore.DBServiceRepositore;

import java.util.List;

@Controller
public class UserController {

    private  DBServiceRepositore dbServiceRepositore;

    public UserController(DBServiceRepositore dbServiceRepositore) {
        this.dbServiceRepositore = dbServiceRepositore;
    }

    @GetMapping("/user/create")
    public String userCreate(Model model) {
        model.addAttribute("user", new UserDataSet() );
        return "userCreate";
    }

    @PostMapping("user/save")
    public RedirectView userSave(@ModelAttribute UserDataSet userDataSet){
        dbServiceRepositore.create(userDataSet);
        return new RedirectView("/user/list", true);
    }

    @GetMapping({"/", "/user/list"})
    public String userList(Model model) {
        List<UserDataSet> users = dbServiceRepositore.allUsers();
        model.addAttribute("users", users);
        return "userList.html";
    }

}*/
