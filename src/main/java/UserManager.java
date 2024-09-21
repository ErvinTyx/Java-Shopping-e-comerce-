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

    // list all user and its information
    public void listUsers() {
        for (UserBase user : users) {
            System.out.println(user.getUsername() + " (" + user.getRole() + ")");
        }
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

    public boolean isValidPassword(String password) {
        if (password.length() < 8) {
            System.out.println("Password Lenght too Short");
            return false; // Password too short
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecialChar = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecialChar = true;
            }
        }
        // prompt out message if password criteria are not met
        if (!hasUppercase) {
            System.out.println("Password must contain at least one uppercase letter");
        }

        if (!hasLowercase) {
            System.out.println("Password must contain at least one lowercase letter");
        }

        if (!hasDigit) {
            System.out.println("Password must contain at least one digit");
        }

        if (!hasSpecialChar) {
            System.out.println("Password must contain at least one special character");
        }

        // Password is valid only if all criteria are met
        return (hasUppercase && hasLowercase && hasDigit && hasSpecialChar);
    }
}
