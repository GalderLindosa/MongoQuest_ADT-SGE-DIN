/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class User extends Person implements WithCart{
    
    private List<Training> trainings;
    private List<Integer> classes;
    private List<Integer> shoppingHistory;
    private HashMap<Product, Integer> cart;

    public User(List<Training> trainings, List<Integer> classes, List<Integer> shoppingHistory,
                String userName, String password, String name, String lastName, String dni, float balance) {
        super(userName, password, name, lastName, dni, balance);
        this.trainings = trainings;
        this.classes = classes;
        this.shoppingHistory = shoppingHistory;
        this.cart = new HashMap<>();
    }
    
        public User(String userName, String password, String name, String lastName, String dni, float balance) {
        super(userName, password, name, lastName, dni, balance);
        
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

    public List<Training> getTrainings() {
        return trainings;
    }

    public List<Integer> getClasses() {
        return classes;
    }

    public List<Integer> getShoppingHistory() {
        return shoppingHistory;
    }
    
    @Override
    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    @Override
    public String toString() {
        return "User{" + "trainings=" + trainings + ", classes=" + classes + ", shoppingHistory=" + shoppingHistory + ", cart=" + cart + '}';
    }
}
