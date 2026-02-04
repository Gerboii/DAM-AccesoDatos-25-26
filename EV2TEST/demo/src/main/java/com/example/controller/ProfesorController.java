package com.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ProfesorController {

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
        //TODO sin terminar
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/interfaz.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Login UEM");
            stage.setScene(new Scene(loader.load()));
            stage.show();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void guardar(ActionEvent event) {

    }

    @FXML
    void salir(ActionEvent event) {
        System.exit(0);
    }

}
