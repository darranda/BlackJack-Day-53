package blackjack;


public class Game {
    private int wins, losses, pushes;

    public Game() {
        wins = 0;
        losses = 0;
        pushes = 0;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public int getPushes() {
        return pushes;
    }

    public void checkOutcome(Deck playerHand, Deck dealerHand) {
        int playerValue = playerHand.cardsValue();
        int dealerValue = dealerHand.cardsValue();

        if (playerValue > 21) {
            System.out.println("You have gone over 21. You busted!");
            losses++;
        } else if (dealerValue > 21) {
            wins++;
        } else if (playerValue > dealerValue) {
            wins++;
        } else if (playerValue < dealerValue) {
            losses++;
        } else {
            pushes++;
        }
    }
}
