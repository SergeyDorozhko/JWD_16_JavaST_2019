package by.dorozhko.transport.entity;


import by.dorozhko.transport.entity.params.CarriageType;


public class Carriage extends TransportEntity {
    /**
     * Carriage type (set by enum).
     */
    private CarriageType type;

    /**
     * Max value of passengers.
     */
    private int numberOfPassengers;


    /**
     * max available baggage (number of positions).
     */
    private int maxValueOfBaggage;


    /**
     * Constructor of Carriage.
     *
     * @param name               super class.
     * @param weightInKilo       superclass.
     * @param lengthInMeters     superclass.
     * @param carriageType     Type of carriage (set  by enum).
     * @param passengersNumber max value of passengers in Carriage.
     * @param maxBaggageValue  max value of baggage.
     *                           (in positions) in Carriage.
     */
    public Carriage(final String name, final int weightInKilo,
                    final int lengthInMeters, final CarriageType carriageType,
                    final int passengersNumber, final int maxBaggageValue) {
        super(name, weightInKilo, lengthInMeters);
        this.type = carriageType;
        this.numberOfPassengers = passengersNumber;
        this.maxValueOfBaggage = maxBaggageValue;
    }


    /**
     * @return type of carriage.
     */
    public CarriageType getType() {
        return type;
    }

    /**
     * This method set type of carriage..
     * @param carriageType type of carriage.
     */
    public void setType(final CarriageType carriageType) {
        this.type = carriageType;
    }

    /**
     * This method get numbers of passengers of carriage.
     *
     * @return Number of passengers.
     */
    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    /**
     * This method set available number of passengers in carriage.
     *
     * @param  passengersNumber up the number of passengers.
     */
    public void setNumberOfPassengers(final int passengersNumber) {
        this.numberOfPassengers = passengersNumber;
    }

    /**
     * This method get max valu of baggage in carriage.
     *
     * @return max value of bagage.
     */
    public int getMaxValueOfBaggage() {
        return maxValueOfBaggage;
    }

    /**
     * This method set max available value of baggage in carriage.
     *
     * @param maxBaggageValue set up the max value of baggage.
     */
    public void setMaxValueOfBaggage(final int maxBaggageValue) {
        this.maxValueOfBaggage = maxBaggageValue;
    }

    /**
     * Method generate hashcode of entity.
     *
     * @return hashcode value.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + type.hashCode();
        result = result * prime + numberOfPassengers;
        result = result * prime + maxValueOfBaggage;
        return result;
    }

    /**
     * Equals.
     * @param o object.
     * @return boolean result.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Carriage obj = (Carriage) o;

        if (this.type.equals(obj.type)) {
            return false;
        }
        if (this.maxValueOfBaggage != obj.maxValueOfBaggage) {
            return false;
        }
        if (this.numberOfPassengers != obj.numberOfPassengers) {
            return false;
        }
        return true;
    }

    /**
     * To string.
     * @return Entity in string view.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + ": id = " + super.getId()
                + ", name = " + super.getName()
                + ", weight = " + super.getWeightInKilo()
                + ", length = " + super.getLengthInMeters()
                + ", carriageType = " + type
                + ", maxValueOfBaggage = " + maxValueOfBaggage
                + ", numberOfPassengers = " + numberOfPassengers;
    }
}
