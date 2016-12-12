package com.noname.wowslibrary;

/**
 * Created by Instant on 11.12.2016.
 */

public class Ship {
    private String description;
    private String name;
    private String nation;
    private int ship_id;
    private String type;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public int getShip_id() {
        return ship_id;
    }

    public void setShip_id(int ship_id) {
        this.ship_id = ship_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getFullName(Ship s){
        return s.getName()+" ("+s.getType()+")";
    }
}
