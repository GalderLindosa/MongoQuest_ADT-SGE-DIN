/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Unai.Ibarguren
 */
public class Gym {

    private int gymId;
    private String dir;
    private String pc;

    public Gym(int gymId, String dir, String pc) {
        this.gymId = gymId;
        this.dir = dir;
        this.pc = pc;
    }

    public Gym() {
        this.gymId = 0;
        this.dir = "";
        this.pc = "";
    }

    public int getGymId() {
        return gymId;
    }

    public String getDir() {
        return dir;
    }

    public String getPc() {
        return pc;
    }

    public void setGymId(int gymId) {
        this.gymId = gymId;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public void setPc(String pc) {
        this.pc = pc;
    }

    @Override
    public String toString() {
        return dir;
    }
    
    
}
