/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */



import java.util.ArrayList;

public class UserManager {
    private ArrayList<UserBase> users;

    public UserManager() {
        users = new ArrayList<>();
    }

    public void addUser(UserBase user) {
        users.add(user);
    }

    public UserBase findUser(String username) {
        for (UserBase user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<UserBase> getUsers() {
        return users;
    }
}
