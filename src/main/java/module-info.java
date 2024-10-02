module com.example.sismed2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.SisMed to javafx.fxml;
    exports com.SisMed;
}