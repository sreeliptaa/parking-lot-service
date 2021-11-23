package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class ParkingLotSystemTest {
    ParkingLotSystem parkingLotSystem;
    Vehicle vehicle;

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
        vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldReturnException() {
        Vehicle vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        parkingLotSystem.capacityOfParkingLot(2);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenNullVehicle_WhenUnParked_ShouldReturnException() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unParkVehicle(null),
                "No Such Vehicle Found");
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        Vehicle vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "Blue");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        AirportSecurity airportSecurity = new AirportSecurity();
        Vehicle vehicle = new Vehicle("Audi", "OR-05AB4321", "white");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "Blue");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);

    }

    @Test
    public void givenVehicle_WhenParkingLotAvailableAndOwnerIsObserver_ShouldInformTheOwner() {
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        Vehicle vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        Vehicle vehicle2 = new Vehicle("Lamborghini", "OR-05CD2222", "Blue");
        parkingLotSystem.capacityOfParkingLot(2);
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenVehicle_ToParkingAttendant_ShouldParkTheVehicle() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.capacityOfParkingLot(1);
        ParkingLotSystemAttendant parkingLotAttendant = new ParkingLotSystemAttendant();
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

//    @Test
//    public void givenADriver_WhenWantsToFindVehicle_ShouldReturnTrue() {
//        vehicle = new Vehicle("Audi", "OR-05AS5647","Black");
//        VehicleDriver vehicleDriver = new VehicleDriver();
//        parkingLotSystem.capacityOfParkingLot(2);
//        parkingLotSystem.parkVehicle(vehicle);
//        Object expectedVehicle = VehicleDriver.searchVehicle(vehicle);
//        Assertions.assertEquals(vehicle, expectedVehicle);
//    }

    @Test
    public void givenAVehicle_WhenParked_ThenCheckTimeOfParking_ShouldReturnParkingTime() {
        vehicle = new Vehicle("Audi", "OR-05AB4321", "White");
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertEquals(LocalDateTime.now(), ParkingLotSystemOwner.vehicleParkingTime(vehicle));
    }

    @Test
    public void givenVehicles_WhenEvenlyParked_ShouldReturnTrue() {
        vehicle = new Vehicle("Audi", "OR-05DS5647", "Black");
        Vehicle vehicle2 = new Vehicle("Toyota", "OR-05AW2356", "White");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertEquals(LocalDateTime.now(), ParkingLotSystemOwner.vehicleParkingTime(vehicle));
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

}