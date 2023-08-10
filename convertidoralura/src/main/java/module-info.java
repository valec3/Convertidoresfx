module com.example.convertidoralura {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.convertidoralura to javafx.fxml;
    exports com.example.convertidoralura;
}