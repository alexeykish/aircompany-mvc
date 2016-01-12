package by.pvt.kish.aircompany.entity;

/**
 * Описывает сущность самолета
 * Самолет используется для осуществления Рейса
 * Самолет характеризуется вместимостью пассажиров <code>capacity</code> и дальностью полета <code>range</code>
 *
 * @author Kish Alexey
 */
public class Plane {

    private int pid;
    private String model;
    private int capacity;
    private int range;

    public Plane() {
    }

    /**
     * @param pid - plane id
     * @param model - plane model
     * @param capacity - plane passenger capacity
     * @param range - plane flight range
     */
    public Plane(int pid, String model, int capacity, int range) {
        this.pid = pid;
        this.model = model;
        this.capacity = capacity;
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plane plane = (Plane) o;

        if (pid != plane.pid) return false;
        if (capacity != plane.capacity) return false;
        if (range != plane.range) return false;
        return model != null ? model.equals(plane.model) : plane.model == null;

    }

    @Override
    public int hashCode() {
        int result = pid;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + capacity;
        result = 31 * result + range;
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
}
