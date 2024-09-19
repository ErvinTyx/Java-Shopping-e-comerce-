/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */


public class Item {
    private static int idCounter = 1;
    private int id;
    private String name;
    private double price;

    public Item(String name, double price) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Price: $" + price;
    }
}

