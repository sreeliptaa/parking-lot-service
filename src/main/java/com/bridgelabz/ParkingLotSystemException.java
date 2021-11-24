package com.bridgelabz;

/**
 * Purpose : To Simulation of Exception for parking lot system
 *
 * @author SREELIPTA
 * @since 2021-11-10
 */

public class ParkingLotSystemException extends RuntimeException {
    ExceptionType exceptionType;

    public ParkingLotSystemException(ExceptionType exceptionType, String message) {
        super(message);
        this.exceptionType = exceptionType;
    }

    public enum ExceptionType {
        PARKING_LOT_IS_FULL, NO_SUCH_VEHICLE,
        VEHICLE_ALREADY_EXIST, VEHICLE_IS_NOT_AVAILABLE
    }
}
