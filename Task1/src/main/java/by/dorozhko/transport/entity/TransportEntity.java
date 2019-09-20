package by.dorozhko.transport.entity;

public abstract class TransportEntity {

    /**
     * Id of transport entity.
     */
    private int id;
    /**
     * Generate new id when entity is adding.
     */
    private static int idGenerator = 0;

    /**
     * Name of transport entity.
     */
    private String name;

    /**
     * Weight of entity (in kilograms).
     */
    private int weight;

    /**
     * Length of transport entity (in miters).
     */
    private int length;


    /**
     * Public TransportEntity consractor.
     *
     * @param entityName     name of entity.
     * @param weightInKilo   weight in kilograms of entity.
     * @param lengthInMeters length in miters of entity.
     */
    public TransportEntity(final String entityName, final int weightInKilo, final int lengthInMeters) {
        this.id = idGenerator++;
        this.name = entityName;
        this.weight = weightInKilo;
        this.length = lengthInMeters;
    }

    /**
     * getId().
     *
     * @return (int) id of entity.
     */
    public int getId() {
        return id;
    }

    /**
     * getName().
     *
     * @return (String) name of entity.
     */
    public String getName() {
        return name;
    }

    /**
     * setName().
     *
     * @param name set name of entity.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * getWeightInKilo().
     *
     * @return (int) weight of entity (in kilograms)
     */
    public int getWeightInKilo() {
        return weight;
    }

    /**
     * setWeightInKilo().
     *
     * @param weightInKilo set weight of entity (in kilograms).
     */
    public void setWeightInKilo(final int weightInKilo) {
        this.weight = weightInKilo;
    }

    /**
     * getLengthInMeters().
     *
     * @return (int) length of entity (in meters)
     */
    public int getLengthInMeters() {
        return length;
    }

    /**
     * setLengthInMeters().
     *
     * @param lengthInMeters set length of entity (in meters)
     */
    public void setLengthInMeters(final int lengthInMeters) {
        this.length = lengthInMeters;
    }

    /**
     * Method generates hashCode.
     *
     * @return hashCode.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = result * prime + id;
        result = result * prime + name == null ? 0 : name.hashCode();
        result = result * prime + weight;
        result = result * prime + length;
        return result;
    }


    /**
     * Method cheack is two objects are equals.
     *
     * @param o
     * @return boolean isEquals.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }

        TransportEntity obj = (TransportEntity) o;
        if (id != obj.id) {
            return false;
        }
        if (!name.equals(obj.name)) {
            return false;
        }
        if (weight != obj.weight) {
            return false;
        }
        if (length != length) {
            return false;
        }

        return true;


    }
}
