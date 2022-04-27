package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.Client;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.User;
import by.bsuir.softcompony.entity.repository.ClientRepository;
import by.bsuir.softcompony.entity.repository.TaskRepository;
import by.bsuir.softcompony.entity.repository.UserRepository;
import by.bsuir.softcompony.service.SortService;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private static final String DEVELOPING = "Разработка";
    private static final String TESTING = "Тестирование";
    private static final String REALISATION = "Реализация";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/admin")
    public String homePage(Model model) {

        Iterable<Client> clientsDev = clientRepository.findAll();
        model.addAttribute("clientsDev", SortService.sortByStageClient(clientsDev, DEVELOPING));

        Iterable<Client> clientsTest = clientRepository.findAll();
        model.addAttribute("clientsTest", SortService.sortByStageClient(clientsTest, TESTING));

        Iterable<Client> clientsReal = clientRepository.findAll();
        model.addAttribute("clientsReal", SortService.sortByStageClient(clientsReal, REALISATION));

        Iterable<Task> taskDev = taskRepository.findAll();
        model.addAttribute("tasksDev", SortService.sortByStageTask(taskDev, DEVELOPING));

        Iterable<Task> taskTest = taskRepository.findAll();
        model.addAttribute("tasksTest", SortService.sortByStageTask(taskDev, TESTING));

        Iterable<Task> taskReal = taskRepository.findAll();
        model.addAttribute("tasksReal", SortService.sortByStageTask(taskDev, REALISATION));

        return "adminPage";
    }

    @GetMapping("/admin/users")
    public String usersPage(Model model) {
        Iterable<User> users = userRepository.findAll();
        model.addAttribute("users", users);

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);
        return "adminUsers";
    }
}
