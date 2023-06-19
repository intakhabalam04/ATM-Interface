# ATM System

This is a simple ATM (Automated Teller Machine) system implemented in Java. It allows users to perform banking operations such as withdrawal, deposit, transfer, and balance inquiry.

## Features

- User authentication with User ID and PIN
- Show transaction history
- Withdraw funds from an account
- Deposit funds into an account
- Transfer funds between accounts
- Show account balance

## Getting Started

To run the ATM system, follow these steps:

1. Make sure you have Java installed on your system.
2. Clone this repository or download the source code files.
3. Open a terminal or command prompt and navigate to the project directory.
4. Compile the Java source files using the following command:

javac Main.java


5. Run the program using the following command:

java Main


6. The program will prompt you to enter a User ID and PIN for authentication.
7. Once authenticated, you can select various options from the ATM menu to perform different operations.

## Usage

Upon running the program, you will be prompted to enter a User ID and PIN for authentication. Use the following dummy account details for testing:

- User ID: 123
- PIN: 123

After successful authentication, you will see the ATM menu with the following options:

1. Show Transaction History: Displays the transaction history for the authenticated account.
2. Withdraw: Allows you to withdraw funds from the account.
3. Deposit: Allows you to deposit funds into the account.
4. Transfer: Allows you to transfer funds to another account.
5. Show Balance: Displays the current account balance.
6. Quit: Exits the program.

Select an option by entering the corresponding number and following the prompts.

## Sample Code

```java
// Create an instance of the ATM
ATM atm = new ATM();
 
// Start the ATM system
atm.start();

Contributing
Contributions to this ATM system are welcome! If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

# License
This project is licensed under the MIT License.


Feel free to modify the generated `README.md` file according to your needs.
