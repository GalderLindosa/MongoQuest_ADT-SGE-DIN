/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class User extends Person{
    
    private ArrayList<Training> trainings;
    private ArrayList<Integer> classes;
    private ArrayList<Integer> shoppingHistory;

    public User(ArrayList<Training> trainings, ArrayList<Integer> classes, ArrayList<Integer> shoppingHistory,
            String userName, String password, String name, String lastName, String dni) {
        super(userName, password, name, lastName, dni);
        this.trainings = trainings;
        this.classes = classes;
        this.shoppingHistory = shoppingHistory;
    }
    
    public User() {
        super();
        this.trainings = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.shoppingHistory = new ArrayList<>();
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
    public String toString() {
        return "User{" + "trainings=" + trainings + ", classes=" + classes + ", shoppingHistory=" + shoppingHistory + '}';
    }
}
