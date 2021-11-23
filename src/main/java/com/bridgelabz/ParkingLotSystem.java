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
    private static List<ParkingLotSystemObserver> observers;
    private static int actualCapacity;
    private static List<Vehicle> parkingLot1;
    private static List<Vehicle> parkingLot2;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
    }

    /**
     * Purpose : To print welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome To The Parking Lot System");
    }

    /**
     * This method is used to register the observers of parking lot.
     *
     * @param observer - The observer of the parking lot.
     */
    public void registerParkingLotSystemObserver(ParkingLotSystemObserver observer) {
        observers.add(observer);
    }

    /**
     * Purpose: This method is used to make a vehicle park in the Parking Lot.
     *
     * @param vehicle object to be parked
     * @throws ParkingLotSystemException if the vehicle is already parked or if the lot
     *                                   is full.
     */
    public void parkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle))
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_EXIST, "Vehicle is already parked");
        if (this.parkingLot1.size() <= this.actualCapacity && this.parkingLot2.size() <= this.actualCapacity) {
            if (parkingLot1.size() > parkingLot2.size()) {
                this.parkingLot2.add(vehicle);
            } else {
                this.parkingLot1.add(vehicle);
            }
        }
        if (this.parkingLot1.size() == this.actualCapacity && this.parkingLot2.size() == this.actualCapacity) {
            for (ParkingLotSystemObserver observers : observers) {
                observers.capacityIsFull();
            }
            throw new ParkingLotSystemException
                    (ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL, "The Parking Lot is Full.");
        }
    }


    /**
     * Purpose: This method is used to make a vehicle un-park from the Parking Lot.
     *
     * @param vehicle - object to be un-parked
     * @return boolean - True if the vehicle is un-parked
     * else false
     * @throws ParkingLotSystemException if there is a null or nothing to be un-parked.
     */
    public boolean unParkVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle)) {
                this.parkingLot1.remove(vehicle);
                for (ParkingLotSystemObserver observer : observers) {
                    observer.parkingCapacityAvailable();
                }
                return true;
            }
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle)) {
                this.parkingLot2.remove(vehicle);
                for (ParkingLotSystemObserver observer : observers) {
                    observer.parkingCapacityAvailable();
                }
                return true;
            }
        }
        throw new ParkingLotSystemException
                (ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "There is no such vehicle");
    }


    /**
     * This method is used to check if the vehicle is parked or not.
     *
     * @param vehicle object to be checked if parked or not.
     * @return boolean true if vehicle is parked or else false.
     */
    public boolean isVehicleParked(Vehicle vehicle) {
        boolean isParked = false;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isParked = true;
        }
        return isParked;
    }

    /**
     * This method is used to check if the vehicle is un-parked or not.
     *
     * @param vehicle object to be checked if un-parked or not.
     * @return boolean true if vehicle is un-parked or else false.
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {
        boolean isUnParked = true;
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        return isUnParked;
    }

    /**
     * This method is used to find the vehicle in the parking lot.
     *
     * @param vehicle object to find.
     * @return object of vehicle if present.
     * @throws ParkingLotSystemException if there is no such vehicle as passed in
     *                                   the parameter in the parking lot
     */
    public Vehicle findVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        for (Vehicle vehicle1 : parkingLot1) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        for (Vehicle vehicle1 : parkingLot2) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");

    }

    /**
     * This method is used to set the capacity of the parking lot.
     *
     * @param capacity - size of the parking lot.
     */
    public void capacityOfParkingLot(int capacity) {
        actualCapacity = capacity;
    }
}
