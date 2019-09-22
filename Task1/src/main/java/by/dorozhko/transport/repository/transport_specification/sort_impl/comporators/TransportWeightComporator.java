package by.dorozhko.transport.repository.transport_specification.sort_impl.comporators;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.Comparator;

public class TransportWeightComporator implements Comparator<TransportEntity> {
    /**
     * Compare Entity by weight.
     *
     * @param o1 one.
     * @param o2 another.
     * @return result of compare.
     */
    @Override
    public int compare(final TransportEntity o1, final TransportEntity o2) {
        int result = 0;
        if (o1.getWeightInKilo() > o2.getWeightInKilo()) {
            result = 1;
        }

        if (o1.getWeightInKilo() < o2.getWeightInKilo()) {
            result = -1;
        }

        return result;
    }
}
