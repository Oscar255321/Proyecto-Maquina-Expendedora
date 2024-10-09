module com.example.maquinaexpendedora {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.maquinaexpendedora to javafx.fxml;
    exports com.example.maquinaexpendedora;
}