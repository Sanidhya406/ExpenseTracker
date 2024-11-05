import java.util.Scanner;

abstract class ExpenseTracker {
    private double budget;
    private double totalExpenses;
    private String[] items;
    private double[] amounts;
    private int expenseCount;

    public ExpenseTracker(double budget) {
        this.budget = budget;
        this.totalExpenses = 0;
        this.items = new String[100];
        this.amounts = new double[100];
        this.expenseCount = 0;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void addExpense(String item, double amount) {
        items[expenseCount] = item;
        amounts[expenseCount] = amount;
        expenseCount++;
        totalExpenses += amount;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getSavings() {
        return budget - totalExpenses;
    }

    public void viewExpenses() {
        System.out.println("\n============ Total Expenses ============");
        if (expenseCount == 0) {
            System.out.println("No expenses recorded yet.");
        } else {
            for (int i = 0; i < expenseCount; i++) {
                System.out.println(items[i] + ": Rs. " + amounts[i]);
            }
            System.out.println("-----------------------------------------");
            System.out.println("Total spent: Rs. " + totalExpenses);
        }
        System.out.println("=========================================");
    }

    public abstract void displayTrackerType();

    public abstract double applyDeductions();
}

class PersonalExpenseTracker extends ExpenseTracker {

    public PersonalExpenseTracker(double budget) {
        super(budget);
    }

    @Override
    public void displayTrackerType() {
        System.out.println("\n============ Personal Expense Tracker ============");
    }

    @Override
    public double applyDeductions() {
        return getTotalExpenses();
    }
}

class BusinessExpenseTracker extends ExpenseTracker {

    public BusinessExpenseTracker(double budget) {
        super(budget);
    }

    @Override
    public void displayTrackerType() {
        System.out.println("\n============ Business Expense Tracker ============");
    }

    @Override
    public double applyDeductions() {
        double totalExpenses = getTotalExpenses();
        double deduction = totalExpenses * 0.10;
        return totalExpenses - deduction; 
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("======================================");
        System.out.println("      Welcome to Expense Tracker      ");
        System.out.println("======================================");

        System.out.println("Choose your tracker type: ");
        System.out.println("1. Personal Expense Tracker");
        System.out.println("2. Business Expense Tracker");
        System.out.print("Your choice: ");
        int trackerType = scanner.nextInt();

        System.out.print("Please set your budget: Rs. ");
        double budget = scanner.nextDouble();

        ExpenseTracker tracker;
        if (trackerType == 1) {
            tracker = new PersonalExpenseTracker(budget); 
        } else {
            tracker = new BusinessExpenseTracker(budget); 
        }

        tracker.displayTrackerType();

        boolean running = true;
        while (running) {
            System.out.println("\n---------------- Menu ----------------");
            System.out.println("1. Add an Expense");
            System.out.println("2. View Total Expenses");
            System.out.println("3. View Total After Deductions");
            System.out.println("4. View Savings");
            System.out.println("5. Exit");
            System.out.println("--------------------------------------");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter the name of the item: ");
                    scanner.nextLine();
                    String item = scanner.nextLine();
                    System.out.print("Enter the amount spent on " + item + ": Rs. ");
                    double amount = scanner.nextDouble();
                    tracker.addExpense(item, amount);
                    System.out.println(item + " added with an amount of Rs. " + amount);
                    break;

                case 2:
                    tracker.viewExpenses();
                    break;

                case 3:
                    System.out.println("\n============ Total After Deductions ============");
                    System.out.println("Total after deductions: Rs. " + tracker.applyDeductions());
                    System.out.println("===============================================");
                    break;

                case 4:
                    System.out.println("\n============= Savings =============");
                    System.out.println("You have saved: Rs. " + tracker.getSavings());
                    System.out.println("===================================");
                    break;

                case 5:
                    System.out.println("\nThank you for using Expense Tracker!");
                    System.out.println("Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
