package com.finalproject.API.datautil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public class UConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Runtime.getRuntime().addShutdownHook(new MyShDwnHook());

                ResourceBundle rb = ResourceBundle.getBundle("jdbc");
                String driver = rb.getString("driver");
                String url = rb.getString("url");
                String pwd = rb.getString("pwd");
                String usr = rb.getString("usr");

                Class.forName(driver).newInstance();
                conn = DriverManager.getConnection(url, usr, pwd);
            }
            return conn;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error creating connection", ex);
        }
    }

    static class MyShDwnHook extends Thread {
        @Override
        public void run() {
            try {
                Connection conn = UConnection.getConnection();
                conn.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }
}
