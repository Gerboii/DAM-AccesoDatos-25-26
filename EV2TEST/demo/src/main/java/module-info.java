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

    // ABRIR paquetes:

    // 1. Permite a JavaFX tocar tu controlador
    opens com.example.controller to javafx.fxml;

    // 2. Permite a Hibernate tocar tus modelos (Entidades)
    opens com.example.modelos to org.hibernate.orm.core;

    // 3. Exporta el paquete principal para que arranque
    exports com.example;
}