package com.example.controller;

import com.example.modelos.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    //TODO Arreglar
    /*@FXML
    public void initialize() {
        // Configurar columnas
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        //PropertyValueFactory. Indica qué propiedad del objeto tiene que usar, en este cado llama al metodo get de la clase Alumno
        //setCellValueFactory. Define cómo obtener el valor de la columna.
        colPass.setCellValueFactory(new PropertyValueFactory<>("contrasenia"));

        tablaAlumnos.setItems(lista);
        tablaAlumnos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Cargar ComboBox al iniciar
        cargarComboBox();
    }
    //TODO Crear
    private void cargarComboBox() {
    }
*/
    public void setProfesor(Profesor profe) {
        this.profesorLog=profe;
    }
}
