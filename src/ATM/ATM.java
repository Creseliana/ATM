package ATM;

import bank.Bank;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ATM {


    private static final String ATM_BalanceFilePath = "src//ATM_Balance.txt";
    private int ATM_Balance;
    private Bank bank = new Bank();
    private String currentUserCardNumber;

    public ATM() {
        if (!getATM_Balance()) {
            System.out.println("Error: can't get ATM.ATM balance."); //todo return where
        }
        System.out.println("Info: ATM.ATM is working correctly.");
    }

    private boolean getATM_Balance() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ATM_BalanceFilePath))) {
            try {
                ATM_Balance = Integer.parseInt(reader.readLine());
            } catch (ClassCastException e) {
                System.out.println("Error: ATM.ATM balance should be int.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error: cannot open or read file with ATM.ATM balance info.");
            return false;
        }
        return ATM_Balance >= 0;
    }
}
