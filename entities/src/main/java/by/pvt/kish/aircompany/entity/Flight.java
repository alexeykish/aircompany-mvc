/**
 *
 */
package by.pvt.kish.aircompany.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Описывает сущность рейса
 * Рейс осуществляется из места вылета <code>from</code> в место прилета <code>to</code> самолетом <code>plane</code>
 * Рейс выполняется в определенныю дату <code>date</code>
 * Каждый рейс обслуживает полетная бригада (указывается id паолетной бригады <code>tid</code>
 *
 * @author Kish Alexey
 */
public class Flight implements Serializable{
    private int fid;
    private Date date;
    private Airport from;
    private Airport to;
    private Plane plane;
    private List<Employee> team;

    public Flight() {

    }

    /**
     * @param fid   - flight id
     * @param date  - flight date (departure date)
     * @param from  - the place where the flight departs
     * @param to    - the place where the flight arrives
     * @param plane - the plane on which the flight is carried out
     */
    public Flight(int fid, Date date, Airport from, Airport to, Plane plane, List<Employee> team) {
        this.fid = fid;
        this.date = date;
        this.from = from;
        this.to = to;
        this.plane = plane;
        this.team = team;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (fid != flight.fid) return false;
        if (date != null ? !date.equals(flight.date) : flight.date != null) return false;
        if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
        if (to != null ? !to.equals(flight.to) : flight.to != null) return false;
        if (plane != null ? !plane.equals(flight.plane) : flight.plane != null) return false;
        return team != null ? team.equals(flight.team) : flight.team == null;

    }

    @Override
    public int hashCode() {
        int result = fid;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        result = 31 * result + (plane != null ? plane.hashCode() : 0);
        result = 31 * result + (team != null ? team.hashCode() : 0);
        return result;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Airport getFrom() {
        return from;
    }

    public void setFrom(Airport from) {
        this.from = from;
    }

    public Airport getTo() {
        return to;
    }

    public void setTo(Airport to) {
        this.to = to;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public List<Employee> getTeam() {
        return team;
    }

    public void setTeam(List<Employee> team) {
        this.team = team;
    }
}
