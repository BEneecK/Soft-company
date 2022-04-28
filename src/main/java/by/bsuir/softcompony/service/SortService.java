package by.bsuir.softcompony.service;

import by.bsuir.softcompony.entity.Client;
import by.bsuir.softcompony.entity.Task;
import by.bsuir.softcompony.entity.Vacancy;

import java.util.ArrayList;
import java.util.List;

public class SortService {

    public static Iterable<Client> sortByStageClient(Iterable<Client> sentClients, String stage) {
        List<Client> clients = Converter.iterableToList(sentClients);
        List<Client> finishClients = new ArrayList<>();

        for(int i = 0; i < clients.size(); i++) {
            if(clients.get(i).getTask().getStage().getStage().equals(stage)) {
                finishClients.add(clients.get(i));
            }
        }
        return finishClients;
    }

    public static Iterable<Task> sortByStageTask(Iterable<Task> sentTasks, String stage) {
        List<Task> tasks = Converter.iterableToList(sentTasks);
        List<Task> finishTasks = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getStage().getStage().equals(stage)) {
                finishTasks.add(tasks.get(i));
            }
        }
        return finishTasks;
    }

    public static Iterable<Task> sortByTakenTask(Iterable<Task> sentTasks) {
        List<Task> tasks = Converter.iterableToList(sentTasks);
        List<Task> finishTasks = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getUser() == null) {
                finishTasks.add(tasks.get(i));
            }
        }
        return finishTasks;
    }

    public static Iterable<Task> sortByPersonalTask(Iterable<Task> sentTasks, Long id) {
        List<Task> tasks = Converter.iterableToList(sentTasks);
        List<Task> finishTasks = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getUser().getId() == id) {
                finishTasks.add(tasks.get(i));
            }
        }
        return finishTasks;
    }

    public static Iterable<Vacancy> sortByResponseVacancy(Iterable<Vacancy> sentVacancies) {
        List<Vacancy> vacancies = Converter.iterableToList(sentVacancies);
        List<Vacancy> finishVacancies = new ArrayList<>();

        for(int i = 0; i < vacancies.size(); i++) {
            if(vacancies.get(i).getVacancyResponse() != null) {
                finishVacancies.add(vacancies.get(i));
            }
        }
        return finishVacancies;
    }
}
