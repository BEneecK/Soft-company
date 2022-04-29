package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.*;
import by.bsuir.softcompony.entity.repository.*;
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

    private static final String CONSIDERATION = "Рассмотрение";
    private static final String DEVELOPING = "Разработка";
    private static final String TESTING = "Тестирование";
    private static final String REALISATION = "Реализация";
    private static final String DONE = "Завершено";
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserPositionRepository userPositionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private VacancyResponseRepository vacancyResponseRepository;
    @Autowired
    private VacancyRepository vacancyRepository;

    @GetMapping("/admin/development")
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

        return "adminDevelopmentPage";
    }

    @PostMapping("/admin/development/accept/{id}")
    public String realisationTask(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        Stage stage = stageRepository.findByStage(DONE);

        task.setStage(stage);
        taskRepository.save(task);

        //TODO Отправка сообщения на почту о завершении проекта

        return "redirect:/admin/development";
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

        return "redirect:/admin/users";
    }

    @PostMapping("/admin/delete-user/{id}")
    public String removeUser(@PathVariable(value = "id") long userId, Model model) {

        User user = userRepository.findById(userId).orElseThrow();
        userRepository.delete(user);

        return "redirect:/admin/users";
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

        return "redirect:/admin/users";
    }

    @GetMapping("/admin")
    public String ordersPage(Model model) {
        Iterable<Task> tasks = taskRepository.findAll();
        model.addAttribute("orders", SortService.sortByStageTask(tasks, CONSIDERATION));
        return "adminOrders";
    }

    @GetMapping("/admin/order/{id}")
    public String orderPage(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();
        model.addAttribute("companyName", task.getClient().getCompanyName());
        model.addAttribute("email", task.getClient().getEmail());
        model.addAttribute("description", task.getDescription());
        model.addAttribute("docName", task.getTaskDocName());

        return "adminOrder";
    }

    @PostMapping("/admin/order/{id}")
    public String acceptOrder(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();

        Stage stage = stageRepository.findByStage(DEVELOPING);
        task.setStage(stage);
        taskRepository.save(task);

        //TODO ОТПРАВКА ПРИНЯТИЯ НА ПОЧТУ

        return "redirect:/admin";
    }

    @PostMapping("/admin/delete-order/{id}")
    public String removeOrder(@PathVariable(value = "id") long taskId, Model model) {

        Task task = taskRepository.findById(taskId).orElseThrow();
        taskRepository.delete(task);

        //TODO ОТПРАВКА ОТКЛОНЕНИЯ НА ПОЧТУ

        return "redirect:/admin";
    }

    @GetMapping("/admin/responses")
    public String responsesPage(Model model) {
        Iterable<Vacancy> vacancies = vacancyRepository.findAll();
        model.addAttribute("responses", SortService.sortByResponseVacancy(vacancies));
        return "adminResponses";
    }

    @GetMapping("/admin/response/{id}")
    public String responsePage(@PathVariable(value = "id") long responseId, Model model) {

        VacancyResponse vacancyResponse = vacancyResponseRepository.findById(responseId).orElseThrow();
        model.addAttribute("vacancy", vacancyResponse.getVacancy().getName());
        model.addAttribute("level", vacancyResponse.getVacancy().getLevel());
        model.addAttribute("category", vacancyResponse.getVacancy().getCategory());
        model.addAttribute("lastName", vacancyResponse.getLastName());
        model.addAttribute("firstName", vacancyResponse.getFirstName());
        model.addAttribute("email", vacancyResponse.getEmail());
        model.addAttribute("docName", vacancyResponse.getCVDocName());

        return "adminResponse";
    }

    @PostMapping("/admin/response/{id}")
    public String acceptResponse(@PathVariable(value = "id") long responseId, Model model) {

        VacancyResponse vacancyResponse = vacancyResponseRepository.findById(responseId).orElseThrow();

        Vacancy vacancy = vacancyRepository.findByVacancyResponse(vacancyResponse);

        vacancy.setVacancyResponse(null);
        vacancyResponseRepository.delete(vacancyResponse);

        //TODO ОТПРАВКА ПРИНЯТИЯ НА ПОЧТУ

        return "redirect:/admin/responses";
    }

    @PostMapping("/admin/delete-response/{id}")
    public String removeResponse(@PathVariable(value = "id") long responseId, Model model) {

        VacancyResponse vacancyResponse = vacancyResponseRepository.findById(responseId).orElseThrow();
        Vacancy vacancy = vacancyRepository.findByVacancyResponse(vacancyResponse);

        vacancy.setVacancyResponse(null);
        vacancyResponseRepository.delete(vacancyResponse);

        //TODO ОТПРАВКА ОТКЛОНЕНИЯ НА ПОЧТУ

        return "redirect:/admin/responses";
    }
}
