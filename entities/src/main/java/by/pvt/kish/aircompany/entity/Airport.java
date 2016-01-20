package by.pvt.kish.aircompany.entity;

import java.io.Serializable;

/**
 * This class represents the Airport model.
 * The airport is used as a flights place of departure and place of arrival.
 * This model class can be used throughout all
 * layers, the data layer, the controller layer and the view layer.
 *
 * @author Kish Alexey
 */
public class Airport implements Serializable {
    private int aid;
    private String city;

    public Airport() {
    }

    /**
     * @param aid  - airport id
     * @param city - the place where the airport is located
     */
    public Airport(int aid, String city) {
        this.aid = aid;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Airport airport = (Airport) o;

        return aid == airport.aid && (city != null ? city.equals(airport.city) : airport.city == null);

    }

    @Override
    public int hashCode() {
        int result = aid;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
