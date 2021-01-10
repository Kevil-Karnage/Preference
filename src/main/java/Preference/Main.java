package main.java.Preference;

import main.java.Preference.models.Game;
import main.java.Preference.models.Player;
import main.java.Preference.models.enums.DeckTypes;
import main.java.Preference.services.GameService;
import main.java.Preference.services.PrintService;
import main.java.Preference.services.RoundService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        PrintService ps = new PrintService();
        RoundService rs = new RoundService(ps);
        GameService gs = new GameService(rs, ps);

        ps.playPreference();
        setupSettings(game, gs, ps);
        Player[] playersQueue = game.getPlayers();
        for (int i = 0; i < game.getRounds().length; i++) {
            ps.startNRound(i + 1);
            playersQueue = gs.playRound(game, playersQueue, i);
        }
        ps.scoreInGame(game);
        ps.gameWinner(game);
    }

    public static void setupSettings(Game game, GameService gs, PrintService ps) {
        gs.selectGameType(game, DeckTypes.DEFAULT);
        gs.addPlayersInGame(game);

        Scanner scn = new Scanner(System.in);
        ps.readCountRounds();
//        int countRounds = scn.nextInt();
        int countRounds = 5;

        gs.addRoundsInGame(game, countRounds);
    }
}
