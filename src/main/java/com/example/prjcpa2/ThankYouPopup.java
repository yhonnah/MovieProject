package com.example.prjcpa2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;


public class ThankYouPopup {

    @FXML
    private Button tyreturn;

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
