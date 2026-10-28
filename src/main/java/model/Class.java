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
public class Class {
    
    private int classId;
    private ArrayList<Schedule> schedule;
    private String employeeUserName;
    private ArrayList<String> users;
    private int gymId;

    public Class(int classId, ArrayList<Schedule> schedule, String employeeUserName, ArrayList<String> users, int gymId) {
        this.classId = classId;
        this.schedule = schedule;
        this.employeeUserName = employeeUserName;
        this.users = users;
        this.gymId = gymId;
    }
    
    public Class() {
        this.classId = 0;
        this.schedule = new ArrayList<>();
        this.employeeUserName = "";
        this.users = new ArrayList<>();
        this.gymId = 0;
    }

    public int getClassId() {
        return classId;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public String getEmployeeUserName() {
        return employeeUserName;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public int getGymId() {
        return gymId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public void setSchedule(ArrayList<Schedule> schedule) {
        this.schedule = schedule;
    }

    public void setEmployeeUserName(String employeeUserName) {
        this.employeeUserName = employeeUserName;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    @Override
    public String toString() {
        return "Classes{" + "classId=" + classId + ", schedule=" + schedule + ", employeeUserName=" + employeeUserName 
                + ", users=" + users + ", gymId=" + gymId + '}';
    }
}
