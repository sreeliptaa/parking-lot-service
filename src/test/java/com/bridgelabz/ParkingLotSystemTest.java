package com.bridgelabz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
    void givenAVehicle_WhenParked_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05AA5647", "Black");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenAVehicle_WhenUnParked_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05AB5647", "Black");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean isUnParked = parkingLotSystem.isVehicleUnParked(vehicle);
        Assertions.assertTrue(isUnParked);
    }

    @Test
    public void givenAVehicle_WhenAlreadyParked_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05AS5647", "Black");
        parkingLotSystem.capacityOfParkingLot(2);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle));
    }

    @Test
    public void givenAVehicle_WhenParkingLotIsFull_ShouldThrowException() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05BB5647", "Black");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05AD2356", "White");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
    }

    @Test
    public void givenNoVehicle_WhenUnParked_ShouldThrowException() {
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.unParkVehicle(null));
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheParkingLotOwner() throws ParkingLotSystemException {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05RR5647", "Black");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05AW2356", "White");
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    void givenWhenParkingLotIsFull_ShouldInformTheAirportSecurity() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05RR5647", "Black");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR5647", "White");
        parkingLotSystem.capacityOfParkingLot(1);
        AirportSecurity airportSecurity = new AirportSecurity();
        parkingLotSystem.registerParkingLotSystemObserver(airportSecurity);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assertions.assertTrue(capacityFull);
    }

    @Test
    public void givenWhenParkingLotSpaceIsAvailableAfterFull_ShouldInformTheObserver() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05RR5647", "Black");
        parkingLotSystem.capacityOfParkingLot(2);
        ParkingLotSystemOwner owner = new ParkingLotSystemOwner();
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "OR-05RR5647", "White");
        parkingLotSystem.registerParkingLotSystemObserver(owner);
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.unParkVehicle(vehicle);
        boolean capacityFull = owner.isCapacityFull();
        Assertions.assertFalse(capacityFull);
    }

    @Test
    public void givenAVehicle_ToParkVehicleByAttendant_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05RR5647", "Black");
        parkingLotSystem.capacityOfParkingLot(1);
        ParkingLotSystemAttendant parkingLotAttendant = new ParkingLotSystemAttendant();
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        boolean isParked = parkingLotSystem.isVehicleParked(vehicle);
        Assertions.assertTrue(isParked);
    }

    @Test
    public void givenADriver_WhenWantToFindVehicle_ShouldReturnTrue() {
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05RR5647", "Black");
        VehicleDriver vehicleDriver = new VehicleDriver();
        parkingLotSystem.capacityOfParkingLot(2);
        parkingLotSystem.parkVehicle(vehicle);
        Object expectedVehicle = vehicleDriver.searchVehicle(vehicle);
        Assertions.assertEquals(vehicle, expectedVehicle);
    }

    @Test
    void givenVehicle_WhenEvenlyParked_ShouldReturnTrue() {
        parkingLotSystem.capacityOfParkingLot(10);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05RR5647", "Black");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05RR5647", "White");
        parkingLotSystem.capacityOfParkingLot(1);
        parkingLotSystem.parkVehicle(vehicle);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> parkingLotSystem.parkVehicle(vehicle2));
        boolean isParked1 = parkingLotSystem.isVehicleParked(vehicle);
        boolean isParked2 = parkingLotSystem.isVehicleParked(vehicle2);
        Assertions.assertTrue(isParked1 && isParked2);
    }

    @Test
    void givenHandicappedPerson_WhenParkedVehicleByAttendantShouldReturnTrue() {
        parkingLotSystem.capacityOfParkingLot(5);
        ParkingLotSystemAttendant parkingLotAttendant = new ParkingLotSystemAttendant();
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.HANDICAP,
                "Mercedes", "OR-05RR5647", "Blue");
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));
    }

    @Test
    void givenPoliceDepartment_WhenSearchForWhiteVehicles_ShouldReturnTrue() {
        Police police = new Police();
        parkingLotSystem.capacityOfParkingLot(5);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05RR5647", "White");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05RR5647", "White");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR5647", "Violet");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Honda", "OR-05RR5647", "White");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05RR5647", "White");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.parkVehicle(vehicle3);
        parkingLotSystem.parkVehicle(vehicle4);
        parkingLotSystem.parkVehicle(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add("ParkingLot1: 0");
        expectedList.add("ParkingLot1: 2");
        expectedList.add("ParkingLot2: 0");
        expectedList.add("ParkingLot2: 1");
        List actualList = police.getAllWhiteVehicles();
        Assertions.assertEquals(actualList, expectedList);
    }

    @Test
    void givenPoliceDepartment_WhenSearchForBlueToyotaVehicles_ShouldReturnTrue() {
        Police police = new Police();
        parkingLotSystem.capacityOfParkingLot(5);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR5847", "Blue");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "OR-05RR5649", "White");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR8647", "Blue");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Nissan", "OR-05RR0647", "White");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR5647", "Blue");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.parkVehicle(vehicle3);
        parkingLotSystem.parkVehicle(vehicle4);
        parkingLotSystem.parkVehicle(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add(" Plate Number = " + vehicle.getVehicleNumber() + " Location = ParkingLot 1: " + 0);
        expectedList.add(" Plate Number = " + vehicle3.getVehicleNumber() + " Location = ParkingLot 1: " + 1);
        expectedList.add(" Plate Number = " + vehicle5.getVehicleNumber() + " Location = ParkingLot 1: " + 2);
        List actualList = police.getBlueToyotaVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    void givenPoliceDepartment_WhenThereAreNoWhiteVehicles_ShouldThrowException() {
        Police police = new Police();
        parkingLotSystem.capacityOfParkingLot(5);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05RR5607", "Yellow");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05RR5667", "Green");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Tesla", "OR-05RR5047", "Violet");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.parkVehicle(vehicle3);
        Assertions.assertThrows(ParkingLotSystemException.class, () -> police.getAllWhiteVehicles());
    }

    @Test
    void givenPoliceDepartment_WhenSearchedBMWVehicles_ShouldReturnTrue() {
        Police police = new Police();
        parkingLotSystem.capacityOfParkingLot(5);
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05RR5647", "Blue");
        Vehicle vehicle2 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05RR2352", "White");
        Vehicle vehicle3 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "BMW", "OR-05RR5353", "Blue");
        Vehicle vehicle4 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Audi", "OR-05SD2354", "White");
        Vehicle vehicle5 = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Toyota", "OR-05RR2355", "Blue");
        parkingLotSystem.parkVehicle(vehicle);
        parkingLotSystem.parkVehicle(vehicle2);
        parkingLotSystem.parkVehicle(vehicle3);
        parkingLotSystem.parkVehicle(vehicle5);
        ArrayList expectedList = new ArrayList();
        expectedList.add(vehicle);
        expectedList.add(vehicle3);
        List actualList = police.getBMWVehicles();
        Assertions.assertEquals(expectedList, actualList);
    }

    @Test
    public void givenVehicle_WhenCheckedVehicleNumber_ShouldPassVehicleNumberPlateValidation() throws ParkingLotSystemException {
        parkingLotSystem.capacityOfParkingLot(5);
        ParkingLotSystemAttendant parkingLotAttendant = new ParkingLotSystemAttendant();
        vehicle = new Vehicle(Vehicle.VehicleType.MEDIUM, Vehicle.PersonType.NORMAL,
                "Mercedes", "OR-05SS7747", "Red");
        parkingLotAttendant.parkVehicleByAttendant(vehicle);
        Assertions.assertTrue(parkingLotSystem.isVehicleParked(vehicle));

    }
}