package parking.model;

public final class ParkingTicket {
    private final String ticketId;
    private final String regNo;
    private final long entryTimestamp;

    public ParkingTicket(String ticketId, String regNo, long entryTimestamp) {
        if (ticketId == null || regNo == null) {
            throw new IllegalArgumentException("Ticket ID and registration are required.");
        }
        this.ticketId = ticketId;
        this.regNo = regNo;
        this.entryTimestamp = entryTimestamp;
    }

    public ParkingTicket(String ticketId, String regNo) {
        this(ticketId, regNo, System.currentTimeMillis());
    }

    public String getTicketId() {
        return ticketId;
    }

    public String getRegNo() {
        return regNo;
    }

    public long getEntryTimestamp() {
        return entryTimestamp;
    }

    @Override
    public String toString() {
        return "Ticket{id='" + ticketId + "', regNo='" + regNo
                + "', entryTimestamp=" + entryTimestamp + "}";
    }
}
