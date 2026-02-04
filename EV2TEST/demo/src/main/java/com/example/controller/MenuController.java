package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MenuController {

    @FXML
    private Button btnEntrar;

    @FXML
    private Button btnLimpiar;

    @FXML
    private Button btnSalir;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private TextField tfUsuario;

    @FXML
    void entrar(ActionEvent event) {
    //Toma los campos pfContrasena y tfUsuario. comprueba coincidencia en BD
    //Posible control excepción campo vacio
    }

    @FXML
    void limpiar(ActionEvent event) {
        tfUsuario.clear();
        pfContrasena.clear();
    }

    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

}
