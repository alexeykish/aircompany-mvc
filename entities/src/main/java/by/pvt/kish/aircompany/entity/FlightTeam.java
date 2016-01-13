/**
 * 
 */
package by.pvt.kish.aircompany.entity;

/**
 * Описывает сущность полетной бригады
 * Полетная бригада обслуживает рейс <code>flight</code> (может обслуживать несколько рейсов)
 * Полетная бригада состоит из двух пилотов, навигатора, радиооператора и трех стюардесс
 *
 * @author Kish Alexey
 */
public class FlightTeam {
	private int tid;
	private Employee firstPilot;
	private Employee secondPilot;
	private Employee navigator;
	private Employee radiooperator;
	private Employee stewardess1;
	private Employee stewardess2;
	private Employee stewardess3;

	public FlightTeam() {

	}

	/**
	 * @param tid - flight team id
	 * @param firstPilot - first pilot id
	 * @param secondPilot - second pilot id
	 * @param navigator - navigator id
	 * @param radiooperator - radiooperator id
	 * @param stewardess1 - 1st stewardess id
	 * @param stewardess2 - 2nd stewardess id
	 * @param stewardess3 - 3rd stewardess id
	 */
	public FlightTeam(int tid, Employee firstPilot, Employee secondPilot, Employee navigator, Employee radiooperator, Employee stewardess1, Employee stewardess2, Employee stewardess3) {
		super();
		this.tid = tid;
		this.firstPilot = firstPilot;
		this.secondPilot = secondPilot;
		this.navigator = navigator;
		this.radiooperator = radiooperator;
		this.stewardess1 = stewardess1;
		this.stewardess2 = stewardess2;
		this.stewardess3 = stewardess3;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstPilot == null) ? 0 : firstPilot.hashCode());
		result = prime * result + ((navigator == null) ? 0 : navigator.hashCode());
		result = prime * result + ((radiooperator == null) ? 0 : radiooperator.hashCode());
		result = prime * result + ((secondPilot == null) ? 0 : secondPilot.hashCode());
		result = prime * result + ((stewardess1 == null) ? 0 : stewardess1.hashCode());
		result = prime * result + ((stewardess2 == null) ? 0 : stewardess2.hashCode());
		result = prime * result + ((stewardess3 == null) ? 0 : stewardess3.hashCode());
		result = prime * result + tid;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FlightTeam))
			return false;
		FlightTeam other = (FlightTeam) obj;
		if (firstPilot == null) {
			if (other.firstPilot != null)
				return false;
		} else if (!firstPilot.equals(other.firstPilot))
			return false;
		if (navigator == null) {
			if (other.navigator != null)
				return false;
		} else if (!navigator.equals(other.navigator))
			return false;
		if (radiooperator == null) {
			if (other.radiooperator != null)
				return false;
		} else if (!radiooperator.equals(other.radiooperator))
			return false;
		if (secondPilot == null) {
			if (other.secondPilot != null)
				return false;
		} else if (!secondPilot.equals(other.secondPilot))
			return false;
		if (stewardess1 == null) {
			if (other.stewardess1 != null)
				return false;
		} else if (!stewardess1.equals(other.stewardess1))
			return false;
		if (stewardess2 == null) {
			if (other.stewardess2 != null)
				return false;
		} else if (!stewardess2.equals(other.stewardess2))
			return false;
		if (stewardess3 == null) {
			if (other.stewardess3 != null)
				return false;
		} else if (!stewardess3.equals(other.stewardess3))
			return false;
		return tid == other.tid;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Employee getFirstPilot() {
		return firstPilot;
	}

	public void setFirstPilot(Employee firstPilot) {
		this.firstPilot = firstPilot;
	}

	public Employee getSecondPilot() {
		return secondPilot;
	}

	public void setSecondPilot(Employee secondPilot) {
		this.secondPilot = secondPilot;
	}

	public Employee getNavigator() {
		return navigator;
	}

	public void setNavigator(Employee navigator) {
		this.navigator = navigator;
	}

	public Employee getRadiooperator() {
		return radiooperator;
	}

	public void setRadiooperator(Employee radiooperator) {
		this.radiooperator = radiooperator;
	}

	public Employee getStewardess1() {
		return stewardess1;
	}

	public void setStewardess1(Employee stewardess1) {
		this.stewardess1 = stewardess1;
	}

	public Employee getStewardess2() {
		return stewardess2;
	}

	public void setStewardess2(Employee stewardess2) {
		this.stewardess2 = stewardess2;
	}

	public Employee getStewardess3() {
		return stewardess3;
	}

	public void setStewardess3(Employee stewardess3) {
		this.stewardess3 = stewardess3;
	}

}
