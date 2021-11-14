package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem = null;
    Vehicle vehicle = null;
    private List<Integer> listOfEmptySlots;

    @BeforeEach
    void setUp() {
        parkingLotSystem = new ParkingLotSystem(2);
    }

    @Test
    public void givenMessage_ShouldPrintWelcomeMessage() {

        parkingLotSystem.printWelcomeMessage();
    }

    @Test
    public void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.parkVehicle(vehicle, 0);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00");
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
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.parkVehicle(vehicle, 0);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
        parkingLotSystem.unParkVehicle(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleUnParked(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00");
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, 0);
            parkingLotSystem.parkVehicle(vehicle2, 1);
            parkingLotSystem.parkVehicle(vehicle3, 2);
            parkingLotSystem.unParkVehicle(vehicle);
        }, "Parking Lot is Full");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00");
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, 0);
            parkingLotSystem.parkVehicle(vehicle2, 1);
            parkingLotSystem.parkVehicle(vehicle3, 2);
        }, "Parking Lot is Full");

    }

    @Test
    public void givenVehicle_WhenParkingLotAvailableAndOwnerIsObserver_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        Vehicle vehicle1 = new Vehicle("Audi", "OR-05AB4321", "09:00");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "11:00");
        Vehicle vehicle3 = new Vehicle("BMW", "OR-05AA6060", "12:00");
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> {
            parkingLotSystem.parkVehicle(vehicle1, 0);
            parkingLotSystem.parkVehicle(vehicle2, 1);
            parkingLotSystem.parkVehicle(vehicle3, 2);
        });
        Assertions.assertTrue(owner.isCapacityFull());
        parkingLotSystem.unParkVehicle(vehicle1);
        Assertions.assertFalse(owner.isCapacityFull());
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() {
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.parkVehicle(vehicle, 0);
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        Assertions.assertEquals(1, listOfEmptySlots.size());
    }

    @Test
    public void givenParkingLotSystem_WhenCheckedForVehicle_ShouldReturnVehicleSlot() {
        listOfEmptySlots = parkingLotSystem.getListOfEmptySlots();
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.parkVehicle(vehicle, 0);
        int slotNumber = parkingLotSystem.findVehicle(vehicle);
        Assertions.assertEquals(0, slotNumber);
    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "09:00");
        parkingLotSystem.parkVehicle(vehicle, 0);
        String vehicleParkingTime = parkingLotSystem.getVehicleParkingTime(vehicle);
        Assertions.assertEquals("09:00", vehicleParkingTime);
    }
}
