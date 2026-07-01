import java.util.Scanner;
import java.util.Random;

class BankAccount {
    String holderName;
    int age;
    int accountNumber;
    int pin;
    double balance;

    BankAccount(String holderName, int age, double balance) {

        Random random = new Random();

        this.holderName = holderName;
        this.age = age;
        this.balance = balance;

        accountNumber = 1000 + random.nextInt(9000);
        pin = 1000 + random.nextInt(9000);
    }
    void displayDetails() {

        System.out.println("\n------ Account Details ------");
        System.out.println("Name           : " + holderName);
        System.out.println("Age            : " + age);
        System.out.println("Account Number : " + accountNumber);

    }

    // Check Balance
    void checkBalance() {
        System.out.println("Current Balance: Rs." + balance);
    }

    // Deposit Money
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("₹" + amount + " deposited successfully.");
            System.out.println("Updated Balance: Rs." + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    // Withdraw Money
    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient Balance!");
        } else {
            balance -= amount;
            System.out.println("" + amount + " withdrawn successfully.");
            System.out.println("Remaining Balance: Rs." + balance);
        }
    }
}

public class ATMBank {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        BankAccount account = null;

        int mainChoice;

        do {

            System.out.println("\n========== ATM ==========");
            System.out.println("1. Login");
            System.out.println("2. Create Account");
            System.out.println("3. Exit");
            System.out.print("Enter your choice : ");

            mainChoice = sc.nextInt();
            sc.nextLine();

            switch (mainChoice) {

                case 1:

                    if (account == null) {
                        System.out.println("\nNo account found!");
                        System.out.println("Please create an account first.");
                        break;
                    }

                    System.out.println("\n======= LOGIN =======");

                    System.out.print("Enter Account Number : ");
                    int acc = sc.nextInt();

                    System.out.print("Enter PIN : ");
                    int enteredPin = sc.nextInt();

                    if (acc == account.accountNumber && enteredPin == account.pin) {

                        System.out.println("\nLogin Successful!");

                        account.displayDetails();

                        int choice;

                        do {

                            System.out.println("\n====== ATM MENU ======");
                            System.out.println("1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Display Account Details");
                            System.out.println("5. Logout");
                            System.out.print("Enter your choice : ");

                            choice = sc.nextInt();

                            switch (choice) {

                                case 1:
                                    account.checkBalance();
                                    break;

                                case 2:

                                    System.out.print("Enter amount to deposit : Rs.");
                                    double depositAmount = sc.nextDouble();

                                    account.deposit(depositAmount);
                                    break;

                                case 3:

                                    System.out.print("Enter amount to withdraw : Rs.");
                                    double withdrawAmount = sc.nextDouble();

                                    account.withdraw(withdrawAmount);
                                    break;

                                case 4:

                                    account.displayDetails();
                                    break;

                                case 5:

                                    System.out.println("Logged Out Successfully.");
                                    break;

                                default:

                                    System.out.println("Invalid Choice!");

                            }

                        } while (choice != 5);

                    } else {

                        System.out.println("Invalid Account Number or PIN.");

                    }

                    break;

                case 2:

                    if (account != null) {

                        System.out.println("\nAn account already exists.");
                        System.out.println("Please login using your Account Number and PIN.");
                        break;
                    }

                    System.out.println("\n===== CREATE ACCOUNT =====");

                    System.out.print("Enter Name : ");
                    String name = sc.nextLine();

                    int age;

                    do {
                        System.out.print("Enter Age : ");
                        age = sc.nextInt();

                        if (age < 18) {
                            System.out.println("You must be at least 18 years old to create a bank account.");
                        }

                    } while (age < 18);

                    System.out.print("Enter Initial Deposit : ");
                    double balance = sc.nextDouble();

                    account = new BankAccount(name, age, balance);

                    System.out.println("\nAccount Created Successfully!");

                    System.out.println("Account Number : " + account.accountNumber);
                    System.out.println("ATM PIN        : " + account.pin);

                    System.out.println("Please remember your PIN.");

                    break;

                case 3:

                    System.out.println("\nThank you for using our ATM.");
                    break;

                default:

                    System.out.println("Invalid Choice!");

            }

        } while (mainChoice != 3);

        sc.close();
    }
}