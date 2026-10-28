/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author ire22
 */
public class ShoppingHistory {
    
    private int shoppingHistoryId;
    private int gymId;
    private String userName;
    private int totalPrice;
    private LocalDate date;
    private HashMap<Integer, Integer> products;

    public ShoppingHistory(int shoppingHistoryId, int gymId, String userName, int totalPrice, LocalDate date, HashMap<Integer, Integer> products) {
        this.shoppingHistoryId = shoppingHistoryId;
        this.gymId = gymId;
        this.userName = userName;
        this.totalPrice = totalPrice;
        this.date = date;
        this.products = products;
    }
    
    public ShoppingHistory() {
        this.shoppingHistoryId = 0;
        this.gymId = 0;
        this.userName = "";
        this.totalPrice = 0;
        this.date = LocalDate.now();
        this.products = new HashMap<>();
    }

    public int getShoppingHistoryId() {
        return shoppingHistoryId;
    }

    public int getGymId() {
        return gymId;
    }

    public String getUserName() {
        return userName;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public HashMap<Integer, Integer> getProducts() {
        return products;
    }

    public void setShoppingHistoryId(int shoppingHistoryId) {
        this.shoppingHistoryId = shoppingHistoryId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setProducts(HashMap<Integer, Integer> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "ShoppingHistory{" + "shoppingHistoryId=" + shoppingHistoryId + ", gymId=" + gymId + ", userName=" + userName +
                ", totalPrice=" + totalPrice + ", date=" + date + ", products=" + products + '}';
    }
}