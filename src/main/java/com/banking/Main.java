package com.banking;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        while (true) {
            System.out.println("\n===== BANKING SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Show All Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            // Check if input is available to avoid infinite loop (useful if input stream is
            // closed)
            if (!sc.hasNextInt()) {
                if (!sc.hasNext()) {
                    break;
                }
                sc.next(); // consume invalid input
                continue;
            }
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter Account Number: ");
                        int accNum = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Initial Balance: ");
                        double bal = sc.nextDouble();

                        bank.createAccount(accNum, name, bal);
                        break;

                    case 2:
                        System.out.print("Enter Account Number: ");
                        accNum = sc.nextInt();
                        System.out.print("Enter Deposit Amount: ");
                        double dep = sc.nextDouble();

                        bank.deposit(accNum, dep);
                        break;

                    case 3:
                        System.out.print("Enter Account Number: ");
                        accNum = sc.nextInt();
                        System.out.print("Enter Withdrawal Amount: ");
                        double wd = sc.nextDouble();

                        bank.withdraw(accNum, wd);
                        break;

                    case 4:
                        bank.showAllAccounts();
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
