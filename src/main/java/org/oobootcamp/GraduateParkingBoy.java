package org.oobootcamp;

public class GraduateParkingBoy  extends CarParkBoy {

    public GraduateParkingBoy(ParkLot[] parkLots){
        super(parkLots);
    }


    public Ticket park(Car car){
        for (ParkLot parkLot : parkLots){
            if(!parkLot.isFull()){
                return parkLot.park(car);
            }
        }
        throw new ParkIsFullException();

    }

}
