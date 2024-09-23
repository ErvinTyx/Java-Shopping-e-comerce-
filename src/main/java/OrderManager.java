import java.util.ArrayList;

public class OrderManager {
    private ArrayList<Order> orders;

    public OrderManager() {
        orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public ArrayList<Order> getAllOrders() {
        return orders;
    }

    public Order findOrder(String orderId) {
        for (Order order : orders) {
            if (order.getOrderId().equals(orderId)) {
                return order;
            }
        }
        return null;
    }
}
