package com.example.prjcpa2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

public class CinemaControllerS2 {

    // Data structures
    private HashMap<String, String> reservations = new HashMap<>();
    private TreeSet<String> availableSeats = new TreeSet<>();
    private Map<String, CheckBox> seatCheckBoxes = new HashMap<>();
    private LinkedList<String> reservationHistory = new LinkedList<>();


    private boolean isProgrammaticChange = false;

    // Seat CheckBoxes (auto-linked by FXML)
    @FXML private CheckBox A1, A2, A3, A4, A5, A6, A7, A8, A9, A10;
    @FXML private CheckBox B1, B2, B3, B4, B5, B6, B7, B8, B9, B10;
    @FXML private CheckBox C1, C2, C3, C4, C5, C6, C7, C8, C9, C10;
    @FXML private CheckBox D1, D2, D3, D4, D5, D6, D7, D8, D9, D10;
    @FXML private CheckBox E1, E2, E3, E4, E5, E6, E7, E8, E9, E10;

    @FXML
    private TextField guestName;

    @FXML
    private TextField guestEmail;

    @FXML
    private Label status;

    @FXML
    private Button return2;

    @FXML
    private Label theaternumber;

    @FXML
    private Label title;

    @FXML
    private TextField search;

    @FXML
    private Pane root;

    @FXML
    private Button removeButton;

    // Initialize seats and checkboxes
    @FXML
    public void initialize() {
        initializeSeats();
        initializeCheckBoxes();

        root.setStyle("-fx-background-color: #0E0F14");

        if ("Admin".equals(AdminWController.userType)) {
            search.setVisible(true);

        } else if ("Guest".equals(AdminWController.userType)) {
            search.setVisible(false);
        } else {
            System.out.println("Error: Invalid userType: " + AdminWController.userType);
        }
    }

    // Initialize available seats in the TreeSet
    private void initializeSeats() {
        for (char row = 'A'; row <= 'E'; row++) {
            for (int col = 1; col <= 10; col++) {
                availableSeats.add(row + String.valueOf(col));
            }
        }
    }

    // Dynamically link CheckBoxes to seat IDs
    private void initializeCheckBoxes() {
        for (char row = 'A'; row <= 'E'; row++) {
            for (int col = 1; col <= 10; col++) {
                String seatID = row + String.valueOf(col);
                CheckBox checkBox = getCheckBoxByID(seatID);
                if (checkBox != null) {
                    seatCheckBoxes.put(seatID, checkBox);

                    checkBox.setOnAction(event -> {
                        if (!isProgrammaticChange) {
                            // Prevent unchecking if it's not a programmatic change
                            if (!checkBox.isSelected() && reservations.containsKey(seatID)) {
                                checkBox.setSelected(true);  // Revert back to checked
                            } else if (checkBox.isSelected()) {
                                handleSeatSelection(seatID, checkBox);
                            }
                        }
                    });
                }
            }
        }
    }


    // Helper method to retrieve a CheckBox using reflection
    private CheckBox getCheckBoxByID(String seatID) {
        try {
            Field field = this.getClass().getDeclaredField(seatID);
            field.setAccessible(true);
            return (CheckBox) field.get(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Handle seat selection directly
    private void handleSeatSelection(String seatID, CheckBox checkBox) {
        if (!checkBox.isSelected() && reservations.containsKey(seatID)) {
            cancelReservation(seatID);
            checkBox.setSelected(false); // Deselect after cancellation
            System.out.println("Reservation for seat " + seatID + " has been canceled.");
        }
    }

    @FXML
    private void removeSelectedSeats(ActionEvent event) {
        String guestNameInput = guestName.getText().trim(); // Use the guestName text field

        // Validate the input name
        if (guestNameInput.isEmpty()) {
            status.setVisible(true);
            status.setText("Please enter a guest name.");
            return;
        }

        List<String> seatsToRemove = new ArrayList<>();

        // Collect seats that match the guest name
        for (Map.Entry<String, String> entry : reservations.entrySet()) {
            String seatID = entry.getKey();
            String reservedBy = entry.getValue();

            if (reservedBy.equalsIgnoreCase(guestNameInput) && seatCheckBoxes.get(seatID).isSelected()) {
                seatsToRemove.add(seatID);
            }
        }

        if (seatsToRemove.isEmpty()) {
            status.setVisible(true);
            status.setText("No reservations found for " + guestNameInput + ".");
            return;
        }

        // Remove reservations for the collected seats
        for (String seatID : seatsToRemove) {
            cancelReservation(seatID);
            seatCheckBoxes.get(seatID).setSelected(false); // Deselect the checkbox
        }

        status.setVisible(true);
        status.setText("Reservations for " + guestNameInput + " have been removed.");
        saveSeatData(); // Save updated data
        System.out.println("Reservations for " + guestNameInput + " removed and data saved.");

        // Clear the guest name input field
        guestName.clear();
    }





    // Reserve a seat
    private void reserveSeat(String customerName, String seatID) {
        if (availableSeats.contains(seatID)) {
            availableSeats.remove(seatID);
            reservations.put(seatID, customerName);
            System.out.println("Seat " + seatID + " reserved for " + customerName);
            reservationHistory.add("Reserved: " + seatID + " for " + customerName);
        } else {
            System.out.println("Seat " + seatID + " is unavailable.");
        }
    }

    private void cancelReservation(String seatID) {
        if (reservations.containsKey(seatID)) {
            String guestName = reservations.get(seatID);
            availableSeats.add(seatID);
            reservations.remove(seatID);
            reservationHistory.add("Canceled: Seat " + seatID + " reserved by " + guestName);
            System.out.println("Reservation for seat " + seatID + " canceled.");
        }
    }



    // Reserve seats for the entered guest name
    @FXML
    void reserveClick(ActionEvent event) {
        status.setVisible(false); // Hide status label initially
        String customerName = guestName.getText().trim();
        String email = guestEmail.getText();

        // Validation for empty name and email
        if (customerName.isEmpty() || email.isEmpty()) {
            status.setVisible(true);
            status.setText("No customer name or email provided. Reservation failed.");
            System.out.println("No customer name or email provided. Reservation failed.");
            return; // Stop execution if validation fails
        }

        // Process seat reservations
        for (Map.Entry<String, CheckBox> entry : seatCheckBoxes.entrySet()) {
            String seatID = entry.getKey();
            CheckBox checkBox = entry.getValue();
            if (checkBox.isSelected()) {
                if (!reservations.containsKey(seatID)) {
                    reserveSeat(customerName, seatID); // Reserve if not already reserved
                }
            }
        }

        // Clear input fields and save data
        guestName.clear();
        guestEmail.clear();
        saveSeatData();
        System.out.println("Reservations updated and saved.");

        // Show the popup if reservation succeeds
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("thank-you.fxml"));
            Parent root = loader.load();

            // Create a new Stage for the popup
            Stage popupStage = new Stage();
            popupStage.setTitle("Reservation Complete");
            popupStage.setScene(new Scene(root));
            popupStage.setResizable(false);

            // Set the current stage as the owner of the popup
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            popupStage.initOwner(currentStage);
            popupStage.initModality(Modality.WINDOW_MODAL);

            popupStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void saveSeatData() {
        Properties seatProperties = new Properties();
        String filename = title.getText() + "1220.properties";

        // Populate properties with reserved seat data
        reservations.forEach(seatProperties::setProperty);

        try (FileOutputStream output = new FileOutputStream(filename)) {
            seatProperties.store(output, "Seat Reservations for " + title);
            System.out.println("Seat data saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving seat data: " + e.getMessage());
        }
    }

    public void loadSeatData() {
        Properties seatProperties = new Properties();
        String filename = title.getText() + "1220.properties";

        try (FileInputStream input = new FileInputStream(filename)) {
            // Load properties from the file
            seatProperties.load(input);

            // Apply the seat states to the checkboxes
            for (String seatID : seatCheckBoxes.keySet()) {
                CheckBox checkBox = seatCheckBoxes.get(seatID);

                if (seatProperties.containsKey(seatID)) {
                    // Mark the checkbox as selected if the seat is reserved
                    checkBox.setSelected(true);

                    // Add the reservation to the internal map
                    String customerName = seatProperties.getProperty(seatID);
                    reservations.put(seatID, customerName);
                    availableSeats.remove(seatID);
                } else {
                    // Ensure the checkbox is deselected if not reserved
                    checkBox.setSelected(false);
                }
            }

            System.out.println("Seat data loaded from " + filename);
        } catch (IOException e) {
            System.err.println("Error loading seat data: " + e.getMessage());
        }
    }

    public void setMovieTitle(String movieTitle) {
        if (title != null) {
            title.setText(movieTitle);
        } else {
            System.out.println("Title label is not initialized!");
        }
    }

    public void setTheaterNumber(String theaterNumber) {
        if (theaternumber != null) {
            theaternumber.setText(theaterNumber);
        } else {
            System.out.println("Theater number label is not initialized!");
        }
    }

    @FXML
    void retClick2(ActionEvent event) {
        status.setVisible(false);
        Stage currentStage = (Stage) return2.getScene().getWindow();
        try {
            goback(new Stage());
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goback(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("customer-landing.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Movies Page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void searchClick(ActionEvent event) {
        String guestNameInput = search.getText().trim();

        // Clear any previous status messages
        status.setVisible(false);
        status.setText("");

        if (guestNameInput.isEmpty()) {
            status.setVisible(true);
            status.setText("Please enter a guest name to search.");
            return;
        }

        // Find reservations for the entered guest name
        List<String> matchingSeats = new ArrayList<>();
        for (Map.Entry<String, String> entry : reservations.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(guestNameInput)) {
                matchingSeats.add(entry.getKey());
            }
        }

        // Display results
        if (matchingSeats.isEmpty()) {
            status.setVisible(true);
            status.setText("No reservations found for guest: " + guestNameInput);
            System.out.println("No reservations found for guest: " + guestNameInput);
        } else {
            status.setVisible(true);
            status.setText("Reservations for " + guestNameInput + ": " + String.join(", ", matchingSeats));
            System.out.println("Reservations for " + guestNameInput + ": " + String.join(", ", matchingSeats));
        }

        // Clear the search field
        search.clear();
    }

    public void printReservationHistory() {
        System.out.println("Reservation History:");
        for (String record : reservationHistory) {
            System.out.println(record);
        }
    }
}
