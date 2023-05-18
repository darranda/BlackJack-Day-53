package blackjack;

public enum Suits {
    CLUB ("Clubs"),
    DIAMOND ("Diamonds"),
    HEART("Hearts"),
    SPADE("Spades");

    String suiteName;

    Suits ( String suiteName) {
        this.suiteName = suiteName;
    }

    public String  toString () {
        return suiteName;
    }


}
