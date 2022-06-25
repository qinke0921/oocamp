package org.oobootcamp;

public class SmartParkingBoy extends CarParkBoy {

    public SmartParkingBoy(ParkLot[] parkLots){
        super(parkLots);

    }


    public Ticket park(Car car){
        ParkLot maxAvailablePark = new ParkLot(0);
        for (ParkLot carpark : parkLots){
            if(carpark.getAvailableSlotNum() > maxAvailablePark.getAvailableSlotNum()){
                maxAvailablePark = carpark;
            }
        }
        return maxAvailablePark.park(car);

    }

}
