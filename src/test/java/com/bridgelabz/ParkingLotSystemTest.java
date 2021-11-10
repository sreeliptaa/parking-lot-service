package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
        vehicle = new Object();
    }

    @Test
    public void givenMessage_ShouldPrintWelcomeMessage() {
        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
            Assertions.assertTrue(isParked);
        } catch (ParkingLotSystemException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        try {
            parkingLotSystem.parkVehicle(vehicle);
            parkingLotSystem.parkVehicle(new Object());
        } catch (ParkingLotSystemException e) {
            Assertions.assertEquals("Parking Lot is Full", e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Object vehicle = new Object();
        parkingLotSystem.isVehicleParked(vehicle);
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Object vehicle = new Object();
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertFalse(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() {
        Object vehicle = new Object();
        parkingLotSystem.isVehicleParked(new Object());
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertFalse(isUnParked);
    }

}