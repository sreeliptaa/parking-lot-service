package com.bridgelabz;

/**
 * Purpose:To Search the vehicle in the parking lot
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */
public class VehicleDriver {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to search for the vehicle parked in the parking lot.
     *
     * @param vehicle to be searched
     * @return object - the resultant vehicle after searching.
     */
    public Object searchVehicle(Vehicle vehicle) {
        return parkingLotSystem.findVehicle(vehicle);
    }

}
