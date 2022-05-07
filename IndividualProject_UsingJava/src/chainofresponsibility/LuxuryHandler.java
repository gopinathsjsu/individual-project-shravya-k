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
           // System.out.println("Lux"+inventoryObj.getCategoryMap().get(temp.item));
            if(inventoryObj.getCategoryMap().get(temp.item).equals("Luxury")){
                luxuryItemCount = luxuryItemCount + temp.quantity;
                if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                    areQuantitiesValid = false;
                    break;
                }
            }
            else if(inventoryObj.getCategoryMap().get(temp.item).equals("Essential")){
             //   System.out.println("ess");
                int essentialItemQuantity  =   temp.quantity;
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

            }
            else if(inventoryObj.getCategoryMap().get(temp.item).equals("Misc")){
             //   System.out.println("misc");
                int miscItemCount =   temp.quantity;
                if(!areQuantitiesValid){
                    strategy = new InvalidOutput();
                    strategy.displayOutput(order);
                }
                //cap is 6 for Misc
                else if(miscItemCount > 6 ){
                    strategy = new InvalidOutput();
                    strategy.displayOutput(order,"Misc");
                 //   System.out.println("misc:exc");
                }
        
            }
        }
        
        if(!areQuantitiesValid){
            strategy = new InvalidOutput();
            strategy.displayOutput(order);
        }
        //cap is 4 for luxury
        else if(luxuryItemCount > 4 ){
           // System.out.println("l:exc");
            strategy = new InvalidOutput();
            strategy.displayOutput(order,"Luxury");
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
