package org.oobootcamp;

import java.util.UUID;

public class ParkCarManager {
    CarPark[] listOfCarPark;

    public ParkCarManager(CarPark[] listOfCarPark){
        this.listOfCarPark=listOfCarPark;

    }


    public String park(Car car){
        for (CarPark carpark : listOfCarPark){
            if(!carpark.isFull()){
                return carpark.park(car);
            }
        }
        throw new RuntimeException("车位已满");

    }

    public Car pickUp(String ticket){
        for (CarPark carpark : listOfCarPark){
            if(carpark.contain(ticket)){
                return carpark.pickUp(ticket);
            }
        }
        throw new RuntimeException("无效票");

    }
}
