package com.example.controller;

import com.example.dao.AsignaturaDaoImp;
import com.example.dao.NotaDaoImp;
import com.example.modelos.Alumno;
import com.example.modelos.Asignatura;
import com.example.modelos.Nota;
import com.example.util.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.util.List;

public class AlumnoController {
    private NotaDaoImp notaDaoImp=new NotaDaoImp();
    private AsignaturaDaoImp asignaturaDaoImp=new AsignaturaDaoImp();
    private Alumno alumnoLog;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnSalir;

    @FXML
    private ComboBox<Asignatura> cbAsignaturas;

    @FXML
    private TableColumn<Nota,String> columnAsignatura;

    @FXML
    private TableColumn<Nota,String> columnNota;

    @FXML
    private Label nombreAlumno;

    @FXML
    private TableView<Nota> tablaAlumno;

    @FXML
    public void initialize() {
        //Iniciamos combo
        cbAsignaturas.setPromptText("Seleccione asignatura");
        //Columna ASIGNATURA
        columnAsignatura.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getAsignatura().getNombre()));
        //Columna NOTA
        columnNota.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getValorNota())));
    }

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
        Asignatura asignatura = cbAsignaturas.getSelectionModel().getSelectedItem();
        if (asignatura != null){
            if (asignatura.getNombre().equals("Mostrar todas")){
                //Mostramos todo el boletin
                List<Nota>todas= notaDaoImp.mostrarEvaluacion(alumnoLog.getDniAlumno());
                tablaAlumno.setItems(FXCollections.observableArrayList(todas));
            }else{
                //Mostramos nota específica
                Nota miNota = notaDaoImp.mostrarNota(asignatura,alumnoLog);
                if (miNota != null){
                    tablaAlumno.setItems(FXCollections.observableArrayList(miNota));
                }else {
                    tablaAlumno.getItems().clear();
                }
            }
        }
    }

    public void setAlumno(Alumno alumno) {
        this.alumnoLog=alumno;
        if (nombreAlumno!=null) {
            nombreAlumno.setText(alumno.getNombre());
        }
        List<Asignatura> listaAsig= asignaturaDaoImp.listarPorAlumno(alumno.getDniAlumno());
        cargarComboBox(listaAsig);
    }

    private void cargarComboBox(List<Asignatura> listaAsig) {
        ObservableList<Asignatura> obsList=FXCollections.observableArrayList();
        if(listaAsig!=null){
            obsList.addAll(listaAsig);
        }
        //Opcion mostrar todas
        Asignatura todas = new  Asignatura();
        todas.setNombre("Mostrar todas");
        obsList.add(todas);
        cbAsignaturas.setItems(obsList);
        //Converter para sacar nombre
        cbAsignaturas.setConverter(new  StringConverter<Asignatura>() {
            @Override
            public String toString(Asignatura asignatura) {
                return (asignatura != null) ? asignatura.getNombre() : "";}
            @Override
            public Asignatura fromString(String string) {return null;}
        });
    }

}
