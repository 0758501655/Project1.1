module com.example.day1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql;
            
                            
    opens com.example.day1 to javafx.fxml;
    exports com.example.day1;
}