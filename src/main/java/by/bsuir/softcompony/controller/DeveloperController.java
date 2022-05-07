package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.controller.consts.PathConsts;
import by.bsuir.softcompony.controller.consts.StageConsts;
import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.repository.StageRepository;
import by.bsuir.softcompony.entity.repository.TaskRepository;
import by.bsuir.softcompony.entity.repository.UserRepository;
import by.bsuir.softcompony.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class DeveloperController {

    private static final String MESSAGE = "Решение отправлено";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/dev/{id}")
    public String homePage(@PathVariable(value = "id") long userId, Model model) {

        //Передача айди пользователя
        User user = userRepository.findById(userId).orElseThrow();
        model.addAttribute("id", user.getId());

        //Вывод задач в разработке
        Iterable<Task> tasks = taskRepository.findAll();
        tasks = SortService.sortByStageTask(tasks, StageConsts.DEVELOPING);
        model.addAttribute("tasks", SortService.sortByTakenTask(tasks));

        return "developerPage";
    }

    @PostMapping("/dev/{dev_id}/accept-task/{task_id}")
    public String acceptTask(@PathVariable(value = "dev_id") long devId,
                             @PathVariable(value = "task_id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();
        User user = userRepository.findById(devId).orElseThrow();

        task.setUser(user);
        taskRepository.save(task);

        String URL = "redirect:/dev/" + devId;
        return URL;
    }

    @GetMapping("/dev/{id}/tasks")
    public String personalTasks(@PathVariable(value = "id") long userId, Model model) {

        //Передача айди пользователя
        User user = userRepository.findById(userId).orElseThrow();
        model.addAttribute("id", user.getId());

        //Вывод задач в разработке
        Iterable<Task> tasks = taskRepository.findAll();
        tasks = SortService.sortByStageTask(tasks, StageConsts.DEVELOPING);
        model.addAttribute("tasks", SortService.sortByPersonalTask(tasks, userId));

        return "developerPersonalTasks";
    }

    @GetMapping("/dev/{dev_id}/tasks/{task_id}/send-solution")
    public String sendSolution(@PathVariable(value = "dev_id") long userId,
                               @PathVariable(value = "task_id") long taskId, Model model) {

        return "developerSendSolution";
    }

    @PostMapping("/dev/{dev_id}/tasks/{task_id}/send-solution")
    public String sendSolution(@PathVariable(value = "dev_id") long devId, @PathVariable(value = "task_id") long taskId,
                               @RequestParam("file") MultipartFile file, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();
        task.setSolutionDocName(file.getOriginalFilename());

        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File(PathConsts.FOLDER_PATH_SOLUTIONS + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = stageRepository.findByStage(StageConsts.TESTING);
        task.setStage(stage);

        taskRepository.save(task);

        model.addAttribute("message", MESSAGE);

        String URL = "redirect:/dev/" + devId + "/tasks";
        return URL;
    }
}
