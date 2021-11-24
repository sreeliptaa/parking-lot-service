package com.bridgelabz;

/**
 * Purpose : To inform parking full to the parking lot owner
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */
public class AirportSecurity implements ParkingLotSystemObserver {
    private boolean isFullCapacity;

    /**
     * Purpose: To change Parking Capacity To True If Parking Is Full
     */
    public void capacityIsFull() {

        this.isFullCapacity = true;
    }

    /**
     * Purpose: To Return actual Capacity
     *
     * @return true if parking lot Full Or False
     */
    public boolean isCapacityFull() {

        return this.isFullCapacity;
    }

    /**
     * Purpose : This method is created to check the status of parking capacity available in the parking lot
     *
     * @return : false if parking is available
     */
    @Override
    public void parkingCapacityAvailable() {

        this.isFullCapacity = false;
    }
}
