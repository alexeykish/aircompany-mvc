package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.Position;

import java.io.Serializable;
import java.util.Map;

/**
 * Описывает сущность самолета
 * Самолет используется для осуществления Рейса
 * Самолет характеризуется вместимостью пассажиров <code>capacity</code> и дальностью полета <code>range</code>
 *
 * @author Kish Alexey
 */
public class Plane implements Serializable{

    private int pid;
    private String model;
    private int capacity;
    private int range;
    private Map<Position, Integer> team;

    public Plane() {
    }

    /**
     * @param pid - plane id
     * @param model - plane model
     * @param capacity - plane passenger capacity
     * @param range - plane flight range
     * @param team - team to service flight
     */

    public Plane(int pid, String model, int capacity, int range, Map<Position, Integer> team) {
        this.pid = pid;
        this.model = model;
        this.capacity = capacity;
        this.range = range;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (pid != plane.pid) return false;
        if (capacity != plane.capacity) return false;
        if (range != plane.range) return false;
        if (model != null ? !model.equals(plane.model) : plane.model != null) return false;
        return team != null ? team.equals(plane.team) : plane.team == null;

    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + range;
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public Map<Position, Integer> getTeam() {
        return team;
    }

    public void setTeam(Map<Position, Integer> team) {
        this.team = team;
    }
}
