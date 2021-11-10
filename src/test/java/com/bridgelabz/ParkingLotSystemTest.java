package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem();
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
}
