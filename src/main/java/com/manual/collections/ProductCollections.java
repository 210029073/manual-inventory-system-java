package com.manual.collections;
import com.manual.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductCollections {
    private List<Product> products;

    public ProductCollections(List<Product> productList) {
        this.products = productList;
    }

    public ProductCollections() {
        this(new ArrayList<>());
    }
}
