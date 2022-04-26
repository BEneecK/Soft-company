package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    private static final String ERROR_MESSAGE = "Неверный логин или пароль";
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/sign-in")
    public String signInForm(Model model) {
        return "signIn";
    }

    @PostMapping("/sign-in")
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        User user = new User();
        if(userRepository.existsByEmail(email)) {
            user = userRepository.findByEmail(email);
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return "redirect:/admin/";
            }
        }
        else {
            model.addAttribute("error", ERROR_MESSAGE);
        }

//        UserRole role = userRoleRepository.findByRole("Пользователь");
//        User user = new User(firstName, lastName, email, password, 5.0, false, role ,null);
//        userRepository.save(user);
        return "signIn";
    }
}