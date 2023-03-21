package com.example.login_registration_sql;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.example.login_registration_sql.For_work_with_sql.Constant;
import com.example.login_registration_sql.For_work_with_sql.DatabaseHandler;
import com.example.login_registration_sql.For_work_with_sql.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.stage.Stage;

//fx:controller="com.example.login_registration_sql.HelloController"
public class HelloController {
    @FXML
    private Button Login_button, Reg_button;

    @FXML
    private TextField Login_textfield;

    @FXML
    private PasswordField Pass_textfield;

    @FXML
    void initialize() {
        Login_button.setOnAction(actionEvent -> {
            try {login();} catch (SQLException e) {throw new RuntimeException(e);}
        });

        Reg_button.setOnAction(actionEvent -> reg());
    }

    private void reg() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registration.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setResizable(false);
        String menu = new File("src/main/resources/icons/add.png").toURI().toString();
        Image menu_ic = new Image(menu);
        stage.getIcons().add(menu_ic);
        stage.setTitle("Sign up");
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void login() throws SQLException {
        User user = new User( Login_textfield.getText().trim(), Pass_textfield.getText().trim());

        if(!Objects.equals(user.getName(), "") && !Objects.equals(user.getPass(), "")){
            loginUser(user);
        }else{
            System.out.println("[-]Error, with login or password");
        }
    }

    private void loginUser(User user) throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet rs = dbHandler.getUser(user);

        int counter = 0;
        while (rs.next()) {counter++;}

        if(counter >=1){
            incasator(user);
            System.out.println("[!]Successful log in");
        }else{
            System.out.println("[-]Don`t find user");
        }
    }

    private void incasator(User user) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("incasator.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        String menu = new File("src/main/resources/icons/money.png").toURI().toString();
        Image menu_ic = new Image(menu);
        stage.getIcons().add(menu_ic);
        stage.setResizable(false);
        stage.setTitle("Incasator");
        stage.setUserData(user);
        stage.show();
    }
}
