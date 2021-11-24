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
    private static List<Vehicle> parkingLotForHandicappedDriver;

    public ParkingLotSystem() {
        observers = new ArrayList<>();
        parkingLot1 = new ArrayList<>();
        parkingLot2 = new ArrayList<>();
        parkingLotForHandicappedDriver = new ArrayList<>();
    }

    /**
     * Purpose : To print welcome message
     */
    public void printWelcomeMessage() {
        System.out.println("Welcome To The Parking Lot System");
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
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_EXIST,
                    "Vehicle is already parked");
        if (parkingLot1.size() <= actualCapacity && parkingLot2.size() <= actualCapacity
                && parkingLotForHandicappedDriver.size() <= actualCapacity) {
            if (vehicle.getPersonType() == Vehicle.PersonType.HANDICAP) {
                parkingLotForHandicappedDriver.add(vehicle);
            } else if (parkingLot1.size() > parkingLot2.size()) {
                parkingLot2.add(vehicle);
            } else {
                parkingLot1.add(vehicle);
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
     * @return boolean - True if the vehicle is un-parked else false
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
        for (Vehicle vehicle1 : parkingLotForHandicappedDriver) {
            if (vehicle1.equals(vehicle)) {
                parkingLotForHandicappedDriver.remove(vehicle);
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
        for (Vehicle vehicle1 : parkingLotForHandicappedDriver) {
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
        for (Vehicle vehicle1 : parkingLotForHandicappedDriver) {
            if (vehicle1.equals(vehicle))
                isUnParked = false;
        }
        return isUnParked;
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
     * This method is used to set the capacity of the parking lot.
     *
     * @param capacity - size of the parking lot.
     */
    public void capacityOfParkingLot(int capacity) {
        actualCapacity = capacity;
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
        for (Vehicle vehicle1 : parkingLotForHandicappedDriver) {
            if (vehicle1.equals(vehicle))
                return vehicle1;
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE, "No Such Vehicle Found");

    }

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotSystemException when there are no white vehicles present in the parking lots.
     */
    public List getWhiteColorVehiclePosition() throws ParkingLotSystemException {
        ArrayList position = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getVehicleColor().equals("White"))
                position.add("ParkingLot1: " + parkingLot1.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getVehicleColor().equals("White"))
                position.add("ParkingLot2: " + parkingLot2.indexOf(vehicle));
        }
        for (Vehicle vehicle : parkingLotForHandicappedDriver) {
            if (vehicle.getVehicleColor().equals("White"))
                position.add("HandicapParkingLot: " + parkingLot2.indexOf(vehicle));
        }
        if (position.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                    "No White Color Vehicle Found");
        return position;
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotSystemException when there are no Blue Toyota vehicles present in the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotSystemException {
        ArrayList vehicles = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("Toyota") && vehicle.getVehicleColor().equals("Blue")) {
                vehicles.add(" Plate Number = " + vehicle.getVehicleNumber() + " Location = ParkingLot 1: " +
                        parkingLot1.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("Toyota") && vehicle.getVehicleColor().equals("Blue")) {
                vehicles.add(" Plate Number = " + vehicle.getVehicleNumber() + " Location = ParkingLot 2: " +
                        parkingLot2.indexOf(vehicle));
            }
        }
        for (Vehicle vehicle : parkingLotForHandicappedDriver) {
            if (vehicle.getName().equals("Toyota") && vehicle.getVehicleColor().equals("Blue")) {
                vehicles.add(" Plate Number = " +
                        vehicle.getVehicleNumber() + " Location = HandicapLot : " +
                        parkingLotForHandicappedDriver.indexOf(vehicle));
            }
        }
        if (vehicles.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                    "No Blue Toyota Vehicle Found");
        return vehicles;
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotSystemException when there are no BMW vehicles present in the parking lots.
     */
    public List getBMWVehicles() throws ParkingLotSystemException {
        ArrayList vehicles = new ArrayList();
        for (Vehicle vehicle : parkingLot1) {
            if (vehicle.getName().equals("BMW")) {
                vehicles.add(vehicle);
            }
        }
        for (Vehicle vehicle : parkingLot2) {
            if (vehicle.getName().equals("BMW")) {
                vehicles.add(vehicle);
            }
        }
        for (Vehicle vehicle : parkingLotForHandicappedDriver) {
            if (vehicle.getName().equals("BMW")) {
                vehicles.add(vehicle);
            }
        }
        if (vehicles.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                    "No BMW Vehicles Found");
        return vehicles;
    }

    public List getHandicappedVehicles() {
        ArrayList vehicles = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicappedDriver) {
            if (vehicle.getVehicleType() == Vehicle.VehicleType.SMALL)
                vehicles.add(vehicle);
        }
        if (vehicles.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                    "No Vehicles Found");
        return vehicles;
    }

    public List getAllVehicles() {
        ArrayList vehicles = new ArrayList();
        for (Vehicle vehicle : parkingLotForHandicappedDriver) {
            vehicles.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot1) {
            vehicles.add(vehicle);
        }
        for (Vehicle vehicle : parkingLot2) {
            vehicles.add(vehicle);
        }
        if (vehicles.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_IS_NOT_AVAILABLE,
                    "No Vehicles Present");
        return vehicles;
    }
}
