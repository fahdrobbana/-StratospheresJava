/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package users.entity;

/**
 *
 * @author majdi
 */
public class User {

    private int id;
    private String email, roles, password, name, firstname, reset_token, image;
    private boolean is_verified,blocked=false;

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public User() {
    }

    public User(int id, String email, String roles, String password, String name, String firstname, String reset_token, String image, boolean is_verified) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.reset_token = reset_token;
        this.image = image;
        this.is_verified = is_verified;
    }
    

    

    public User(String email, String roles, String password, String name, String firstname, String image, boolean blcked) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.image = image;
        this.blocked = blocked;
    }

    
    
    public User(int id, String email, String roles, String password, String name, String firstname, String image, boolean is_verified) {
        this.id = id;
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.image = image;
        this.is_verified = is_verified;
    }

    public User(String email, String roles, String password, String name, String firstname, String reset_token, String image, boolean blocked) {
        this.email = email;
        this.roles = roles;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.reset_token = reset_token;
        this.image = image;
        this.blocked=blocked;
    }

    public User(String email, String password, String name, String firstname, String reset_token, String image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.firstname = firstname;
        this.reset_token = reset_token;
        this.image = image;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getReset_token() {
        return reset_token;
    }

    public void setReset_token(String reset_token) {
        this.reset_token = reset_token;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isIs_verified() {
        return is_verified;
    }

    public void setIs_verified(boolean is_verified) {
        this.is_verified = is_verified;
    }

    @Override
    public String toString() {
        return "User{" + ", email=" + email + ", roles=" + roles + ", password=" + password + ", name=" + name + ", firstname=" + firstname + ", image=" + image + ", is_verified=" + is_verified + ", isblocked=" + blocked +  '}';
    }

}
