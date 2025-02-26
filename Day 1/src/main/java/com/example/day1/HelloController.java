package com.example.day1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private final String CONNECTION_URL = "jdbc:mysql://localhost:3306/healthdb";
    private final String USER_NAME = "devuser";
    private final String USER_PASSWORD = "251088";
    @FXML
    private TextField fn;
    @FXML
    private TextField sn;
    @FXML
    private PasswordField p;
    @FXML
    private PasswordField cp;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD);
            System.out.println("Successful Connection....");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void CreateAccountHandler(ActionEvent event) {
        String firstname = fn.getText();
        String secondname = sn.getText();
        String password = p.getText();
        String confirmpassword = cp.getText();

        Validation(firstname, secondname, password, confirmpassword, event);
        fn.clear();
        sn.clear();
        p.clear();
        p.clear();
        cp.clear();

    }

    public void LoginHandler(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            //create a new stage
            Stage stage = new Stage();
            stage.setTitle("Login");
            stage.setScene(new Scene(root));
            stage.show();

            //close the current stage
            Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            currentStage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Validation(String firstname, String secondname, String password, String confirmpassword, ActionEvent event) {
        if (firstname.isEmpty() || secondname.isEmpty() || password.isEmpty() || confirmpassword.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please Fill The Required Details");
            alert.setTitle("CONFIRMATION DETAILS");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                alert.close();
            }
        } else {

            if (!password.equalsIgnoreCase(confirmpassword)) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Password Miss Match");
                alert.setTitle("Password Miss Match");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    alert.close();
                }
            } else {
                try {
                    Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, USER_PASSWORD);

                    Statement statement = connection.createStatement();
                    String createUserQuery = "INSERT INTO healthdb.users(FirstName, SecondName, Password)VALUES (%s, %s, %s)".formatted(
                            statement.enquoteLiteral(firstname), statement.enquoteLiteral(secondname), statement.enquoteLiteral(password)
                    );
                    statement.executeUpdate(createUserQuery);
                    connection.close();

                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Account Successfully Created");

                    Optional<ButtonType> result = alert.showAndWait();
                    if ((result.isPresent() && result.get() == ButtonType.OK)) {
                        alert.close();
                    }
                    try {

                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("login.fxml"));
                        Parent root = loader.load();

                        //create a new stage
                        Stage stage = new Stage();
                        stage.setTitle("Login");
                        stage.setScene(new Scene(root));
                        stage.show();

                        //close the current stage
                        Stage currentStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                        currentStage.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
