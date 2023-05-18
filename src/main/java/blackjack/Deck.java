package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck;

    public Deck() {
        this.deck = new ArrayList<Card>();
    }

    public void createFullDeck() {

        // generate cards

        for (Suits cardSuits : Suits.values()) {
            for (Values cardValues : Values.values()) {
                this.deck.add(new Card(cardSuits, cardValues));
            }
        }
    }

    // shuffle deck
    public void shuffleDeck() {
        Collections.shuffle(deck, new Random());
    }

    // get card
    public Card getCard(int i) {
        return this.deck.get(i);
    }
    // remove card
    public void removeCard(int i) {
        this.deck.remove(i);
    }

    // add card
    public void addCard(Card addCard) {
        this.deck.add(addCard);
    }

    // get the size of deck
    public int deckSize() {
        return this.deck.size();
    }

    // draw from deck
    public void draw(Deck comingFrom) {
        deck.add(comingFrom.getCard(0));
        comingFrom.removeCard(0);
    }

    // move cards back into the deck to continue playing
    public int moveAllToDeck(Deck moveTo) {
        int thisDeckSize = this.deck.size();


        for (int i = 0; i < thisDeckSize; i++) {
            moveTo.addCard(this.getCard(i));
        }

        for (int i = 0; i < thisDeckSize; i++) {
            this.removeCard(0);
        }
        return thisDeckSize;
    }

        // total value of cards

        public int cardsValue() {
            int totalCardValue = 0;
            int ace = 0;

            for (Card card : this.deck) {
                switch (card.getValue()) {
                    case TWO:
                        totalCardValue += 2;
                        break;
                    case THREE:
                        totalCardValue += 3;
                        break;
                    case FOUR:
                        totalCardValue += 4;
                        break;
                    case FIVE:
                        totalCardValue += 5;
                        break;
                    case SIX:
                        totalCardValue += 6;
                        break;
                    case SEVEN:
                        totalCardValue += 7;
                        break;
                    case EIGHT:
                        totalCardValue += 8;
                        break;
                    case NINE:
                        totalCardValue += 9;
                        break;
                    case TEN, JACK, QUEEN, KING:
                        totalCardValue += 10;
                        break;
                    case ACE:
                        ace++;
                        break;
                }
            }

            for (int i = 0; i < ace; i++) {
                if (totalCardValue > 10) {
                    totalCardValue += 1;
                } else {
                    totalCardValue += 11;
                }
                }

                return totalCardValue;

            }
        }

