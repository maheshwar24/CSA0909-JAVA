public class ThreadLifeCycleDemo {
    static class ScannerStation extends Thread {
        private final int id;
        ScannerStation(int id) { this.id = id; }

        public void run() {
            System.out.println("Scanner station " + id + " started.");
            try { Thread.sleep(500); }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            System.out.println("Scanner station " + id + " completed.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ScannerStation one = new ScannerStation(1);
        ScannerStation two = new ScannerStation(2);
        System.out.println("Before start: " + one.getState());
        one.start();
        two.start();
        System.out.println("After start: " + one.getState());
        one.join();
        two.join();
        System.out.println("After join: " + one.getState());
    }
}
