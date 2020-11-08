package Preference.Classes;

import java.util.HashMap;
import java.util.Map;

public class Fight {
    private Map<Integer, Card> cards;
    private int idWinnerPlayer;

    public Fight() {
        cards = new HashMap<>();
    }

    public Map<Integer, Card> getCards() {
        return cards;
    }

    public void setCards(Map<Integer, Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card, int idPlayer) {
        cards.put(idPlayer, card);

        //сразу ищем самую старшую карту из тех что есть в наличии
        if(cards.size() == 1) {
            idWinnerPlayer = idPlayer;
        } else {
            int n = cards.get(idWinnerPlayer).compareTo(card);
            if (n > 0) {
                idWinnerPlayer = idPlayer;
            }
        }
    }

    public int getIdWinnerPlayer() {
        return idWinnerPlayer;
    }
}
