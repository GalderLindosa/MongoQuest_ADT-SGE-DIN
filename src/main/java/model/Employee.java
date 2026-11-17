/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author ire22
 */
public class Employee extends Person implements WithCart{
    
    private List<Integer> classes;
    private List<Integer> shoppingHistory;
    private String discountCode;
    private int gymId;
    private HashMap<Product, Integer> cart;

      public Employee(List<Integer> classes, List<Integer> shoppingHistory,
            String discountCode, int gymId, String userName, String password, String name,
            String lastName, String dni, float balance,LocalDate startDate) {
        super(userName, password, name, lastName, dni, balance,startDate);
        this.classes = classes;
        this.shoppingHistory = shoppingHistory;
        this.discountCode = discountCode;
        this.gymId = gymId;
        this.cart = new HashMap<>();
    }
    
    
        public Employee(String discountCode, int gymId, String userName, String password, String name, String lastName, String dni, float balance, LocalDate startDate) {
        super(userName, password, name, lastName, dni, balance,startDate);
        this.discountCode = discountCode;
        this.gymId = gymId;
    }

    public Employee() {
        super();
        this.classes = new ArrayList<>();
        this.shoppingHistory = new ArrayList<>();
        this.discountCode = "";
        this.gymId = 0;
        this.cart = new HashMap<>();
    }

    public List<Integer> getClasses() {
        return classes;
    }

    public List<Integer> getShoppingHistory() {
        return shoppingHistory;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getGymId() {
        return gymId;
    }
    
    @Override
    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public void setClasses(List<Integer> classes) {
        this.classes = classes;
    }

    public void setShoppingHistory(List<Integer> shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }
    
    @Override
    public void setCart(Product p, Integer cantidad) {
        this.cart.put(p, cantidad);
    }

    @Override
    public String toString() {
    //Cambio el toString para que cuando lo muetsr en la lista solo enseñe el nombre 
        return getUserName();
    }

    
}
