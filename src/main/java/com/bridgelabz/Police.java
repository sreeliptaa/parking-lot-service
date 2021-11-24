package com.bridgelabz;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Police {
    ParkingLotSystem parkingLotSystem = new ParkingLotSystem();

    /**
     * This method is to find all the white vehicles that are present in the parking lots.
     *
     * @return List of white vehicles.
     * @throws ParkingLotSystemException when there are no white vehicles present in the parking lots.
     */
    public List getAllWhiteVehicles() throws ParkingLotSystemException {
        return parkingLotSystem.getWhiteColorVehiclePosition();
    }

    /**
     * This method is to find all the Blue Toyota vehicles that are present in the parking lots.
     *
     * @return List of Blue Toyota vehicles.
     * @throws ParkingLotSystemException when there are no Blue Toyota vehicles present in the parking lots.
     */
    public List getBlueToyotaVehicles() throws ParkingLotSystemException {
        return parkingLotSystem.getBlueToyotaVehicles();
    }

    /**
     * This method is to find all the BMW vehicles that are present in the parking lots.
     *
     * @return List of BMW vehicles.
     * @throws ParkingLotSystemException when there are no BMW vehicles present in the parking lots.
     */
    public List getBMWVehicles() throws ParkingLotSystemException {
        return parkingLotSystem.getBMWVehicles();
    }

    public List getAllHandicappedVehicles() {
        return parkingLotSystem.getHandicappedVehicles();
    }

    public List vehicleNumberValidate() {
        List<Vehicle> parkedVehicles = new ArrayList();
        List<Vehicle> temp = new ArrayList();
        Pattern pattern = Pattern.compile("^[A-Z]{2}[-][0-9]{2}[A-Z]{2}[0-9]{4}$");
        parkedVehicles = parkingLotSystem.getAllVehicles();
        for (Vehicle vehicle : parkedVehicles) {
            Matcher matcher = pattern.matcher(vehicle.getVehicleNumber());
            if (matcher.matches() == false)
                temp.add(vehicle);
        }
        if (temp.size() == 0)
            throw new ParkingLotSystemException(ParkingLotSystemException.ExceptionType.NO_SUCH_VEHICLE,
                    "No Invalid Vehicles Present");
        return temp;
    }
}
