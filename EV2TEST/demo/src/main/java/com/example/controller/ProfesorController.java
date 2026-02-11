package com.example.controller;

import com.example.dao.AsignaturaDaoImp;
import com.example.dao.NotaDaoImp;
import com.example.modelos.Asignatura;
import com.example.modelos.Nota;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.util.StringConverter;
import java.util.List;

public class ProfesorController {
    private NotaDaoImp notaDaoImp;
    private Profesor profesorLog;
    private AsignaturaDaoImp asigDao = new AsignaturaDaoImp();

    @FXML
    private Button btnAtras, btnGuardar, btnSalir;

    // ComboBox maneja objetos 'Asignatura'
    @FXML
    private ComboBox<Asignatura> cbAsignaturas;

    @FXML
    private Label nombreProfesor;

    @FXML
    private TableView<Nota> tablaNotas;

    @FXML
    public void initialize() {
        //ComboBox por defecto sin selección y con texto de ayuda.
        cbAsignaturas.setPromptText("Seleccione asignatura");
        // Aquí configuraremos las columnas de la TableView

        // No cargamos datos aquí porque profesorLog aún es null
    }

    //Se ejecuta cuando entramos desde el login.
    public void setProfesor(Profesor profe) {
        this.profesorLog = profe;

        // Ponemos el nombre en la interfaz
        if (nombreProfesor != null) {
            nombreProfesor.setText(profe.getNombre());
        }

        // Llamamos al DAO para traer las asignaturas de este profesor
        List<Asignatura> listaAsig = asigDao.listarPorProfesor(profe.getDniProfesor());

        // Llenamos el combo
        cargarComboBox(listaAsig);
    }

    private void cargarComboBox(List<Asignatura> asignaturas) {
        if (asignaturas != null && !asignaturas.isEmpty()) {
            ObservableList<Asignatura> obsList = FXCollections.observableArrayList(asignaturas);
            cbAsignaturas.setItems(obsList);

            // El Converter sirve para que el combo muestre el NOMBRE de la asignatura y no la dirección  del objeto.
            cbAsignaturas.setConverter(new StringConverter<Asignatura>() {
                @Override
                public String toString(Asignatura asig) {
                    return (asig != null) ? asig.getNombre() : "";
                }

                @Override
                public Asignatura fromString(String string) {
                    return null; // No necesario para selección simple
                }
            });
        }
    }



    @FXML
    void salir(ActionEvent event) {
        if (HibernateUtil.getSessionFactory() != null) {
            HibernateUtil.getSessionFactory().close();
        }
        System.exit(0);
    }

    public void seleccionCb(ActionEvent actionEvent) {
        Asignatura seleccionada = cbAsignaturas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            List<Nota> listaNotas=notaDaoImp.alumAsignatura(seleccionada);
            //Hay que convertir a observable para el CB
            ObservableList<Nota> notasObservable = FXCollections.observableArrayList(listaNotas);
            tablaNotas.setItems(notasObservable);
        }
    }

    public void atras(ActionEvent actionEvent) {
    }

    public void guardar(ActionEvent actionEvent) {
    }
}