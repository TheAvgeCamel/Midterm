import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
public class Menu{

    Scanner sc = new Scanner(System.in);
    Bank bank = new Bank();

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.Prompt();
    }

    private void Prompt(){
        int choice;

        do {
            System.out.println();
            System.out.println("1. Check Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Previous Transaction");
            System.out.println("5. Create Account");
            System.out.println("0. Finish");
            System.out.println();
            System.out.println("Enter number for selected option, from 0-5");
            choice = sc.nextInt();

            switch (choice)
            {
                case 1:
                    AccDetails();
                    break;

                case 2:
                    MakeDeposit();
                    break;

                case 3:
                    MakeWithdraw();

                    break;

                case 4:
                    getPrevAmount();
                    System.out.println();
                    break;

                case 5:
                    createAccount();
                    break;

                case 0:
                    break;

                default:
                    System.out.println();
                    System.out.println("Not a Valid Input! Please try again!");
                    break;
            }

        }while (choice != 0);
        System.out.println();
        System.out.println("Thank You for your services. Please use again.");

    }

    private void AccDetails(){
        int account = selectAccount();
        if (account >= 0) {
            System.out.println(bank.getCustomer(account).getAccount());
        }
        else {
            System.out.println("Invalid account selected!");
        }
    }

    private void MakeDeposit() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int account = selectAccount();
        if (account >= 0) {
            System.out.println("How much money to deposit?");
            double amount;
            try {
                amount = Double.parseDouble(sc.next());
            }catch (NumberFormatException e){
                amount = 0;
            }

            bank.getCustomer(account).getAccount().Deposit(amount);
            System.out.println("\nDate Deposited: "+sdf.format(date));
        }
    }

    private void MakeWithdraw(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();

        int account = selectAccount();
        if (account >= 0) {
            System.out.println("How much money to withdraw?");
            double amount;
            amount = Double.parseDouble(sc.next());
            bank.getCustomer(account).getAccount().Withdraw(amount);
            System.out.println("\nDate Deposited: "+sdf.format(date));
        }
    }

    private void getPrevAmount(){

        int account = selectAccount();
        if (account >= 0) {
            System.out.print("Your previous Transaction is: $");
            bank.getCustomer(account).getAccount().getPrevAmount();
        }
    }

    private void createAccount() {
        String SSN, Name, Address, accType = "";
        double initialDeposit = 0;
        boolean type = false;

        while (!type){
            System.out.println("Please enter an Account type. Either 'Checking' or 'Savings'.");
            accType = sc.next();

            if (accType.equalsIgnoreCase("Checking") || accType.equalsIgnoreCase("Savings")){
                type = true;
            }
            else {
                System.out.println("Invalid response option!");
            }
        }
        System.out.println("Enter SSN#:");
        SSN = sc.next();
        System.out.println("Enter an Alias:");
        Name = sc.next();
        System.out.println("Enter Address:");
        Address = sc.next();

        boolean iDep = false;
        while (!iDep){
            System.out.print("Please enter an initial deposit:");
            initialDeposit = Double.parseDouble(sc.next());

            if (accType.equalsIgnoreCase("Checking")){
                if (initialDeposit < 150){
                    System.out.println("Minimum of $150 is required to open Checking.");
                }else{
                    iDep = true;
                }
            } else if (accType.equalsIgnoreCase("Savings")){
                if (initialDeposit < 50){
                    System.out.println("Minimum of $50 is required to open Savings.");
                }else {
                    iDep = true;
                }
            }
        }

        Account account;
        if (accType.equalsIgnoreCase("Checking")) {
            account = new Checking(initialDeposit);
        }
        else {
            account = new Savings(initialDeposit);
        }
        Customer customer = new Customer(SSN, Name, Address, account);
        bank.AddCustomer(customer);
    }

    private int selectAccount(){

        ArrayList<Customer> customers = bank.getCustomers();
        if (customers.size() <=0){
            System.out.println("No customers");
            return -1;
        }
        System.out.println("Choose an account");
        for (int i = 0; i < customers.size(); i++){
            System.out.println((i+1)+".)"+customers.get(i).Info());
        }
        int account;
        System.out.println("Please enter your selection: ");
        try {
            account = Integer.parseInt(sc.next()) - 1;
        }catch (NumberFormatException e){
            account = -1;
        }
        return account;
    }

}

