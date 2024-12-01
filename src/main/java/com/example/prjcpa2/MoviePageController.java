package com.example.prjcpa2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class MoviePageController {

    @FXML
    private TextField date;

    @FXML
    private TextArea desc1;

    @FXML
    private TextArea desc2;

    @FXML
    private TextArea desc3;

    @FXML
    private TextArea desc4;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private TextField rating1;

    @FXML
    private TextField rating2;

    @FXML
    private TextField rating3;

    @FXML
    private TextField rating4;

    @FXML
    private Button replace;

    @FXML
    private Button replace1;

    @FXML
    private TextField title1;

    @FXML
    private TextField title2;

    @FXML
    private TextField title3;

    @FXML
    private TextField title4;

    public static String movie1Title;
    public static String movie2Title;
    public static String movie3Title;
    public static String movie4Title;

    public static Image movie1Image;
    public static Image movie2Image;
    public static Image movie3Image;
    public static Image movie4Image;

    public static String movie1Rate;
    public static String movie2Rate;
    public static String movie3Rate;
    public static String movie4Rate;

    public static String moviedate;

    // Path to the properties file
    private static final String PROPERTIES_FILE = "movieTitles.properties";

    private static final String PROPERTIES_FILE2 = "movieDescs.properties";

    private static final String PROPERTIES_FILE3 = "movieRatings.properties";

    private static final String PROPERTIES_FILE4 = "movieDate.properties";

    // Called automatically when the FXML is loaded
    public void initialize() {

        loadTitles();
        setInitialImages();
        loadDescs();
        loadRatings();
        loadDates();

        if (AdminWController.userType == null) {
            AdminWController.userType = "Guest";
        }

        if ("Admin".equals(AdminWController.userType)) {
            title1.setEditable(true);
            title2.setEditable(true);
            title3.setEditable(true);
            title4.setEditable(true);

            desc1.setEditable(true);
            desc2.setEditable(true);
            desc3.setEditable(true);
            desc4.setEditable(true);

            rating1.setEditable(true);
            rating2.setEditable(true);
            rating3.setEditable(true);
            rating4.setEditable(true);

            replace.setVisible(true);
            replace1.setVisible(true);
            date.setEditable(true);

        } else if ("Guest".equals(AdminWController.userType)) {
            title1.setEditable(false);
            title2.setEditable(false);
            title3.setEditable(false);
            title4.setEditable(false);

            desc1.setEditable(false);
            desc2.setEditable(false);
            desc3.setEditable(false);
            desc4.setEditable(false);

            replace.setVisible(false);
            replace1.setVisible(false);
            date.setEditable(false);

        } else {
            System.out.println("Error: Invalid userType: " + AdminWController.userType);
        }

    }

    // Set initial images based on the TextFields at stage launch
    private void setInitialImages() {
        String fileName1 = removeSpaces(title1.getText());
        String fileName2 = removeSpaces(title2.getText());
        String fileName3 = removeSpaces(title3.getText());
        String fileName4 = removeSpaces(title4.getText());

        // Ensure that img1, img2, img3, and img4 are not null before calling setImageForImageView
        if (img1 != null) {
            setImageForImageView(img1, fileName1 + ".png");
        }
        if (img2 != null) {
            setImageForImageView(img2, fileName2 + ".png");
        }
        if (img3 != null) {
            setImageForImageView(img3, fileName3 + ".png");
        }
        if (img4 != null) {
            setImageForImageView(img4, fileName4 + ".png");
        }
    }

    // Method to remove spaces from the filename
    private String removeSpaces(String input) {
        return input.replace(" ", "");
    }

    // Method to set an image for an ImageView
    private void setImageForImageView(ImageView imageView, String fileName) {
        String basePath = "file:C:/Users/Hanns/IdeaProjects/PRJ-CPA2/src/Movie Pictures/";  // Base directory for images
        String imagePath = basePath + fileName;

        try {
            Image image = new Image(imagePath);  // Load the image from the file system
            imageView.setImage(image);  // Set the image to the ImageView
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Image file not found or invalid: " + imagePath);
            e.printStackTrace();
        }
    }

    // Save titles to the properties file
    private void saveDescs() {
        Properties properties2 = new Properties();
        properties2.setProperty("desc1", desc1.getText());
        properties2.setProperty("desc2", desc2.getText());
        properties2.setProperty("desc3", desc3.getText());
        properties2.setProperty("desc4", desc4.getText());

        try (FileOutputStream out = new FileOutputStream(PROPERTIES_FILE2)) {
            properties2.store(out, "Movie Descriptions");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveRatings() {
        Properties properties3 = new Properties();
        properties3.setProperty("rating1", rating1.getText());
        properties3.setProperty("rating2", rating2.getText());
        properties3.setProperty("rating3", rating3.getText());
        properties3.setProperty("rating4", rating4.getText());

        try (FileOutputStream out = new FileOutputStream(PROPERTIES_FILE3)) {
            properties3.store(out, "Movie Ratings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTitles() {
        Properties properties = new Properties();
        properties.setProperty("title1", title1.getText());
        properties.setProperty("title2", title2.getText());
        properties.setProperty("title3", title3.getText());
        properties.setProperty("title4", title4.getText());

        try (FileOutputStream out = new FileOutputStream(PROPERTIES_FILE)) {
            properties.store(out, "Movie Titles");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDate() {
        Properties properties4 = new Properties();
        properties4.setProperty("date", date.getText());

        try (FileOutputStream out = new FileOutputStream(PROPERTIES_FILE4)) {
            properties4.store(out, "Movie Date");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load titles from the properties file
    private void loadTitles() {
        Properties properties = new Properties();

        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(in);
            title1.setText(properties.getProperty("title1", ""));
            title2.setText(properties.getProperty("title2", ""));
            title3.setText(properties.getProperty("title3", ""));
            title4.setText(properties.getProperty("title4", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDescs() {
        Properties properties2 = new Properties();

        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE2)) {
            properties2.load(in);
            desc1.setText(properties2.getProperty("desc1", ""));
            desc2.setText(properties2.getProperty("desc2", ""));
            desc3.setText(properties2.getProperty("desc3", ""));
            desc4.setText(properties2.getProperty("desc4", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadRatings() {
        Properties properties3 = new Properties();

        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE3)) {
            properties3.load(in);
            rating1.setText(properties3.getProperty("rating1", ""));
            rating2.setText(properties3.getProperty("rating2", ""));
            rating3.setText(properties3.getProperty("rating3", ""));
            rating4.setText(properties3.getProperty("rating4", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDates() {
        Properties properties4 = new Properties();

        try (FileInputStream in = new FileInputStream(PROPERTIES_FILE4)) {
            properties4.load(in);
            date.setText(properties4.getProperty("date", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Called when the "Replace Images" button is clicked
    @FXML
    void rplcclick(ActionEvent event) {
        saveDescs();
        setInitialImages();  // Update images based on modified titles
        saveTitles();  // Save the updated titles to the properties file
        saveRatings();
        saveDate();
    }

    @FXML
    void mov1(MouseEvent event) {
        Stage currentStage = (Stage) img1.getScene().getWindow();
        try {
            // Set static variables for use in the ScheduleControlM1 controller
            movie1Title = title1.getText();
            movie1Image = img1.getImage();
            movie1Rate = rating1.getText();
            moviedate = date.getText();

            // Transition to the ScheduleControlM1 screen
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("schedule-1.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            ScheduleControlM1 controller = fxmlLoader.getController();

            // Pass data to ScheduleControlM1
            controller.setMovieDetails(movie1Title, movie1Image, movie1Rate, moviedate);

            // Show the new stage
            Stage newStage = new Stage();
            newStage.setTitle("Movie 1 Schedule");
            newStage.setScene(scene);
            newStage.show();

            // Close the current stage
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mov2(MouseEvent event) {
        Stage currentStage = (Stage) img2.getScene().getWindow();
        try {
            // Set static variables for use in the ScheduleControlM2 controller
            movie2Title = title2.getText();
            movie2Image = img2.getImage();
            movie2Rate = rating2.getText();
            moviedate = date.getText();

            // Transition to the ScheduleControlM2 screen
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("schedule-2.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            ScheduleControlM2 controller = fxmlLoader.getController();

            // Pass data to ScheduleControlM2
            controller.setMovieDetails(movie2Title, movie2Image, movie2Rate, moviedate);

            // Show the new stage
            Stage newStage = new Stage();
            newStage.setTitle("Movie 2 Schedule");
            newStage.setScene(scene);
            newStage.show();

            // Close the current stage
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mov3(MouseEvent event) {
        Stage currentStage = (Stage) img3.getScene().getWindow();
        try {
            // Set static variables for use in the ScheduleControlM3 controller
            movie3Title = title3.getText();
            movie3Image = img3.getImage();
            movie3Rate = rating3.getText();
            moviedate = date.getText();

            // Transition to the ScheduleControlM3 screen
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("schedule-3.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            ScheduleControlM3 controller = fxmlLoader.getController();

            // Pass data to ScheduleControlM3
            controller.setMovieDetails(movie3Title, movie3Image, movie3Rate, moviedate);

            // Show the new stage
            Stage newStage = new Stage();
            newStage.setTitle("Movie 3 Schedule");
            newStage.setScene(scene);
            newStage.show();

            // Close the current stage
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void mov4(MouseEvent event) {
        Stage currentStage = (Stage) img4.getScene().getWindow();
        try {
            // Set static variables for use in the ScheduleControlM4 controller
            movie4Title = title4.getText();
            movie4Image = img4.getImage();
            movie4Rate = rating4.getText();
            moviedate = date.getText();

            // Transition to the ScheduleControlM4 screen
            FXMLLoader fxmlLoader = new FXMLLoader(WelcomePage.class.getResource("schedule-4.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            ScheduleControlM4 controller = fxmlLoader.getController();

            // Pass data to ScheduleControlM4
            controller.setMovieDetails(movie4Title, movie4Image, movie4Rate, moviedate);

            // Show the new stage
            Stage newStage = new Stage();
            newStage.setTitle("Movie 4 Schedule");
            newStage.setScene(scene);
            newStage.show();

            // Close the current stage
            currentStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
