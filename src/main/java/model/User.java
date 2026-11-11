/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Usuario
 */
public class User extends Person implements WithCart{
    
    private ArrayList<Training> trainings;
    private ArrayList<Integer> classes;
    private ArrayList<Integer> shoppingHistory;
    private HashMap<Product, Integer> cart;


    public User(ArrayList<Training> trainings, ArrayList<Integer> classes, ArrayList<Integer> shoppingHistory,
                String userName, String password, String name, String lastName, String dni, float balance,LocalDate startDate) {
        super(userName, password, name, lastName, dni, balance, startDate);
        this.trainings = trainings;
        this.classes = classes;
        this.shoppingHistory = shoppingHistory;
        this.cart = new HashMap<>();
    }
    public User(String userName, String password, String name, String lastName, String dni, float balance,LocalDate startDate) {
        super(userName, password, name, lastName, dni, balance, startDate);
        
    }
    public User() {
        super();
        this.trainings = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.shoppingHistory = new ArrayList<>();
        this.cart = new HashMap<>();
    }
    public void setTrainings(ArrayList<Training> trainings) {
        this.trainings = trainings;
    }

    public void setClasses(ArrayList<Integer> classes) {
        this.classes = classes;
    }

    public void setShoppingHistory(ArrayList<Integer> shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }
    
    @Override
    public void setCart(Product p, Integer cantidad) {
        this.cart.put(p, cantidad);
    }

    public ArrayList<Training> getTrainings() {
        return trainings;
    }

    public ArrayList<Integer> getClasses() {
        return classes;
    }

    public ArrayList<Integer> getShoppingHistory() {
        return shoppingHistory;
    }
    
    @Override
    public HashMap<Product, Integer> getCart() {
        return cart;
    }

//Cambio el toString para que cuando lo muetsr en la lista solo enseñe el nombre 
    @Override
    public String toString() {
        return getUserName();  
    }
}
