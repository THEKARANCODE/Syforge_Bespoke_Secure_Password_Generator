module com.javafx.passwordgenerator_1_0 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.javafx.passwordgenerator_1_0 to javafx.fxml;
    exports com.javafx.passwordgenerator_1_0;
}