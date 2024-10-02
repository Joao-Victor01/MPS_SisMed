module com.example.sismed2 {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.sismed2 to javafx.fxml;
    exports com.example.sismed2;
}