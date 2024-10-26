package game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import fileio.ActionsInput;
import fileio.CardInput;
import fileio.GameInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public final class GameThread {

    private Random random;

    private static volatile GameThread instance;

    private final ArrayList<ArrayList<CardInput>> playerOneDecks;
    private final ArrayList<ArrayList<CardInput>> playerTwoDecks;
    private final ArrayList<GameInput> gameList;

    private GameThread(final ArrayList<ArrayList<CardInput>> playerOneDecks,
                       final ArrayList<ArrayList<CardInput>> playerTwoDecks,
                       final ArrayList<GameInput> gameList) {
        this.playerOneDecks = playerOneDecks;
        this.playerTwoDecks = playerTwoDecks;
        this.gameList = gameList;
    }

    /**
     * Singleton pattern since we need just one instance
     * @param playerOneDecks
     * @param playerTwoDecks
     * @param gameList
     * @return
     */
    public static GameThread getInstance(final ArrayList<ArrayList<CardInput>> playerOneDecks,
                                         final ArrayList<ArrayList<CardInput>> playerTwoDecks,
                                         final ArrayList<GameInput> gameList) {
        if (instance == null) {
            synchronized (GameThread.class) {
                if (instance == null) {
                    instance = new GameThread(playerOneDecks, playerTwoDecks, gameList);
                }
            }
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public ArrayNode run() {
        ArrayList<CardInput> deckOne;
        ArrayList<CardInput> deckTwo;
        CardInput heroOne;
        CardInput heroTwo;
        int startingPlayer;
        int seed;
        Game game;
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode returnValue = objectMapper.createArrayNode();
        for (GameInput gameInput : gameList) {
            seed = gameInput.getStartGame().getShuffleSeed();

            int playerOneIndex = gameInput.getStartGame().getPlayerOneDeckIdx();
            int playerTwoIndex = gameInput.getStartGame().getPlayerTwoDeckIdx();
            deckOne = new ArrayList<>(playerOneDecks.get(playerOneIndex));
            deckTwo = new ArrayList<>(playerTwoDecks.get(playerTwoIndex));
            random = new Random(seed);
            Collections.shuffle(deckOne, random);
            random = new Random(seed);
            Collections.shuffle(deckTwo, random);

            heroOne = gameInput.getStartGame().getPlayerOneHero();
            heroTwo = gameInput.getStartGame().getPlayerTwoHero();

            startingPlayer = gameInput.getStartGame().getStartingPlayer();

            game = new Game(startingPlayer, deckOne, deckTwo, heroOne, heroTwo);

            for (ActionsInput actionsInput : gameInput.getActions()) {
                returnValue.add(game.execute(actionsInput));
            }
        }
        return returnValue;
    }
}
