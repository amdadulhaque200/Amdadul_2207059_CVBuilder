package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PreviewpageController {

    @FXML private Label fullNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    @FXML private Label skillsLabel;
    @FXML private Label educationLabel;
    @FXML private Label experienceLabel;

    @FXML private ImageView photoView;

    private Getter_Setter cvData;

    public void setCVData(Getter_Setter data) {

        this.cvData = data;

        fullNameLabel.setText(data.getFullName());
        emailLabel.setText(data.getEmail());
        phoneLabel.setText(data.getPhone());
        addressLabel.setText(data.getAddress());

        skillsLabel.setText(data.getSkills());
        educationLabel.setText(data.getEducation());
        experienceLabel.setText(data.getExperience());

        if (data.getApplicantPhoto() != null) {
            photoView.setImage(data.getApplicantPhoto());
        }
    }

    @FXML
    private void onEditCVClick() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/_207059_cv/Form.fxml")
            );
            Scene formScene = new Scene(loader.load(), 900, 650);

            FormController controller = loader.getController();
            controller.fillFormWithExistingData(cvData);

            Stage stage = (Stage) fullNameLabel.getScene().getWindow();
            stage.setScene(formScene);
            stage.setTitle("Edit CV");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void onSaveClick() {
        try {
            Databasehandler db = Databasehandler.getInstance();
            boolean success;
            if (cvData.getId() > 0) {
                success = db.updateCV(cvData.getId(), cvData);
            } else {
                success = db.insertCV(cvData);
            }

            if (success) {
                System.out.println("CV saved successfully!");
                if (cvData.getId() > 0) {

                    cvData = db.getCV(cvData.getId());
                }
            } else {
                System.out.println("Failed to save CV.");
            }
            Stage stage = (Stage) fullNameLabel.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
