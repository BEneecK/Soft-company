package by.bsuir.softcompony.controller;

import by.bsuir.softcompony.entity.Stage;
import by.bsuir.softcompony.entity.Vacancy;
import by.bsuir.softcompony.entity.VacancyResponse;
import by.bsuir.softcompony.entity.repository.StageRepository;
import by.bsuir.softcompony.entity.repository.VacancyRepository;
import by.bsuir.softcompony.entity.repository.VacancyResponseRepository;
import net.bytebuddy.dynamic.DynamicType;
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
import java.util.Optional;

@Controller
public class VacancyController {

    private static final String MESSAGE = "Отклик отправлен";
    @Autowired
    private VacancyRepository vacancyRepository;
    @Autowired
    private VacancyResponseRepository vacancyResponseRepository;
    @Autowired
    private StageRepository stageRepository;

    @GetMapping("/vacancies")
    public String vacancyForm(Model model) {
        Iterable<Vacancy> vacancies = vacancyRepository.findAll();
        model.addAttribute("vacancies", vacancies);
        return "vacancy";
    }

    @GetMapping("/vacancies/{id}")
    public String responseForm(/*@PathVariable(value = "id") long VacancyId, */Model model) {

        return "response";
    }

    @PostMapping("/vacancies/{id}")
    public String makeResponse(@PathVariable(value = "id") long vacancyId, @RequestParam String lastName,
                               @RequestParam String firstName, @RequestParam String email,
                               @RequestParam("file") MultipartFile file, Model model) {

        //Сохранение файла
        String fileName = file.getOriginalFilename();
        try {
            file.transferTo(new File("D:\\upload\\" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Optional<Vacancy> vacancyTmp = vacancyRepository.findById(vacancyId);
        //Выбранная вакансия
        Vacancy vacancy = vacancyTmp.get();
        //Сохранение отклика
        VacancyResponse vacancyResponse = new VacancyResponse(firstName, lastName, email, fileName, vacancy);
        vacancyResponseRepository.save(vacancyResponse);

        //Изменение этапа
        Stage stage = stageRepository.findByStage("Рассмотрение");
        vacancy.setStage(stage);
        vacancy.setVacancyResponse(vacancyResponse);
        vacancyRepository.save(vacancy);

        model.addAttribute("message", MESSAGE);
        return "response";
    }
}
