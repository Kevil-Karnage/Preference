package Preference.Classes;

import Preference.Enums.CountTricks;
import Preference.Enums.RoundTypes;
import Preference.Enums.TrickTrump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Round {
    private List<Card> deck;                    //колода

    private Card[] buiIn;                       //прикуп
    private Fight[] fights;                     //бои
    private Map<Integer, Integer> countTricks;  //количество взяток игроков
    private Bet bet;                            //ставка
    private RoundTypes roundType;               //тип раунда

    public Round(Player[] players) {
        this.deck = new ArrayList<>();
        countTricks = new HashMap<>();
        for (int i = 0; i < players.length; i++) {
            countTricks.put(players[i].getId(), 0);
        }

        this.buiIn = new Card[2];
        this.fights = new Fight[10];
        for (int i = 0; i < 10; i++) {
            fights[i] = new Fight();
        }
        this.bet = new Bet(CountTricks.DEFAULT, TrickTrump.DEFAULT, null);
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void setDeck(List<Card> deck) {
        this.deck = deck;
    }

    public Card[] getBuiIn() {
        return buiIn;
    }

    public void setBuiIn(Card[] buiIn) {
        this.buiIn = buiIn;
    }

    public Fight[] getFights() {
        return fights;
    }

    public void setFights(Fight[] fights) {
        this.fights = fights;
    }

    public Map<Integer, Integer> getCountTricks() {
        return countTricks;
    }

    public void setCountTricks(Map<Integer, Integer> countTricks) {
        this.countTricks = countTricks;
    }

    public int getPlayerCountTricks(int idPlayer) {
        return countTricks.get(idPlayer);
    }

    public void addNTricksToPlayer(int n, int idPlayer) {
        countTricks.put(idPlayer, countTricks.get(idPlayer) + n);
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
    }

    public RoundTypes getRoundType() {
        return roundType;
    }

    public void setRoundType(RoundTypes roundType) {
        this.roundType = roundType;
    }

    public void printBet() {
        if (roundType == RoundTypes.ALL_PASS) {
            System.out.println("Все игроки спасовали");
        } else {
            System.out.print("\n" + bet.getPlayer().getName());
            System.out.print(" заключил контракт: " + bet.getCountTricks().getName());
            if (bet.getCountTricks() != CountTricks.MISER) {
                System.out.print(" " + bet.getTrickTrump().getName() + "\n");
            }
        }
    }
}
