package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import java.io.File;

public class FormController {

    @FXML private TextField fullNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;

    @FXML private TextArea summaryField;
    @FXML private TextArea skillsField;
    @FXML private TextArea educationField;
    @FXML private TextArea experienceField;

    @FXML private Button uploadPhotoButton;
    @FXML private Button generateButton;
    @FXML private Label photoPathLabel;


    private Image uploadedImage;
    private File selectedPhoto;

    @FXML
    protected void onUploadPhotoClick() {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select Applicant Photo");
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
            );

            File file = fileChooser.showOpenDialog(uploadPhotoButton.getScene().getWindow());

            if (file != null) {
                selectedPhoto = file;
                uploadedImage = new Image(file.toURI().toString());

                if (photoPathLabel != null) {
                    photoPathLabel.setText(file.getName());
                }

                System.out.println("Photo selected: " + file.getName());
            }  else {
                System.out.println("No photo selected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void onGenerateCVClick() {
        try {
            Getter_Setter data = new Getter_Setter();

            data.setFullName(fullNameField.getText());
            data.setEmail(emailField.getText());
            data.setPhone(phoneField.getText());
            data.setAddress(addressField.getText());
            //data.setSummary(summaryField.getText());
            data.setSkills(skillsField.getText());
            data.setEducation(educationField.getText());
            data.setExperience(experienceField.getText());
            data.setApplicantPhoto(uploadedImage);

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/_207059_cv/Previewpage.fxml")
            );
            Scene scene = new Scene(loader.load(), 600, 700);

            PreviewpageController controller = loader.getController();
            controller.setCVData(data);

            Stage stage = (Stage) generateButton.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("CV Preview");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void fillFormWithExistingData(Getter_Setter data) {

        fullNameField.setText(data.getFullName());
        emailField.setText(data.getEmail());
        phoneField.setText(data.getPhone());
        addressField.setText(data.getAddress());

        skillsField.setText(data.getSkills());
        educationField.setText(data.getEducation());
        experienceField.setText(data.getExperience());

        if (data.getApplicantPhoto() != null) {
            uploadedImage = data.getApplicantPhoto();
            if (photoPathLabel != null) {
                photoPathLabel.setText("Photo loaded");
            }
        }
    }


}
