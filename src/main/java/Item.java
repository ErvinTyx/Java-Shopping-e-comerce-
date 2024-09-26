public class Item {
    private static int counter = 1;
    private int id;
    private String name;
    private double price;
    private int quantity; // New quantity field

    public Item(String name, double price, int quantity) {
        this.id = counter;
        counter++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-8d|%-20s|%-15.2f|%13d|", id, name, price, quantity);
    }
}
