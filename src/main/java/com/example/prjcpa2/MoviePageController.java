package com.example.prjcpa2;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MoviePageController {

    @FXML
    private Pane root;

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
    private TextField title1;

    @FXML
    private TextField title2;

    @FXML
    private TextField title3;

    @FXML
    private TextField title4;

    public static String movie1Title = "Moana 2";
    public static String movie2Title = "Wicked";
    public static String movie3Title = "The Colors Within";
    public static String movie4Title = "Venom: The Last Dance";

    public static Image movie1Image;
    public static Image movie2Image;
    public static Image movie3Image;
    public static Image movie4Image;

    public static String movie1Rate = "G";
    public static String movie2Rate = "PG";
    public static String movie3Rate = "PG";
    public static String movie4Rate = "R13";

    public static String movie1Desc = "Moana 2 opens three years after the end of the first movie with the titular heroine (Auli'i Cravalho) endeavoring to break the curse of Nalo that has essentially isolated islands of people. Moana is searching the seas for the mythical island of Motufetu, which can reportedly unite these different cultures.\")";
    public static String movie2Desc = "Set in the Land of Oz, mostly before Dorothy Gale's arrival from Kansas, it follows Elphaba as she embarks on her path to becoming the Wicked Witch of the West, and chronicles her friendship with her classmate Galinda, who becomes Glinda the Good.";
    public static String movie3Desc = "Totsuko is a high school student with the ability to see the 'colors' of others. Colors of bliss, excitement, and serenity, plus a color she treasures as her favorite. Kimi, a classmate at her school, gives off the most beautiful color of all.";
    public static String movie4Desc = "Eddie Brock (Tom Hardy) and Venom (Tom Hardy) are fugitives from the American government. While in Mexico, back in his universe, Eddie gets the idea of resetting life in NYC. But on their way back to 'Merica, the two are attacked by a monstrous spider-lizard creature that Venom recognizes.";

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

        root.setStyle("-fx-background-color: #0E0F14;");

        if (AdminWController.userType == null) {
            AdminWController.userType = "Guest";
        }

        if ("Guest".equals(AdminWController.userType)) {
            title1.setText("Moana 2");
            title2.setText("Wicked");
            title3.setText("The Colors Within");
            title4.setText("Venom: The Last Dance");

            title1.setStyle("-fx-text-fill: #0E0F14; -fx-control-inner-background: #EEB51E");
            title2.setStyle("-fx-text-fill: #0E0F14; -fx-control-inner-background: #EEB51E");
            title3.setStyle("-fx-text-fill: #0E0F14; -fx-control-inner-background: #EEB51E");
            title4.setStyle("-fx-text-fill: #0E0F14; -fx-control-inner-background: #EEB51E");

            desc1.setStyle("-fx-text-fill: white; -fx-control-inner-background: #0E0F14; -fx-font-family: 'Arial Rounded MT Bold';");
            desc2.setStyle("-fx-text-fill: white; -fx-control-inner-background: #0E0F14; -fx-font-family: 'Arial Rounded MT Bold';");
            desc3.setStyle("-fx-text-fill: white; -fx-control-inner-background: #0E0F14; -fx-font-family: 'Arial Rounded MT Bold';");
            desc4.setStyle("-fx-text-fill: white; -fx-control-inner-background: #0E0F14; -fx-font-family: 'Arial Rounded MT Bold';");

            rating1.setText("G");
            rating2.setText("PG");
            rating3.setText("PG");
            rating4.setText("R-13");

            date.setEditable(false);

        } else {
            System.out.println("Error: Invalid userType: " + AdminWController.userType);
        }

    }

    // Set initial images based on the TextFields at stage launch
    private void setInitialImages() {
        String basePath = "file:///C:/Users/Yohann/OneDrive%20-%20De%20La%20Salle%20University%20-%20Manila/DLSU/TERM%204/LBYCPA2/Final%20Project/Movie/src/Movie Pictures/";

        img1.setImage(new Image(basePath + "Moana.png"));
        img2.setImage(new Image(basePath + "Wicked.png"));
        img3.setImage(new Image(basePath + "colors.png"));
        img4.setImage(new Image(basePath + "venom.png"));
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


    @FXML
    void mov1() {
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
            controller.setMovieDetails(movie1Title, movie1Image, movie1Rate, movie1Desc);

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
    void mov2() {
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
            controller.setMovieDetails(movie2Title, movie2Image, movie2Rate);

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
    void mov3() {
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
            controller.setMovieDetails(movie3Title, movie3Image, movie3Rate);

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
    void mov4() {
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
