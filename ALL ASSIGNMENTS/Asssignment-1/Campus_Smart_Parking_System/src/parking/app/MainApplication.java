package parking.app;

import parking.model.*;
import parking.service.ParkingFacility;
import parking.service.TicketManager;
import java.util.Arrays;

public class MainApplication {
    public static void main(String[] args) {
        ParkingFacility facility = new ParkingFacility(new int[] {2, 1});
        TicketManager ticketManager = new TicketManager();

        Vehicle bike = new TwoWheeler("TN01AB1111");
        Vehicle car = new Car("TN02CD2222");
        ElectricCar ev = new ElectricCar("TN03EF3333");

        parkVehicle(facility, ticketManager, bike);
        parkVehicle(facility, ticketManager, car);
        parkVehicle(facility, ticketManager, ev);

        Vehicle extra = new Car("TN04GH4444");
        System.out.println("Extra vehicle allocation (facility full): "
                + Arrays.toString(facility.allocate(extra)));

        System.out.println("\n--- Parking fee demonstration ---");
        printFee(bike, 130, false);
        printFee(car, 130, true); // Active campus pass: no time-based fee.
        printFee(ev, 130, false);
        System.out.println("EV charging fee for 10 kWh: Rs "
                + ((Chargeable) ev).chargingFee(10));

        System.out.println("\n--- Release and re-allocation ---");
        System.out.println("Release floor 0, slot 1: " + facility.release(0, 1));
        System.out.println("Release same slot again: " + facility.release(0, 1));
        System.out.println("Re-allocation: "
                + Arrays.toString(facility.allocate(extra)));

        System.out.println("\n--- Defensive copy check ---");
        Vehicle[][] snapshot = facility.getFacilityState();
        System.out.println("Number of floors in returned snapshot: " + snapshot.length);
    }

    private static void parkVehicle(ParkingFacility facility,
                                    TicketManager manager, Vehicle vehicle) {
        int[] location = facility.allocate(vehicle);
        if (location == null) {
            System.out.println(vehicle + " could not be allocated.");
            return;
        }
        ParkingTicket ticket = manager.createTicket(vehicle.getRegNo());
        System.out.println(vehicle + " allocated at floor " + location[0]
                + ", slot " + location[1]);
        System.out.println(ticket);
    }

    private static void printFee(Vehicle vehicle, int minutes, boolean passHolder) {
        System.out.println(vehicle + " fee for " + minutes + " minutes"
                + (passHolder ? " (campus pass)" : "")
                + ": Rs " + vehicle.calculateFee(minutes, passHolder));
    }
}
