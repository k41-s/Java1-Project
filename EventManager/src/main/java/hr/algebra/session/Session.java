/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.session;

import hr.algebra.model.User;

/**
 *
 * @author kaish
 */
public class Session {
    
    private static User currentUser;
    
    public static User getCurrentUser(){
        return currentUser;
    }
    
    public static void setCurrentUser(User user){
        currentUser = user;
    }
    
    public static void clearSession(){
        currentUser = null;
    }
}
