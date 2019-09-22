package by.dorozhko.transport.repository.transport_specification.sort_impl.comporators;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.Comparator;

public class TransportNameComporator implements Comparator<TransportEntity> {
    /**
     * Method comparing two TransportEntity by name.
     *
     * @param o1 first.
     * @param o2 another one.
     * @return int result of compare.
     */
    @Override
    public int compare(final TransportEntity o1, final TransportEntity o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
