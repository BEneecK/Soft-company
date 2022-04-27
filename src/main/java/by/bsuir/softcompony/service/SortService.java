package by.bsuir.softcompony.service;

import by.bsuir.softcompony.entity.Client;
import by.bsuir.softcompony.entity.Task;

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

    public static Iterable<Task> sortByStageTask(Iterable<Task> sentClients, String stage) {
        List<Task> tasks = Converter.iterableToList(sentClients);
        List<Task> finishTasks = new ArrayList<>();

        for(int i = 0; i < tasks.size(); i++) {
            if(tasks.get(i).getStage().getStage().equals(stage)) {
                finishTasks.add(tasks.get(i));
            }
        }
        return finishTasks;
    }
}
