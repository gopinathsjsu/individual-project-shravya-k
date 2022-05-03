package chainofresponsibility;
import order.Order;
public interface OrderHandler {
    public void handleOrder(Order order) throws Exception;
    public void setNext(OrderHandler next);
}

