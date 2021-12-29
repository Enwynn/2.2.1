module demo {
    requires javafx.controls;
    requires javafx.fxml;



    exports demo.ServerClient;
    opens demo.ServerClient to javafx.fxml;
    exports demo.FX;
    opens demo.FX to javafx.fxml;
}