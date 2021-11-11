package com.bridgelabz;

/**
 * Purpose : To Simulate the Parking Lot System
 *
 * @author SREELIPTA
 * @since 2021-11-9
 */

public class ParkingLotSystem {
    private Object vehicle;
    private ParkingLotSystemOwner owner;

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
     * @throws ParkingLotSystemException : when the parking lot is full
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
     * @throws ParkingLotSystemException : when there is no vehicle to unpark
     */
    public void unParkVehicle(Object vehicle) throws ParkingLotSystemException {
        if (this.vehicle == null) throw new ParkingLotSystemException("No Such Vehicle Found");
        if (this.vehicle.equals(vehicle)) {
            this.vehicle = null;
        }
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

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return The Vehicle is UnParked
     */
    public boolean isVehicleUnParked(Object vehicle) {

        return this.vehicle == null;
    }

    /**
     * Purpose : This method created for register Parking Lot Owner
     *
     * @param owner given Parameter as Owner
     */
    public void registerOwner(ParkingLotSystemOwner owner) {
        this.owner = owner;
    }
}
