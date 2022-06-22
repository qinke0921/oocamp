package org.oobootcamp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParkCarManagerTest {

    @Test
    public void should_park_in_first_and_get_ticket_when_all_park_empty(){
        //        Given
        CarPark carPark1 = new CarPark(10);
        CarPark carPark2 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car = new Car();

//        when
        String ticket =parkCarManager.park(car);
        boolean hasCarInFirst = carPark1.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInFirst).isTrue();

    }

    @Test
    public void should_park_in_second_and_get_ticket_when_first_is_full(){
        //        Given
        CarPark carPark1 = new CarPark(1);
        CarPark carPark2 = new CarPark(10);
        CarPark carPark3 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2,carPark3});
        Car car = new Car();
        carPark1.park(car);

//        when
        Car testCar = new Car();
        String ticket =parkCarManager.park(testCar);
        boolean hasCarInSecond = carPark2.contain(ticket);

//        then
        assertThat(ticket).isNotNull();
        assertThat(hasCarInSecond).isTrue();

    }


    @Test
    public void should_fail_and_notice_full_when_all_park_full(){
        //        Given
        CarPark carPark1 = new CarPark(1);
        CarPark carPark2 = new CarPark(1);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car = new Car();
        carPark1.park(car);
        Car car2 = new Car();
        carPark2.park(car2);

//        when
        Car testCar = new Car();
        RuntimeException runtimeException = null;
        try {
            parkCarManager.park(testCar);
        } catch (RuntimeException e){
            runtimeException=e;
        }

//        then
        assertThat(runtimeException).isNotNull();
        assertThat(runtimeException.getMessage()).isEqualTo("车位已满");

    }


    @Test
    public void should_pickup_correct_car_when_car_in_first_park(){

        //        Given
        CarPark carPark1 = new CarPark(10);
        CarPark carPark2 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car = new Car();
        String ticket =parkCarManager.park(car);

//        when
        Car pickUpCar = parkCarManager.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(car);


    }
    @Test
    public void should_pickup_correct_car_when_car_in_second_park(){

        //        Given
        CarPark carPark1 = new CarPark(1);
        CarPark carPark2 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car1 = new Car();
        parkCarManager.park(car1);
        Car testCar = new Car();
        String ticket =parkCarManager.park(testCar);

//        when
        Car pickUpCar = parkCarManager.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(testCar);


    }


    @Test
    void should_fail_when_depark_given_incorrect_ticket(){

        //        Given
        CarPark carPark1 = new CarPark(10);
        CarPark carPark2 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car = new Car();
        parkCarManager.park(car);

//        when
        RuntimeException runtimeException = null;
        try {
            parkCarManager.pickUp("test");
        } catch (RuntimeException e){
            runtimeException=e;
        }

//        then
        assertThat(runtimeException).isNotNull();
        assertThat(runtimeException.getMessage()).isEqualTo("无效票");
    }


    @Test
    void should_fail_when_depark_given_used_ticket(){
        //        Given
        CarPark carPark1 = new CarPark(10);
        CarPark carPark2 = new CarPark(10);
        ParkCarManager parkCarManager = new ParkCarManager(new CarPark[]{carPark1, carPark2});
        Car car = new Car();
        String ticket =parkCarManager.park(car);
        parkCarManager.pickUp(ticket);

//        when
        RuntimeException runtimeException = null;
        try {
            parkCarManager.pickUp(ticket);
        } catch (RuntimeException e){
            runtimeException=e;
        }

//        then
        assertThat(runtimeException).isNotNull();
        assertThat(runtimeException.getMessage()).isEqualTo("无效票");

    }
}
