package com.example.demojava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Translate.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("Dictionary App");
        Image image = new Image("C:\\Users\\Admin\\" +
                "IdeaProjects\\demojava\\src\\main\\image2");
        primaryStage.getIcons().add(image);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}