package com.example.controller;

import com.example.Main;
import com.example.modelos.Alumno;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
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
    void atras(ActionEvent event) {
        //TODO sin terminar. Falla, falta cerrar al salir

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
    private void cargarVistaLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/com/example/interfaz.fxml"));
            Stage nuevoStage = new Stage();
            Scene scene = new Scene(loader.load());
            nuevoStage.setTitle("Login UEM");
            nuevoStage.setResizable(false);
            //Carga Icono
            nuevoStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/ue40px.png")));
            nuevoStage.setScene(scene);
            nuevoStage.centerOnScreen();
            nuevoStage.show();

            //TODO Arreglar esto

            // Cerrar ventana de alumno actual
           /* Stage alumnoStage = (Stage) tfUsuario.getScene().getWindow();
            alumnoStage.close();*/

        } catch (IOException e) {
            System.err.println("Error cargando interfaz.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
