package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartParkingBoyTest {


//
//    Given <两个有空位的停车场，A空位两个，B空位一个>, When <停车>, Then <停车到A停车场，拿到票>
//            -  Given <两个有空位的停车场，A空位一个，B空位两个>, When <停车>, Then <停车到B停车场，拿到票>

    @Test
    public void test_should_park_in_A_when_A_available_slot_is_most(){
        //        Given
        ParkLot carParkA = new ParkLot(20);
        ParkLot carParkB = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA, carParkB});
        Car car = new Car();

//        when
        Ticket ticket =smartParkingBoy.park(car);
        boolean hasCarInA = carParkA.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInA).isTrue();
    }


    @Test
    public void test_should_park_in_B_when_B_available_slot_is_most(){
        //        Given
        ParkLot carParkA = new ParkLot(10);
        ParkLot carParkB = new ParkLot(20);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA, carParkB});
        Car car = new Car();

//        when
        Ticket ticket =smartParkingBoy.park(car);
        boolean hasCarInB = carParkB.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInB).isTrue();
    }


//    -  Given <两个有空位的停车场，A空位两个，B空位两个>, When <停车>, Then <停车到A停车场，拿到票>?

    @Test
    public void test_should_park_a_when_most_available_slot_park_have_multiple(){
        //        Given
        ParkLot carParkA = new ParkLot(20);
        ParkLot carParkB = new ParkLot(20);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carParkA, carParkB});
        Car car = new Car();

//        when
        Ticket ticket =smartParkingBoy.park(car);
        boolean hasCarInA = carParkA.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInA).isTrue();

    }



    @Test
    public void should_pickup_correct_car_when_car_in_first_park(){

        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        Ticket ticket =smartParkingBoy.park(car);

//        when
        Car pickUpCar = smartParkingBoy.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(car);


    }
    @Test
    public void should_pickup_correct_car_when_car_in_second_park(){

        //        Given
        ParkLot carPark1 = new ParkLot(1);
        ParkLot carPark2 = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car1 = new Car();
        smartParkingBoy.park(car1);
        Car testCar = new Car();
        Ticket ticket =smartParkingBoy.park(testCar);

//        when
        Car pickUpCar = smartParkingBoy.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(testCar);


    }


    @Test
    void should_fail_when_depark_given_incorrect_ticket(){

        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        smartParkingBoy.park(car);

//        when then
        assertThrows(TicketInvalidException.class,() -> {
            smartParkingBoy.pickUp(new Ticket());
        });
    }


    @Test
    void should_fail_when_depark_given_used_ticket(){
        //        Given
        ParkLot carPark1 = new ParkLot(10);
        ParkLot carPark2 = new ParkLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(new ParkLot[]{carPark1, carPark2});
        Car car = new Car();
        Ticket ticket =smartParkingBoy.park(car);
        smartParkingBoy.pickUp(ticket);

//        when then
        assertThrows(TicketInvalidException.class,() -> {
            smartParkingBoy.pickUp(ticket);
        });
    }
}
