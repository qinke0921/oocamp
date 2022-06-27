package org.oobootcamp;

public interface Parkability {

    Ticket park(Car car);

    Car pickUp(Ticket ticket);
}
