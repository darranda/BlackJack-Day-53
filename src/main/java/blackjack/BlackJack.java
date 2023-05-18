package blackjack;

import java.util.Scanner;

public class BlackJack {

    public static void main(String[] args) {

        System.out.println("Welcome to Blackjack!");

        //Create and start the Game
        Game blackJack = new Game();

        // Create the playing deck
        Deck playingDeck = new Deck();
        playingDeck.createFullDeck();
        playingDeck.shuffleDeck();

        // Create hands for the player and the dealer - hands are created from methods that are made in the deck class
        Deck playerHand = new Deck();
        Deck dealerHand = new Deck();


        double playerMoney = 500.00;

        Scanner input = new Scanner(System.in);

        // Game loops

        while (playerMoney > 0) {

            //players turn

            System.out.println("You have $" + playerMoney + ", how much are you betting?");
            double playersBetMoney = input.nextDouble();

            if (playersBetMoney > playerMoney) {
                System.out.println("You do not have enough money for that bet!");
                break;
            }
            boolean endRound = false;

            //lets deal

            playerHand.draw(playingDeck);
            playerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);
            dealerHand.draw(playingDeck);

            int hitOrStand;


            while (true) {

                //prints hands

                System.out.println("Dealer's Hand looks like this: " + dealerHand.getCard(0).toString());
                System.out.println("The second card is face down.");

                System.out.println("Your hand looks like this: ");
                for (int i = 0; i < playerHand.deckSize(); i++) {
                    System.out.print(playerHand.getCard(i).toString() + " ");
                }
                ;

                System.out.println("Your hand total is " + playerHand.cardsValue());

                //player hit or stand?

                try {
                    System.out.print("Player, would you like to hit or stand? ");
                    System.out.println("Enter 1) Hit or 2) Stand");
                    hitOrStand = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input");
                    input.next();
                    continue;
                }

                //player hits
                if (hitOrStand == 1) {
                    playerHand.draw(playingDeck);
                    System.out.println("You drew " + playerHand.getCard(playerHand.deckSize() - 1).toString());
                    System.out.println("Your now have: " + playerHand.cardsValue());

                    // Bust if > 21
                    if (playerHand.cardsValue() > 21) {
                        System.out.println("You have gone over 21. You busted!");
                        playerMoney -= playersBetMoney;
                        endRound = true;
                        break;
                    }
                }
                if (hitOrStand == 2) {
                    break;
                }

                System.out.println("Dealer's hand: " + dealerHand.getCard(0).toString());

                if ((dealerHand.cardsValue() > playerHand.cardsValue()) && !endRound) {
                    System.out.println("Dealer wins!");
                    playerMoney -= playersBetMoney;
                    endRound = true;
                }
                while ((dealerHand.cardsValue()) < 17 && !endRound) {
                    dealerHand.draw(playingDeck);
                    System.out.println("Dealer drew: " + dealerHand.getCard(dealerHand.deckSize() - 1).toString());
                }
                System.out.println("Dealer's hand: " + dealerHand.cardsValue());

                if ((dealerHand.cardsValue() > 21) && !endRound) {
                    System.out.println("Dealer busts! You win!");
                    playerMoney += playersBetMoney;
                    endRound = true;

                } else if ((playerHand.cardsValue() > 21) && !endRound) {
                    System.out.println("You have gone over 21. You busted! Dealer wins!");
                    playerMoney -= playersBetMoney;
                    endRound = true;

                } else if ((dealerHand.cardsValue() > playerHand.cardsValue()) && !endRound) {
                    System.out.println("Uh Oh, You lose!");

                } else if ((playerHand.cardsValue() > dealerHand.cardsValue()) && !endRound) {
                    System.out.println("Yay, You win!");
                    endRound = true;

                } else {
                    System.out.println("Push. Its a tie!");
                    endRound = true;

                }
            }

            if ((playerHand.cardsValue() > dealerHand.cardsValue()) && !endRound) {
              System.out.println("You win the hand!");
                playerMoney += playersBetMoney;
            } else if (!endRound) {
                System.out.println("You lose the hand!");
                playerMoney -= playersBetMoney;
           }


            playerHand.moveAllToDeck(playingDeck);
            dealerHand.moveAllToDeck(playingDeck);
            System.out.println("End of Round");
        }
        blackJack.checkOutcome(playerHand, dealerHand);


            System.out.println("Game Over! No money left !");

        // display  scores
        System.out.println("Wins: " + blackJack.getWins());
        System.out.println("Losses: " + blackJack.getLosses());
        System.out.println("Pushes: " + blackJack.getPushes());
        }
    }

