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

public class WelcomeController {

    @FXML
    private Label adminpage;

    @FXML
    private Button enter;

    @FXML
    private PasswordField invi_password;

    @FXML
    private TextField invi_username;

    public static String userType;

    @FXML
    public void initialize() {
        enter.setStyle("-fx-font-family: 'Tahoma'; -fx-font-size: 14px;");
    }

    public void enterlogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("customer-landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Movies Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void entclick(ActionEvent event) {
        String enteredUsername = invi_username.getText();
        String enteredPassword = invi_password.getText();

        // Debug message for testing
        System.out.println("Entered Username: " + enteredUsername);
        System.out.println("Entered Password: " + enteredPassword);

        userType = Credentials.getUserType(enteredUsername, enteredPassword);

        if (!userType.equals("INVALID")) {
            System.out.println("User type: " + userType + ". Logging in...");
            Stage currentStage = (Stage) enter.getScene().getWindow();
            try {
                enterlogin(new Stage());
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid username or password");
        }
    }

    public void adminlogin(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("admin-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Admin Login Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void hyperlink(MouseEvent event) {
        Stage currentStage = (Stage) adminpage.getScene().getWindow();
        try {
            adminlogin(new Stage());
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}