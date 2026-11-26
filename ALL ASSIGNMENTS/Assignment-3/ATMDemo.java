import java.io.*;
import java.util.*;

class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) { super(message); }
}

public class ATMDemo {
    public static void main(String[] args) {
        double balance = 5000.0;
        try (Scanner input = new Scanner(System.in)) {
            try (BufferedReader card = new BufferedReader(new FileReader("card.txt"))) {
                String account = card.readLine();
                if (account == null || account.isBlank())
                    throw new IOException("Account information is missing.");

                System.out.println("Welcome, " + account);
                System.out.print("Enter withdrawal amount: ");
                if (!input.hasNextDouble())
                    throw new InputMismatchException("Enter a numeric amount.");

                double amount = input.nextDouble();
                if (amount <= 0)
                    throw new IllegalArgumentException("Amount must be positive.");
                if (amount > balance)
                    throw new InsufficientFundsException("Insufficient balance.");

                balance -= amount;
                System.out.println("Cash dispensed: " + amount);
                System.out.println("Remaining balance: " + balance);
            } catch (FileNotFoundException e) {
                System.out.println("Card file error: " + e.getMessage());
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            } catch (InsufficientFundsException | IOException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } finally {
                System.out.println("ATM transaction attempt completed.");
            }
        }
    }
}
