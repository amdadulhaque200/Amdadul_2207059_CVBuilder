package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class HomepageController {

    @FXML private Label welcomeText;

    @FXML
    protected void onCreateCVClick() throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/_207059_cv/Form.fxml")
        );
        Scene scene = new Scene(loader.load(), 900, 650);
        Stage stage = (Stage) welcomeText.getScene().getWindow();
        stage.setTitle("CV Form");
        stage.setScene(scene);
        stage.show();
    }
}
