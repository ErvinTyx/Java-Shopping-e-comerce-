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
    private UserManager userManager;
    private boolean shopopen;

    public Shop(UserManager userManager) {
        items = new ArrayList<>();
        shopopen = true;
        this.userManager = userManager;
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

    public void openCloseShop() {
        shopopen = !shopopen;
        if (shopopen) {
            System.out.println("Shop is now open.");
        } else {
            System.out.println("Shop is now closed. Please log out to terminate program.");
        }
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public void setUserManager(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean isShopopen() {
        return shopopen;
    }

    public void setShopopen(boolean shopopen) {
        this.shopopen = shopopen;
    }

}
