package output;

import database.Inventory;
import order.Card;
import order.Order;
import order.GivenOrder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class InvalidOutput implements OutputStrategy {
    @Override
    // selected quantity exceed  available in inventory
    public void printOutput(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        StringBuilder sb = new StringBuilder();
        for(GivenOrder temp : order.getOrders()){
            if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                sb.append(temp.item);
                sb.append(",");
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileOutputName = "output_" + timestamp.getTime();
        String fileName = "data" + "/" + fileOutputName + ".txt";
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("Please correct quantities as they exceed quantity available in inventory");
        br.newLine();
        br.write(sb.toString());
        br.close();

    }

    // Print if all categories have more items
    @Override
    public void printOutput(Order order, String category) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append(category + " category has more items");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileOutputName = "output_" + timestamp.getTime();
        String fileName = "data" + "/" + fileOutputName + ".txt";
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write(sb.toString());
        br.newLine();
        br.write("Items ordered of " + category + " category exceeded the allowed order cap, please modify");
        br.newLine();
        StringBuilder outputItems = new StringBuilder();
        if(category.equals("Essential")){
            for(GivenOrder temp : order.getOrders()){
                if(inventoryObj.getCategoryMap().get(temp.item).equals("Essential")){
                    outputItems.append("Item: "+temp.item +" "+"Quantity: "+temp.quantity+ ", " + "");

                }
            }
        }
        else if(category.equals("Misc")){
            for(GivenOrder temp : order.getOrders()){
                if(inventoryObj.getCategoryMap().get(temp.item).equals("Misc")){
                    outputItems.append("Item: "+temp.item +" "+"Quantity: "+temp.quantity+ ", " + "");
                }
            }
        }
        else {
            for (GivenOrder temp : order.getOrders()) {
                if (inventoryObj.getCategoryMap().get(temp.item).equals("Luxury")) {
                    outputItems.append("Item: "+temp.item +" "+"Quantity: "+temp.quantity+ ", " + "");
                }
            }
        }
        br.write(outputItems.toString());
        br.close();

    }


}
