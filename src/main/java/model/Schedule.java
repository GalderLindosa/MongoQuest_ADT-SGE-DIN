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
    private String hour;

    public Schedule(String day, String hour) {
        this.day = day;
        this.hour = hour;
    }
    
    public Schedule() {
        this.day = "";
        this.hour = "";
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    @Override
    public String toString() {
        return "Schedule{" + "day=" + day + ", hour=" + hour + '}';
    }
}
