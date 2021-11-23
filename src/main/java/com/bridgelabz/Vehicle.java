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

    public Vehicle(String name, String vehicleNumber, String vehicleColor) {
        this.name = name;
        this.vehicleNumber = vehicleNumber;
        this.vehicleColor = vehicleColor;
    }

    /**
     * Purpose: To get the color of the Vehicle
     *
     * @return the color of the vehicle
     */

    public String getVehicleColor() {

        return vehicleColor;
    }

    /**
     * Purpose: To get the number of the Vehicle
     *
     * @return the number of the vehicle
     */
    public String getVehicleNumber() {

        return vehicleNumber;
    }

    /**
     * Purpose: To get the name of the Vehicle
     *
     * @return the name of the vehicle
     */
    public String getName() {

        return name;
    }

}
