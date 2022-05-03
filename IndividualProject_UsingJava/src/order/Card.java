package order;

import java.util.Objects;

public class Card {
    private String cardNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return Objects.equals(getCardNumber(), card.getCardNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardNumber());
    }

    public Card(String cardNumber){
        this.cardNumber = cardNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
