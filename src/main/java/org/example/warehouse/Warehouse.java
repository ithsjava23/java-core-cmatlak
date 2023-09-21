package org.example.warehouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Warehouse {
    Warehouse warehouse;
    private String name;
    private List<ProductRecord> products = new ArrayList<>();
    private static final Map<String, Warehouse> listOfWarehouses = new HashMap<>();
    private static final List<ProductRecord> changedProducts = new ArrayList<>();

    private Warehouse(String name) {
        this.name = name;
        this.products = new ArrayList<>();
    }

    private Warehouse() {

    }
public static Warehouse getInstance(String name){
        Warehouse existingWarehouse = listOfWarehouses.get(name);
    if (existingWarehouse == null) {
        return existingWarehouse;
    }
    return new Warehouse(name);

    }
    public static Warehouse getInstance(){
        return new Warehouse();
}

}