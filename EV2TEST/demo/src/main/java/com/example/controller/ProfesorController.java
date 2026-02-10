package com.example.controller;

import com.example.modelos.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfesorController {
    private Profesor profesorLog;
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnSalir;

    @FXML
    private ComboBox<?> cbAsignaturas;

    @FXML
    private Label nombreProfesor;

    @FXML
    void atras(ActionEvent event) {

    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

    public void setProfesor(Profesor profe) {
        this.profesorLog=profe;
    }
}
