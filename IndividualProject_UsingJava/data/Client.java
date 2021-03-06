import chainofresponsibility.EssentialHandler;
import chainofresponsibility.LuxuryHandler;
import chainofresponsibility.MiscHandler;
import chainofresponsibility.OrderHandler;
import database.Inventory;
import order.Card;
import order.Item;
import order.Order;
import order.GivenOrder;

import java.io.*;
import java.util.*;

public class Billing {

    // Starting the code from here
    public static void main(String [] args) throws Exception {
       Billing clientObject = new Client();

        // populating the database (cards and items)
        clientObject.populateInventory();
        clientObject.populateCards();

        // get order.Order
        Scanner sc = new Scanner(System.in); // creating object of Scanner class
        areQuantitiesValid("Enter Order plus comma separated file i/p");

        String ch = "";
        while(true){
            System.out.print("Make your choice: ");
            ch = sc.nextLine(); // reading user's choice

//            areQuantitiesValid(ch);
            // If the user enters the order
            if(ch.startsWith("Order")){

                // reading the file
                String filePath = ch.split(",")[1];
                File file = new File(filePath);
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;
                line = br.readLine();
                boolean first = true;
                Order obj = new Order();

                while(line != null)
                {
                    String [] lines = line.split(",");
                    if (first){
                        first = false;
                        line = br.readLine();
                        continue;
                    }

                    // Removing the extra ""
                    String Item = lines[0].replace("\"","");
                    int quantity = Integer.valueOf(lines[1].replace("\"",""));
                    String cardNumber = lines[2].replace("\"","");

                    // Set orders in the order object
                    GivenOrder pOrderObj = new GivenOrder(Item,quantity,cardNumber);
                    List<GivenOrder> tempList = obj.getOrders();
                    if(tempList == null)tempList = new ArrayList<>();
                    tempList.add(pOrderObj);
                    obj.setOrders(tempList);
                    line = br.readLine();
                }

                // Setting the chain of responsibilities
                OrderHandler h1 = clientObject.setChainOfResponsibilites();

                // Handling the chain of responsibilities
                h1.handleOrder(obj);
            }
            else{
                break;
            }
        }

        areQuantitiesValid("Thank you for using Spartan database.Inventory Management System. Please do visit again");

    }

    public void populateInventory() throws IOException {
        // reading the dataset csv file
        String filePath = "data/Dataset.csv";
        Inventory inventoryObj = Inventory.getInstance();
        HashSet<Item> selectedItems = inventoryObj.getItems();
        HashMap<String,String> selectedCategoryMap = inventoryObj.getCategoryMap();
        HashMap<String,Integer> selectedCategoryCountMap = inventoryObj.getCategorCountMap();
        HashMap<String,Integer> selectedIndividualItemMap = inventoryObj.getIndividualItemMap();
        HashMap<String,Float> selectedPricePerQuantityMap = inventoryObj.getPricePerCategoryMap();
        if(selectedCategoryMap == null)selectedCategoryMap = new HashMap<>();
        if(selectedItems == null)selectedItems = new HashSet<>();
        if(selectedCategoryCountMap == null)selectedCategoryCountMap = new HashMap<>();
        if(selectedIndividualItemMap == null)selectedIndividualItemMap = new HashMap<>();
        if(selectedPricePerQuantityMap == null)selectedPricePerQuantityMap = new HashMap<>();

        //read the file, line by line from txt
        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        while(line != null)
        {
            String [] lines = line.split(",");
            if (lines[0].equals("Category")){
                line = br.readLine();
                continue;
            }
            Item itemObj = new Item(lines[0],lines[1],Double.valueOf(lines[2]),Double.valueOf(lines[3]));
            presentCategoryMap.put(lines[1],lines[0]);
            selectedItems.add(itemObj);
            selectedPricePerQuantityMap.put(lines[1],Float.valueOf(lines[3]));
            selectedCategoryCountMap.put(lines[0],selectedCategoryCountMap.getOrDefault(lines[0],0)+Integer.valueOf(lines[2]));
            selectedIndividualItemMap.put(lines[1],selectedCategoryCountMap.getOrDefault(lines[1],0)+Integer.valueOf(lines[2]));
            line = br.readLine();
        }
        inventoryObj.setItems(selectedItems);
        inventoryObj.setCategoryMap(presentCategoryMap);
        inventoryObj.setCategorCountMap(selectedCategoryCountMap);
        inventoryObj.setIndividualCategoryCountMap(selectedIndividualItemMap);
        inventoryObj.setPricePerCategoryMap(selectedPricePerQuantityMap);
        fr.close();
    }

    public void populateCards() throws IOException {
        // reading the dataset csv file
        String filePath = "data/Cards.csv";
        Inventory inventoryObj = Inventory.getInstance();
        HashSet<Card> presentCards = inventoryObj.getCards();
        if(presentCards == null)presentCards = new HashSet<>();

        File file = new File(filePath);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        line = br.readLine();
        while(line != null)
        {
            String [] lines = line.split(",");
            if (lines[0].equals("CardNumber")){
                line = br.readLine();
                continue;
            }
            Card cardObj = new Card((lines[0]));
            presentCards.add(cardObj);
            System.out.println(presentCards);
            line = br.readLine();
        }
        inventoryObj.setCards(presentCards);
        fr.close();
    }

    public OrderHandler setChainOfResponsibilites() {
        OrderHandler h1  = new LuxuryHandler();
        OrderHandler h2  = new EssentialHandler();
        OrderHandler h3  = new MiscHandler();

        h1.setNext(h2);
        h2.setNext(h3);

        return h1;
    }
}

