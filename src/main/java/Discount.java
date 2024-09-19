/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */


public class Discount {
    private double percentage;
    private String applicableItem;

    public Discount(double percentage, String applicableItem) {
        this.percentage = percentage;
        this.applicableItem = applicableItem;
    }

    public double applyDiscount(double price) {
        return price - (price * percentage / 100);
    }

    public boolean isApplicable(Item item) {
        return item.getName().equalsIgnoreCase(applicableItem);
    }
}

