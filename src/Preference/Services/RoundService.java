package Preference.Services;

import Preference.Classes.*;
import Preference.Enums.*;

import java.util.ArrayList;
import java.util.List;

public class RoundService {

    /**
     * Добавляем колоду в раунд
     * @param round
     */
    public static void addDeckInRound(Round round) {
        List<Card> deck = new ArrayList<>();
        for (CardsNominal nominal: CardsNominal.values()) {
            for (CardsSuits suite: CardsSuits.values()) {
                Card card = new Card(nominal, suite);
                deck.add(card);
            }
        }
        System.out.println("Добавлена колода из 36 карт\n");
        round.setDeck(deck);
    }

    /**
     * Добавляем все бои в раунд
     * @param round
     */
    public static void addFightsInRound(Round round) {
        Fight[] fights = new Fight[10];
        //round.setFights(fights);
    }

    /**
     *  Перемешиваем колоду
     * @param round
     */
    public static void mixDeck(Round round) {
        List<Card> deck = round.getDeck();
        List<Card> mixedDeck = new ArrayList<>();
        while(deck.size() != 0) {
            int n = (int) (deck.size() * Math.random());
            mixedDeck.add(deck.get(n));
            deck.remove(n);
        }
        round.setDeck(mixedDeck);
    }

    /**
     * Раздача карт игрокам
     * @param game
     * @param round
     */
    public static void distributionCards(Game game, Round round) {
        List<Card>[] playersCards = new ArrayList[game.getPlayers().length];

        for (int i = 0; i < playersCards.length; i++) {
            playersCards[i] = new ArrayList<>();
        }

        Card[] buiIn = new Card[2];             // прикуп
        List<Card> deck = round.getDeck();      //берём колоду

        //раздаём карты игрокам
        while (deck.size() > 2) {
            int i = 0;
            while (i < 3) {
                playersCards[i].add(deck.get(0));
                deck.remove(0);
                i++;
            }
        }

        //оставшиеся две карты кладём в прикуп
        int i = 0;
        while (deck.size() != 0) {
            buiIn[i] = deck.remove(0);
            i++;
        }

        //сохраняем информацию об игроках в Game
        Player[] players = game.getPlayers();
        for (int j = 0; j < players.length; j++) {
            players[j].setCards(playersCards[j]);
        }
        round.setBuiIn(buiIn);
        game.setPlayers(players);
        System.out.println("\nИгроки получили свои карты\n");
    }

    /**
     * разыгрывание раунда
     * @param fight
     * @param players
     * @return
     */
    public static int playFights(Fight fight, Player[] players) {

        for (int i = 0; i < players.length; i++) {

            Card movedCard = players[i].move();
            fight.addCard(movedCard, players[i].getId());

            System.out.println(
                    players[i].getName() +
                    " выложил карту " +
                    movedCard.getSuit().getName() +
                    " " +
                    movedCard.getNominal().getName());
        }
        return fight.getIdWinnerPlayer();
    }


    public static void trade(Game game, Round round) {
        //торги за прикуп
        Player[] players = game.getPlayers();
        while (tradeIsActive(game)) {
            for (int i = 0; i < players.length; i++) {
                if (players[i].isPlaced()) {
                    players[i].placeBet(round);
                    round.printBet();
                }
            }
        }
        if (isAllPass(game)) {
            round.setRoundType(RoundTypes.ALL_PASS);
        } else if (round.getBet().getCountTricks() == CountTricks.MISER) {
            round.setRoundType(RoundTypes.MISER);
        } else {
            round.setRoundType(RoundTypes.DEFAULT);
        }
    }

    private static boolean isAllPass(Game game) {
        Player[] players = game.getPlayers();
        boolean isAllPass = true;
        for (int i = 0; i < players.length; i++) {
            isAllPass = isAllPass && !players[i].isPlaced();
        }
        return isAllPass;
    }

    private static boolean tradeIsActive(Game game) {
        Player[] players = game.getPlayers();
        boolean isActive = true;
        for (int i = 0; i < players.length - 1; i++) {
            for (int j = i + 1; j < players.length; j++) {
                isActive = isActive &&
                        (players[i].isPlaced() || players[j].isPlaced());
            }
        }
        return isActive;
    }

    public static void contract(Round round) {
        Player player = round.getBet().getPlayer();
        if (player == null) {
            round.setRoundType(RoundTypes.ALL_PASS);
        } else {
            round.setBet(player.getMaxBet());
        }
    }
}
