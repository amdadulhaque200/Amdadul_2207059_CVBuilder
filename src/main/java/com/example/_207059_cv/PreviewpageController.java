package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PreviewpageController {

    @FXML private Label fullNameLabel, emailLabel, phoneLabel, addressLabel;
    @FXML private Label summaryLabel, skillsLabel, educationLabel, experienceLabel;

    public void setCVData(Getter_Setter cvData) {
        fullNameLabel.setText("Full Name: " + cvData.getFullName());
        emailLabel.setText("Email: " + cvData.getEmail());
        phoneLabel.setText("Phone: " + cvData.getPhone());
        addressLabel.setText("Address: " + cvData.getAddress());
        skillsLabel.setText(cvData.getSkills());
        educationLabel.setText(cvData.getEducation());
        experienceLabel.setText(cvData.getExperience());
    }
}
