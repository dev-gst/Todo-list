package app;

import model.*;
import services.*;
import ui.*;

import java.time.ZonedDateTime;

public class Application {
    public void start() {
        TaskServices taskServices = new TaskServices();

        MainMenu mainMenu = new MainMenu(taskServices);
        mainMenu.startMenu();
    }
}
