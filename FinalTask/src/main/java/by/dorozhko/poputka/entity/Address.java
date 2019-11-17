package by.dorozhko.poputka.entity;

import java.util.Objects;

public class Address extends Entity {
    /**
     * Country name.
     */
    private String country;
    /**
     * Name of region main city.
     */
    private String regionalCenter;
    /**
     * Name of district center.
     */
    private String districtCenter;
    /**
     * Name of city.
     */
    private String city;
    /**
     * Name of city's district.
     */
    private String districtOfCity;
    /**
     * Street name.
     */
    private String street;
    /**
     * number of house.
     */
    private String houseNumber;

    /**
     * Constructs object with null value of parameters.
     */
    public Address() {
    }

    /**
     * Constructs Address with not null value.
     *
     * @param id                 id of Entity.
     * @param countryName        name of country.
     * @param regionalCenterName name of region main city.
     * @param districtCenterName Name of district center.
     * @param cityName           Name of city.
     * @param districtOfCityName Name of city's district.
     * @param streetName         name of street.
     * @param houseNumberValue   number of house.
     */
    public Address(final int id,
                   final String countryName,
                   final String regionalCenterName,
                   final String districtCenterName,
                   final String cityName,
                   final String districtOfCityName,
                   final String streetName,
                   final String houseNumberValue) {
        super(id);
        this.country = countryName;
        this.regionalCenter = regionalCenterName;
        this.districtCenter = districtCenterName;
        this.city = cityName;
        this.districtOfCity = districtOfCityName;
        this.street = streetName;
        this.houseNumber = houseNumberValue;
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
     * Get name of district center method.
     *
     * @return Name of district center.
     */
    public String getDistrictCenter() {
        return districtCenter;
    }

    /**
     * Set or update name of district center method.
     *
     * @param districtCenterName name of district center.
     */
    public void setDistrictCenter(final String districtCenterName) {
        districtCenter = districtCenterName;
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
     * Get name of city's district method.
     *
     * @return Name of city's district.
     */
    public String getDistrictOfCity() {
        return districtOfCity;
    }

    /**
     * Set or update name of city's district method.
     *
     * @param districtOfCityName Name of city's district.
     */
    public void setDistrictOfCity(final String districtOfCityName) {
        districtOfCity = districtOfCityName;
    }

    /**
     * Get name of street method.
     *
     * @return name of street.
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set or update name of street method.
     *
     * @param streetName name of street.
     */
    public void setStreet(final String streetName) {
        street = streetName;
    }

    /**
     * Get house number method.
     *
     * @return number of house.
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Set or update number of house.
     *
     * @param numberOfHouse number of house.
     */
    public void setHouseNumber(final String numberOfHouse) {
        houseNumber = numberOfHouse;
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
                && Objects.equals(districtCenter, address.districtCenter)
                && Objects.equals(city, address.city)
                && Objects.equals(districtOfCity, address.districtOfCity)
                && Objects.equals(street, address.street)
                && Objects.equals(houseNumber, address.houseNumber);
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
                districtCenter,
                city,
                districtOfCity,
                street,
                houseNumber);
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
                + ", districtCenter='" + districtCenter + '\''
                + ", City='" + city + '\''
                + ", districtOfCity='" + districtOfCity + '\''
                + ", street='" + street + '\''
                + ", houseNumber='" + houseNumber + '\''
                + '}';
    }
}
