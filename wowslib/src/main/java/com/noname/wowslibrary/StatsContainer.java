package com.noname.wowslibrary;

import java.sql.Date;

public class StatsContainer {
    private int battles;
    private int miles;
    private String name;
    private Date created_at;
    private Date last_battle_time;
    private int leveling_tier;
    private int leveling_points;
    private int battle_life_time;
    private int credits;
    private int free_xp;
    private int gold;

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

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getLast_battle_time() {
        return last_battle_time;
    }

    public void setLast_battle_time(Date last_battle_time) {
        this.last_battle_time = last_battle_time;
    }

    public int getLeveling_tier() {
        return leveling_tier;
    }

    public void setLeveling_tier(int leveling_tier) {
        this.leveling_tier = leveling_tier;
    }

    public int getLeveling_points() {
        return leveling_points;
    }

    public void setLeveling_points(int leveling_points) {
        this.leveling_points = leveling_points;
    }

    public int getBattle_life_time() {
        return battle_life_time;
    }

    public void setBattle_life_time(int battle_life_time) {
        this.battle_life_time = battle_life_time;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getFree_xp() {
        return free_xp;
    }

    public void setFree_xp(int free_xp) {
        this.free_xp = free_xp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}

