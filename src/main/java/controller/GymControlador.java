/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.*;

/**
 *
 * @author ire22
 */
public class GymControlador {
    private ImplementacionBD dao;
  
    // -------------------- CONSTRUCTOR --------------------
    public GymControlador() {
        this.dao = ImplementacionBD.getInstance();
      
    }
   
    public ArrayList<User> getUsers(){
        return dao.getUsers();
    }
    public ArrayList<Employee> getEmployee(){
        return dao.getEmployee();
    }
    public boolean insertarPerson (Person p){
        return dao.insertarPerson(p);
    }
    public boolean modificarPerson(Person p){
        return dao.modificarPerson(p);
    }
    public boolean eliminarPerson(Person p) {
        return dao.modificarPerson(p);
    }
     
}
