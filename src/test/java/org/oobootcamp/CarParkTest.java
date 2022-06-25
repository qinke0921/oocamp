package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarParkTest {


    @Test
    void should_success_when_park_given_have_slot() {
//        Given
        ParkLot carPark = new ParkLot(10);
        Car car = new Car();

//        when
        Ticket result = carPark.park(car);

//        then
        assertThat(result).isNotNull();


    }

    @Test
    void should_fail_when_park_given_no_slot() {
//        Given
        ParkLot carPark = new ParkLot(1);
        Car car = new Car();
        carPark.park(car);

//        when

        assertThrows(ParkIsFullException.class, () -> {
            carPark.park(new Car());
        });
    }

    @Test
    void should_success_when_depark_given_correct_ticket() {
        //        Given
        ParkLot carPark = new ParkLot(1);
        Car parkCar = new Car();
        Ticket ticket = carPark.park(parkCar);

//        when
        Car pickUpCar = carPark.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(parkCar);
    }


    @Test
    void should_fail_when_depark_given_incorrect_ticket() {
        //        Given
        ParkLot carPark = new ParkLot(1);
        Car parkCar = new Car();
        carPark.park(parkCar);

//        then
        assertThrows(TicketInvalidException.class, () -> {
            carPark.pickUp(new Ticket());
        });
    }


    @Test
    void should_fail_when_depark_given_used_ticket() {
        //        Given
        ParkLot carPark = new ParkLot(1);
        Car parkCar = new Car();
        Ticket ticket = carPark.park(parkCar);
        carPark.pickUp(ticket);

//        then

        assertThrows(TicketInvalidException.class, () -> {
            carPark.pickUp(ticket);
        });
    }


    @Test
    void  should_return_true_when_car_is_parked(){
        //        Given
        ParkLot parkLot = new ParkLot(1);
        Car parkCar = new Car();
        Ticket ticket = parkLot.park(parkCar);
//        when
        boolean containFlag = parkLot.contain(ticket);

//        then
        assertThat(containFlag).isTrue();
    }
}
