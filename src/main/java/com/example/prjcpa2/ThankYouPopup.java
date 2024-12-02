package com.example.prjcpa2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;


public class ThankYouPopup {

    @FXML
    private Button tyreturn;

    @FXML
    private Pane root;

    @FXML
    public void initialize() {
        root.setStyle("-fx-background-color: #0E0F14");

    }

    @FXML
    void tyreturnClick(ActionEvent event) {
        try {
            // Load the target FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer-landing.fxml"));
            Parent root = loader.load();

            // Get the current popup stage and close it
            Stage popupStage = (Stage) tyreturn.getScene().getWindow();
            popupStage.close();

            // Set the new scene in the main stage
            Stage mainStage = (Stage) popupStage.getOwner();
            mainStage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
