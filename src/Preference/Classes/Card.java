package Preference.Classes;

import Preference.Enums.CardsNominal;
import Preference.Enums.CardsSuits;

public class Card implements Comparable<Card>{
    private CardsNominal nominal;
    private CardsSuits suit;

    public Card(CardsNominal nominal, CardsSuits suit) {
        this.nominal = nominal;
        this.suit = suit;
    }

    public CardsNominal getNominal() {
        return nominal;
    }

    public void setNominal(CardsNominal nominal) {
        this.nominal = nominal;
    }

    public CardsSuits getSuit() {
        return suit;
    }

    public void setSuit(CardsSuits suit) {
        this.suit = suit;
    }

    @Override
    public int compareTo(Card second) {
        return second.nominal.getRank() -
                this.nominal.getRank();
    }
}
