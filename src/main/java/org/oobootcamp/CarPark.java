package org.oobootcamp;
import java.util.HashMap;
import java.util.UUID;
public class CarPark {

    private int  volume;
    private HashMap<String,Car> stringCarHashMap;

    public  CarPark(int volume){
        this.volume = volume;
        this.stringCarHashMap = new HashMap<String,Car>();
    }

    public String park(Car car){
        if (this.isFull()){
            throw new RuntimeException("车位已满");
        }else{
            UUID uuid = UUID.randomUUID();
            String ticket =  uuid.toString();
            this.stringCarHashMap.put(ticket,car);
            return ticket;
        }

    }

    public Car pickUp(String ticket){
            if(this.contain(ticket)) {
                Car car =stringCarHashMap.get(ticket);
                this.stringCarHashMap.remove(ticket);
                return car;
            }
            else{
                throw new RuntimeException("无效票");
            }


    }

    public boolean contain(String ticket){
        return this.stringCarHashMap.containsKey(ticket);
    }

    public boolean isFull(){
        return this.stringCarHashMap.size()==this.volume;
    }
}
