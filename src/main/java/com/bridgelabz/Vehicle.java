package com.bridgelabz;

/**
 * Purpose : To creating different properties of Vehicle
 *
 * @author SREELIPTA
 * @since 2021-11-15
 */

public class Vehicle {
    private final String name;
    private final String vehicleNumber;
    private final String vehicleColor;
    private final VehicleType vehicleType;
    private final PersonType personType;

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    enum PersonType {
        NORMAL, HANDICAP;
    }

    public PersonType getPersonType() {
        return personType;
    }

    enum VehicleType {
        SMALL, MEDIUM;
    }

    public String getName() {
        return name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public Vehicle(VehicleType type, PersonType personType, String name, String vehicleNumber, String vehicleColor) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
        this.vehicleType = type;
        this.personType = personType;
    }
}