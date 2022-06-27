package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingManagerTest {


    @Test
    public void test_should_park_when_parkingboys_and_manager_all_have_slots(){
        //        Given
        ParkLot carParkA = new ParkLot(20);
        ParkLot carParkB = new ParkLot(10);
        ParkLot carParkC = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();

//        when
        Ticket ticket =parkingManager.park(car);

//        then
        assertThat(ticket).isNotNull();
    }

    @Test
    public void test_should_park_when_parkingboys_have_slots_and_manager_full(){
        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();
        Car previous_parked = new Car();
        carParkC.park(previous_parked);

//        when
        Ticket ticket =parkingManager.park(car);

//        then
        assertThat(ticket).isNotNull();
    }


    @Test
    public void test_should_park_when_parkingboys_full_and_manager_have_slots(){
        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();
        Car previous_parkedA = new Car();
        Car previous_parkedB = new Car();
        carParkA.park(previous_parkedA);
        carParkB.park(previous_parkedB);

//        when
        Ticket ticket =parkingManager.park(car);

//        then
        assertThat(ticket).isNotNull();
    }


    @Test
    public void should_fail_when_manager_boys_all_full(){
        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});

        Car previous_parkedA = new Car();
        Car previous_parkedB = new Car();
        Car previous_parkedC = new Car();
        carParkA.park(previous_parkedA);
        carParkB.park(previous_parkedB);
        carParkC.park(previous_parkedC);


//        when then

        Car testCar = new Car();
        assertThrows(ParkIsFullException.class,() -> {
            parkingManager.park(testCar);
        });
    }


    @Test
    public void should_pickup_correct_car_when_car_parked(){

        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();
        Car previous_parkedA = new Car();
        Car previous_parkedB = new Car();
        carParkA.park(previous_parkedA);
        carParkB.park(previous_parkedB);
        Ticket ticket =parkingManager.park(car);

//        when
        Car pickUpCar = parkingManager.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(car);


    }


    @Test
    void should_fail_when_depark_given_incorrect_ticket(){


        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();
        Car previous_parkedA = new Car();
        Car previous_parkedB = new Car();
        carParkA.park(previous_parkedA);
        carParkB.park(previous_parkedB);
        parkingManager.park(car);


//        when then
        assertThrows(TicketInvalidException.class,() -> {
            parkingManager.pickUp(new Ticket());
        });
    }


    @Test
    void should_fail_when_depark_given_used_ticket(){

        //        Given
        ParkLot carParkA = new ParkLot(1);
        ParkLot carParkB = new ParkLot(1);
        ParkLot carParkC = new ParkLot(1);

        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA});
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carParkB});
        ParkingManager parkingManager = new ParkingManager(new CarParkBoy[]{smartParkingBoy,graduateParkingBoy},new ParkLot[]{carParkC});
        Car car = new Car();
        Car previous_parkedA = new Car();
        Car previous_parkedB = new Car();
        carParkA.park(previous_parkedA);
        carParkB.park(previous_parkedB);
        Ticket ticket =parkingManager.park(car);
        parkingManager.pickUp(ticket);


//        when then
        assertThrows(TicketInvalidException.class,() -> {
            parkingManager.pickUp(ticket);
        });
    }
}
