/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;
import java.util.ArrayList;
import excepciones.*;

/**
 *
 * @author ire22
 */
public class Person {
    
    protected String userName;
    protected String password;
    protected String name;
    protected String lastName;
    protected String dni;
    protected LocalDate startDate;
    protected float balance;
    
    public Person(String userName, String password, String name, String lastName, String dni, float balance,LocalDate startDate) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.startDate = startDate;
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

    public void setPassword(String password) throws PasswordException  {
      if (!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[_@#$%^&+=!]).{8,}$")) {
        throw new PasswordException("The password must be at least 8 characters long and include an uppercase letter, a lowercase letter, a number, and a special character.");
    }

    this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   public void setDni(String dni) throws DniException {
        if (!dni.matches("\\d{8}[A-Za-z]")) {
            throw new DniException("The DNI must have 8 numbers and one letter.");
        }

    this.dni = dni;
}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    
    public void setBalance(float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Person{" + "userName=" + userName + ", password=" + password + ", name=" + name + ", lastName=" + lastName + ", dni=" + dni + ", startDate=" + startDate + '}';
    }
   
}
