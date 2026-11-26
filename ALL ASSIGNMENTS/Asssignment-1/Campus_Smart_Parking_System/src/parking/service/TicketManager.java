package parking.service;

import parking.model.ParkingTicket;
import java.util.concurrent.atomic.AtomicInteger;

public class TicketManager {
    private static final AtomicInteger SEQUENCE = new AtomicInteger(1000);

    public ParkingTicket createTicket(String regNo) {
        String id = "TKT-" + SEQUENCE.incrementAndGet();
        return new ParkingTicket(id, regNo);
    }
}
