package com.bridgelabz;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Purpose : To simulate parking lot system by the owner
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */

public class ParkingLotSystemOwner implements ParkingLotSystemObserver {
    private boolean isFullCapacity;
    static Map<Object, LocalDateTime> localDateTimeMap = new HashMap<>();

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

    /**
     * Purpose : This method is created to check status of parking capacity available in the parking lot
     */
    @Override
    public void parkingCapacityAvailable() {

        isFullCapacity = false;
    }

    /**
     * Purpose: To get parking time ime of vehicle when vehicle is parked.
     *
     * @param vehicle object for mapping time and date of the parked vehicle
     * @return LocalDateTime of the parked vehicle
     */
    public static LocalDateTime vehicleParkingTime(Object vehicle) {
        LocalDateTime now = LocalDateTime.now();
        localDateTimeMap.put(vehicle, now);
        return now;
    }
}
