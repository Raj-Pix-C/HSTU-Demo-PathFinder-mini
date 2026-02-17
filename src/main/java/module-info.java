module com.example.demo_hstu_pathfinder {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens com.example.demo_hstu_pathfinder to javafx.fxml;
    exports com.example.demo_hstu_pathfinder;
}