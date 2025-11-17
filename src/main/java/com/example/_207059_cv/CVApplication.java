package com.example._207059_cv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class CVApplication extends Application {
    @Override  public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CVApplication.class.getResource("Homepage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 650);
        stage.setTitle("CVBuilder APK!");
        stage.setScene(scene);
        stage.show();
    }
}
