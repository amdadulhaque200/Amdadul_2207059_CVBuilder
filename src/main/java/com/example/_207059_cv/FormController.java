package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea summaryField;
    @FXML private TextArea skillsField;
    @FXML private TextArea educationField;
    @FXML private TextArea experienceField;

    @FXML private Button generateButton;

    @FXML
    protected void onGenerateCVClick() {
        try {
            Getter_Setter cvData = new Getter_Setter();
            cvData.setFullName(fullNameField.getText());
            cvData.setEmail(emailField.getText());
            cvData.setPhone(phoneField.getText());
            cvData.setAddress(addressField.getText());
            cvData.setSkills(skillsField.getText());
            cvData.setEducation(educationField.getText());
            cvData.setExperience(experienceField.getText());

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/_207059_cv/Previewpage.fxml")
            );
            Scene previewScene = new Scene(loader.load(), 600, 700);


            PreviewpageController controller = loader.getController();
            controller.setCVData(cvData);

            Stage stage = (Stage) generateButton.getScene().getWindow();
            stage.setTitle("CV Preview");
            stage.setScene(previewScene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onEditCVClick() {
        System.out.println("Edit CV clicked!");
    }
}
