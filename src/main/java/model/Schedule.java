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
public class Schedule {
    
    private String day;
    private int hour;

    public Schedule(String day, int hour) {
        this.day = day;
        this.hour = hour;
    }
    
    public Schedule() {
        this.day = "";
        this.hour = 0;
    }

    public String getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Schedule{" + "day=" + day + ", hour=" + hour + '}';
    }
}
