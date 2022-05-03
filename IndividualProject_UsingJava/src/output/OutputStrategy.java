package output;

import order.Order;

public interface OutputStrategy {
    void printOutput(Order order) throws Exception;
    void printOutput(Order order,String category) throws Exception;
}
