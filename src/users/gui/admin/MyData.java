/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users.gui.admin;

import javafx.scene.image.Image;


    class MyData {
        private  String name;
        private  String lastname;
        private  String image;
        private  String email;
        private int id ; 

    public MyData(String name, String lastname, String image, String email, int id) {
        this.name = name;
        this.lastname = lastname;
        this.image = image;
        this.email = email;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getImage() {
        return image;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }
   
    

      
    }