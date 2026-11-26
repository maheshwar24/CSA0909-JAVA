import java.util.concurrent.*;

public class DeadlockProducerConsumer {
    static class Wallet {
        final String name;
        int balance;
        Wallet(String name, int balance) { this.name = name; this.balance = balance; }
    }

    // Always acquire wallet locks in the same name order to prevent deadlock.
    static void transfer(Wallet from, Wallet to, int amount) {
        Wallet first = from.name.compareTo(to.name) < 0 ? from : to;
        Wallet second = first == from ? to : from;
        synchronized (first) {
            synchronized (second) {
                if (from.balance >= amount) {
                    from.balance -= amount;
                    to.balance += amount;
                    System.out.println("Transferred " + amount + " from "
                            + from.name + " to " + to.name);
                } else System.out.println("Insufficient balance.");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Wallet a = new Wallet("A", 100), b = new Wallet("B", 100);
        Thread t1 = new Thread(() -> transfer(a, b, 10));
        Thread t2 = new Thread(() -> transfer(b, a, 20));
        t1.start(); t2.start(); t1.join(); t2.join();

        BlockingQueue<Integer> buffer = new ArrayBlockingQueue<>(5);
        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++) {
                    buffer.put(i);
                    System.out.println("Produced: " + i);
                }
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 10; i++)
                    System.out.println("Consumed: " + buffer.take());
            } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
        });
        producer.start(); consumer.start();
        producer.join(); consumer.join();
        System.out.println("Producer-consumer completed.");
    }
}
