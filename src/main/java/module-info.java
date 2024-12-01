module com.example.prjcpa2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens com.example.prjcpa2 to javafx.fxml;
    exports com.example.prjcpa2;
}