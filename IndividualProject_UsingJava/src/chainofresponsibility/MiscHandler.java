package chainofresponsibility;
import java.util.HashMap;
import java.util.HashSet;
import database.Inventory;
import order.Order;
import order.GivenOrder;
import output.OutputStrategy;
import output.InvalidOutput;
import output.ValidOutput;
import order.Card;

public class MiscHandler implements OrderHandler {
    private OrderHandler successor = null;
    private OutputStrategy strategy;
    @Override
    public void handleOrder(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        int miscItemCount = 0;
        boolean areQuantitiesValid = true;
        for(GivenOrder temp : order.getOrders()){
            System.out.println("Misc"+inventoryObj.getCategoryMap().get(temp.item));
            if(inventoryObj.getCategoryMap().get(temp.item).equals("Misc")){
                miscItemCount = temp.quantity + miscItemCount;
                if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                    areQuantitiesValid = false;
                    break;
                }
            }
        }
        if(!areQuantitiesValid){
            strategy = new InvalidOutput();
            strategy.displayOutput(order);
        }
        //cap is 6 for Misc
        else if(miscItemCount > 6 ){
            strategy = new InvalidOutput();
            strategy.displayOutput(order,"Misc");
            System.out.println("misc:exc");
        }

        else{
            strategy = new ValidOutput();
            HashMap<String,Integer>  categoryCountMap = inventoryObj.getCategorCountMap();
            HashMap<String,Integer>  individualCategoryMap = inventoryObj.getIndividualItemMap();
            HashSet<Card> presentCards = inventoryObj.getCards();

            // updating the inventory
            for(GivenOrder temp : order.getOrders()){
                inventoryObj.getCategoryMap().get(temp.item);
                String itemCategory = inventoryObj.categoryMap.get(temp.item);
                String item = temp.item;
                categoryCountMap.put(itemCategory, categoryCountMap.get(itemCategory) - temp.quantity);
                individualCategoryMap.put(item,individualCategoryMap.get(item) - temp.quantity);
            }
            // If card does not exist, add it to the database
            for(GivenOrder temp : order.getOrders()){
                if(!presentCards.contains(new Card(temp.cardNumber))){
                    presentCards.add(new Card(temp.cardNumber));
                }
            }
            inventoryObj.setIndividualCategoryCountMap(individualCategoryMap);
            inventoryObj.setCategoryCountMap(categoryCountMap);
            inventoryObj.setCards(presentCards);

            // print the output
            strategy.displayOutput(order);

        }
    }

    @Override
    public void setNext(OrderHandler next) {
        this.successor = next;
    }
}
