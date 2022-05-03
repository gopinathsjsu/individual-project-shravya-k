package output;

import database.Inventory;
import order.Card;
import order.Order;
import order.GivenOrder;
import output.OutputStrategy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Timestamp;

public class ValidOutput implements OutputStrategy {

    @Override
    public void printOutput(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        float totalAmountPaid = 0;
        // calculate the total amount paid
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String fileOutputName = "output_" + timestamp.getTime();
        String fileName = "data" + "/" + fileOutputName + ".csv";
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("Item  ");
        br.write("Quantity  ");
        br.write("Price  ");
        br.newLine();

        for(GivenOrder temp : order.getOrders()){
           
        
            String item = temp.item;
            
            br.write(item+"  ");
            Integer itemQuantity = temp.quantity;
            br.write(String.valueOf(itemQuantity+"  "));
            Float pricePerQuantity = inventoryObj.getPricePerCategoryMap().get(item);
            br.write(String.valueOf(pricePerQuantity+"  "));
            totalAmountPaid += (pricePerQuantity) * (itemQuantity);
            br.newLine();

        }
        br.newLine();
        br.newLine();
       
        br.write("Total Price");
        br.newLine();
        br.write(String.valueOf(totalAmountPaid));
        br.close();

        int i=1;
        for(Card card  : inventoryObj.cards){
            System.out.println("Card " + i + "=" + card.getCardNumber());
            i++;
        }
    }

    @Override
    public void printOutput(Order order, String category) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();


        int i=1;
        for(Card card  : inventoryObj.cards){
            System.out.println("Card " + i + "=" + card.getCardNumber());
            i++;
        }
    }

}
