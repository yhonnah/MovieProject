package com.example.prjcpa2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.input.MouseEvent;

public class ScheduleControlM3 {

    @FXML
    private Button back;

    @FXML
    private Label cinema1;

    @FXML
    private Label cinema2;

    @FXML
    private Label cinema3;

    @FXML
    private Label cinema4;

    @FXML
    private Label cinema5;

    @FXML
    private ImageView movimg;

    @FXML
    private TextField rating;

    @FXML
    private Label title;

    @FXML
    private TextField time1;

    @FXML
    private TextField time2;

    @FXML
    private TextField time3;

    @FXML
    private TextField time4;

    @FXML
    private TextField time5;

    @FXML
    private Pane root;

    // Reference to the movie data
    private static String movieTitle = MoviePageController.movie1Title;
    private static Image movieImage = MoviePageController.movie1Image;
    private static String movieRating = MoviePageController.movie1Rate;

    @FXML
    public void initialize() {
        // Check if movieTitle and movieImage are updated
        updateMovieDetails();
        root.setStyle("-fx-background-color: #0E0F14");

    }

    private void updateMovieDetails() {
        // Set the title
        if (movieTitle != null && !movieTitle.isEmpty()) {
            title.setText(movieTitle);
        } else {
            title.setText("Movie Title Unavailable");
        }

        // Set the image
        if (movieImage != null) {
            movimg.setImage(movieImage);
        } else {
            movimg.setImage(new Image("file:default-image.png")); // Ensure the path is correct
        }

        if (movieRating != null && !movieRating.isEmpty()) {
            rating.setText(movieRating);
            rating.setStyle("-fx-control-inner-background: #EEB51E;");
        } else {
            rating.setText("N/A");
        }
    }

    @FXML
    void backClick(ActionEvent event) {
        Stage currentStage = (Stage) back.getScene().getWindow();
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
    void time1Click(MouseEvent event) {
        try {
            // Get the movie title
            String movieTitle = title.getText();

            // Get the theater number (adjust for the relevant label, e.g., cinema1)
            String theaterNumber = cinema1.getText();

            // Load the CinemaControllerS1 FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CS1-view.fxml"));
            Parent root = loader.load();

            // Get the controller instance and pass the title and theater number
            CinemaControllerS1 cinemaController = loader.getController();
            cinemaController.setMovieTitle(movieTitle);
            cinemaController.setTheaterNumber(theaterNumber);

            // Load seat data for the movie
            cinemaController.loadSeatData();

            // Navigate to the CinemaControllerS1 screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void time2Click(MouseEvent event) {
        try {
            // Get the movie title
            String movieTitle = title.getText();

            // Get the theater number (adjust for the relevant label, e.g., cinema1)
            String theaterNumber = cinema2.getText();

            // Load the CinemaControllerS1 FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CS2-view.fxml"));
            Parent root = loader.load();

            // Get the controller instance and pass the title and theater number
            CinemaControllerS2 cinemaController = loader.getController();
            cinemaController.setMovieTitle(movieTitle);
            cinemaController.setTheaterNumber(theaterNumber);

            // Load seat data for the movie
            cinemaController.loadSeatData();

            // Navigate to the CinemaControllerS1 screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void time3Click(MouseEvent event) {
        try {
            // Get the movie title
            String movieTitle = title.getText();

            // Get the theater number (adjust for the relevant label, e.g., cinema1)
            String theaterNumber = cinema3.getText();

            // Load the CinemaControllerS1 FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CS3-view.fxml"));
            Parent root = loader.load();

            // Get the controller instance and pass the title and theater number
            CinemaControllerS3 cinemaController = loader.getController();
            cinemaController.setMovieTitle(movieTitle);
            cinemaController.setTheaterNumber(theaterNumber);

            // Load seat data for the movie
            cinemaController.loadSeatData();

            // Navigate to the CinemaControllerS1 screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void time4Click(MouseEvent event) {
        try {
            // Get the movie title
            String movieTitle = title.getText();

            // Get the theater number (adjust for the relevant label, e.g., cinema1)
            String theaterNumber = cinema4.getText();

            // Load the CinemaControllerS1 FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CS4-view.fxml"));
            Parent root = loader.load();

            // Get the controller instance and pass the title and theater number
            CinemaControllerS4 cinemaController = loader.getController();
            cinemaController.setMovieTitle(movieTitle);
            cinemaController.setTheaterNumber(theaterNumber);

            // Load seat data for the movie
            cinemaController.loadSeatData();

            // Navigate to the CinemaControllerS1 screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void time5Click(MouseEvent event) {
        try {
            // Get the movie title
            String movieTitle = title.getText();

            // Get the theater number (adjust for the relevant label, e.g., cinema1)
            String theaterNumber = cinema1.getText();

            // Load the CinemaControllerS1 FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CS5-view.fxml"));
            Parent root = loader.load();

            // Get the controller instance and pass the title and theater number
            CinemaControllerS5 cinemaController = loader.getController();
            cinemaController.setMovieTitle(movieTitle);
            cinemaController.setTheaterNumber(theaterNumber);

            // Load seat data for the movie
            cinemaController.loadSeatData();

            // Navigate to the CinemaControllerS1 screen
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setMovieDetails(String title, Image image, String rating, String date) {
        // Update movieTitle and movieImage variables
        movieTitle = title;
        movieImage = image;
        movieRating = rating;

        // Update UI elements
        if (title != null && !title.isEmpty()) {
            this.title.setText(title);
        } else {
            this.title.setText("Movie Title Unavailable");
        }

        if (rating != null && !rating.isEmpty()) {
            this.rating.setText(rating);
        } else {
            this.rating.setText("N/A");
        }

        if (image != null) {
            this.movimg.setImage(image);
        } else {
            this.movimg.setImage(new Image("file:default-image.png")); // Ensure the path is correct
        }

    }
}