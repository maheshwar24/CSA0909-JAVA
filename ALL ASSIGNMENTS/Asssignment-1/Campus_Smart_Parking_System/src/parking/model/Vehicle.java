package parking.model;

import java.util.Objects;

public abstract class Vehicle {
    private final String regNo;

    protected Vehicle(String regNo) {
        if (regNo == null || regNo.trim().isEmpty()) {
            throw new IllegalArgumentException("Registration number is required.");
        }
        this.regNo = regNo.trim().toUpperCase();
    }

    public final String getRegNo() {
        return regNo;
    }

    protected abstract double ratePerHour();

    public double calculateFee(int minutes) {
        return calculateFee(minutes, false);
    }

    public double calculateFee(int minutes, boolean activeCampusPass) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative.");
        }
        if (activeCampusPass) {
            return 0.0;
        }
        int hours = (minutes + 59) / 60; // Round every started hour upward.
        return hours * ratePerHour();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Vehicle)) return false;
        Vehicle other = (Vehicle) obj;
        return regNo.equalsIgnoreCase(other.regNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNo.toUpperCase());
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[" + regNo + "]";
    }
}
