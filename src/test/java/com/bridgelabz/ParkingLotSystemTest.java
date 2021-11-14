package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Object vehicle = null;
    private List<Integer> listOfEmptySlots;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
        vehicle = new Object();
    }

    @Test
    public void givenMessage_ShouldPrintWelcomeMessage() {

        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle, 0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle, 0);
            parkingLotSystem.parkVehicle(vehicle, 1);
        }, "Parking Lot is Full");
    }

    @Test
    public void givenNullVehicle_WhenUnParked_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unParkVehicle(null), "No Such Vehicle Found");
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        parkingLotSystem.parkVehicle(vehicle, 0);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
        parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleUnParked(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParkedFromEmptySlot_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unParkVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParkedAnotherVehicle_ShouldReturnFalse() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unParkVehicle(vehicle), "No Such Vehicle Found");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle, 0);
            parkingLotSystem.parkVehicle(vehicle, 1);
            parkingLotSystem.parkVehicle(vehicle, 2);
            parkingLotSystem.unParkVehicle(vehicle);
        }, "Parking Lot is Full");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(new Object(), 0);
            parkingLotSystem.parkVehicle(vehicle, 1);
            parkingLotSystem.parkVehicle(vehicle, 2);
        }, "Parking Lot is Full");

    }

    @Test
    public void givenVehicle_WhenParkingLotAvailableAndOwnerIsObserver_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle, 0);
            parkingLotSystem.parkVehicle(new Object(), 1);
            parkingLotSystem.parkVehicle(new Object(), 2);
        });
        Assertions.assertTrue(owner.isCapacityFull());
        parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertFalse(owner.isCapacityFull());
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() {
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        parkingLotSystem.parkVehicle(vehicle, 0);
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        Assertions.assertEquals(1, listOfEmptySlots.size());
    }

    @Test
    public void givenParkingLotSystem_WhenCheckedForVehicle_ShouldReturnVehicleSlot() {
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        parkingLotSystem.parkVehicle(vehicle, 0);
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(0, slotNumber);
    }
}
