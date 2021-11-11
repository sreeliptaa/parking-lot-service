package com.bridgelabz;

/**
 * Purpose : To simulate parking lot system by the owner
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */

public class ParkingLotSystemOwner {
    private boolean isFullCapacity;

    /**
     * Purpose : This method is created to give the status of full capacity of parking lot
     */
    public void capacityIsFull() {
        isFullCapacity = true;
    }

    /**
     * Purpose : This method is created to check the status of full capacity of parking lot
     *
     * @return the status of the parking lot
     */
    public boolean isCapacityFull() {
        return this.isFullCapacity;
    }
}
