module com.example.idz12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.idz12 to javafx.fxml;
    exports com.example.idz12;
}