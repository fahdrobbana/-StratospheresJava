/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package users;

import users.entity.User;
import users.services.user_crud;

/**
 *
 * @author majdi
 */
public class Users {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        user_crud u = new user_crud();
        //u.ajouter(new User("majdi.jendoubi@esprit.tn","ROLE_USER","0000","Jendoubi","Majdi","xxx",true));
        //u.modifier(new User(27,"majdi.jendoubi@esprit.tn","ROLE_USER","0000","Jendoubi","Sarah","xxx",true));
        //u.supprimer(27);
        System.out.println(u.afficher());
    }
    
}
