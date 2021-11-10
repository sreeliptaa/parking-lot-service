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
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnFalse() {
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.parkVehicle(new Object());
        Assertions.assertFalse(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        Object vehicle = new Object();
        parkingLotSystem.parkVehicle(vehicle);
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
        parkingLotSystem.parkVehicle(new Object());
        boolean isUnParked = parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertFalse(isUnParked);
    }

}