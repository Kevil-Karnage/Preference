package Preference.Classes;

import Preference.Enums.DeckTypes;

import java.util.List;

public class Game {
    private DeckTypes deckType; //тип колоды
    private List<Card> deck;    //колода
    private Player[] players;   //игроки
    private Round[] rounds;     //раунды

    public Game() {
        System.out.println("\nЗапущена игра Преферанс\n");

    }

    public DeckTypes getDeckType() {
        return deckType;
    }

    public void setDeckType(DeckTypes deckType) {
        this.deckType = deckType;
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Round[] getRounds() {
        return rounds;
    }

    public Round getRound(int numberRound) {
        return rounds[numberRound];
    }

    public void setRounds(Round[] rounds) {
        this.rounds = rounds;
    }
}
