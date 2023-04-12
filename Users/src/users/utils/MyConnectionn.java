/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Nour Saidi
 */
public class MyConnectionn {

    private static Connection conn; //DB Credations

    String url = "jdbc:mysql://localhost:3306/amaltest";
    String user = "root";
    String pwd = "";
    private static MyConnectionn instance;

    private MyConnectionn() {
        try {
            conn = DriverManager.getConnection(url, user, pwd);
            System.out.println("Connexion etablie!!!");
        } catch (SQLException ex) {
            System.out.println("Probleme de connexion");
        }
    }

    public static MyConnectionn getInstance() {
        if (instance == null) {
            instance = new MyConnectionn();
        }
        return instance;
    }

    public Connection getConn() {
        return MyConnectionn.getInstance().conn;
    }

}
