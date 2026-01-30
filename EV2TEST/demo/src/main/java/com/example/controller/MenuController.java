package com.example.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MenuController {
    @FXML
    private Label etiquetaTitulo;

    @FXML
    public void initialize() {
        etiquetaTitulo.setText("Sistema Cargado Correctamente");
    }
}
