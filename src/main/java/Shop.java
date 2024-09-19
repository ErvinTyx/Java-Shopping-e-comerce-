/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
import java.util.ArrayList;

public class Shop {
    private ArrayList<Item> items;
    private ArrayList<Category> categories;
    private UserManager userManager;

    public Shop() {
        items = new ArrayList<>();
        categories = new ArrayList<>();
        userManager = new UserManager();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item getItemById(int id) {
        for (Item item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void removeItem(int id) {
        items.removeIf(item -> item.getId() == id);
    }

    public void listItems() {
        System.out.println("Available Items:");
        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void listUsers() {
        System.out.println("Registered Users:");
        for (UserBase user : userManager.getUsers()) {
            System.out.println(user.getUsername() + " (" + user.getRole() + ")");
        }
    }

    public UserManager getUserManager() {
        return userManager;
    }
}
