package chainofresponsibility;
import order.Order;
import database.Inventory;
import order.GivenOrder;

import output.OutputStrategy;
import output.InvalidOutput;

public class LuxuryHandler implements OrderHandler {
    private OrderHandler successor = null;
    private OutputStrategy strategy;

    @Override
    public void handleOrder(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        int luxuryItemCount = 0;
        boolean areQuantitiesValid = true;
        for(GivenOrder temp : order.getOrders()){
            if(inventoryObj.getCategoryMap().get(temp.item).equals("Luxury")){
                luxuryItemCount = luxuryItemCount + temp.quantity;
                if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                    areQuantitiesValid = false;
                    break;
                }
            }
        }

        if(!areQuantitiesValid){
            strategy = new InvalidOutput();
            strategy.printOutput(order);
        }
        //cap is 4 for luxury
        else if(luxuryItemCount > 4 ){
            strategy = new InvalidOutput();
            strategy.printOutput(order,"Luxury");
        }
        else{
            if(successor != null){
                successor.handleOrder(order);
            }
        }
    }

    @Override
    public void setNext(OrderHandler next) {
        this.successor = next;
    }
}
