package model;

public class Client {
    private String id;
    private String name;
    private double balance;

    public boolean canAfford(double amount) {
        return this.balance >= amount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
}
