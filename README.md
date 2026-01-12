# Banking Application

A simple console-based banking system built with Java and MongoDB.

## Features

-   **Create Account**: Open a new bank account with an initial balance.
-   **Deposit**: Add funds to an existing account.
-   **Withdraw**: Withdraw funds from an account (with balance checks).
-   **Show All Accounts**: List all registered accounts in the system.
-   **Data Persistence**: Uses MongoDB to store account details permanently.

## Technologies Used

-   **Language**: Java
-   **Database**: MongoDB
-   **Build Tool**: Maven

## Prerequisites

-   Java Development Kit (JDK) installed.
-   Maven installed.
-   MongoDB installed and running locally on `localhost:27017`.

## Setup & Execution

1.  **Clone the Repository**
    ```sh
    git clone https://github.com/Nithikamalini/Banking-Application.git
    cd Banking-Application
    ```

2.  **Build the Project**
    ```sh
    mvn clean install
    ```

3.  **Run Main Class**
    Run the `Main` class located in `src/main/java/com/banking/Main.java`.

## Usage

Follow the on-screen menu to perform banking operations:

```text
===== BANKING SYSTEM =====
1. Create Account
2. Deposit
3. Withdraw
4. Show All Accounts
5. Exit
Enter choice:
```
