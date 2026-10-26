module com.mycompany.jogo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql; // Permite o uso do JDBC/PostgreSQL

    opens com.mycompany.jogo to javafx.fxml;
    opens com.mycompany.jogo.model to javafx.base;
    
    exports com.mycompany.jogo;
    exports com.mycompany.jogo.model;
    exports com.mycompany.jogo.dao;
    exports com.mycompany.jogo.database;
}