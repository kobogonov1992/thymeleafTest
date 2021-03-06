package kg.megacom.thymeleaf.controller;

import kg.megacom.thymeleaf.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Controller
public class UserController {

    private List<User> userList;
    private Long userId;
//    ThreadLocalRandom.current().nextLong();
//    Random random = new Random();
    public UserController(List<User> userList, Long userId) {
        this.userList = userList;
        this.userId = userId;

//        User u = new User( userId,"Dima","asd","ada");
//        User u2 = new User( userId,"Dima2","asd","ada");
//        userList.add(u);
//        userList.add(u2);

//        System.out.printf(userList.toString()+" d");
    }

    @GetMapping("/index")
    public String showUserList(Model model) {
        model.addAttribute("users", userList);
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User user) {
        return "add-user";
    }

    @PostMapping("/addUser")
    public String addUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        user.setId(userId++);
        userList.add(user);

        return "redirect:/index";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {

        User user = userList.stream().filter(u-> u.getId() == id).findFirst().get();
        model.addAttribute("user", user);
        return "update-user";
    }


    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "update-user";
        }

        User user1 = userList.stream().filter(u-> u.getId() == id).findFirst().get();

            userList.remove(user1);
            userList.add(user);

        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        User user = userList.stream().filter(u -> u.getId() == id).findFirst().get();
        userList.remove(user);
        return "redirect:/index";
    }
}
