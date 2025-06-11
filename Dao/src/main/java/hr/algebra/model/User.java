/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

/**
 *
 * @author kaish
 */
public class User {
    
    private int ID;
    private String Username;
    private String Password;
    private Boolean IsAdmin;
    
    public User(int id, String username, String password, Boolean isAdmin){
        this.ID = id;
        this.Username = username;
        this.Password = password;
        this.IsAdmin = isAdmin;
    }

    public int getID() {
        return ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public Boolean getIsAdmin() {
        return IsAdmin;
    }

    public void setIsAdmin(Boolean IsAdmin) {
        this.IsAdmin = IsAdmin;
    }

    @Override
    public String toString() {
        return Username;
    }
    
    
}
