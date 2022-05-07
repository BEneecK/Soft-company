package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.controller.consts.StageConsts;
import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.UserPosition;
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

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class TesterController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/tester")
    public String homePage(Model model) {

        //Вывод задач в разработке
        Iterable<Task> tasks = taskRepository.findAll();
        tasks = SortService.sortByStageTask(tasks, StageConsts.TESTING);
        model.addAttribute("tasks", tasks);

        return "testerPage";
    }

    @GetMapping("/tester/accept-task/{id}")
    public String acceptTaskPage(@PathVariable(value = "id") long taskId, Model model) {

        return "testerAcceptTask";
    }

    @PostMapping("/tester/accept-task/{id}")
    public String acceptTask(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        Stage stage = stageRepository.findByStage(StageConsts.REALISATION);

        task.setStage(stage);
        taskRepository.save(task);

        return "redirect:/tester";
    }

    @PostMapping("/tester/reject-task/{id}")
    public String rejectTask(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        Stage stage = stageRepository.findByStage(StageConsts.DEVELOPING);

        task.setStage(stage);
        task.setSolutionDocName(null);
        taskRepository.save(task);

        return "redirect:/tester";
    }
}
