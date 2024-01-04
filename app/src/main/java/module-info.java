module app {
    requires javafx.controls;
    requires javafx.fxml;
    requires api;
    exports it.unicam.cs.followme.app;

    opens it.unicam.cs.followme.app to javafx.fxml;

}
