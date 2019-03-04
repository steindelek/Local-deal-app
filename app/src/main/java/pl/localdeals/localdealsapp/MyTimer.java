package pl.localdeals.localdealsapp;

import java.util.ArrayList;
import java.util.Map;

public class MyTimer {
    private final ArrayList<String> days = new ArrayList<>();
    public int today;
    public int hour;
    public int minutes;


    public MyTimer(int today, int hour, int minutes) {
        this.today = dayToAtheism(today);
        this.hour = hour;
        this.minutes = minutes;
        this.days.add("Zerowek");
        this.days.add("Poniedziałek");
        this.days.add("Wtorek");
        this.days.add("Środa");
        this.days.add("Czwartek");
        this.days.add("Piątek");
        this.days.add("Sobota");
        this.days.add("Niedziala");
    }

    public ArrayList<String> getDays() {
        return days;
    }

    private int dayToAtheism(int day){
        if(day == 1){
            day = 8;
        }
        return day - 1;
    }

    public ArrayList pickDealsByDay(ArrayList<Deal> deals, int picked_day){
        int all_deals = deals.size();
        int i = 0;

        while (i < all_deals){
            if (deals.get(i).get_day() != picked_day){
                deals.remove(i);
                all_deals -= 1;
            }else {
                i += 1;
            }
        }
        return deals;
    }

    public ArrayList pickDealsByType(ArrayList<Deal> deals, int picked_type){
        int all_deals = deals.size();
        int i = 0;

        while (i < all_deals){
            if (deals.get(i).get_type() != picked_type){
                deals.remove(i);
                all_deals -= 1;
            }else {
                i += 1;
            }
        }
        return deals;
    }
}


