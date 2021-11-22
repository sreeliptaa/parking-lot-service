package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Purpose : To Simulate the Parking Lot System
 *
 * @author SREELIPTA
 * @since 2021-11-9
 */

public class ParkingLotSystem {
    private static boolean isDriverHandicapped;
    private final int actualCapacity;
    private ArrayList<Vehicle> vehicleList;
    private final ArrayList<ParkingLotSystemObserver> parkingLotSystemObservers;

    public ParkingLotSystem(int actualCapacity) {
        this.actualCapacity = actualCapacity;
        initialiseParkingLot();
        this.parkingLotSystemObservers = new ArrayList<>();
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
    public void initialiseParkingLot() {
        this.vehicleList = new ArrayList();
        for (int i = 0; i < this.actualCapacity; i++) {
            vehicleList.add(i, null);
        }
    }

    /**
     * Purpose : This method created to Park Given Vehicle in Parking Lot
     *
     * @param vehicle given vehicle as parameter
     * @throws ParkingLotSystemException : when the parking lot is full
     */
    public void parkVehicle(Vehicle vehicle, Integer slot) throws ParkingLotSystemException {
        if (this.vehicleList.size() == actualCapacity && !this.vehicleList.contains(null)) {
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.capacityIsFull();
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.PARKING_LOT_IS_FULL,
                    "Parking Lot is Full");
        }
        if (this.vehicleList.contains(vehicle)) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_ALREADY_EXIST,
                    "Vehicle already exist");
        }
        this.vehicleList.set(slot, vehicle);
    }

    /**
     * Purpose : To Check Vehicle is Parked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return Vehicle Equal to Given Vehicle
     */
    public boolean isVehicleParked(Vehicle vehicle) {

        return this.vehicleList.contains(vehicle);
    }

    /**
     * Purpose : This method created to UnParked the Vehicle from parking lot
     *
     * @param vehicle given vehicle as parameter
     * @throws ParkingLotSystemException : when there is no vehicle to un park
     */
    public boolean unParkVehicle(Object vehicle) throws ParkingLotSystemException {
        if (vehicle == null) {
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_IS_NOT_AVAILABLE,
                    "Vehicle is not available");
        }
        if (this.vehicleList.contains(vehicle)) {
            this.vehicleList.remove(vehicle);
            for (ParkingLotSystemObserver parkingLotSystemObserver : parkingLotSystemObservers)
                parkingLotSystemObserver.parkingCapacityAvailable();
            return true;
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No Such Vehicle Found");
    }

    /**
     * Purpose : To Check a Vehicle is UnParked Or Not
     *
     * @param vehicle given Vehicle as parameter
     * @return The Vehicle is UnParked
     */
    public boolean isVehicleUnParked(Vehicle vehicle) {

        return !this.vehicleList.contains(vehicle);
    }

    /**
     * Purpose : Register Observer as Like Owner and Airport Security In List
     *
     * @param observer To add in the List
     */
    public void registerParkingLotSystemObserver(ParkingLotSystemObserver observer) {
        this.parkingLotSystemObservers.add(observer);
    }

    /**
     * Purpose: To Get List Of Empty Parking Slots
     *
     * @return : List Of empty Slots
     */
    public List<Integer> getListOfEmptySlots() {
        List<Integer> emptyParkingSlots = new ArrayList<>();
        for (int i = 0; i < actualCapacity; i++) {
            if (this.vehicleList.get(i) == null) {
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
    public int findVehicle(Vehicle vehicle) {
        if (this.vehicleList.contains(vehicle)) {
            return this.vehicleList.indexOf(vehicle);
        }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.VEHICLE_IS_NOT_AVAILABLE,
                "Vehicle Is Not Available");
    }

    /**
     * Purpose: To Find the Time when Vehicle Parked
     *
     * @param vehicle is passed as parameter
     * @return parking time of the vehicle
     */
    public String getVehicleParkingTime(Vehicle vehicle) {
        if (isVehicleParked(vehicle)) {
            return vehicle.getParkingTime();
        }
        return null;
    }

    /**
     * Purpose: To Find the position of the white color Vehicle Parked
     *
     * @param vehicle is passed as parameter to find the position
     * @return the position of the white color vehicle
     * @throws ParkingLotSystemException : when there is no vehicle found
     */
    public int getPositionOfWhiteColorVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle) && vehicle.getVehicleColor().equals("White"))
            for (Vehicle position : vehicleList) {
                if (position.equals(vehicle))
                    return vehicleList.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No white color vehicle found");
    }

    /**
     * Purpose: To Find the position of the blue color Vehicle Parked
     *
     * @param vehicle is passed as parameter to find the position
     * @return the position of the blue color vehicle
     * @throws ParkingLotSystemException : when there is no vehicle found
     */
    public int getPositionOfBlueColorVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("Toyota"))
            for (Vehicle position : vehicleList) {
                if (position.equals(vehicle))
                    return vehicleList.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    /**
     * Purpose: To Find the number plate of the blue color Vehicle Parked
     *
     * @param vehicle is passed as parameter to find the number plate of the vehicle
     * @return the position of the number plate of the vehicle
     * @throws ParkingLotSystemException : when there is no vehicle found
     */
    public String getNumberPlateOfBlueColorVehicle(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getVehicleColor().equals("Blue")
                && vehicle.getName().equals("Toyota"))
            for (Vehicle numberPlate : vehicleList) {
                if (numberPlate.equals(vehicle))
                    return numberPlate.getVehicleNumber();
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    /**
     * Purpose: To Find the all BMW Vehicle Parked in the parking lot system
     *
     * @param vehicle is passed as parameter to find the all BMW vehicle
     * @return the all BMW vehicles
     * @throws ParkingLotSystemException : when there is no vehicle found
     */
    public int getBMWVehiclePosition(Vehicle vehicle) throws ParkingLotSystemException {
        if (isVehicleParked(vehicle)
                && vehicle.getName().equals("BMW"))
            for (Vehicle position : vehicleList) {
                if (position.equals(vehicle))
                    return vehicleList.indexOf(position);
            }
        throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                "No such vehicle found");
    }

    /**
     * Purpose : This method is checking the validity of vehicle number plates
     *
     * @param vehicleNumber : takes vehicle number as parameter for checking validation of number plate
     * @return : the matching value of that vehicle number if true or false
     */
    public boolean validateVehicleNumberPlate(String vehicleNumber) {
        Pattern pattern = Pattern.compile("^[A-Z]{2}[ -][0-9]{2}[A-Z]{2}[0-9]{4}$");
        Matcher matcher = pattern.matcher(vehicleNumber);
        boolean number = matcher.matches();
        if (vehicleNumber.isEmpty())
            return false;
        return number;
    }
}
