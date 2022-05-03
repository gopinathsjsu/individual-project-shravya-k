package order;

import java.util.HashMap;

public class Item {

    private String category;
    private String item;
    private double availableQuantity;
    private double price;

    public void setQuantityInInventory(double availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Item( ) {
    }

    public Item(String category, String item, double price, double availableQuantity) {

        this.category = category;
        this.item = item;
        this.availableQuantity = availableQuantity;
        this.price = price;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public double getAvailableQuantity() {
        return availableQuantity;
    }

    public void setQuantityInInventory(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
