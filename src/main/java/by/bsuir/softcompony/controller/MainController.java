package by.bsuir.softcompony.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.File;

@Controller
public class MainController {

    @GetMapping("/")
    public String homePage(Model model) {

        return "homePage";
    }

}
