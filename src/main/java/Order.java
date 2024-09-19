/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
import java.util.*;

public class Order {
    private List<Item> items;
    private String status; // Placed, Shipped, Delivered

    public Order(List<Item> items) {
        this.items = new ArrayList<>(items);
        this.status = "Placed";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order status: " + status + ", items: " + items;
    }
}
