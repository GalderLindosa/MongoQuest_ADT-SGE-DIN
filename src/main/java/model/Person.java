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
public class Person {
    
    private String userName;
    private String password;
    private String name;
    private String lastName;
    private String dni;
    private float balance;

    public Person(String userName, String password, String name, String lastName, String dni, float balance) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.balance = balance;
    }
    
    public Person() {
        this.userName = "";
        this.password = "";
        this.name = "";
        this.lastName = "";
        this.dni = "";
        this.balance = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDni() {
        return dni;
    }
    
    public float getBalance() {
        return balance;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
    
    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{" + "userName=" + userName + ", password=" + password + ", name=" + name 
                + ", lastName=" + lastName + ", dni=" + dni + '}';
    }
    
}
