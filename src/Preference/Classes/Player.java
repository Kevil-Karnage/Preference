package Preference.Classes;

import Preference.Enums.CardsNominal;
import Preference.Enums.CardsSuits;
import Preference.Enums.CountTricks;
import Preference.Enums.TrickTrump;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int id;                     //ID игрока
    private String name;                //имя игрока в игре
    private List<Card> cards;           //карты на руках
    private int score;                  //счёт за всю игру
    private Bet maxBet;                 //максимальная ставка, которую он может поставить
    private boolean isPlaced = true;    //может сделать ставку или нет
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.cards = new ArrayList<>();

    }

    public Card move() {
        return cards.remove(cards.size() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        //сортируем карты по масти, а карты каждой масти по номиналу
        this.cards = sortForSuit(cards);
        printAllCards();
        searchMaxBet();
    }

    private List<Card> sortForSuit(List<Card> cards) {
        Card[] cardsArray = cardsListToArray(cards);
        Card swap;
        boolean ifSorted = true;
        while (ifSorted) {
            ifSorted = false;
            for (int i = 0; i < cards.size() - 1; i++) {
                if (cardsArray[i].getSuit().getRank() >
                        cardsArray[i + 1].getSuit().getRank()) {
                    ifSorted = true;
                    swap = cardsArray[i];
                    cardsArray[i] = cardsArray[i + 1];
                    cardsArray[i + 1] = swap;
                } else if (cardsArray[i].getSuit().getRank() ==
                        cardsArray[i + 1].getSuit().getRank()) {
                    if (cardsArray[i].getNominal().getRank() >
                            cardsArray[i + 1].getNominal().getRank()) {
                        ifSorted = true;
                        swap = cardsArray[i];
                        cardsArray[i] = cardsArray[i + 1];
                        cardsArray[i + 1] = swap;
                    }
                }
            }
        }
        return ArrayToCardsList(cardsArray);
    }

    private List<Card> ArrayToCardsList(Card[] array) {
        List<Card> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return list;
    }

    private Card[] cardsListToArray(List<Card> cards) {
        Card[] array = new Card[cards.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = cards.get(i);
        }
        return array;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int score) {
        this.score += score;
    }

    public Bet getMaxBet() {
        return maxBet;
    }

    private void searchMaxBet() {
        if (cards.size() != 0) {
            int maxCountTricksWithTrump = 0;
            int maxCountTricksNoTrump = 0;
            CardsSuits bestSuit = CardsSuits.SPADE;

            for (CardsSuits suit: CardsSuits.values()) {
                int countTricksInThisSuit = 0;
                for (int i = cards.size() - 1; i > -1; i--) {
                    Card iCard = cards.get(i);
                    CardsSuits suitICard = iCard.getSuit();
                    Card lastThisSuitsCard = null;
                    if (suitICard.getRank() == suit.getRank()) {
                        if (lastThisSuitsCard == null) {
                            if (iCard.getNominal().getRank() != CardsNominal.getMaxRank()) {
                                break;
                            }
                            lastThisSuitsCard = iCard;
                        }
                        if (iCard.getNominal().getRank() == lastThisSuitsCard.getNominal().getRank() - 1) {
                            countTricksInThisSuit++;
                            lastThisSuitsCard = iCard;
                        } else {
                            break;
                        }
                    }
                }

                if (countTricksInThisSuit > maxCountTricksWithTrump) {
                    maxCountTricksWithTrump = countTricksInThisSuit;
                    bestSuit = suit;
                }

                maxCountTricksNoTrump += countTricksInThisSuit;
            }
            if (maxCountTricksNoTrump == 0) {
                maxBet = new Bet(
                        CountTricks.MISER,
                        TrickTrump.get(bestSuit.getRank()),
                        this
                );
            } else if (maxCountTricksWithTrump < 6) {
                if (maxCountTricksNoTrump < 6) {
                    maxBet = new Bet(
                            CountTricks.PASS,
                            TrickTrump.DEFAULT,
                            this
                    );
                } else {
                    maxBet = new Bet (
                            CountTricks.get(maxCountTricksNoTrump),
                            TrickTrump.NO_TRUMP,
                            this
                    );
                }
            } else {
                maxBet = new Bet (
                        CountTricks.get(maxCountTricksWithTrump),
                        TrickTrump.get(bestSuit.getRank()),
                        this
                );
            }
        }
    }

    private void printAllCards() {
        System.out.println(name);
        for (int i = 0; i < cards.size(); i++) {
            System.out.println(
                    cards.get(i).getSuit().getRank() +
                    " " +
                    cards.get(i).getNominal().getName()
            );
        }
    }

    public void placeBet(Round round) {
        Bet roundBet = round.getBet();
        TrickTrump roundBetTrump = roundBet.getTrickTrump();

        Bet playerMaxBet = getMaxBet();
        TrickTrump playerMaxBetTrump = playerMaxBet.getTrickTrump();
        CountTricks playerMaxBetCountTricks = playerMaxBet.getCountTricks();

        Bet playerCurrentBet = new Bet(CountTricks.PASS,
                                       TrickTrump.DEFAULT,
                                 this
        );

        if (roundBet.getTrumpRank() >= playerMaxBet.getTrumpRank()) {
            isPlaced = false;
        } else {
            isPlaced = true;
            playerCurrentBet.setTrickTrump(playerMaxBetTrump);
            if (roundBetTrump.getRank() < playerMaxBetTrump.getRank()) {
                playerCurrentBet.setCountTricks(playerMaxBetCountTricks);
            } else {
                playerCurrentBet.setCountTricks(
                        CountTricks.get(playerMaxBetCountTricks.getRank() + 1)
                );
            }
            round.setBet(playerCurrentBet);
        }
    }

    public boolean isPlaced() {
        return isPlaced;
    }
}
