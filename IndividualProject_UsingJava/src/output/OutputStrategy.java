package output;

import order.Order;

public interface OutputStrategy {
    void displayOutput(Order order) throws Exception;
    void displayOutput(Order order,String category) throws Exception;
}
