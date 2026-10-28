/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author ire22
 */
public class Employee extends Person{
    
    private ArrayList<Integer> classes;
    private ArrayList<Integer> shoppingHistory;
    private String discountCode;
    private int gymId;

    public Employee(ArrayList<Integer> classes, ArrayList<Integer> shoppingHistory,
            String discountCode, int gymId, String userName, String password, String name, String lastName, String dni) {
        super(userName, password, name, lastName, dni);
        this.classes = classes;
        this.shoppingHistory = shoppingHistory;
        this.discountCode = discountCode;
        this.gymId = gymId;
    }

    public Employee() {
        super();
        this.classes = new ArrayList<>();
        this.shoppingHistory = new ArrayList<>();
        this.discountCode = "";
        this.gymId = 0;
    }

    public ArrayList<Integer> getClasses() {
        return classes;
    }

    public ArrayList<Integer> getShoppingHistory() {
        return shoppingHistory;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public int getGymId() {
        return gymId;
    }

    public void setClasses(ArrayList<Integer> classes) {
        this.classes = classes;
    }

    public void setShoppingHistory(ArrayList<Integer> shoppingHistory) {
        this.shoppingHistory = shoppingHistory;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return "Employee{" + "classes=" + classes + ", shoppingHistory=" + shoppingHistory + ", discountCode=" + discountCode + ", gymId=" + gymId + '}';
    }
}
