package org.example.warehouse;


import java.util.HashMap;
import java.util.Map;

public class Category {

    private static final Map<String, Category> theCategories = new HashMap<>();


    private Category(String name) {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
    }


    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }
        return theCategories.computeIfAbsent(name, Category::new);


    }
        public String getName () {
            return name;
    }
    private final String name;
}











