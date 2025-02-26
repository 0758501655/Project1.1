package com.example.day1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private ImageView logoImageView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            InputStream logoStream = getClass().getResourceAsStream("/com/example/day1/h1.png");
            if (logoStream == null){
                throw new IllegalArgumentException("h1.png not found");
            }
            Image logoImage = new Image( logoStream);
            logoImageView.setImage(logoImage);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void CreateAccountHandler(ActionEvent event){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("hello-view.fxml"));
            Parent root = loader.load();

            //create a new stage
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            //close the current stage
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void LoginHandler(ActionEvent event){
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();

            //create a new stage
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            //close the current stage
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


