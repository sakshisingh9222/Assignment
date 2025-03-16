package com.ecomm.customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

// @RequestMapping("/api")
// @RestController
// public class CustomerController 
// {
//     RestTemplate rest;
// @GetMapping("/customers")
//     public List<String>getProduct()
//     {
//         String[]names={"A,B,C"};
//         List<String>nameList=Arrays.stream(names)
//         .collect(Collectors.toList());
//         return nameList;
//     }
//     @SuppressWarnings("unchecked")
//     @GetMapping("/products")
//     public List<String>getUserData() 
//     {
//         rest = new RestTemplate();
//         return rest.getForObject("http://localhost:6666/api/products",
//         List.class);
//     }

// }


@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String PRODUCT_SERVICE_URL = "http://localhost:8081/products";

    private List<Customer> customers = new ArrayList<>(List.of(
    	    new Customer(1, "Aarav Sharma", "aarav.sharma@example.com"),
    	    new Customer(2, "Ishaan Verma", "ishaan.verma@example.com"),
    	    new Customer(3, "Vihaan Patel", "vihaan.patel@example.com"),
    	    new Customer(4, "Kabir Reddy", "kabir.reddy@example.com"),
    	    new Customer(5, "Aditi Mehta", "aditi.mehta@example.com"),
    	    new Customer(6, "Anaya Gupta", "anaya.gupta@example.com"),
    	    new Customer(7, "Aryan Nair", "aryan.nair@example.com"),
    	    new Customer(8, "Riya Iyer", "riya.iyer@example.com"),
    	    new Customer(9, "Vivaan Kulkarni", "vivaan.kulkarni@example.com"),
    	    new Customer(10, "Tanvi Joshi", "tanvi.joshi@example.com"),
    	    new Customer(11, "Shaurya Choudhury", "shaurya.choudhury@example.com"),
    	    new Customer(12, "Mihika Malhotra", "mihika.malhotra@example.com"),
    	    new Customer(13, "Reyansh Das", "reyansh.das@example.com"),
    	    new Customer(14, "Avni Bose", "avni.bose@example.com"),
    	    new Customer(15, "Hrithik Roy", "hrithik.roy@example.com"),
    	    new Customer(16, "Saanvi Menon", "saanvi.menon@example.com"),
    	    new Customer(17, "Devansh Ghosh", "devansh.ghosh@example.com"),
    	    new Customer(18, "Ishika Sengupta", "ishika.sengupta@example.com"),
    	    new Customer(19, "Rudra Bhatt", "rudra.bhatt@example.com"),
    	    new Customer(20, "Anushka Chakraborty", "anushka.chakraborty@example.com"),
    	    new Customer(21, "Nakul Joshi", "nakul.joshi@example.com")
    	));


    // ✅ GET all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customers;
    }

    // ✅ GET customer by ID
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // ✅ POST: Add a new customer
    @PostMapping
    public String addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        return "Customer added!";
    }

    // ✅ PUT: Update an existing customer
    @PutMapping("/{id}")
    public String updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        Optional<Customer> existingCustomer = customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst();

        if (existingCustomer.isPresent()) {
            Customer c = existingCustomer.get();
            c.setName(updatedCustomer.getName());
            c.setEmail(updatedCustomer.getEmail());
            return "Customer updated!";
        }
        return "Customer not found!";
    }

    // ✅ DELETE: Remove a customer
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable int id) {
        boolean removed = customers.removeIf(c -> c.getId() == id);
        return removed ? "Customer deleted!" : "Customer not found!";
    }

    // ✅ Fetch products from product-service
    @GetMapping("/products")
    public Object getAllProducts() {
        return restTemplate.getForObject(PRODUCT_SERVICE_URL, Object.class);
    }
}


