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
     * Purpose : This method created to Park Given Vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter
     * @return True For Vehicle Parked
     */
    public void parkVehicle(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle != null)
            throw new ParkingLotSystemException("Parking Lot is Full");
        this.vehicle = vehicle;
        // return true;
    }

    /**
     * Purpose : This method created to UnParked the Vehicle from parking lot
     *
     * @param vehicle given vehicle as parameter
     * @return Vehicle UnParked or Not
     */
    public boolean unParkVehicle(Object vehicle) {
        if (this.vehicle == null) return false;
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return Vehicle Equal to Given Vehicle
     */
    public boolean isVehicleParked(Object vehicle) {
        return this.vehicle.equals(vehicle);
    }
}
