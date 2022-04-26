package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.Client;
import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.repository.StageRepository;
import by.bsuir.softcompony.entity.repository.TaskRepository;
import by.bsuir.softcompony.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class OrderController {



    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/order")
    public String orderForm(Model model) {
        return "order";
    }

    @PostMapping("/order")
    public String makeOrder(@RequestParam String company, @RequestParam String phoneNumber, @RequestParam String email,
                            @RequestParam String description, @RequestParam("file")MultipartFile file, Model model) {

        Client client = new Client();
        client.setCompanyName(company);
        client.setPhoneNumber(phoneNumber);
        client.setEmail(email);
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\Универ\\Курсовая работа\\softcompony\\upload" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = stageRepository.findByStage("Рассмотрение");
        Task task = new Task(description, fileName, null, client, stage, null);
        taskRepository.save(task);
//        User user = new User();
//        if(userRepository.existsByEmail(email)) {
//            user = userRepository.findByEmail(email);
//            if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
//                return "redirect:/admin/";
//            }
//        }
//        else {
//            model.addAttribute("error", ERROR_MESSAGE);
//        }

//        UserRole role = userRoleRepository.findByRole("Пользователь");
//        User user = new User(firstName, lastName, email, password, 5.0, false, role ,null);
//        userRepository.save(user);
        return "order";
    }
}
