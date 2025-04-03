module org.example.localizationapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens org.example.localizationapplication to javafx.fxml;
    exports org.example.localizationapplication;
}