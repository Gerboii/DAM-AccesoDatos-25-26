package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.image.Image;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
       FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("interfaz.fxml"));
       Scene scene = new Scene(fxmlLoader.load());
       stage.setTitle("Login UEM");
       stage.setResizable(false);
       //Carga Icono
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/ue40px.png")));
       stage.setScene(scene);
       stage.centerOnScreen();
       stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}