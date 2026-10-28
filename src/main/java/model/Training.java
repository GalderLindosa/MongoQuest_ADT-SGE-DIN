/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ire22
 */
public class Training {
    
    private String trainingName;
    private int set;
    private int reps;
    private int weight;

    public Training(String trainingName, int set, int reps, int weight) {
        this.trainingName = trainingName;
        this.set = set;
        this.reps = reps;
        this.weight = weight;
    }
    
    public Training() {
        this.trainingName = "";
        this.set = 0;
        this.reps = 0;
        this.weight = 0;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getTrainingName() {
        return trainingName;
    }

    public int getSet() {
        return set;
    }

    public int getReps() {
        return reps;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Training{" + "trainingName=" + trainingName + ", set=" + set + ", reps=" + reps + ", weight=" + weight + '}';
    }
}
