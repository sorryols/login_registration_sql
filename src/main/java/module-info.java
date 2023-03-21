module com.example.login_registration_sql {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires mysql.connector.j;

    opens com.example.login_registration_sql to javafx.fxml;
    exports com.example.login_registration_sql;
}