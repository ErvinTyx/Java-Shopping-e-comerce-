/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
public class Shipping {
    private String address;
    private String method;

    public Shipping(String address, String method) {
        this.address = address;
        this.method = method;
    }

    public void shipOrder() {
        System.out.println("Shipping to " + address + " via " + method);
    }
}

