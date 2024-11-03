package game;

import cards.Card;
import cards.CardHelper;

import java.util.ArrayList;

public final class OutputConstructor {

    private final CardHelper cardHelper = new CardHelper();
    ArrayList<Card> outputCards = new ArrayList<>();
    private String command;
    private int playerIndex;

    public OutputConstructor(final String command, final int playerIndex,
                             final ArrayList<Card> cards) {
        this.command = command;
        this.playerIndex = playerIndex;
        for (Card card : cards) {
            outputCards.add(cardHelper.createCard(card));
        }
    }


}
