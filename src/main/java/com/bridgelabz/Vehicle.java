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

    public Vehicle(String name, String vehicleNumber, String vehicleParkingTime) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleParkingTime = vehicleParkingTime;
    }

    /**
     * Purpose: To Find the parking time of the Vehicle
     *
     * @return parking time of the vehicle
     */
    public String getParkingTime() {

        return vehicleParkingTime;
    }
}
