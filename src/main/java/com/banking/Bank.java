package com.banking;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

import java.util.function.Consumer;

public class Bank {

    private MongoCollection<Document> collection;
    private MongoClient mongoClient;

    public Bank() {
        try {
            System.out.println("DEBUG: Attempting to connect to MongoDB at localhost:27017...");
            // Connect to MongoDB (default localhost:27017)
            mongoClient = MongoClients.create("mongodb://localhost:27017");

            System.out.println("DEBUG: Client created. Getting database 'banking_app'...");
            MongoDatabase database = mongoClient.getDatabase("banking_app");

            System.out.println("DEBUG: Database selected. Getting collection 'accounts'...");
            collection = database.getCollection("accounts");

            System.out.println("DEBUG: Successfully initialized MongoDB connection.");
        } catch (Exception e) {
            System.err.println("FATAL: Failed to connect to MongoDB: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void createAccount(int accNum, String name, double balance) {
        if (collection == null) {
            System.out.println("Error: Database not connected.");
            return;
        }
        System.out.println("DEBUG: creating account " + accNum);
        Document doc = new Document("accountNumber", accNum)
                .append("name", name)
                .append("balance", balance);
        collection.insertOne(doc);
        System.out.println("Account created successfully!");
    }

    public Account getAccount(int accNum) throws AccountNotFoundException {
        Document doc = collection.find(eq("accountNumber", accNum)).first();
        if (doc == null) {
            throw new AccountNotFoundException("Account not found!");
        }
        int num = doc.getInteger("accountNumber");
        String name = doc.getString("name");
        double bal = doc.getDouble("balance");
        return new Account(num, name, bal);
    }

    public void deposit(int accNum, double amount) throws Exception {
        Account acc = getAccount(accNum);
        acc.deposit(amount);

        collection.updateOne(eq("accountNumber", accNum), set("balance", acc.getBalance()));
        System.out.println("Amount deposited successfully!");
    }

    public void withdraw(int accNum, double amount) throws Exception {
        Account acc = getAccount(accNum);
        acc.withdraw(amount);

        collection.updateOne(eq("accountNumber", accNum), set("balance", acc.getBalance()));
        System.out.println("Amount withdrawn successfully!");
    }

    public void showAllAccounts() {
        long count = collection.countDocuments();
        if (count == 0) {
            System.out.println("No accounts available!");
        } else {
            collection.find().forEach((Consumer<Document>) doc -> {
                int num = doc.getInteger("accountNumber");
                String name = doc.getString("name");
                double bal = doc.getDouble("balance");
                System.out.println(new Account(num, name, bal));
            });
        }
    }
}
