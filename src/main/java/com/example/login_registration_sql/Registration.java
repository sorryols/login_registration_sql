package com.example.login_registration_sql;

import com.example.login_registration_sql.For_work_with_sql.DatabaseHandler;
import com.example.login_registration_sql.For_work_with_sql.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Registration {

    @FXML
    private Button Createacc_button;

    @FXML
    private TextField Login_textfiedl;

    @FXML
    private TextField Money_textfiedl;

    @FXML
    private PasswordField Pass_textfield;

    @FXML
    private PasswordField REPPass_textfield;
    @FXML
    void initialize() {

        Createacc_button.setOnAction(actionEvent -> {
            signUpUser();
        });
    }

    private void signUpUser() {
        DatabaseHandler dbhandler = new DatabaseHandler();

        User user = new User(Login_textfiedl.getText().trim(), Pass_textfield.getText().trim(), Money_textfiedl.getText().trim());
        String reppass = REPPass_textfield.getText().trim();

        boolean chek = chek_field(user.getName(), user.getPass(), reppass, user.getMoney());

        if(chek){
            ResultSet rs = dbhandler.getUserName(user);

            int counter = 0;
            while (true) {
                try {if (!rs.next()) break;} catch (SQLException e) {throw new RuntimeException(e);}
                counter++;}

            if(counter >=1){
                System.out.println("[-]User with this name is already exists");
            }else{
                dbhandler.sigUpUser(user);
                System.out.println("[+]User is created");
            }
        }else {
            System.out.println("[-]Wrong data");
        }
    }

    private boolean chek_field(String name, String pass, String reppass, String sMoney) {
        int money;

        try {money = Integer.parseInt(sMoney);}
        catch (Exception ex){money = -1;}

        if(money == -1 ){ return false;}
        if(!Objects.equals(pass, reppass)){return false;}
        if(Objects.equals(name, "")){return false;}
        if(Objects.equals(sMoney, "")){return false;}
        if(Objects.equals(pass, "")){;return false;}

        return true;
    }
}
