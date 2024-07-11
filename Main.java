import java.util.Scanner;

public class Main extends BankAccount {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("-------------WELCOME TO BANK MANAGEMENT SYSTEM-------------");
        System.out.println("1.Register \n2.Exit");
        System.out.print("Enter Your Choice -> ");
        int choice = scn.nextInt();
        if (choice == 1) {
            BankAccount BankAccount = new BankAccount();
            BankAccount.register();
            while (true) {
                System.out.println("\n1.Login \n2.Exit");
                System.out.print("Enter Your Choice -> ");
                int ch = scn.nextInt();
                if (ch == 1) {
                    if (BankAccount.login()) {
                        System.out.println("\nWELCOME BACK " + BankAccount.name + "\n");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("1.Withdraw \n2.Deposit \n3.Check Balance \n4.Exit");
                            System.out.print("Enter Your Choice -> ");
                            int c = scn.nextInt();
                            switch (c) {
                                case 1 :
                                    BankAccount.withdraw();
                                    break;
                                case 2 :
                                    BankAccount.deposit();
                                    break;
                                case 3 :
                                    BankAccount.checkBalance();
                                    break;
                                case 4 :
                                    isFinished = true;
                                    break;
                                default : System.out.println("\nInvalid Choice");
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}

interface BankSystem{
    void register();
    boolean login();
    void withdraw();
    void deposit();
    void checkBalance();
}

class BankAccount implements BankSystem{
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;

    public BankAccount(){

    }
    public BankAccount(String name, String userName, String password, String accountNo) {
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.accountNo = accountNo;
    }

    public void register() {
        Scanner scn= new Scanner(System.in);
        System.out.println("Enter your Name: ");
        this.name=scn.nextLine();
        System.out.print("Enter your Username: ");
        this.userName = scn.nextLine();
        System.out.print("Enter your Password: ");
        this.password = scn.nextLine();
        System.out.print("Enter your Account Number: ");
        this.accountNo = scn.nextLine();
        System.out.println("Registration completed..kindly login...");
    }

    public boolean login() {
        boolean isLogin=false;
        Scanner scn= new Scanner(System.in);
        while(!isLogin){
            System.out.println("Enter your Username: ");
            String username= scn.nextLine();
            if(username.equals(userName)){
                while(!isLogin){
                    System.out.println("Enter your Password: ");
                    String pass= scn.nextLine();
                    if(pass.equals(password)){
                        System.out.println("Login successful!");
                        isLogin=true;
                    }
                    else {
                        System.out.println("Incorrect Password");
                    }
                }
            }
            else{
                System.out.println("Username not found...");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.print("Enter amount to withdraw -> ");
        Scanner scn = new Scanner(System.in);
        float amt= scn.nextFloat();
        if(balance>=amt){
            transactions++;
            balance-=amt;
            System.out.println("Withdraw successful!");
        }
        else{
            System.out.println("Insufficient Balance");
        }
    }

    public void deposit() {
        System.out.println("Enter amount to deposit -> ");
        Scanner scn=new Scanner(System.in);
        float amt= scn.nextFloat();
        if (amt<= 100000f) {
            transactions++;
            balance+=amt;
            System.out.println("Successfully Deposited");
        }
        else {
            System.out.println("Can't deposit...Limit is 100000.00");
        }
    }

    public void checkBalance() {
        System.out.println("Your account balance is: " + balance + " Rs");
    }
}
