package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.controller.consts.PathConsts;
import by.bsuir.softcompony.entity.Client;
import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.repository.StageRepository;
import by.bsuir.softcompony.entity.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/order")
public class OrderController {


    private static final String MESSAGE = "Заявка отправлена";

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StageRepository stageRepository;

    @GetMapping
    public String orderForm(Model model) {
        return "order";
    }

    @PostMapping
    public String makeOrder(@RequestParam String company, @RequestParam String phoneNumber, @RequestParam String email,
                            @RequestParam String description, @RequestParam("file")MultipartFile file, Model model) {

        Client client = new Client();
        client.setCompanyName(company);
        client.setPhoneNumber(phoneNumber);
        client.setEmail(email);
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(PathConsts.FOLDER_PATH_TASKS + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = stageRepository.findByStage("Рассмотрение");
        Task task = new Task(description, fileName, null, client, stage, null);
        taskRepository.save(task);

        model.addAttribute("message", MESSAGE);
        return "order";
    }
}
