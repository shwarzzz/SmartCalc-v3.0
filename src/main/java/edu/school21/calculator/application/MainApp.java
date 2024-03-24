package edu.school21.calculator.application;

import edu.school21.calculator.view.CalcView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static final String FXML_FILE_PATH = "/fxml/calculator.fxml";
    private final String APPLICATION_TITLE = "Smart Calc v3.0 by tomokoki";

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(FXML_FILE_PATH));
        Parent root = loader.load();
        primaryStage.setTitle(APPLICATION_TITLE);
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        CalcView view = loader.getController();
        primaryStage.setOnCloseRequest(view.getCloseEventHandler());
    }
}
