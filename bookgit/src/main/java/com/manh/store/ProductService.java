package com.manh.store;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static ProductService instance;
    private List<Product> products = new ArrayList<>();

    // Private constructor to ensure singleton
    private ProductService() {
        // Initialize with some dummy products
        products.add(new Product("book001", "Java Programming", 49.99, "Information Technology", "Comprehensive guide to Java programming."));
        products.add(new Product("book002", "Python for Beginners", 39.99, "Information Technology", "Introductory text for Python newcomers."));
        products.add(new Product("book003", "HTML & CSS Mastery", 29.99, "Information Technology", "Essential skills for web designers."));
        products.add(new Product("book004", "Data Structures and Algorithms", 59.99, "Computer Science", "Detailed introduction to data structures and algorithms."));
        products.add(new Product("book005", "Machine Learning Basics", 45.99, "Artificial Intelligence", "Beginner's guide to the concepts of machine learning."));
        products.add(new Product("book006", "Effective Project Management", 34.99, "Business Management", "Strategies for effective management of software projects."));
        products.add(new Product("book007", "Introduction to Cryptography", 40.00, "Computer Security", "Fundamentals of cryptographic systems and their applications."));
        products.add(new Product("book008", "The Art of Software Testing", 47.50, "Software Engineering", "Comprehensive overview on techniques in software testing."));
        products.add(new Product("book009", "Advanced Web Development", 38.95, "Web Development", "Explore advanced techniques for modern web development."));
    }

    // Public method to get the single instance of the class
    public static synchronized ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    // Method to get all products
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);  // Return a copy of the list to prevent modification
    }

    // Method to get a product by ID
    public Product getProductById(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }

    // Method to add a product
    public void addProduct(Product product) {
        products.add(product);
    }

    // Method to update a product
    public void updateProduct(Product product) {
        int index = -1;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(product.getId())) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            products.set(index, product);
        }
    }

    // Method to delete a product by ID
    public void deleteProduct(String productId) {
        products.removeIf(p -> p.getId().equals(productId));
    }
}
