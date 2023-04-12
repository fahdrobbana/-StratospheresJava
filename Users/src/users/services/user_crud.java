/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import users.entity.User;
import users.interfaces.Interface;
import users.utils.MyConnectionn;

/**
 *
 * @author majdi
 */
public class user_crud implements Interface<User> {

    Statement ste;
    Connection connexion = MyConnectionn.getInstance().getConn();

    @Override
    public void ajouter(User U) {
        try {
            String req = "INSERT INTO `users` (`name`, `firstname`, `email`, `password`, `roles`, `image`, `is_verified`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.setString(1, U.getName());
            ps.setString(2, U.getFirstname());
            ps.setString(3, U.getEmail());
            ps.setString(4, U.getPassword());
            ps.setString(5, U.getRoles());
            ps.setString(6, U.getImage());
            ps.setBoolean(7, U.isIs_verified());

            ps.executeUpdate();
            System.out.println("User added!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(User U) {
        try {
            String req = "UPDATE `users` SET `name` = '" + U.getName() + "', `firstname` = '" + U.getFirstname() + "', `email` = '" + U.getEmail() + "', `password` = '" + U.getPassword() + "', `image` = '" + U.getImage() + "'";
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("Informations updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `users` WHERE id = " + id;
            PreparedStatement ps = connexion.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("User deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from users";
            PreparedStatement ps = connexion.prepareStatement(req);

            ResultSet RS = ps.executeQuery(req);
            while (RS.next()) {
                User U = new User();
                U.setName(RS.getString(6));
                U.setFirstname(RS.getString(7));
                U.setEmail(RS.getString(2));
                U.setRoles(RS.getString(3));
                U.setIs_verified(RS.getBoolean(5));
                U.setImage(RS.getString(9));
                                U.setId(RS.getInt(1));
                list.add(U);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }
    
      
    public List<User> afficheruser() {
        List<User> list = new ArrayList<>();
        try {
            String req = "Select * from users where roles!='Admin'";
            PreparedStatement ps = connexion.prepareStatement(req);

            ResultSet RS = ps.executeQuery(req);
            while (RS.next()) {
                User U = new User();
                U.setName(RS.getString(6));
                U.setFirstname(RS.getString(7));
                U.setEmail(RS.getString(2));
                U.setRoles(RS.getString(3));
                U.setIs_verified(RS.getBoolean(5));
                U.setImage(RS.getString(9));
                                U.setId(RS.getInt(1));
                list.add(U);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return list;
    }

    public User getByID(int id) {
        User U = new User();
        try {
            String req = "Select * from users where id = '" + id+"'";
            Statement st = connexion.createStatement();

            ResultSet RS = st.executeQuery(req);
            while (RS.next()) {
                U.setId(RS.getInt(1));
                U.setName(RS.getString(6));
                U.setFirstname(RS.getString(7));
                U.setEmail(RS.getString(2));
                U.setPassword(RS.getString(4));
                U.setRoles(RS.getString(3));
                U.setIs_verified(RS.getBoolean(5));
                U.setImage(RS.getString(9));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return U;

    }

    public int login(String password, String email) throws SQLException {
        String req = "SELECT * FROM users WHERE email = ? AND password = ?";
        PreparedStatement pstmt = connexion.prepareStatement(req);

        pstmt.setString(1, email);
        pstmt.setString(2, password);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }
}
