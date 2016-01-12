package by.pvt.kish.aircompany.entity;

/**
 * Описывает сущность аэропорта
 * Аэропорт используется, как место вылета и место прилета рейса
 *
 * @author Kish Alexey
 */
public class Airport {
    private int aid;
    private String city;

    public Airport() {
    }

    /**
     * @param aid - airport id
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
