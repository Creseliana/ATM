package bank;

import client.Client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Bank {
    private static final int PIN_TRY = 3;
    private static ArrayList<CardInfo> cardList = new ArrayList<>();
    private static final String cardFilePath = "src//bank//cards.txt";

    public static boolean getCardsFromFile() {
        String line;
        try (BufferedReader reader = new BufferedReader(new FileReader(cardFilePath))) {
            while ((line = reader.readLine()) != null) {
                cardList.add(getCard(line));
            }
        } catch (IOException e) {
            System.out.println("Error: cannot open or read file with card info.");
            return false;
        }
        return !cardList.isEmpty();
    }

    private static CardInfo getCard(String line) {
        String splittedLine[];
        CardInfo card = new CardInfo();

        splittedLine = line.split(" ");

        if (splittedLine.length != CardInfo.CARD_FIELDS) {
            System.out.println("Error: Wrong line format."); //todo return where
        }
        card.setCardNumber(splittedLine[0]);
        if (!CardInfo.checkCardFormat(card.getCardNumber())) {
            System.out.println("Error: Wrong line format: card number doesn't match format."); //todo return where
        }
        try {
            card.setPin(Integer.parseInt(splittedLine[1]));
        } catch (NumberFormatException e) {
            System.out.println("Error: Wrong line format: pin is not int."); //todo return where
        }
        try {
            card.setBalance(Integer.parseInt(splittedLine[2]));
        } catch (NumberFormatException e) {
            System.out.println("Error: Wrong line format: balance is not int."); //todo return where
        }
        card.setBlockStatus(Boolean.parseBoolean(splittedLine[3]));
        try {
            card.setBlockDateTime(Long.parseLong(splittedLine[4]));
        } catch (NumberFormatException e) {
            System.out.println("Error: Wrong line format: date and time should be long milliseconds."); //todo return where
        }
        return card;
    }

    private boolean checkClientInputCardNumber(Client client) {
        if (!CardInfo.checkCardFormat(client.getUserCardNumber())) {
            System.out.println("Info: Wrong format of card number.");
            return false;
        }
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardNumber().equals(client.getUserCardNumber())) {
                return true;
            }
        }
        System.out.println("Info: no such card number.");
        return false;
    }

    private boolean checkClientInputCardPin(Client client) {
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardNumber().equals(client.getUserCardNumber())) {
                if (cardList.get(i).getPin() == client.getUserPin()) {
                    return true;
                }
            }
        }
        System.out.println("Info: Wrong pin.");
        return false;
    }

    public boolean clientAuth(Client client) {
        int pinInputNum = 0;
        if (client.getClientCardNumberInput() && client.getClientPinInput()) {
            if (checkClientInputCardNumber(client)) {
                while (pinInputNum < PIN_TRY) {
                    if(checkClientInputCardPin(client)) {
                        return true;
                    } else {
                        pinInputNum++;
                        client.getClientPinInput();
                    }
                }
                if (pinInputNum == PIN_TRY) {
                    System.out.println("Info: Pin was entered incorrectly 3 times. Your card is blocked for 24 hours.");
                    blockCard(client.getUserCardNumber());
                }
            }
        }
        return false;
    }

    private void blockCard(String cardNumber) {
        Date currentDate = new Date();
        for (int i = 0; i < cardList.size(); i++) {
            if (cardList.get(i).getCardNumber().equals(cardNumber)) {
                cardList.get(i).setBlockStatus(true);
                cardList.get(i).setBlockDateTime(currentDate.getTime());
            }
        }
    }
}