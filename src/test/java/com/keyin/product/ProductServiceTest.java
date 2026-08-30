package com.keyin.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest
{
    private ProductService productService;
    private List<Product> products;

    @BeforeEach
    public void setup()
    {
        productService = new ProductService();

        products = new ArrayList<>();

        products.add(new Product("Product 1", 20.00, 50));
        products.add(new Product("Product 2", 10.00, 30));
        products.add(new Product("Product 3", 30.00, 10));
    }

    @Test
    public void sortByPrice_ReturnsProductsByPriceInDescendingOrder()
    {
        List<Product> result = productService.sortByPrice(products);

        assertEquals(30.00, result.get(0).getPrice());
        assertEquals(20.00, result.get(1).getPrice());
        assertEquals(10.00, result.get(2).getPrice());
    }

    @Test
    public void sortByStock_ReturnsProductsByStockInAscendingOrder()
    {
        List<Product> result = productService.sortByStock(products);

        assertEquals(10, result.get(0).getStock());
        assertEquals(30, result.get(1).getStock());
        assertEquals(50, result.get(2).getStock());
    }
}
