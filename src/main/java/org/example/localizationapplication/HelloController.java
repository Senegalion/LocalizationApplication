package org.example.localizationapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    public ComboBox<String> languageComboBox;
    @FXML
    public Label lblTitle;
    @FXML
    public TextArea jobTitleTextArea;
    @FXML
    public TextField keyNameTextField;
    @FXML
    public TextField translationTextField;
    @FXML
    public Button addButton;

    private ResourceBundle rb;
    private LocalizationDatabase db;

    public void initialize() {
        languageComboBox.getItems().addAll("English", "French", "Spanish", "Chinese");
        languageComboBox.setValue("English");

        db = new LocalizationDatabase();

        try {
            db.connect();
        } catch (SQLException e) {
            showAlert("Database Connection Error", e.getMessage());
        }

        setLanguage(Locale.of("en"));

        languageComboBox.setOnAction(this::onLanguageChange);
    }

    private void setLanguage(Locale locale) {
        rb = ResourceBundle.getBundle("messages", locale);
        lblTitle.setText(rb.getString("title"));
        updateJobTitleTable();
    }

    public void onLanguageChange(ActionEvent actionEvent) {
        updateJobTitleTable();
    }

    public void onAddButtonClick(ActionEvent actionEvent) {
        String languageCode = getLanguageCode();
        String keyName = keyNameTextField.getText();
        String translation = translationTextField.getText();
        if (keyName != null && !keyName.isEmpty() && translation != null && !translation.isEmpty()) {
            try {
                db.addTranslation(keyName, translation, languageCode);
                keyNameTextField.clear();
                translationTextField.clear();
                updateJobTitleTable();
            } catch (SQLException e) {
                showAlert("Database Error", e.getMessage());
            }
        }
    }

    private void updateJobTitleTable() {
        String languageCode = getLanguageCode();
        try {
            List<String> jobTitles = db.getJobTitles(languageCode);
            StringBuilder sb = new StringBuilder();
            for (String title : jobTitles) {
                sb.append(title).append("\n");
            }
            jobTitleTextArea.setText(sb.toString());
        } catch (SQLException e) {
            showAlert("Database Error", e.getMessage());
        }
    }

    private String getLanguageCode() {
        String selectedLanguage = languageComboBox.getValue();
        return switch (selectedLanguage) {
            case "French" -> "fr";
            case "Spanish" -> "es";
            case "Chinese" -> "zh";
            default -> "en";
        };
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}