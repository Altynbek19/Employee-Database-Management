module org.example.emplyeemanagerapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.emplyeemanagerapp to javafx.fxml;
    requires java.sql;
    exports org.example.emplyeemanagerapp;
}