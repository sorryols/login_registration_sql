package com.example.login_registration_sql;

import com.example.login_registration_sql.For_work_with_sql.DatabaseHandler;
import com.example.login_registration_sql.For_work_with_sql.User;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Objects;

public class Incasator {
    @FXML
    private TextField Add_textfield;

    @FXML
    private Button Execute_button;

    @FXML
    private Label Informatin_label;

    @FXML
    private TextField Minus_texxtfield;

    @FXML
    private Label Status_label;
    @FXML
    private Button Startwork_button;
    @FXML
    void initialize() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();

        Startwork_button.setOnAction(event -> {
                // Step 1
            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
                // Step 2
            User u = (User) stage.getUserData();
                // Step 3
            user.setName(u.getName());
            user.setPass(u.getPass());
            user.setMoney(dbHandler.getMoney(user));

            show_element();
            show_info(user);
        });

        Execute_button.setOnAction(actionEvent -> {
            execute(user);
            user.setMoney(dbHandler.getMoney(user));
            show_info(user);
        });
    }

    private void execute(User user) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        boolean chek = chek_vaules();
        if(chek){
            int money_toadd = Integer.parseInt(Add_textfield.getText().trim());
            int money_tomin = Integer.parseInt(Minus_texxtfield.getText().trim());
            int curent_money = Integer.parseInt(user.getMoney());
            int result = curent_money + money_toadd - money_tomin;
            if(result>=0){
                String money = Integer.toString(result);
                dbHandler.changeMoney(user, money);
                status_change_balance();
            }else{status_not_enogh_money();}
        }else{status_wrong_data();}
    }

    private void status_change_balance() {
        Status_label.setText("Status: balance changed");
    }

    private void status_wrong_data() {
        Status_label.setText("Status: wrong data in text field");
    }

    private void status_not_enogh_money() {
        Status_label.setText("Status: not enough money in balance");
    }

    private boolean chek_vaules() {
        int money_toadd=0;
        int money_tomin=0;

        if (Add_textfield.getText().trim().equals("")){Add_textfield.setText("0");}
        if (Minus_texxtfield.getText().trim().equals("")){Minus_texxtfield.setText("0");}

        try {money_toadd = Integer.parseInt(Add_textfield.getText().trim());
            money_tomin = Integer.parseInt(Minus_texxtfield.getText().trim());}
        catch (Exception ex){return false;}
        return true;
    }

    private void show_info(User user) {
        Informatin_label.setText("Information: user "+user.getName()+" have "+user.getMoney());
    }

    private void show_element() {
        Add_textfield.setVisible(true);
        Informatin_label.setVisible(true);
        Minus_texxtfield.setVisible(true);
        Status_label.setVisible(true);
        Execute_button.setVisible(true);
        Startwork_button.setVisible(false);
    }
}
