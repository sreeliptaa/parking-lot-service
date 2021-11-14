package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;

/**
 * Purpose : To Simulate the Parking Lot System
 *
 * @author SREELIPTA
 * @since 2021-11-9
 */

public class ParkingLotSystem {
    private final int actualCapacity;
    private List vehicle;
    // private int currentCapacity;
    private ArrayList<ParkingLotSystemObserver> parkingLotSystemObservers;

    public ParkingLotSystem(int capacity) {
        this.actualCapacity = capacity;
        initializeParkingLot();
        this.parkingLotSystemObservers = new ArrayList<ParkingLotSystemObserver>();
    }

    /**
     * Purpose : To print welcome message
     */
    public void printWelcomeMessage() {

        System.out.println("Welcome To The Parking Lot System");
    }

    /**
     * Purpose: Initialize The VehiclesList With Null Values
     */
    public void initializeParkingLot() {
        this.vehicle = new ArrayList();
        for (int i = 0; i < this.actualCapacity; i++) {
            vehicle.add(i, null);
        }
    }

    /**
     * Purpose : This method created to Park Given Vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void parkVehicle(Object vehicle, Integer slot) throws ParkingLotSystemException {
        if (this.vehicle.size() == actualCapacity && !this.vehicle.contains(null)) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.capacityIsFull();
            throw new ParkingLotSystemException("Parking Lot is Full");
        }
        if (this.vehicle.contains(vehicle)) {
            throw new ParkingLotSystemException("Vehicle already exist");
        }
        this.vehicle.set(slot, vehicle);
    }

    /**
     * Purpose : This method created to UnParked the Vehicle from parking lot
     *
     * @param vehicle given vehicle as parameter
     * @throws ParkingLotSystemException : when there is no vehicle to un park
     */
    public boolean unParkVehicle(Object vehicle) throws ParkingLotSystemException {
        if (vehicle == null) {
            throw new ParkingLotSystemException("Vehicle is not available");
        }
        if (this.vehicle.contains(vehicle)) {
            this.vehicle.remove(vehicle);
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingCapacityAvailable();
            return true;
        }
        throw new ParkingLotSystemException("No Such Vehicle Found");
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return Vehicle Equal to Given Vehicle
     */
    public boolean isVehicleParked(Object vehicle) {

        return this.vehicle.contains(vehicle);
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return The Vehicle is UnParked
     */
    public boolean isVehicleUnParked(Object vehicle) {
        if (this.vehicle.contains(vehicle)) {
            return false;
        }
        return true;
    }

    /**
     * Purpose : Register Observer as Like Owner and Security In List
     *
     * @param observer To add in the List
     */
    public void registerParkingLotSystemObserver(ParkingLotSystemObserver observer) {
        this.parkingLotSystemObservers.add(observer);
    }

    /**
     * Purpose: To Get List Of Empty Parking Slots
     *
     * @return List Of Slots
     */
    public List<Integer> getListOfEmptySlots() {
        List<Integer> emptyParkingSlots = new ArrayList<>();
        for (int i = 0; i < actualCapacity; i++) {
            if (this.vehicle.get(i) == null) {
                emptyParkingSlots.add(i);
            }
        }
        return emptyParkingSlots;
    }

    /**
     * Purpose : To find the vehicle in the parking lot
     *
     * @param vehicle : Take vehicle as a parameter
     * @return index of vehicle that we want to find
     */
    public int findVehicle(Object vehicle) {
        if (this.vehicle.contains(vehicle)) {
            return this.vehicle.indexOf(vehicle);
        }
        throw new ParkingLotSystemException("Vehicle Is Not Available");
    }
}
