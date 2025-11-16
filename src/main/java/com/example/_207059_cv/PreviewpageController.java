package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class PreviewpageController {

    @FXML private Label fullNameLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label addressLabel;

    @FXML private Label skillsLabel;
    @FXML private Label educationLabel;
    @FXML private Label experienceLabel;

    @FXML private ImageView photoView;

    public void setCVData(Getter_Setter data) {
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
}
