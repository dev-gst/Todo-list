package app;

import models.entities.Task;
import repositories.FileRepository;
import services.*;
import ui.*;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public void start() {
        List<Task> taskList = new ArrayList<>();
        FileRepository fileRepository = new FileRepository(taskList);
        TaskServices taskServices = new TaskServices(fileRepository);

        MainMenu mainMenu = new MainMenu(taskServices);
        mainMenu.startMenu();
    }
}
