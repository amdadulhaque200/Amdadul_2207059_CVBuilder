package com.example._207059_cv;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class HomepageController {

    @FXML private Label welcomeText;

    @FXML private TextField searchNameField;
    private ContextMenu suggestionsPopup = new ContextMenu();
    @FXML
    public void initialize() {
        searchNameField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.isEmpty()) {
                suggestionsPopup.hide();
            } else {
                showSuggestions(newText);
            }
        });
    }
    private void showSuggestions(String text) {
        Databasehandler db = Databasehandler.getInstance();
        List<String> matches = db.getNamesStartingWith(text);

        if (matches.isEmpty()) {
            suggestionsPopup.hide();
            return;
        }

        List<MenuItem> menuItems = new ArrayList<>();
        for (String name : matches) {
            MenuItem item = new MenuItem(name);
            item.setOnAction(e -> {
                searchNameField.setText(name);
                suggestionsPopup.hide();
            });
            menuItems.add(item);
        }

        suggestionsPopup.getItems().setAll(menuItems);
        if (!suggestionsPopup.isShowing()) {
            suggestionsPopup.show(searchNameField, Side.BOTTOM, 0, 0);
        }
    }
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
