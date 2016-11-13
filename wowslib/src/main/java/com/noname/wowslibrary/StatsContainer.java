package com.noname.wowslibrary;

/**
 * Created by Void on 05.03.2015.
 */
public class StatsContainer {
    private int battles;
    private int miles;

    public StatsContainer() {
    }


    public StatsContainer(int battles, int miles) {
        this.battles = battles;
        this.miles = miles;
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
}

