/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ervin
 */
public class Review {
    private String username;
    private String reviewText;
    private int rating;

    public Review(String username, String reviewText, int rating) {
        this.username = username;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return username + " (" + rating + "/5): " + reviewText;
    }
}
