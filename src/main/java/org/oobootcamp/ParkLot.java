package org.oobootcamp;
import java.util.HashMap;
import java.util.UUID;
public class ParkLot {

    private int  volume;
    private HashMap<Ticket,Car> ticketsCarHashMap;

    public ParkLot(int volume){
        this.volume = volume;
        this.ticketsCarHashMap = new HashMap<Ticket,Car>();
    }

    public int getAvailableSlotNum(){
        return volume- ticketsCarHashMap.size();
    }
    public Ticket park(Car car){
        if (this.isFull()){
            throw new ParkIsFullException();
        }else{
            Ticket ticket = new Ticket();
            this.ticketsCarHashMap.put(ticket,car);
            return ticket;
        }

    }

    public Car pickUp(Ticket ticket){
            if(this.contain(ticket)) {
                Car car = ticketsCarHashMap.get(ticket);
                this.ticketsCarHashMap.remove(ticket);
                return car;
            }
            else{
                throw new TicketInvalidException();
            }


    }

    public boolean contain(Ticket ticket){
        return this.ticketsCarHashMap.containsKey(ticket);
    }

    public boolean isFull(){
        return this.ticketsCarHashMap.size()==this.volume;
    }
}
