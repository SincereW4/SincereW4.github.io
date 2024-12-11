import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            // Initialize deck and shuffle
            List<Integer> deck = createDeck();
            Collections.shuffle(deck);

            // Deal initial cards
            List<Integer> playerHand = new ArrayList<>();
            List<Integer> dealerHand = new ArrayList<>();
            playerHand.add(deck.remove(0));
            playerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));
            dealerHand.add(deck.remove(0));

            // Game loop
            boolean playerTurn = true;
            while (playerTurn) {
                // Display hands (hide dealer's first card)
                System.out.println("Your hand: " + playerHand + " (Total: " + calculateHandValue(playerHand) + ")");
                System.out.println("Dealer's hand: [Hidden Card] " + dealerHand.get(1));

                // Player's turn
                if (calculateHandValue(playerHand) == 21) {
                    System.out.println("Blackjack! You win!");
                    playerTurn = false;
                } else if (calculateHandValue(playerHand) > 21) {
                    System.out.println("Bust! You lose.");
                    playerTurn = false;
                } else {
                    System.out.print("Hit or stand? (h/s): ");
                    String choice = scanner.nextLine().toLowerCase();
                    if (choice.equals("h")) {
                        playerHand.add(deck.remove(0));
                    } else if (choice.equals("s")) {
                        playerTurn = false;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                }
            }

            // Dealer's turn
            System.out.println("Dealer's hand: " + dealerHand + " (Total: " + calculateHandValue(dealerHand) + ")");
            while (calculateHandValue(dealerHand) < 17) {
                System.out.println("Dealer hits.");
                dealerHand.add(deck.remove(0));
                System.out.println("Dealer's hand: " + dealerHand + " (Total: " + calculateHandValue(dealerHand) + ")");
            }

            // Determine winner
            if (calculateHandValue(dealerHand) > 21) {
                System.out.println("Dealer busts! You win!");
            } else if (calculateHandValue(dealerHand) > calculateHandValue(playerHand)) {
                System.out.println("Dealer wins.");
            } else if (calculateHandValue(dealerHand) < calculateHandValue(playerHand)) {
                System.out.println("You win!");
            } else {
                System.out.println("Push! It's a tie.");
            }

            // Play again?
            System.out.print("Play again? (y/n): ");
            String playAgainChoice = scanner.nextLine().toLowerCase();
            playAgain = playAgainChoice.equals("y");
        }

        scanner.close();
    }

    // Create a deck of cards (Ace = 11, face cards = 10)
    private static List<Integer> createDeck() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 13; i++) {
            deck.add(i);
        }
        return deck;
    }

    // Calculate the value of a hand, considering Ace as 11 or 1
    private static int calculateHandValue(List<Integer> hand) {
        int value = 0;
        int aceCount = 0;
        for (int card : hand) {
            if (card >= 10) {
                value += 10;
            } else if (card == 1) {
                aceCount++;
                value += 11;
            } else {
                value += card;
            }
        }
        // Adjust Ace value to 1 if necessary to avoid busting
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }
}