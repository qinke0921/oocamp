package org.oobootcamp;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager implements Parkability{

    private ArrayList<CarParkBoy> carParkBoys;
    public ParkingManager(CarParkBoy[] carParkBoys, ParkLot[] parkLots){
        this.carParkBoys=new ArrayList<CarParkBoy>(List.of(carParkBoys));
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(parkLots);
        this.carParkBoys.add(graduateParkingBoy);
    }

    public Ticket park(Car car) {
        for(CarParkBoy boy: this.carParkBoys){
            try {
                return boy.park(car);
            }
            catch (Exception ignored){

            }
        }
        throw new ParkIsFullException();

    }

    public Car pickUp(Ticket ticket) {
        for(CarParkBoy boy: this.carParkBoys){
            try {
                return boy.pickUp(ticket);
            }
            catch (Exception ignored){

            }
        }
        throw new TicketInvalidException();

    }
}
