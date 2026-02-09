package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("interfaz.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       stage.setTitle("Login UEM");
       //TODO Cargar Icono
        /*
       Image icon = new Image("ue40px.png");
       stage.getIcons().add(icon);*/
       stage.setScene(scene);
       stage.centerOnScreen();
       stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}