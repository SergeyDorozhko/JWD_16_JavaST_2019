package by.dorozhko.transport.entity;

import by.dorozhko.transport.entity.params.EngineType;

public class Train extends TransportEntity {
    /**
     * Engine type of power.
     */
    private EngineType engineType;

    /**
     * Power of engine in horse power.
     */
    private int enginePower;

    /**
     * Max value of speed (in kilometer per hour).
     */
    private int maxSpeed;


    /**
     * Public constructor of class Train.
     *
     * @param name           super class parametr.
     * @param weightInKilo   super class parametr.
     * @param lengthInMeters super class parametr.
     * @param typeOfEngine     Engine type of power.
     * @param powerOfEngine    Power of engine in horse power.
     * @param maximumSpeed       Max value of speed (in kilometer per hour)
     */
    public Train(final String name,
                 final int weightInKilo,
                 final int lengthInMeters,
                 final EngineType typeOfEngine,
                 final int powerOfEngine,
                 final int maximumSpeed) {
        super(name, weightInKilo, lengthInMeters);
        this.engineType = typeOfEngine;
        this.enginePower = powerOfEngine;
        this.maxSpeed = maximumSpeed;
    }


    /**
     * Type of engine power.
     *
     * @return Engine type of power.
     */
    public EngineType getEngineType() {
        return engineType;
    }

    /**
     * Method set type of engine power.
     *
     * @param typeOfEngine enum.
     */
    public void setEngineType(final EngineType typeOfEngine) {
        this.engineType = typeOfEngine;
    }

    /**
     * Method get engine power.
     *
     * @return Power of engine in horse power.
     */
    public int getEnginePower() {
        return enginePower;
    }

    /**
     * Method set engine power.
     *
     * @param poserOfEngine Power in horse power.
     */
    public void setEnginePower(final int poserOfEngine) {
        this.enginePower = poserOfEngine;
    }

    /**
     * Method get max value of speed.
     *
     * @return Speed in kilometers per hour.
     */
    public int getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * Method set max value of speed in kilometers per hour.
     *
     * @param maximumSpeed max value of speed.
     */
    public void setMaxSpeed(final int maximumSpeed) {
        this.maxSpeed = maximumSpeed;
    }

    /**
     * generating hashcode.
     *
     * @return hashcode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + engineType.hashCode();
        result = result * prime + enginePower;

        result = result * prime + maxSpeed;
        return result;
    }

    /**
     * Equals.
     * @param o object.
     * @return result.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (this == null || this.getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Train obj = (Train) o;
        if (!engineType.equals(obj.engineType)) {
            return false;
        }
        if (enginePower != obj.enginePower) {
            return false;
        }
        if (maxSpeed != obj.maxSpeed) {
            return false;
        }

        return true;
    }

    /**
     * to string.
     * @return string view of entity.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName()
                + ": id = " + super.getId()
                + ", name = " + super.getName()
                + ", weight = " + super.getWeightInKilo()
                + ", length = " + super.getLengthInMeters()
                + ", engineType = " + engineType
                + ", enginePower = " + enginePower
                + ", maxSpeed = " + maxSpeed;
    }
}
