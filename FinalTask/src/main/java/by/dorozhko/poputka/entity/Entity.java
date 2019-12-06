package by.dorozhko.poputka.entity;

import java.util.Objects;

public abstract class Entity {
    /**
     * Identity of entity.
     */
    private int id;


    /**
     * Get idenntity of entity method.
     *
     * @return identity of entity.
     */
    public int getId() {
        return id;
    }

    /**
     * Set identity of entity method.
     *
     * @param newId new identity of entity.
     */
    public void setId(final int newId) {
        this.id = newId;
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
        Entity entity = (Entity) o;
        return id == entity.id;
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
        return Objects.hash(id);
    }
}
