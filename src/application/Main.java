package application;

import application.design.PathWindows;
import application.design.component.WindowsWorker;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        WindowsWorker windowsStart = new WindowsWorker(PathWindows.Start.getPath(), false);
        windowsStart.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
