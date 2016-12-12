package com.noname.wowslibrary;

public class StatsContainer {
    private int battles;
    private int miles;
    private String name;

    public StatsContainer() {
    }


    public StatsContainer(int battles, int miles, String name) {
        this.battles = battles;
        this.miles = miles;
        this.name = name;
    }

    public int getBattles() {
        return battles;
    }

    public void setBattles(int battles) {
        this.battles = battles;
    }

    public int getMiles() {
        return miles;
    }

    public void setMiles(int miles) {
        this.miles = miles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getString(StatsContainer sc){
        return (sc.getName()+";"+sc.getBattles()+";"+sc.getMiles());
    }
}

