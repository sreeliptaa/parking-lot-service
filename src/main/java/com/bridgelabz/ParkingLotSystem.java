package com.bridgelabz;

import java.util.ArrayList;

/**
 * Purpose : To Simulate the Parking Lot System
 *
 * @author SREELIPTA
 * @since 2021-11-9
 */

public class ParkingLotSystem {
    private final int actualCapacity;
    private Object vehicle;
    private int currentCapacity;
    private final ArrayList<ParkingLotSystemObserver> parkingLotSystemObservers;

    public ParkingLotSystem(int capacity) {
        this.currentCapacity = 0;
        this.actualCapacity = capacity;
        this.parkingLotSystemObservers = new ArrayList<>();
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
        if (this.currentCapacity == this.actualCapacity) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.capacityIsFull();
            throw new ParkingLotSystemException("Parking Lot is Full");
        }
        this.currentCapacity++;
        this.vehicle = vehicle;
    }

    /**
     * Purpose : This method created to UnParked the Vehicle from parking lot
     *
     * @param vehicle given vehicle as parameter
     * @throws ParkingLotSystemException : when there is no vehicle to un park
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
     * Purpose : Register Observer as Like Owner and Security In List
     *
     * @param observer To Add in the List
     */
    public void registerParkingLotSystemObserver(ParkingLotSystemObserver observer) {
        this.parkingLotSystemObservers.add(observer);
    }
}
