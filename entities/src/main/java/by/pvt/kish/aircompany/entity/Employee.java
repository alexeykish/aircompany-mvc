package by.pvt.kish.aircompany.entity;

import by.pvt.kish.aircompany.enums.Position;

/**
 * Описывает сущность сотрудника
 * Сотрудник состоит в полетной бригаде (может состоять в нескольких бригадах)
 *
 * @author Kish Alexey
 */
public class Employee {
	private int eid;
	private String firstName;
	private String lastName;
	private Position position;
	
	public Employee(){
	}
	
	/**
	 * @param eid - employee id
	 * @param firstName - employee firstname
	 * @param lastName - employee lastname
	 * @param position - employee position in a flight team
	 */
	public Employee(int eid, String firstName, String lastName, Position position) {
		super();
		this.eid = eid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.position = position;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Employee employee = (Employee) o;

		if (eid != employee.eid) return false;
		if (firstName != null ? !firstName.equals(employee.firstName) : employee.firstName != null) return false;
		if (lastName != null ? !lastName.equals(employee.lastName) : employee.lastName != null) return false;
		return position == employee.position;

	}

	@Override
	public int hashCode() {
		int result = eid;
		result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
		result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
		result = 31 * result + (position != null ? position.hashCode() : 0);
		return result;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}
