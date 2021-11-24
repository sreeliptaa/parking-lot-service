package com.bridgelabz;

/**
 * Purpose:To park the vehicles by attendant whenever required
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */

public class ParkingLotSystemAttendant {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is created to park vehicle by attendant.
     *
     * @param vehicle - the vehicle to be parked.
     */
    public void parkVehicleByAttendant(Vehicle vehicle) {
        parkingLotSystem.parkVehicle(vehicle);
    }
}