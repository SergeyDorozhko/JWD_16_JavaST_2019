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
     * @param engineType     Engine type of power.
     * @param enginePower    Power of engine in horse power.
     * @param maxSpeed       Max value of speed (in kilometer per hour)
     */
    public Train(String name, int weightInKilo, int lengthInMeters, EngineType engineType, int enginePower, int maxSpeed) {
        super(name, weightInKilo, lengthInMeters);
        this.engineType = engineType;
        this.enginePower = enginePower;
        this.maxSpeed = maxSpeed;
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
     * @param engineType
     */
    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
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
     * @param enginePower Power in horse power.
     */
    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
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
     * @param maxSpeed
     */
    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = result * prime + engineType.hashCode();
        result = result * prime + enginePower;

        result = result * prime + maxSpeed;
        return result;
    }


    @Override
    public boolean equals(Object o) {
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": id = " + super.getId() + ", name = " + super.getName() + ", weight = " + super.getWeightInKilo() + ", length = " + super.getLengthInMeters() + ", engineType = " + engineType + ", enginePower = " + enginePower + ", maxSpeed = " + maxSpeed;
    }
}
