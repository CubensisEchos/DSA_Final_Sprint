package com.keyin.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for managing product operations
 * Handles CRUD and sorting for products
 */
@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepository;

    public Product addNewProduct(Product product)
    {
        return productRepository.save(product);
    }

    public List<Product> getAllProducts()
    {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id)
    {
        return productRepository.findById(id);
    }

    public Optional<Product> updateProduct(Long id, Product updatedProduct)
    {
        return productRepository.findById(id).map(existing->
        {
            existing.setName(updatedProduct.getName());
            existing.setPrice(updatedProduct.getPrice());
            existing.setStock(updatedProduct.getStock());
            return productRepository.save(existing);
        });
    }

    public boolean deleteProductById(Long id)
    {
        if (productRepository.existsById(id))
        {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Product> sortByPrice(List<Product> products)
    {
        for (int i = 0; i < products.size(); i++)
        {
            Product current = products.get(i);
            int j = i -1;

            while (j >= 0 && products.get(j).getPrice() < current.getPrice())
            {
                products.set(j + 1, products.get(j));
                j--;
            }
            products.set(j + 1, current);
        }
        return products;
    }

    public List<Product> sortByStock(List<Product> products)
    {
        for (int i =0; i < products.size(); i++)
        {
            Product current = products.get(i);
            int j = i -1;

            while (j >= 0 && products.get(j).getStock() > current.getStock())
            {
                products.set(j + 1, products.get(j));
                j--;
            }
            products.set(j + 1, current);
        }
        return products;
    }

    public List<Product> getSorted(String by)
    {
        List<Product> products = getAllProducts();

        if (by.equals("price"))
        {
            return sortByPrice(products);
        }
        else if (by.equals("stock"))
        {
            return sortByStock(products);
        }
        throw new IllegalArgumentException("Invalid sort type: " + by);
    }
}
