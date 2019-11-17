package by.dorozhko.poputka.entity;

import java.util.Objects;

public class Car extends Entity {
    /**
     * Name of car's brand.
     */
    private String brand;

    /**
     * Name of car's model.
     */
    private String model;

    /**
     * Year when car's was produced.
     */
    private int yearOfProduce;

    /**
     * Has value true if car has an air conditioner, otherwise false.
     */
    private boolean airConditioner;

    /**
     * Constructs an object of this class with null value of parameters.
     */
    public Car() {
    }

    /**
     * Constructs car with not null value of parameters.
     *
     * @param id                Id of Entity.
     * @param brandName         Name of brand.
     * @param modelName         Value of model.
     * @param yearOfCarProduce  Year when car was produced.
     * @param hasAirConditioner Has value true if car has an
     *                          air conditioner, otherwise false.
     */
    public Car(final int id,
               final String brandName,
               final String modelName,
               final int yearOfCarProduce,
               final boolean hasAirConditioner) {
        super(id);
        brand = brandName;
        model = modelName;
        yearOfProduce = yearOfCarProduce;
        airConditioner = hasAirConditioner;
    }


    /**
     * Get name of car's brand method.
     *
     * @return name of car's brand.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Set or update name of car's brand method.
     *
     * @param brandName name of car's brand.
     */
    public void setBrand(final String brandName) {
        brand = brandName;
    }

    /**
     * Get name of car's model method.
     *
     * @return name of car's model
     */
    public String getModel() {
        return model;
    }

    /**
     * Set or update name of car's model method.
     *
     * @param modelName ame of car's model.
     */
    public void setModel(final String modelName) {
        model = modelName;
    }

    /**
     * Get year when car was produced method.
     *
     * @return year of production.
     */
    public int getYearOfProduce() {
        return yearOfProduce;
    }

    /**
     * Set or update year when car was produced method.
     *
     * @param yearOfCarProduce year of production.
     */
    public void setYearOfProduce(final int yearOfCarProduce) {
        yearOfProduce = yearOfCarProduce;
    }

    /**
     * Get information about car air conditioner.
     *
     * @return Value true if car has an
     * air conditioner, otherwise false.
     */
    public boolean isAirConditioner() {
        return airConditioner;
    }

    /**
     * Set or update information about car air conditioner.
     *
     * @param hasAirConditioner Has value true if car has an
     *                          air conditioner, otherwise false.
     */
    public void setAirConditioner(final boolean hasAirConditioner) {
        airConditioner = hasAirConditioner;
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
        Car car = (Car) o;
        return yearOfProduce == car.yearOfProduce
                && airConditioner == car.airConditioner
                && brand.equals(car.brand)
                && model.equals(car.model);
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
                brand,
                model,
                yearOfProduce,
                airConditioner);
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
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", yearOfProduce=" + yearOfProduce
                + ", airConditioner=" + airConditioner
                + '}';
    }
}
