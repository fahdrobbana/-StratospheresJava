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
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;


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
            ps.setBoolean(7, U.isIs_verified());

            ps.executeUpdate();
            System.out.println("User added!!!");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void modifier(User U) {
         String  req ;
                      System.out.println(U.isBlocked());
         try {
           req = "UPDATE `users` SET `name` = '" + U.getName() + "', `firstname` = '" + U.getFirstname() + "', `email` = '" + U.getEmail() + "', `password` = '" + U.getPassword() + "', `image` = '" + U.getImage()+ "', `reset_token` = '" + U.getReset_token()  + "', `blocked` = '"+U.isBlocked() +"'where `id` = " + U.getId();
            System.out.println(req);
           PreparedStatement ps = connexion.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("Informations updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void nomodifierblocker(User U) {
         String  req ;
         try {
           req = "UPDATE `users` SET `blocked` = 0  where `id` = " + U.getId();
            System.out.println(req);
           PreparedStatement ps = connexion.prepareStatement(req);
            ps.executeUpdate(req);
            System.out.println("Informations updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifierblocker(User U) {
         String  req ;
         try {
           req = "UPDATE `users` SET `blocked` = 1  where `id` = " + U.getId();
            System.out.println(req);
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
                U.setReset_token(RS.getString("reset_token"));

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
                U.setReset_token(RS.getString("reset_token"));

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
                U.setBlocked(RS.getBoolean(10));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return U;

    }
     /* public boolean FoundUser(String email, String motpass) throws SQLException {
        User U = new User();
        try {
            String req = "SELECT COUNT(*) FROM user WHERE email = ? AND password = ?";
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
        return false;

    }
*/public boolean FoundUser(String email, String motpass) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE email = ? AND motPass = ?";
        PreparedStatement pstmt =  connexion.prepareStatement(query);

        pstmt.setString(1, email);
        pstmt.setString(2, motpass);

        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            int count = rs.getInt(1);
            return count > 0;
        }

        return false;
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
    
    public void sendMail(User user,String message,String subject) throws SQLException {



        System.out.println(user.getEmail());

        if (user == null) {
            System.out.println("User with ID " + user.getId() + " not found!");
            return;
        } else {
        }

        final String username = "majdi.jendoubi@esprit.tn";
        final String password = "181JMT1795";
        String recipientEmail = user.getEmail();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
                
            }
            
        });
        try {
            
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(username));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            msg.setSubject(subject);
            msg.setText(message);
            Transport.send(msg);
            System.out.println("Email notification sent successfully.");
        } catch (MessagingException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
   public user_crud()
   {
       
   }
    public List<User> getUsersByEmail(String email) throws SQLException {
        String query = "SELECT * FROM users WHERE email = ?";
        PreparedStatement preparedStatement =  connexion.prepareStatement(query);
        preparedStatement.setString(1, email);
        ResultSet rs = preparedStatement.executeQuery();
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
           user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setFirstname(rs.getString("firstname"));
        user.setReset_token(rs.getString("reset_token"));
            users.add(user);
        }
        return users;
    }
    
    public User getUserByEmai(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE `email`=?";
            PreparedStatement statement = connexion.prepareStatement(query);
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
            user.setReset_token(rs.getString("reset_token"));

            }
        } catch (SQLException ex) {
            System.out.println("Failed to get user: " + ex.getMessage());
            // log or rethrow the exception
        }
        return user;
    }
    

}