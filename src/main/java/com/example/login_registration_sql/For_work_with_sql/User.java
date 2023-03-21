package com.example.login_registration_sql.For_work_with_sql;

public class User {
    private String name;
    private String pass;
    private String money;

    public User(String name, String pass, String money) {
        this.name = name;
        this.pass = pass;
        this.money = money;
    }

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
