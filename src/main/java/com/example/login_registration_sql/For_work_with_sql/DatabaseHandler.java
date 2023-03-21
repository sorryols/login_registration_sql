package com.example.login_registration_sql.For_work_with_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
public class DatabaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser,dbPass);

        return dbConnection;
    }

    //Write
    public void sigUpUser(User user){
        String insert = "INSERT INTO " + Constant.USER_TABLE + " (" + Constant.USER_NAME +
                "," + Constant.USER_PASS + ","+Constant.USER_MONEY + ")" + "VALUES(?,?,?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);

        prSt.setString(1, user.getName());
        prSt.setString(2, user.getPass());
        prSt.setString(3, user.getMoney());

        prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //Get
    public ResultSet getUser(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE +
                " WHERE "+ Constant.USER_NAME+" = ? AND " + Constant.USER_PASS +" = ?";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getName());
            prSt.setString(2, user.getPass());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    public ResultSet getUserName(User user){
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE +
                " WHERE "+ Constant.USER_NAME+" = ?";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getName());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }
    //UPDATE `users` SET `user_money` = '100' WHERE (`idusers` = '1');
    public void changeMoney(User user, String money) {
        String insert = "UPDATE " +Constant.USER_TABLE + " SET " +Constant.USER_MONEY+" = ? WHERE (" +Constant.USER_NAME+"= ?)";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, money);
            prSt.setString(2, user.getName());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMoney(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Constant.USER_TABLE +
                " WHERE "+ Constant.USER_NAME+" = ?";

        PreparedStatement prSt = null;
        try {
            prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getName());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        String money = "" ;

        while (true) {
            try {
                if (!resSet.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                money = resSet.getString(Constant.USER_MONEY);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return money;
    }
}
