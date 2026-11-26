public class SeatBooking {
    static class Cinema {
        private int availableSeats = 5;

        public synchronized void book(String customer, int seats) {
            System.out.println(customer + " requested " + seats + " seat(s).");
            if (seats <= availableSeats) {
                availableSeats -= seats;
                System.out.println("Booking successful. Seats left: " + availableSeats);
            } else {
                System.out.println("Booking failed: not enough seats.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Cinema cinema = new Cinema();
        Thread a = new Thread(() -> cinema.book("Customer A", 3));
        Thread b = new Thread(() -> cinema.book("Customer B", 2));
        Thread c = new Thread(() -> cinema.book("Customer C", 2));
        a.start(); b.start(); c.start();
        a.join(); b.join(); c.join();
        System.out.println("Booking process completed.");
    }
}
