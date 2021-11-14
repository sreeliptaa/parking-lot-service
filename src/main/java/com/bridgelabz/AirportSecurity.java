package com.bridgelabz;

/**
 * Purpose : To inform parking full to the parking lot owner
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */
public class AirportSecurity implements ParkingLotSystemObserver {
    private boolean actualCapacity;

    /**
     * Purpose: To change Parking Capacity To True If Parking Is Full
     */
    public void capacityIsFull() {

        this.actualCapacity = true;
    }

    /**
     * Purpose: To Return Parking Capacity
     *
     * @return true if parkingFull Or False
     */
    public boolean isCapacityFull() {

        return this.actualCapacity;
    }

    /**
     * Purpose : This method is created to check the status of parking capacity available in the parking lot
     * * @return false if parking is available
     */
    @Override
    public boolean parkingCapacityAvailable() {

        return this.actualCapacity = false;
    }
}
