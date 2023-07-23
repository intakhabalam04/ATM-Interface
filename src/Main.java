public class Main {
    public static void main(String[] args) {
        // Create a new ATM instance.
        ATM atm1 = new ATM();

        // Continuously start the ATM application in an infinite loop.
        // This allows the ATM to keep running and serving multiple users one after another.
        while (true) {
            atm1.start(); // Start the ATM application for each user.
        }
    }
}
