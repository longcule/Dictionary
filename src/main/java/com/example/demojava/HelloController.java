package com.example.demojava;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.PrimitiveIterator;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;

    @FXML
      public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if(event.getSource()== button1){
            stage = (Stage) button1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        }else if(event.getSource()== button2){
            stage = (Stage) button2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Bookmark.fxml"));
        }else if(event.getSource()== button3){
            stage = (Stage) button3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Add-New-Word.fxml"));
        }else if(event.getSource()== button4){
            stage = (Stage) button4.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Delete-Word.fxml"));
        }else if(event.getSource()== button5){
            stage = (Stage) button5.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Edit-Word.fxml"));
        }else{
            stage = (Stage) button6.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Exit.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}