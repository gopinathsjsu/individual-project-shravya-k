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
    public static boolean create_file = false;
    public static boolean check_ess = false;
    public static boolean check_lux = false;
    public static boolean check_misc = false;
    public static String fileName = "";
    @Override
    // selected quantity exceed  available in inventory
    
    public void displayOutput(Order order) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        StringBuilder strb = new StringBuilder();
        for(GivenOrder temp : order.getOrders()){
            if(temp.quantity > inventoryObj.getIndividualItemMap().get(temp.getItem())){
                strb.append(temp.item);
                strb.append(",");
            }
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileOutputName = "output_" + timestamp.getTime();
        String fileName = "data" + "/" + fileOutputName + ".txt";
        BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
        br.write("Please correct quantities as they exceed quantity available in inventory");
        br.newLine();
        br.write(strb.toString());
        br.close();

    }

    // Print if all categories have more items
    @Override
    public void displayOutput(Order order, String category) throws Exception {
        Inventory inventoryObj = Inventory.getInstance();
        StringBuilder strb = new StringBuilder();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if(! create_file){
            String fileOutputName = "output_" + timestamp.getTime();
            fileName = "data" + "/" + fileOutputName + ".txt";
            create_file=true;
            BufferedWriter br = new BufferedWriter(new FileWriter(fileName));
            br.write(strb.toString());
            br.newLine();
            br.write("Items ordered of " + category + " category exceeded the allowed order cap, please modify");
            br.newLine();

        
        
     //   System.out.println(category);
        
        StringBuilder outputItemsList = new StringBuilder();
        if(category.equals("Essentials")){
            for(GivenOrder temp : order.getOrders()){
                if(inventoryObj.getCategoryMap().get(temp.item).equals("Essentials")&& temp.quantity>3){
                    outputItemsList.append("Item: "+temp.item +" "+"of category essentail and Quantity: "+temp.quantity+ ", " + "");

                }
            }
            check_ess=true;
        }
        if(category.equals("Misc")){
            for(GivenOrder temp : order.getOrders()){
                if(inventoryObj.getCategoryMap().get(temp.item).equals("Misc")&& temp.quantity>6){
                    outputItemsList.append("Item: "+temp.item +" "+" of category Misc and Quantity: "+temp.quantity+ ", " + "");
                }
            }
            check_misc = true;
        }
        if(category.equals("Luxury")) {
            for (GivenOrder temp : order.getOrders()) {
                if (inventoryObj.getCategoryMap().get(temp.item).equals("Luxury") && temp.quantity>4) {
                    outputItemsList.append("Item: "+temp.item +" "+"of category Luxury and Quantity: "+temp.quantity+ ", " + "");
                }
            }
            check_lux=true;
        }
        br.write(outputItemsList.toString());
        br.close();

    }

     else{
      //  System.out.println("ess"+check_ess+"lux"+check_lux+"c"+check_misc);
        FileWriter myWriter = new FileWriter(fileName, true);
        myWriter.write("\n");
        myWriter.write("\n");
        myWriter.write(strb.toString());
        myWriter.write("\n");
      

    
    
  //  System.out.println(category);
    
    StringBuilder outputItemsList = new StringBuilder();
    if(category.equals("Essentials") && (! check_ess)){
        myWriter.write("Items ordered of " + category + " category exceeded the allowed order cap, please modify");
        myWriter.write("\n");
        for(GivenOrder temp : order.getOrders()){
            if(inventoryObj.getCategoryMap().get(temp.item).equals("Essentials")&& temp.quantity>3){
                outputItemsList.append("Item: "+temp.item +" "+"of category essentail and Quantity: "+temp.quantity+ ", " + "");

            }
        }
    }
    else if(category.equals("Misc") && (! check_misc)){
        myWriter.write("Items ordered of " + category + " category exceeded the allowed order cap, please modify");
        myWriter.write("\n");
        for(GivenOrder temp : order.getOrders()){
            if(inventoryObj.getCategoryMap().get(temp.item).equals("Misc")&& temp.quantity>6){
                outputItemsList.append("Item: "+temp.item +" "+" of category Misc and Quantity: "+temp.quantity+ ", " + "");
            }
        }
    }
    else if(category.equals("Luxury") && (! check_lux)) {
        myWriter.write("Items ordered of " + category + " category exceeded the allowed order cap, please modify");
        myWriter.write("\n");
        for (GivenOrder temp : order.getOrders()) {
            if (inventoryObj.getCategoryMap().get(temp.item).equals("Luxury") && temp.quantity>4) {
                outputItemsList.append("Item: "+temp.item +" "+"of category Luxury and Quantity: "+temp.quantity+ ", " + "");
            }
        }
    }
    myWriter.write(outputItemsList.toString());
    myWriter.close();



    } 
}

}
