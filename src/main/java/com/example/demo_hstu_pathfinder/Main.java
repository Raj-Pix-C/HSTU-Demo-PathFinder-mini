package com.example.demo_hstu_pathfinder;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;


public class Main extends Application {
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primarystage) throws IOException {

        primarystage.setTitle("Pathfinder");
        primarystage.setResizable(false);

        FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("main.fxml")));
        Scene scene = new Scene(loader.load(), 1280, 720);

        primarystage.setScene(scene);
        primarystage.show();
    }
}
