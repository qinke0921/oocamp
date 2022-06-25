package org.oobootcamp;

public abstract class CarParkBoy {
    protected final ParkLot[] parkLots;


    public CarParkBoy(ParkLot[] parkLots){
        this.parkLots =parkLots;

    }

    public abstract Ticket park(Car car);

    public Car pickUp(Ticket ticket){
        for (ParkLot carpark : parkLots){
            if(carpark.contain(ticket)){
                return carpark.pickUp(ticket);
            }
        }
        throw new TicketInvalidException();

    }

}
