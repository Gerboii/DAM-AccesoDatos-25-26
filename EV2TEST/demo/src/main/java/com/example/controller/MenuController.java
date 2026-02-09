package com.example.controller;

import com.example.dao.AlumnoDaoImp;
import com.example.dao.ProfesorDaoImp;
import com.example.modelos.Alumno;
import com.example.modelos.Profesor;
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
        //Variables locales
        String user = tfUsuario.getText();
        String pass = pfContrasena.getText();
        //Buscamos por profesor
        ProfesorDaoImp profeImp = new ProfesorDaoImp();
        Profesor profe = profeImp.buscarPorDni(user);

        if (profe != null) {
            if (profe.getContrasena().equals(pass)) {
                //TODO Cambiamos a vista profe
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
                } else {
                    //TODO contraseña incorrecta
                }
            } else { //TODO No existe el usuario o credenciales incorrectas
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
    }

    }
