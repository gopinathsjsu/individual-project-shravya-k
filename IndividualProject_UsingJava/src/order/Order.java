package order;

import java.util.List;

public class Order {

    List<GivenOrder> order;

    public Order(){};

    public Order(List<GivenOrder> order) {
        this.order = order;
    }

    public List<GivenOrder> getOrders() {
        return order;
    }

    public void setOrders(List<GivenOrder> order) {
        this.order = order;
    }
}
