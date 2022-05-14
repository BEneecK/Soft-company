package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.controller.consts.RoleConsts;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sign-in")
public class SignInController {

    private static final String ERROR_MESSAGE = "Неверный логин или пароль";

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public String signInForm(Model model) {
        return "signIn";
    }

    @PostMapping
    public String signIn(@RequestParam String email, @RequestParam String password, Model model) {
        User user = new User();
        if(userRepository.existsByEmail(email)) {
            user = userRepository.findByEmail(email);
            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                if(user.getUserPosition().getPosition().equals(RoleConsts.ADMIN)) {
                    return "redirect:/admin";
                }
                else if(user.getUserPosition().getPosition().equals(RoleConsts.DEVELOPER)) {
                    return "redirect:/dev/" + user.getId();
                }
                else if(user.getUserPosition().getPosition().equals(RoleConsts.TESTER)){
                    return "redirect:/tester";
                }
            }
            else {
                model.addAttribute("error", ERROR_MESSAGE);
            }
        }
        return "signIn";
    }
}