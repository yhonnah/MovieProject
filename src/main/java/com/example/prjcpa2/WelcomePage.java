package com.example.prjcpa2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("welcome-view.fxml"));
        Pane root = fxmlLoader.load();

        root.setStyle("-fx-background-color: #808080;");



        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Customer Welcome Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
