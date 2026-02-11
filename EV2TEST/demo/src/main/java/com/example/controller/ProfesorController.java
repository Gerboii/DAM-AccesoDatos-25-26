package com.example.controller;

import com.example.dao.AsignaturaDaoImp;
import com.example.modelos.Asignatura;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import java.util.List;

public class ProfesorController {

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
    public void initialize() {
        // Aquí configuraremos las columnas de la TableView (cuando la definas)
        // No cargamos datos aquí porque profesorLog aún es null
    }

    //Se ejecuta cuando entramos desde el login.
    public void setProfesor(Profesor profe) {
        this.profesorLog = profe;

        // Ponemos el nombre en la interfaz
        if (nombreProfesor != null) {
            nombreProfesor.setText(profe.getNombre());
        }

        // 2. Llamamos al DAO para traer las asignaturas de este profesor
        List<Asignatura> listaAsig = asigDao.listarPorProfesor(profe.getDniProfesor());

        // 3. Llenamos el combo
        cargarComboBox(listaAsig);
    }

    private void cargarComboBox(List<Asignatura> asignaturas) {
        if (asignaturas != null && !asignaturas.isEmpty()) {
            ObservableList<Asignatura> obsList = FXCollections.observableArrayList(asignaturas);
            cbAsignaturas.setItems(obsList);

            // 4. TRUCO VISUAL: El 'Converter' sirve para que el combo muestre
            // el NOMBRE de la asignatura y no la dirección de memoria del objeto.
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

            // Seleccionamos la primera por defecto
            cbAsignaturas.getSelectionModel().selectFirst();
        }
    }

    @FXML
    void salir(ActionEvent event) {
        if (HibernateUtil.getSessionFactory() != null) {
            HibernateUtil.getSessionFactory().close();
        }
        System.exit(0);
    }
}