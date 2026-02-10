package com.example.controller;

import com.example.Main;
import com.example.dao.AlumnoDaoImp;
import com.example.dao.ProfesorDaoImp;
import com.example.modelos.Alumno;
import com.example.modelos.Profesor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    Stage stage;
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
        //Variables locales
        String user = tfUsuario.getText();
        String pass = pfContrasena.getText();

        //Buscamos por profesor
        ProfesorDaoImp profeImp = new ProfesorDaoImp();
        Profesor profe = profeImp.buscarPorDni(user);
        //Evito NullPointerException
        if (profe != null) {
            if (profe.getContrasena().equals(pass)) {
                //Cambiamos a vista profe
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/profesor.fxml"));
                    //Crear y mostrar nueva ventana
                    Stage stage = new Stage();
                    stage.setTitle("Gestión de notas-"+user);
                    stage.setScene(new Scene(loader.load()));
                    stage.show();
                    //Cerrar ventana anterior
                    Stage loginStage =(Stage)tfUsuario.getScene().getWindow();
                    loginStage.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                //TODO Contraseña incorrecta
            }
        } else {
            //Si no se encuentra por profe buscamos alumno
            AlumnoDaoImp alumnoImp = new AlumnoDaoImp();
            Alumno alumno = alumnoImp.buscarPorDni(user);
            if (alumno != null) {
                if (alumno.getContrasena().equals(pass)) {
                    //TODO cambiamos a vista alumno
                        try {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/alumno.fxml"));
                            //Crear y mostrar nueva ventana
                            Stage stage = new Stage();
                            stage.setTitle("Evaluaciones-"+user);
                            stage.setScene(new Scene(loader.load()));
                            stage.show();
                            //Cerrar ventana anterior
                            Stage loginStage =(Stage)tfUsuario.getScene().getWindow();
                            loginStage.close();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    //TODO contraseña incorrecta
                }
            }
        }

    @FXML
    void limpiar (ActionEvent event){
        tfUsuario.clear();
        pfContrasena.clear();
    }

    @FXML
    void salir (ActionEvent event){
        System.exit(0);
        //TODO Cerrar la session factory aqui.
    }
}