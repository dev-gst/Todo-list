package app;

import services.*;
import ui.*;

public class Application {
    public void start() {
        TaskServices taskServices = new TaskServices();

        MainMenu mainMenu = new MainMenu(taskServices);
        mainMenu.startMenu();
    }
}
