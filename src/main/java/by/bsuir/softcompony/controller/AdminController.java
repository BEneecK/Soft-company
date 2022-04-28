package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.*;
import by.bsuir.softcompony.entity.repository.ClientRepository;
import by.bsuir.softcompony.entity.repository.TaskRepository;
import by.bsuir.softcompony.entity.repository.UserPositionRepository;
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
public class AdminController {

    private static final String DEVELOPING = "Разработка";
    private static final String TESTING = "Тестирование";
    private static final String REALISATION = "Реализация";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPositionRepository userPositionRepository;
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
        return "adminUserPage";
    }

    @GetMapping("/admin/edit-user/{id}")
    public String usersUpdatePage(@PathVariable(value = "id") long userId, Model model) {
        Optional<User> user = userRepository.findById(userId);
        ArrayList<User> users = new ArrayList<>();
        user.ifPresent(users::add);
        model.addAttribute("user", users);
        return "adminUserEdit";
    }

    @PostMapping("/admin/edit-user/{id}")
    public String updateUser(@PathVariable(value = "id") long userId, @RequestParam String firstName,
                            @RequestParam String lastName, @RequestParam String email,
                            @RequestParam String password,  @RequestParam String position, Model model) {

        //Поиск должности
        UserPosition userPosition = userPositionRepository.findByPosition(position);

        //Внос изменённых данных
        User user = userRepository.findById(userId).orElseThrow();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setUserPosition(userPosition);
        userRepository.save(user);

        return "redirect:/admin/users/";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String removeUser(@PathVariable(value = "id") long userId, Model model) {

        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);

        return "redirect:/admin/users/";
    }

    @GetMapping("/admin/add-user")
    public String addUserPage(Model model) {

        return "adminUserAdd";
    }

    @PostMapping("/admin/add-user")
    public String addUser(@RequestParam String firstName,
                             @RequestParam String lastName, @RequestParam String email,
                             @RequestParam String password,  @RequestParam String position, Model model) {

        //Поиск должности
        UserPosition userPosition = userPositionRepository.findByPosition(position);

        //Внос изменённых данных
        User user = new User(firstName, lastName, email, password, userPosition);
        userRepository.save(user);

        return "redirect:/admin/users/";
    }
}
