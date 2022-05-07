package chainofresponsibility;
import database.Inventory;
import order.Order;
import order.GivenOrder;

import output.OutputStrategy;
import output.InvalidOutput;

public class EssentialHandler implements OrderHandler {
    private OrderHandler successor = null;
    private OutputStrategy strategy;

    @Override
    public void handleOrder(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        int essentialItemQuantity = 0;
        boolean areQuantitiesValid = true;
        // Check for invalid input
        for(GivenOrder temp : order.getOrders()){
           // System.out.println("ESS"+inventoryObj.getCategoryMap().get(temp.item));

            if(inventoryObj.getCategoryMap().get(temp.item).equals("Essential")){
                essentialItemQuantity = essentialItemQuantity + temp.quantity;
                if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                    areQuantitiesValid = false;
                    break;
                }
            }
          
            
        }
        // if the total essential qty greater than allowed qty
        if(!areQuantitiesValid){
            strategy = new InvalidOutput();
            strategy.displayOutput(order);
        }
        // cap is 3 for essentials
        else if(essentialItemQuantity > 3 ){
         //   System.out.println("ee:exc");
            strategy = new InvalidOutput();
            strategy.displayOutput(order,"Essential");
        }

        // Set the order to other
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
