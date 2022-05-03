package database;

import java.util.HashMap;
import java.util.HashSet;
import order.Item;
import order.Card;


public class Inventory {
    public HashSet<Item> items; // stores the items in the HashSet

    public HashSet<Card> cards; // stores the payment cards

    public HashMap<String,String> categoryMap; // stores the category of a particular item

    public HashMap<String,Float> pricePerCategoryMap; // stores price per category map

    public HashMap<String,Integer> categoryCountMap; // stores the count of category

    public HashMap<String,Integer> individualItemMap; //  stores the count of each item of category

    public static Inventory instance ;


    public static Inventory getInstance( ) {

        if( instance == null ) {

            instance = new Inventory();

        }

        return instance;

    }

    public HashSet<Item> getItems() {
        return items;
    }

    public void setItems(HashSet<Item> items) {
        this.items = items;
    }

    public HashSet<Card> getCards() {
        return cards;
    }

    public void setCards(HashSet<Card> cards) {
        this.cards = cards;
    }

    public static void setInstance(Inventory instance) {
        Inventory.instance = instance;
    }

    public HashMap<String, String> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(HashMap<String, String> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public HashMap<String, Integer> getCategorCountMap() {
        return categoryCountMap;
    }

    public void setCategorCountMap(HashMap<String, Integer> categorCountMap) {
        this.categoryCountMap = categorCountMap;
    }

    public HashMap<String, Integer> getCategoryCountMap() {
        return categoryCountMap;
    }

    public void setCategoryCountMap(HashMap<String, Integer> categoryCountMap) {
        this.categoryCountMap = categoryCountMap;
    }

    public HashMap<String, Integer> getIndividualItemMap() {
        return individualItemMap;
    }

    public void setIndividualCategoryCountMap(HashMap<String, Integer> individualItemMap) {
        this.individualItemMap = individualItemMap;
    }

    public HashMap<String, Float> getPricePerCategoryMap() {
        return pricePerCategoryMap;
    }

    public void setPricePerCategoryMap(HashMap<String, Float> pricePerCategoryMap) {
        this.pricePerCategoryMap = pricePerCategoryMap;
    }

    public void setIndividualItemMap(HashMap<String, Integer> individualItemMap) {
        this.individualItemMap = individualItemMap;
    }
}

