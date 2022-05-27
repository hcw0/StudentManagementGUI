module com.example.studentmanagementgui {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.studentmanagementgui to javafx.fxml;
    exports com.example.studentmanagementgui;
}