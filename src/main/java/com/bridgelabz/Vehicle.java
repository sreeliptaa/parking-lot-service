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
    private final String vehicleParkingTime;
    private final String vehicleColor;

    public Vehicle(String name, String vehicleNumber, String vehicleParkingTime, String vehicleColor) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleParkingTime = vehicleParkingTime;
        this.vehicleColor = vehicleColor;
    }

    /**
     * Purpose: To Find the parking time of the Vehicle
     *
     * @return parking time of the vehicle
     */
    public String getParkingTime() {

        return vehicleParkingTime;
    }

    /**
     * Purpose: To get the color of the Vehicle
     *
     * @return the color of the vehicle
     */

    public String getVehicleColor() {
        return vehicleColor;
    }

}
