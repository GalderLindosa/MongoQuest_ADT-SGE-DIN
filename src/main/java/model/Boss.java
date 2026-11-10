/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ire22
 */
public class Boss extends Person{
    
    private int gymId;

    public Boss(int gymId, String userName, String password, String name, String lastName, String dni, LocalDate startDate) {
        super(userName, password, name, lastName, dni, startDate);
        this.gymId = gymId;
    }
    
    public Boss() {
        super();
        this.gymId = 0;
    }

    public int getGymId() {
        return gymId;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return "Boss{" + "gymId=" + gymId + '}';
    }
}
