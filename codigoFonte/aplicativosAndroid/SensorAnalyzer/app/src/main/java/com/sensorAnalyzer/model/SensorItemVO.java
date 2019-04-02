package com.sensorAnalyzer.model;

/**
 * Created by leonardo on 11/05/15.
 */
public class SensorItemVO {
    private String name, manufacturer;
    private int type;

    public SensorItemVO(String name, int type, String manufacturer) {
        this.name = name;
        this.type = type;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return this.name;
    }

    public int getType() {
        return this.type;
    }

    public String getManufacturer() {
        return this.manufacturer;
    }
}
