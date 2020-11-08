package Preference.Services;

import Preference.Classes.Game;
import Preference.Classes.Player;
import Preference.Classes.Round;
import Preference.Enums.DeckTypes;

public class GameService {

    /**
     * выбор типа колоды
     * @param game
     * @param deckType
     */
    public static void selectGameType(Game game, DeckTypes deckType) {
        game.setDeckType(deckType);
    }

    /**
     * добавляем игроков
     * @param game
     */
    public static void addPlayersInGame(Game game) {
        String[] playersNames = {"Коля", "Игорь", "Саша"};

        Player[] players = new Player[3];
        for (int i = 0; i < 3; i++) {
            players[i] = new Player(i, playersNames[i]);
            System.out.printf("Игрок %s присоединяется к игрe\n", playersNames[i]);
        }
        System.out.println();
        game.setPlayers(players);
        System.out.println("\nВсе игроки готовы к игре\n");
    }

    /**
     * добавляем n раундов
     * @param game
     * @param countRounds
     */
    public static void addRoundsInGame(Game game, int countRounds) {
        Round[] rounds = new Round[countRounds];
        for (int i = 0; i < countRounds; i++) {
            rounds[i] = new Round(game.getPlayers());
        }
        game.setRounds(rounds);
        System.out.printf("Создано %d раундов\n", countRounds);
    }

    /**
     * начинаем раунд
     * @param game
     * @param roundNumber
     */
    public static void playRound(Game game, int roundNumber) {
        Round round = game.getRound(roundNumber);

        RoundService.addDeckInRound(round);             //Добавляем колоду в раунд
        RoundService.mixDeck(round);                    //перемешиваем колоду
        RoundService.distributionCards(game, round);    //раздаём карты
        RoundService.addFightsInRound(round);           //добавляем бои в раунд
        RoundService.trade(game, round);                //создаем торги за прикуп
        RoundService.contract(round);                   //создаём контракт
        round.printBet();                               //выводим контракт

        for (int i = 0; i < 10; i++) {
            System.out.printf("\n/---------/ Начинается %d бой /---------/\n", i);
            int idWinnerPlayer = RoundService.playFights(
                    round.getFights()[i], game.getPlayers());
            round.addNTricksToPlayer(1, idWinnerPlayer);
        }
        addRoundScoreToGameScore(game, round);          //добавляем счёт за раунд в общий счёт
        roundScore(round, game.getPlayers());           //выводим счёт за раунд
    }

    /**
     * Выводим итоговый счёт за игру
     * @param game
     */
    public static void ScoreGame(Game game) {
        System.out.println("\nСчёт за игру:");
        for (int i = 0; i < 3; i++) {
            System.out.println(game.getPlayers()[i].getScore());
        }
    }

    /**
     * Выводим счёт за 1 раунд
     * @param round
     * @param players
     */
    private static void roundScore(Round round, Player[] players) {
        System.out.println("****Счёт****");
        for (int i = 0; i < 3; i++) {
            System.out.println(players[i].getName() + ": "
                    + round.getPlayerCountTricks(players[i].getId()));
        }
    }

    /**
     * добавляем счёт за 1 раунд в счёт за игру
     * @param game
     * @param round
     */
    private static void addRoundScoreToGameScore(Game game, Round round) {
        Player[] players = game.getPlayers();
        for (int i = 0; i < players.length; i++) {
            players[i].addScore(
                    round.getPlayerCountTricks(players[i].getId()));
        }
    }
}
