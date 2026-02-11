package com.example.controller;

import com.example.dao.AsignaturaDaoImp;
import com.example.dao.NotaDaoImp;
import com.example.modelos.Alumno;
import com.example.modelos.Asignatura;
import com.example.modelos.Nota;
import com.example.modelos.Profesor;
import com.example.util.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import javafx.util.converter.DoubleStringConverter;

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
    private TableColumn<Nota, String> columnaAlumno;

    @FXML
    private TableColumn<Nota, Double> columnaNota;

    @FXML
    public void initialize() {
        //Iniciamos combo
        cbAsignaturas.setPromptText("Seleccione asignatura");

        //Recibe Nota, devuelve String (Nombre) -> OK
        columnaAlumno.setCellValueFactory(cellData -> {
            String nombre = cellData.getValue().getAlumno().getNombre();
            return new SimpleStringProperty(nombre);
        });

        // Recibe Nota, devuelve Double
        // Usamos PropertyValueFactory conversión a Observable
        columnaNota.setCellValueFactory(new PropertyValueFactory<>("valorNota"));

        //Habilitar edición
        tablaNotas.setEditable(true);

        columnaNota.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));

        // Manejo de la edición (Commit)
        columnaNota.setOnEditCommit(event -> {
            //Tomamos valor nuevo
            Double nuevaNota = event.getNewValue();
            Nota notaFila = event.getRowValue();
            //Filtro para solo poder meter notas válidas
            if (nuevaNota != null && nuevaNota >= 0 && nuevaNota <= 10) {
                notaFila.setValorNota(nuevaNota);
            } else {
                // Si el valor no es válido, refrescamos la tabla para que vuelva al valor anterior
                tablaNotas.refresh();
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("Error");
                alerta.setHeaderText(null);
                alerta.setContentText("Introduce un valor entre 0 y 10.");
                alerta.showAndWait();
            }
        });
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
        //Obtenemos la lista de la tabla.

        List<Nota> listaParaGuardar = tablaNotas.getItems();

        if (listaParaGuardar != null && !listaParaGuardar.isEmpty()) {
            try {
                notaDaoImp.guardarNota(listaParaGuardar);

                // Persistencia exitosa
                Alert exito = new Alert(Alert.AlertType.INFORMATION);
                exito.setTitle("Confirmación");
                exito.setHeaderText(null);
                exito.setContentText("Las calificaciones se han sincronizado con la base de datos.");
                exito.show();

            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Error");
                error.setContentText("No se han guardado los cambios.");
                error.show();
                e.printStackTrace();
            }
        }
    }
}