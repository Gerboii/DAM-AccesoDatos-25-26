package com.example.controller;

import com.example.Main;
import com.example.dao.AlumnoDaoImp;
import com.example.dao.ProfesorDaoImp;
import com.example.modelos.Alumno;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btnEntrar, btnLimpiar, btnSalir;

    @FXML
    private PasswordField pfContrasena;

    @FXML
    private TextField tfUsuario;

    @FXML
    void entrar(ActionEvent event) {
        String user = tfUsuario.getText();
        String pass = pfContrasena.getText();

        // Control básico de campos vacíos
        if (user.isEmpty() || pass.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Por favor, introduce usuario y contraseña.");
            return;
        }

        // Intentamos buscar como Profesor
        ProfesorDaoImp profeImp = new ProfesorDaoImp();
        Profesor profe = profeImp.buscarPorDni(user);

        if (profe != null) {
            if (profe.getContrasena().equals(pass)) {
                cargarVistaProfesor(profe);
            } else {
                mostrarAlerta("Login error", "Contraseña de profesor incorrecta.");
            }
        } else {
            //Si no es profesor, buscamos como Alumno
            AlumnoDaoImp alumnoImp = new AlumnoDaoImp();
            Alumno alumno = alumnoImp.buscarPorDni(user);

            if (alumno != null) {
                if (alumno.getContrasena().equals(pass)) {
                    cargarVistaAlumno(alumno);
                } else {
                    mostrarAlerta("Login error", "Contraseña de alumno incorrecta.");
                }
            } else {
                mostrarAlerta("Login error", "El usuario no existe en la base de datos.");
            }
        }
    }

    @FXML
    void limpiar(ActionEvent event) {
        tfUsuario.clear();
        pfContrasena.clear();
    }

    @FXML
    void salir(ActionEvent event) {
        // Cerrar SessionFactory de Hibernate
        if (HibernateUtil.getSessionFactory() != null) {
            HibernateUtil.getSessionFactory().close();
        }
        System.exit(0);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    private void cargarVistaProfesor(Profesor profe) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/profesor.fxml"));
            Parent root = loader.load();

            // Pasamos el objeto al controlador de la nueva ventana
            ProfesorController controller = loader.getController();
            controller.setProfesor(profe);

            abrirNuevaVentana(root, "Gestión de notas - " + profe.getNombre());
        } catch (IOException e) {
            System.err.println("Error cargando profesor.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarVistaAlumno(Alumno alumno) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/alumno.fxml"));
            Parent root = loader.load();

            // Pasamos el objeto al controlador de la nueva ventana
            AlumnoController controller = loader.getController();
            controller.setAlumno(alumno);

            abrirNuevaVentana(root, "Evaluaciones - " + alumno.getNombre());
        } catch (IOException e) {
            System.err.println("Error cargando alumno.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void abrirNuevaVentana(Parent root, String titulo) {
        Stage nuevoStage = new Stage();
        nuevoStage.setTitle(titulo);
        nuevoStage.setScene(new Scene(root));
        nuevoStage.show();

        // Cerrar ventana de login actual
        Stage loginStage = (Stage) tfUsuario.getScene().getWindow();
        loginStage.close();
    }

}