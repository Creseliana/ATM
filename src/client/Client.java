package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Client extends ClientAccount {

    public Client() {
        super();
    }

    public Client(String userCardNumber, int userPin) {
        super(userCardNumber, userPin);
    }

    public boolean getClientCardNumberInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Enter card number: ");
            setUserCardNumber(reader.readLine());
        } catch (IOException e) {
            System.out.println("Error: can't read from the stream.");
            return false;
        }
        return true;
    }

    public boolean getClientPinInput() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                System.out.println("Enter pin: ");
                setUserPin(Integer.parseInt(reader.readLine()));
            } catch (ClassCastException e) {
                System.out.println("Error: pin should be int.");
                return false;
            }
        } catch (IOException e) {
            System.out.println("Error: can't read from the stream.");
            return false;
        }
        return true;
    }
}
