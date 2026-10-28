/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ire22
 */
public class Product {
    
    private int productId;
    private float price;
    private int stock;

    public Product(int productId, float price, int stock) {
        this.productId = productId;
        this.price = price;
        this.stock = stock;
    }
    
    public Product() {
        this.productId = 0;
        this.price = 0;
        this.stock = 0;
    }

    public int getProductId() {
        return productId;
    }

    public float getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", price=" + price + ", stock=" + stock + '}';
    }
}
