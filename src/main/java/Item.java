public class Item {

    private static int idCounter = 1;
    private int id;
    private String name;
    private double price;
    private int quantity; // New quantity field

    public Item(String name, double price, int quantity) {
        this.id = idCounter++;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Item ID: " + id + ", Name: " + name + ", Price: $" + price + ", Quantity: " + quantity;
    }
}
