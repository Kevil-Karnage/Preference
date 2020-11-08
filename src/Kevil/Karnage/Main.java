package Kevil.Karnage;

import Preference.Classes.Game;
import Preference.Enums.DeckTypes;
import Preference.Services.GameService;

public class Main {

    public static void main(String[] args) {
        int countRounds = 5;

        Game game = new Game();
        GameService.selectGameType(game, DeckTypes.DEFAULT);
        GameService.addPlayersInGame(game);
        GameService.addRoundsInGame(game, countRounds);
        for (int i = 0; i < countRounds; i++) {
            System.out.printf("\n/*/*/*/ Начинается %d раунд /*/*/*/\n", i);
            GameService.playRound(game, i);
        }
        GameService.ScoreGame(game);
    }
}
