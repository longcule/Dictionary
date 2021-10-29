package com.example.dictionaryapplication;


import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    DictionaryManagement dictionaryManagement = new DictionaryManagement();

    @FXML
    private Button button1, button2, button3, button4, button5, button6, button7, button8;

    @FXML
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

    @FXML
    public void exitAlert() throws IOException {
        alert.setTitle("COMFIRMATION");
        alert.setHeaderText("Do you want to exit ?");
        alert.setContentText("Choose your option:");

        ButtonType buttonTypeYes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeYes) {
            System.exit(0);
        }

    }
    //change scene
    @FXML
    public void handleButtonAction(ActionEvent event) throws Exception {
        Stage stage;
        Parent root;
        if (event.getSource() == login && (isFieldFilled() && isValid())) {
            stage = (Stage) login.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        } else if (event.getSource() == button1) {
            stage = (Stage) button1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Translate.fxml"));
        } else if (event.getSource() == button2) {
            stage = (Stage) button2.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Bookmark.fxml"));
        } else if (event.getSource() == button3) {
            stage = (Stage) button3.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Add-New-Word.fxml"));
            dictionaryManagement.insertFromFile();
        } else if (event.getSource() == button4) {
            stage = (Stage) button4.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Delete-Word.fxml"));
        } else if (event.getSource() == button5) {
            stage = (Stage) button5.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Edit-Word.fxml"));
        } else if (event.getSource() == button6) {
            stage = (Stage) button6.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Exit.fxml"));
        } else if (event.getSource() == button7) {
            stage = (Stage) button7.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Search-Data.fxml"));
        } else {
            stage = (Stage) button8.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dictionaryManagement.insertFromFile();
        searcher.textProperty().addListener((observable, oldValue, newValue) -> {
            suggestions.getItems().clear();
            autoComplete();
        });
        setComboBox();
        setBookMark();
    }
    //translate:
    @FXML
    private ComboBox<String> from = new ComboBox<>();

    @FXML
    private ComboBox<String> to = new ComboBox<>();

    @FXML
    ObservableList<String> languages = FXCollections.
            observableArrayList(
                    "English", "Vietnamese",
                    "French", "Chinese",
                    "Japanese", "German",
                    "Dutch", "Korean");
    @FXML
    private TextArea in, out;

    @FXML
    public void setComboBox() {
        from.setItems(languages);
        to.setItems(languages);
    }
    @FXML
    public void translate(ActionEvent event) throws IOException {
        String fromLang = Dictionary.getFromLanguageFromFile(from.getValue());
        String toLang = Dictionary.getToLanguageFromFile(to.getValue());
        String translate = Translator.translate(fromLang,
                toLang, in.getText());
        out.clear();
        out.setText(translate);
        in.setWrapText(true);
        out.setWrapText(true);
        Word word = new Word();
        word.setWord_target(in.getText());
        word.setWord_explain(translate);
        dictionaryManagement.dictionary.addBookmark(word);
        dictionaryManagement.exportBookmark();
    }

    //login:
    @FXML
    private Label error_login;
    @FXML
    private PasswordField pass;

    @FXML
    private TextField user;

    @FXML
    private Button login;

    private String error = "";

    private boolean isFieldFilled() {
        boolean isFilled = true;
        if (user.getText().isEmpty()) {
            isFilled = false;
            error = "Username is Empty";
        }
        if (pass.getText().isEmpty()) {
            isFilled = false;
            if (error.isEmpty()) {
                error = "Password is Empty";
            } else {
                error += "\nPassword is Empty";
            }
        }
        error_login.setText(error);
        return isFilled;
    }

    private boolean isValid() {
        boolean isValid = true;
        if (!user.getText().equals(DictionaryApplication.USENAME)) {
            isValid = false;
            error = "Invalid Username";
        }

        if (!pass.getText().equals(DictionaryApplication.PASSWORD)) {
            isValid = false;
            if (error.isEmpty()) {
                error = "Invalid Password!";
            } else {
                error += "\nInvalid Password";
            }
        }
        error_login.setText(error);
        return isValid;
    }

    //add new word:
    @FXML
    private TextArea enWord = new TextArea();
    @FXML
    private TextArea vnWord = new TextArea();
    @FXML
    Alert notice = new Alert(Alert.AlertType.INFORMATION );
    @FXML
    public void saveNotice() {
        notice.setTitle("Add a new word");
        notice.setHeaderText("Your word '" + enWord.getText() + "' is added successfully");

        Optional<ButtonType> result = notice.showAndWait();
    }
    @FXML
    public void addNewWord() throws IOException {
        dictionaryManagement.addWord(enWord.getText(), vnWord.getText());
        dictionaryManagement.exportData();
        saveNotice();
    }

    //lookup:
    @FXML
    private TextArea result = new TextArea();

    @FXML
    private TextField searcher = new TextField();

    @FXML
    private ListView<String> suggestions = new ListView<>();

    @FXML
    public void autoComplete(){
        try {
            List<String> elements = dictionaryManagement.dictionarySearcherFX(searcher.getText());
            ObservableList<String> listViewData = FXCollections.observableArrayList(elements);
            suggestions.setItems(listViewData);
            suggestions.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
                String selectedItem = suggestions.getSelectionModel().getSelectedItem();
                int index = suggestions.getSelectionModel().getSelectedIndex();

                searcher.setText(selectedItem);
            });

        } catch (StringIndexOutOfBoundsException e) {}

    }



    @FXML
    public void lookUp(ActionEvent event) throws IOException{
        result.setText(dictionaryManagement.lookUp(searcher.getText()));
    }

    @FXML
    public void lookUpAuto(String s) throws IOException{
        result.setText(dictionaryManagement.lookUp(s));
    }

    //delete:
    @FXML
    private TextArea word;

    @FXML
    Alert delAlert = new Alert(Alert.AlertType.INFORMATION );
    @FXML
    public void deleteSuccess() {
        delAlert.setTitle("Delete a word");
        delAlert.setHeaderText("Your word '" + word.getText() + "' is deleted successfully");

        Optional<ButtonType> result = delAlert.showAndWait();
    }

    public void deleteFail() {
        delAlert.setTitle("Delete a word");
        delAlert.setHeaderText("Your word '" + word.getText() + "' is not deleted successfully");

        Optional<ButtonType> result = delAlert.showAndWait();
    }
    @FXML
    public void deleteWord() throws IOException{
        if(dictionaryManagement.deleteWordFx(word.getText())) {
            deleteSuccess();
        } else {
            deleteFail();
        }
        dictionaryManagement.exportData();
    }


    //Edit:
    @FXML
    private TextArea editWord;
    @FXML
    private TextArea newExplain;
    @FXML
    Alert editAlert = new Alert(Alert.AlertType.INFORMATION );
    @FXML
    public void editSuccess() {
        editAlert.setTitle("Edit word");
        editAlert.setHeaderText("Your word '" + editWord.getText() + "' is edited successfully!");

        Optional<ButtonType> result = editAlert.showAndWait();
    }

    @FXML
    public void editFail() {
        editAlert.setTitle("Edit word");
        editAlert.setHeaderText("Your word '" + editWord.getText() + "' is not found!");

        Optional<ButtonType> result = editAlert.showAndWait();
    }
    public void changeToNewWord() throws IOException{
        boolean check;
        check = dictionaryManagement.editWordFX(editWord.getText(), newExplain.getText());
        if (check) {
            editSuccess();
        } else {
            editFail();
        }
        dictionaryManagement.exportData();
    }

    //Bookmark:
    @FXML
    private TextArea wordTarget = new TextArea();

    @FXML
    private TextArea wordExplain = new TextArea();

    @FXML
    public void setBookMark() {
        int i = 0;
        String left = "";
        String right = "";
        dictionaryManagement.insertFromBookmark();
        for (Word iter : dictionaryManagement.dictionary.Bookmark) {
            i++;
            left = left + i + "." + iter.getWord_target() + "\n";
            right = right + iter.getWord_explain() + "\n";
        }
        wordTarget.setText(left);
        wordExplain.setText(right);
    }
}
