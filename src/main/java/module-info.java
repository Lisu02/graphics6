module org.example.graphics6 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.graphics6 to javafx.fxml;
    exports org.example.graphics6;
}