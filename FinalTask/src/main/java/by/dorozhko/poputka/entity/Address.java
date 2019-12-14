package by.dorozhko.poputka.entity;

import java.io.Serializable;
import java.util.Objects;

public class Address extends Entity implements Serializable {
    /**
     * Country name.
     */
    private String country;
    /**
     * Name of region main city.
     */
    private String regionalCenter;

    /**
     * Name of city.
     */
    private String city;

    /**
     * Constructs object with null value of parameters.
     */
    public Address() {
    }

    /**
     * Constructs Address with not null value.
     *
     * @param countryName        name of country.
     * @param regionalCenterName name of region main city.
     * @param cityName           Name of city.
     */
    public Address(
                   final String countryName,
                   final String regionalCenterName,
                   final String cityName) {
        this.country = countryName;
        this.regionalCenter = regionalCenterName;
        this.city = cityName;
    }





    /**
     * Get country name method.
     *
     * @return country name.
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set or update country name method.
     *
     * @param countryName name of country.
     */
    public void setCountry(final String countryName) {
        this.country = countryName;
    }

    /**
     * Get name of region main city.
     *
     * @return name of region main city
     */
    public String getRegionalCenter() {
        return regionalCenter;
    }

    /**
     * Set name of region main city.
     *
     * @param regionalCenterName name of region main city.
     */
    public void setRegionalCenter(final String regionalCenterName) {
        this.regionalCenter = regionalCenterName;
    }


    /**
     * Get name of city method.
     *
     * @return name of city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Set or update name of city.
     *
     * @param cityName name of city.
     */
    public void setCity(final String cityName) {
        city = cityName;
    }


    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o - the reference object with which to compare.
     * @return true if this object is the same as the obj argument;
     * false otherwise.
     */


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Address address = (Address) o;
        return Objects.equals(country, address.country)
                && Objects.equals(regionalCenter, address.regionalCenter)
                && Objects.equals(city, address.city);
    }

    /**
     * Returns a hash code value for the object. This method is
     * supported for the benefit of hash tables such as those
     * provided by HashMap.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),
                country,
                regionalCenter,
                city);
    }


    /**
     * Returns a string representation of the object. In general,
     * the toString method returns a string that "textually represents"
     * this object. The result should be a concise but informative
     * representation that is easy for a person to read.
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{"
                + "id=" + getId()
                + "country='" + country + '\''
                + ", regionalCenter='" + regionalCenter + '\''
                + ", City='" + city + '\''
                + '}';
    }
}
