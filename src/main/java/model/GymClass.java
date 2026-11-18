package model;

import java.util.ArrayList;

public class GymClass {

    private int id;
    private String className;
    private ArrayList<Schedule> schedule;
    private String employeeUsername;
    private ArrayList<String> members;
    private int gymId;

    public GymClass(int id, String className,
            ArrayList<Schedule> schedule,
            String employeeUsername,
            ArrayList<String> members,
            int gymId) {

        this.id = id;
        this.className = className;
        this.schedule = schedule;
        this.employeeUsername = employeeUsername;
        this.members = members;
        this.gymId = gymId;
    }

    public int getId() {
        return id;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Schedule> getSchedule() {
        return schedule;
    }

    public String getEmployeeUsername() {
        return employeeUsername;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public int getGymId() {
        return gymId;
    }

    
}