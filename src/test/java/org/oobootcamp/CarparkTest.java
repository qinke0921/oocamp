package org.oobootcamp;

import org.junit.jupiter.api.Test;

import javax.print.attribute.standard.ReferenceUriSchemesSupported;

import static org.assertj.core.api.Assertions.assertThat;

public class CarparkTest {


    @Test
    void should_success_when_park_given_have_slot(){
//        Given
        CarPark carPark = new CarPark(10);
        Car car = new Car();

//        when
        String result =carPark.park(car);

//        then
        assertThat(result).isNotNull();


    }

    @Test
    void should_fail_when_park_given_no_slot(){
//        Given
        CarPark carPark = new CarPark(1);
        Car car = new Car();
        carPark.park(car);

//        when
        RuntimeException runtimeException = null;
        Car carForTest = new Car();
        try {
            String result = carPark.park(carForTest);
        } catch (RuntimeException e){
            runtimeException=e;
        }

//        then
        assertThat(runtimeException).isNotNull();
        assertThat(runtimeException.getMessage()).isEqualTo("车位已满");
    }

    @Test
    void should_success_when_depark_given_correct_ticket(){
        //        Given
        CarPark carPark = new CarPark(1);
        Car parkCar = new Car();
        String ticket = carPark.park(parkCar);

//        when
        Car pickUpCar =carPark.pickUp(ticket);

//        then
        assertThat(pickUpCar).isEqualTo(parkCar);
    }





    @Test
    void should_fail_when_depark_given_incorrect_ticket(){
        //        Given
        CarPark carPark = new CarPark(1);
        Car parkCar = new Car();
        carPark.park(parkCar);

//        then
        RuntimeException runtimeException = null;
        try {
            carPark.pickUp("test");
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
        CarPark carPark = new CarPark(1);
        Car parkCar = new Car();
        String ticket =carPark.park(parkCar);
        carPark.pickUp(ticket);

//        then
        RuntimeException runtimeException = null;
        try {
            carPark.pickUp(ticket);
        } catch (RuntimeException e){
            runtimeException=e;
        }

//        then
        assertThat(runtimeException).isNotNull();
        assertThat(runtimeException.getMessage()).isEqualTo("无效票");
    }


}
