package com.bridgelabz;

/**
 * Purpose : To Simulate the Parking Lot System
 *
 * @author SREELIPTA
 * @since 2021-11-9
 */

public class ParkingLotSystem {
    private Object vehicle;

    public ParkingLotSystem() {
    }

    /**
     * Purpose : To print welcome message
     */
    public void printWelcomeMessage() {

        System.out.println("Welcome To The Parking Lot System");
    }

    /**
     * Purpose : To Park Given Vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter
     * @return True For Vehicle Parked
     */
    public boolean parkVehicle(Object vehicle) {
        if (this.vehicle != null)
            return false;
        this.vehicle = vehicle;
        return true;
    }
}
