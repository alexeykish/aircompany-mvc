/**
 * 
 */
package by.pvt.kish.aircompany.entity;

import java.sql.Date;

/**
 * @author Kish Alexey
 */
public class Flight {
	private int fid;
	private Date date;
	private Airport from;
	private Airport to;
	private int tid;
	private Plane plane;

	public Flight() {

	}

	/**
	 * @param fid - flight id
	 * @param date - flight date (departure date)
	 * @param from - the place where the flight departs
	 * @param to - the place where the flight arrives
	 * @param tid - flight team id
	 * @param plane - the plane on which the flight is carried out
	 */
	public Flight(int fid, Date date, Airport from, Airport to, int tid, Plane plane) {
		super();
		this.fid = fid;
		this.date = date;
		this.from = from;
		this.to = to;
		this.tid = tid;
		this.plane = plane;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Flight flight = (Flight) o;

		if (fid != flight.fid) return false;
		if (tid != flight.tid) return false;
		if (date != null ? !date.equals(flight.date) : flight.date != null) return false;
		if (from != null ? !from.equals(flight.from) : flight.from != null) return false;
		if (to != null ? !to.equals(flight.to) : flight.to != null) return false;
		return plane != null ? plane.equals(flight.plane) : flight.plane == null;

	}

	@Override
	public int hashCode() {
		int result = fid;
		result = 31 * result + (date != null ? date.hashCode() : 0);
		result = 31 * result + (from != null ? from.hashCode() : 0);
		result = 31 * result + (to != null ? to.hashCode() : 0);
		result = 31 * result + tid;
		result = 31 * result + (plane != null ? plane.hashCode() : 0);
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

	public int getTid() {
		return tid;
	}

	public void setTid(int i) {
		this.tid = i;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
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
}
