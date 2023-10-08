package org.example.warehouse;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Warehouse {
private  String name;
private List<ProductRecord> product = new ArrayList<>();
private final List<ProductRecord> changed = new ArrayList<>();

   private Warehouse (String name){
       this.name = name;
       this.product = new ArrayList<>();

   }
private Warehouse(){

}
public static Warehouse getInstance(String name){
       return new Warehouse(name);
}
public static Warehouse getInstance(){
       return new Warehouse();
}

    public ProductRecord addProduct(UUID uuid, String produce, Category category, BigDecimal price) {
        if (produce == null || produce.isEmpty()) {
            throw new IllegalArgumentException("Product name can't be null or empty.");
        }
        if (price == null) {
            price = BigDecimal.valueOf(0);
        }
        if (uuid == null) {
            uuid = UUID.randomUUID();
        }
        if (category == null) {
            throw new IllegalArgumentException("Category can't be null.");

        }
        UUID duoUuid = uuid;
        if (product.stream().anyMatch(p-> p.uuid().equals(duoUuid))){
            throw new IllegalArgumentException("Product with that id already exists, use updateProduct for updates.");
        }

        ProductRecord produkt = new ProductRecord(uuid,produce,category,price);
        product.add(produkt);

        return produkt;
    }

    public List<ProductRecord> getProducts() {
        return List.copyOf(this.product);
    }
    public  Optional <ProductRecord> getProductById(UUID uuid) {
        return product.stream()
                .filter(product -> product.uuid().equals(uuid))
                .findFirst();
    }

    public void updateProductPrice(UUID uuid, BigDecimal price) {
   Optional <ProductRecord> changedP = getProductById(uuid);
   changedP.ifPresent(product -> {
       product.setPrice(price);
       changed.add(product);
   });
        if (changed.isEmpty()) {
            throw new IllegalArgumentException("Product with that id doesn't exist.");
        }

    }

    public List<ProductRecord> getChangedProducts() {
        return List.copyOf(changed);
    }

    public Map<Category,List<ProductRecord>> getProductsGroupedByCategories() {
        return product.stream()
                .collect(Collectors.groupingBy(ProductRecord::category));
    }

    public List<ProductRecord>getProductsBy(Category meat) {
        return product.stream()
                .filter(product -> product.category().equals(meat))
                .collect(Collectors.toList());

    }
    public boolean isEmpty() {
    return product.isEmpty();
    }
}
