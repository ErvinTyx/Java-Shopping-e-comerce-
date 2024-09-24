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
    
    public void viewOrderHistory(String username){
        boolean orderFound = false;

        for(Order order: orders){
            if(order.getCustomerName().equals(username)){
                order.displayOrder();
                orderFound = true;
            }
        }
        if(!orderFound){
            System.out.println("No orders found.");
        }
    }

    public Order findOrderCus(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {

                return order;
            }
        }

        return null;
    }
}
