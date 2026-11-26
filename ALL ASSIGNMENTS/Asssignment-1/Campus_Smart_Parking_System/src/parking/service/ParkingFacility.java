package parking.service;

import parking.model.Vehicle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ParkingFacility {
    private final Vehicle[][] slots;
    private final Map<String, int[]> vehicleLocations = new HashMap<String, int[]>();

    public ParkingFacility(int[] slotsPerFloor) {
        if (slotsPerFloor == null || slotsPerFloor.length == 0) {
            throw new IllegalArgumentException("At least one floor is required.");
        }
        slots = new Vehicle[slotsPerFloor.length][];
        for (int floor = 0; floor < slotsPerFloor.length; floor++) {
            if (slotsPerFloor[floor] < 0) {
                throw new IllegalArgumentException("Slot count cannot be negative.");
            }
            slots[floor] = new Vehicle[slotsPerFloor[floor]];
        }
    }

    // Returns {floor, slot}; returns null when full or vehicle is already parked.
    public synchronized int[] allocate(Vehicle vehicle) {
        if (vehicle == null || vehicleLocations.containsKey(vehicle.getRegNo())) {
            return null;
        }
        for (int floor = 0; floor < slots.length; floor++) {
            for (int slot = 0; slot < slots[floor].length; slot++) {
                if (slots[floor][slot] == null) {
                    slots[floor][slot] = vehicle;
                    int[] location = new int[] {floor, slot};
                    vehicleLocations.put(vehicle.getRegNo(), location);
                    return location.clone();
                }
            }
        }
        return null;
    }

    public synchronized boolean release(int floor, int slot) {
        if (floor < 0 || floor >= slots.length
                || slot < 0 || slot >= slots[floor].length
                || slots[floor][slot] == null) {
            return false;
        }
        Vehicle removed = slots[floor][slot];
        vehicleLocations.remove(removed.getRegNo());
        slots[floor][slot] = null;
        return true;
    }

    public synchronized int[] findVehicle(String regNo) {
        int[] location = vehicleLocations.get(regNo.toUpperCase());
        return location == null ? null : location.clone();
    }

    public synchronized Vehicle[][] getFacilityState() {
        Vehicle[][] copy = new Vehicle[slots.length][];
        for (int i = 0; i < slots.length; i++) {
            copy[i] = Arrays.copyOf(slots[i], slots[i].length);
        }
        return copy;
    }
}
