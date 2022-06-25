package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GraduateParkingBoyTest {

    @Test
    public void should_park_in_first_and_get_ticket_when_all_park_empty(){
        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();

//        when
        Ticket ticket =graduateParkingBoy.park(car);
        boolean hasCarInFirst = carPark1.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInFirst).isTrue();

    }

    @Test
    public void should_park_in_second_and_get_ticket_when_first_is_full(){
        //        Given
        ParkLot carPark1 = new ParkLot(1);
        ParkLot carPark2 = new ParkLot(10);
        ParkLot carPark3 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2,carPark3});
        Car car = new Car();
        carPark1.park(car);

//        when
        Car testCar = new Car();
        Ticket ticket =graduateParkingBoy.park(testCar);
        boolean hasCarInSecond = carPark2.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInSecond).isTrue();

    }


    @Test
    public void should_fail_and_notice_full_when_all_park_full(){
        //        Given
        ParkLot carPark1 = new ParkLot(1);
        ParkLot carPark2 = new ParkLot(1);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        carPark1.park(car);
        Car car2 = new Car();
        carPark2.park(car2);



//        when then

        Car testCar = new Car();
        assertThrows(ParkIsFullException.class,() -> {
            graduateParkingBoy.park(testCar);
        });
    }


    @Test
    public void should_pickup_correct_car_when_car_in_first_park(){

        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        Ticket ticket =graduateParkingBoy.park(car);

//        when
        Car pickUpCar = graduateParkingBoy.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(car);


    }
    @Test
    public void should_pickup_correct_car_when_car_in_second_park(){

        //        Given
        ParkLot carPark1 = new ParkLot(1);
        ParkLot carPark2 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car1 = new Car();
        graduateParkingBoy.park(car1);
        Car testCar = new Car();
        Ticket ticket =graduateParkingBoy.park(testCar);

//        when
        Car pickUpCar = graduateParkingBoy.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(testCar);


    }


    @Test
    void should_fail_when_depark_given_incorrect_ticket(){

        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        graduateParkingBoy.park(car);


//        when then
        assertThrows(TicketInvalidException.class,() -> {
            graduateParkingBoy.pickUp(new Ticket());
        });

    }


    @Test
    void should_fail_when_depark_given_used_ticket(){
        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        GraduateParkingBoy graduateParkingBoy = new GraduateParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        Ticket ticket = graduateParkingBoy.park(car);
        graduateParkingBoy.pickUp(ticket);

//        when

        assertThrows(TicketInvalidException.class,() -> {
            graduateParkingBoy.pickUp(ticket);
        });
    }
}
