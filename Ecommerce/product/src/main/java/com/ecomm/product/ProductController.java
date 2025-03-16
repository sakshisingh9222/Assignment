package com.ecomm.product;
import java.util.ArrayList;
// import java.util.stream.Collector;
// import java.util.stream.Collectors;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private List<Product> products = new ArrayList<>(List.of(
		    new Product(1, "Laptop", 75000),
		    new Product(2, "Smartphone", 45000),
		    new Product(3, "Headphones", 2000),
		    new Product(4, "Smartwatch", 10000),
		    new Product(5, "Tablet", 30000),
		    new Product(6, "Gaming Console", 40000),
		    new Product(7, "Wireless Mouse", 1500),
		    new Product(8, "Mechanical Keyboard", 3500),
		    new Product(9, "Monitor", 25000),
		    new Product(10, "External Hard Drive", 5000),
		    new Product(11, "Power Bank", 3000),
		    new Product(12, "Bluetooth Speaker", 7000),
		    new Product(13, "Graphics Card", 60000),
		    new Product(14, "Processor", 30000),
		    new Product(15, "Motherboard", 18000),
		    new Product(16, "RAM 16GB", 7000),
		    new Product(17, "SSD 1TB", 12000),
		    new Product(18, "Cooling Pad", 2500),
		    new Product(20, "USB-C Docking Station", 12000)
		));

    // GET all products
    @GetMapping
    public List<Product> getAllProducts() {
        return products;
    }

    // GET product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return products.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // POST: Add a new product
    @PostMapping
    public String addProduct(@RequestBody Product product) {
        products.add(product);
        return "Product added!";
    }

    // PUT: Update a product
    @PutMapping("/{id}")
    public String updateProduct(@PathVariable int id, @RequestBody Product updatedProduct) {
        for (Product p : products) {
            if (p.getId() == id) {
                p.setName(updatedProduct.getName());
                p.setPrice(updatedProduct.getPrice());
                return "Product updated!";
            }
        }
        return "Product not found!";
    }

    // DELETE: Remove a product
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        products.removeIf(p -> p.getId() == id);
        return "Product deleted!";
    }
}



