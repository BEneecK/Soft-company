package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.UserPosition;
import by.bsuir.softcompony.entity.repository.UserPositionRepository;
import by.bsuir.softcompony.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPositionRepository userPositionRepository;

    @GetMapping("/")
    public String homePage(Model model) {
        Iterable<User> users = userRepository.findAll();
       // model.addAttribute("file", new File("D:\\upload\\hey.txt"));
       /* Iterable<UserPosition> positions = userPositionRepository.findAll();
        model.addAttribute("pos", positions);*/
        return "homePage";
    }

}
