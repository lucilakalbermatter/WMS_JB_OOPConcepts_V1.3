package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {

    //Properties
    private final int id;
    private final List<Item> stock;

    //Constructor
    public Warehouse(int warehouseId) {
        this.id = warehouseId;
        this.stock = new ArrayList<Item>();
    }

    //Methods
    public int occupancy (){
        return stock.size();
    }

    public void addItem (Item newItem){
        boolean add = stock.add(newItem);
    }

    public List<Item> search (String searchTerm) {
        for(Item item : stock) {
            if (item.contains(searchTerm)) {
                return item;
            }
        }
}




