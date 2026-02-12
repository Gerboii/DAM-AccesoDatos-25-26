module com.example {
    //JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Hibernate y Base de Datos
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires java.sql;
    requires static lombok;

    // Permite a JavaFX tocar tu controlador
    opens com.example.controller to javafx.fxml;

    // Permite a Hibernate y Java FX tocar modelos
    opens com.example.modelos to org.hibernate.orm.core, javafx.base;

    // Exporta el paquete principal para que se lance
    exports com.example;
}