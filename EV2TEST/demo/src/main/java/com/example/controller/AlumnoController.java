package com.example.controller;

import com.example.Main;
import com.example.modelos.Alumno;
import com.example.modelos.Asignatura;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class AlumnoController {
    private Alumno alumnoLog;
    @FXML
    private Button btnAtras;

    @FXML
    private Button btnSalir;

    @FXML
    private ComboBox<?> cbAsignaturas;

    @FXML
    private TableColumn<?, ?> columnAsignatura;

    @FXML
    private TableColumn<?, ?> columnNota;

    @FXML
    private Label nombreAlumno;

    @FXML
    private TableView<?> tablaAlumno;

    @FXML
    void atras(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/interfaz.fxml"));
            Scene scene = new Scene(loader.load());

            // Stage actual a partir del evento del botón
            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Login UEM");
            stage.setResizable(false);
            //Carga Icono
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/ue40px.png")));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salir(ActionEvent event) {
        if (HibernateUtil.getSessionFactory() != null) {
            HibernateUtil.getSessionFactory().close();
        }
        System.exit(0);
    }

    @FXML
    void seleccionAsignaturaAlumno(ActionEvent event) {

    }

    public void setAlumno(Alumno alumno) {
        this.alumnoLog=alumno;
    }

}
