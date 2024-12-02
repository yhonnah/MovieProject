package com.example.prjcpa2;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;

public class AdminWController {

    @FXML
    private Label cstmrpage;

    @FXML
    private PasswordField password;

    @FXML
    private Label subtitle;

    @FXML
    private TextField username;

    @FXML
    private Button login;

    public static String userType;

    @FXML
    void loginClick(ActionEvent event) {
        String enteredUsername = username.getText();
        String enteredPassword = password.getText();

        System.out.println("Entered Username: " + enteredUsername);
        System.out.println("Entered Password: " + enteredPassword);

        userType = Credentials.getUserType(enteredUsername, enteredPassword);

        if (!userType.equals("INVALID")) {
            System.out.println("User type: " + userType + ". Logging in...");
            Stage currentStage = (Stage) login.getScene().getWindow();
            try {
                enterlogin(new Stage());
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid username or password");
            subtitle.setVisible(true);
        }
    }

    public void cstmrlogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("welcome-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Customer Welcome Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hyperlink(MouseEvent event) {
        Stage currentStage = (Stage) cstmrpage.getScene().getWindow();
        try {
            cstmrlogin(new Stage());
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enterlogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("customer-landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Movies Page");
        stage.setScene(scene);
        stage.show();
    }

}