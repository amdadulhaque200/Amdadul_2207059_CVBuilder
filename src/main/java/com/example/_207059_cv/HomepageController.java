package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomepageController {

    @FXML private Label welcomeText;

    @FXML private TextField searchNameField;

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
    @FXML
    protected void onPreviewCVClick() {
        try {
            String name = searchNameField.getText().trim();
            if (name.isEmpty()) {
                System.out.println("Please enter a name!");
                return;
            }
            Databasehandler db = Databasehandler.getInstance();
            Getter_Setter cvData = db.getCVByName(name);

            if (cvData != null) {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/com/example/_207059_cv/Previewpage.fxml")
                );
                Scene scene = new Scene(loader.load(), 600, 700);

                PreviewpageController controller = loader.getController();
                controller.setCVData(cvData);
                controller.setCVId(-1);

                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle("CV Preview");
                stage.show();
            } else {
                System.out.println("CV not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
