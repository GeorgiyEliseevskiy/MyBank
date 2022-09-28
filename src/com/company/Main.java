package com.company;


import java.sql.SQLException;
import java.util.*;
import java.lang.*;

import static com.company.Bank.startUse;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DB db = new DB();

    }
}



/* class Bank {

    public static Scanner scanner = new Scanner(System.in);

    int name_account;


    protected static Map<Integer, Double> base_accounts = new HashMap<>();
    protected static Map<Integer, Double> base_accounts_deposits = new HashMap<>();
    protected static Map<Integer, String> base_accounts_with_name = new HashMap<>();

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return false;
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public static void add_deposit_in_base(int name_deposit, double amount) {
        base_accounts_deposits.put(name_deposit, amount);
    }

    public static void create_deposit(int name_account, double amount) {

        boolean hasNameAccount = true;
        int deposit = name_account;

        add_deposit_in_base(deposit, amount);


        String name_person = base_accounts_with_name.get(name_account);

        System.out.printf("%s, number your deposit is %d\n", name_person, deposit);

        System.out.println("Deposit was created!!!");


    }

    public static void startUse() throws NullPointerException {
        System.out.println("Hello! Please log in system. Don't have account ? Let's create! Choice action. ");
        boolean isItMainTrue = true;
        while (isItMainTrue) {
            boolean hasCorrect = true;
            int name_account = 0;
            boolean hasCorrect1 = false;
            String str = null;
            System.out.println("1 - log in, 2 sign up");
            while (hasCorrect) {
                str = scanner.nextLine();
                hasCorrect = isNumeric(str);
            }
            int choice = Integer.parseInt(str);
            hasCorrect = true;
            String name_acc = null;
            double balance = 0;
            boolean isItWork = true;
            while (isItWork) {
                switch (choice) {
                    case 1:
                        System.out.println("Hello! Enter the name account: ");
                        while (hasCorrect) {
                            name_acc = scanner.nextLine();
                            hasCorrect = isNumeric(name_acc);
                            if (hasCorrect1) {
                                System.out.println("Please enter the correct value");
                            }
                            hasCorrect1 = hasCorrect;
                        }
                        name_account = Integer.parseInt(name_acc);
                        if (checkAccount(name_account)) {
                            System.out.println("You entered!");
                        } else {
                            System.out.println("Account not found.");
                        }
                        break;
                    case 2:
                        System.out.println("For sign up: Enter the name and balance.\n ");
                        String name_person = scanner.nextLine();
                        while (hasCorrect) {
                            str = scanner.nextLine();
                            hasCorrect = isNumeric(str);
                            if (hasCorrect1 == true) {
                                System.out.println("Please enter the correct value");
                            }
                            hasCorrect1 = hasCorrect;
                        }

                        balance = Double.parseDouble(str);
                        name_account = create_account(name_person, balance);
                }

                System.out.println("Your balance is " + balance + "\n");

                double amount = 0;

                System.out.println(name_account);
                boolean work = true;
                while (work) {
                    hasCorrect = true;
                    System.out.println("Choice action: 1 - check balance, 2- top up account, 3- withdraw money, 4- transfer, 5- deposit, 6- leave in menu, 7- leave program ");
                    int choice2 = scanner.nextInt();
                    switch (choice2) {
                        case 1:
                            double balance1 = checkBalance(name_account);
                            System.out.println("Your balance is " + balance1);
                            break;
                        case 2:
                            System.out.println("Please enter the amount: ");
                            while (hasCorrect) {
                                str = scanner.nextLine();
                                hasCorrect = isNumeric(str);

                                if (hasCorrect1) {
                                    System.out.println("Please enter the correct value");
                                }
                                hasCorrect1 = hasCorrect;
                            }
                            amount = Double.parseDouble(str);
                            top_up_account(name_account, amount);
                            System.out.println("Your balance is " + checkBalance(name_account));
                            break;

                        case 3:
                            System.out.println("Please enter the amount: ");
                            while (hasCorrect) {
                                str = scanner.nextLine();
                                hasCorrect = isNumeric(str);
                                if (hasCorrect1) {
                                    System.out.println("Please enter the correct value");
                                }
                                hasCorrect1 = hasCorrect;
                            }
                            amount = Double.parseDouble(str);
                            withdraw_money(name_account, amount);
                            System.out.println("Your balance is " + checkBalance(name_account));
                            break;
                        case 4:
                            System.out.println("Enter name account: ");
                            int name_accounts_for_transfer = 0;

                            boolean checkAcc = false;
                            hasCorrect = true;

                            String str1 = null;
                            while(!checkAcc) {
                                while (hasCorrect) {
                                    str1 = scanner.nextLine();
                                    hasCorrect = isNumeric(str1);
                                    if(!hasCorrect) {
                                        break;
                                    }
                                    if (hasCorrect1) {
                                        System.out.println("Enter the correct account");
                                    }
                                }
                                name_accounts_for_transfer = Integer.parseInt(str1);
                                hasCorrect = checkAccount(name_accounts_for_transfer);
                                checkAcc = true;
                                hasCorrect1 = hasCorrect;
                            }
                            System.out.println("Enter amount: ");
                            hasCorrect1 = false;
                            boolean hasCorrect2 = true;
                            int amounts_for_transfer = 0;
                            double current_balance = 0;
                            str = null;

                            while(hasCorrect2) {
                                while (hasCorrect) {
                                    str = scanner.nextLine();
                                    hasCorrect = isNumeric(str);
                                }
                                System.out.println(str);
                                amounts_for_transfer = Integer.parseInt(str);
                                current_balance = base_accounts.get(name_account);
                                System.out.println(amounts_for_transfer);
                                System.out.println(current_balance);

                                if (current_balance < amounts_for_transfer) {
                                    System.out.println("Insufficient funds for the operation\n Enter the correct amount");
                                    hasCorrect = true;
                                }
                                else {
                                    hasCorrect2 = false;
                                    hasCorrect = false;
                                }
                            }

                            double balance_current_account, balance_for_transfer, end_balance = 0;

                            balance_current_account = base_accounts.get(name_account);
                            balance_current_account = balance_current_account - amounts_for_transfer;

                            boolean overLap = true;

                            /* while(overLap) {
                                current_balance = base_accounts.get(name_account);
                                if (current_balance > amounts_for_transfer) {

                                }
                            }

                            balance_for_transfer = base_accounts.get(name_accounts_for_transfer);

                            System.out.println("Amount for transfer: " + balance_for_transfer);
                            end_balance = balance_for_transfer + amounts_for_transfer;

                            base_accounts.put(name_accounts_for_transfer, end_balance);
                            base_accounts.put(name_account, balance_current_account);

                            System.out.println("Balance current account: " + base_accounts.get(name_account));
                            System.out.println("Balance acoount for transfer: " + base_accounts.get(name_accounts_for_transfer));

                            break;
                        case 5:
                            hasCorrect1 = false;
                            System.out.println("Please choice action: 1- create deposit, 2- check deposit balance");
                            while (hasCorrect) {
                                str = scanner.nextLine();
                                hasCorrect = isNumeric(str);
                            }
                            int choice3 = Integer.parseInt(str);
                            switch (choice3) {
                                case 1:
                                    boolean hasDeposit = true;
                                    while (hasDeposit) {

                                        hasDeposit = base_accounts_deposits.containsKey(name_account);

                                        if (!hasDeposit) {
                                            double sum = base_accounts.get(name_account);
                                            System.out.println("Please enter amount: ");
                                            hasCorrect = true;
                                            while (hasCorrect) {
                                                str = scanner.nextLine();
                                                hasCorrect = isNumeric(str);
                                            }

                                            double amount1 = Double.parseDouble(str);

                                            double end = sum - amount1;

                                            base_accounts.put(name_account, end);
                                            create_deposit(name_account, amount1);
                                            System.out.println("Balance Bank account is: " + checkBalance(name_account));

                                            break;
                                        } else {
                                            System.out.println("You have already have deposit.");
                                            break;
                                        }
                                    }
                                    break;
                                case 2:
                                    hasDeposit = true;
                                    while (hasDeposit) {

                                        hasDeposit = base_accounts_deposits.containsKey(name_account);
                                        if (hasDeposit == false) {
                                            System.out.println("You don't have a deposit");
                                            break;
                                        } else {
                                            System.out.println("Balance of deposit is " + checkBalanceDeposit(name_account));
                                            break;
                                        }
                                    }
                                    break;
                            }

                            break;
                        case 6:
                            System.out.println("Do you want to leave in menu ? 1 - Yes, 2- no");
                            hasCorrect = true;
                            hasCorrect1 = false;
                            while (hasCorrect) {
                                str = scanner.nextLine();
                                hasCorrect = isNumeric(str);
                                if (hasCorrect1 == true) {
                                    System.out.println("Please enter the correct value");
                                }
                                hasCorrect1 = hasCorrect;
                            }
                            int deb = Integer.parseInt(str);
                            if (deb == 1) {
                                work = false;
                                isItWork = false;
                                choice = 0;
                            } else {
                                System.out.println("Okey");
                            }
                            break;

                        case 7:
                            System.out.println("Do you really want to leave? 1- yes, 2- no");
                            while (hasCorrect) {
                                str = scanner.nextLine();
                                hasCorrect = isNumeric(str);
                                if (hasCorrect1) {
                                    System.out.println("Please enter the correct value");
                                }
                                hasCorrect1 = hasCorrect;
                            }
                            int des = Integer.parseInt(str);
                            if (des == 1) {
                                work = false;
                                isItWork = false;
                                isItMainTrue = false;
                                System.out.println("Goodbye!");
                            } else System.out.println("Great! ");
                            break;
                        case 8:
                            System.out.println(base_accounts);
                            System.out.println(base_accounts_with_name);
                            System.out.println(base_accounts_deposits);
                            break;
                        default:
                            System.out.println("Unknown operation. ");
                            break;
                    }

                }
            }
        }
    }

    public static void top_up_account(int name_account, double amount) {
        double sum = base_accounts.get(name_account);
        double end = sum + amount;
        base_accounts.put(name_account, end);
    }

    public static void withdraw_money(int name_account, double amount) {
        double sum = base_accounts.get(name_account);
        double end = sum - amount;
        base_accounts.put(name_account, end);
    }

    public static boolean checkAccount(int name_account) {
        return base_accounts.containsKey(name_account);
    }

    public static double checkBalance(int name_account) {
        return base_accounts.get(name_account);
    }

    public static double checkBalanceDeposit(int name_account) {
        return base_accounts_deposits.get(name_account);
    }

    public static void add_account_in_base(int name_account, double balance) {
        base_accounts.put(name_account, balance);
    }

    public void delete_account() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello. Do you really want to delete your account ? Enter 1, if you sure. Enter 2, if you want to cancel this operation. Enter: ");

        boolean hasNotCorrectAnswer = true;
        while (hasNotCorrectAnswer) {
            try {
                int answer = scanner.nextInt();

                switch (answer) {
                    case 1:
                        System.out.println("Please confirm your decision: 1/2: ");
                        int end_answer = scanner.nextInt();
                        if (end_answer == 1) {
                            base_accounts.remove(name_account);
                            System.out.println("Your account was deleted");
                        } else System.out.println("Good choice");
                        break;
                    case 2:
                        System.out.println("Don't scare us anymore please ^_^");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static int create_account(String name_person, double bank_account) {
        System.out.println("Please waiting " + name_person + "." + " Your account is creating.");

        boolean hasNameAccount = true;
        int name_account = 0;
        while (hasNameAccount) {
            name_account = (int) (Math.random() * 100000);
            hasNameAccount = base_accounts.containsKey(name_account);
            if (!hasNameAccount) {
                add_account_in_base(name_account, bank_account);
                break;
            }
        }
        base_accounts_with_name.put(name_account, name_person);
        System.out.printf("%s, number your account is %d\n", name_person, name_account);
        System.out.println("Account was created!!!");
        return name_account;
    }

    static class Client {
        String name, surname;
        int age, name_account;
        double balance;

        public Client(String name, String surname, int name_account, int age) {
            this.name = name;
            this.name_account = name_account;
            this.age = age;
            this.surname = surname;
        }

        public void info() {
            System.out.println("Name: " + name + " .Surname: " + surname + ". Age: " + age + ". Balance: " + balance);
        }
    }
}

*/


