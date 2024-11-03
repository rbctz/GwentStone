package game;

import cards.Card;
import cards.CardHelper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OutputConstructor {

    @JsonIgnore
    private final CardHelper cardHelper = new CardHelper();

    @JsonProperty("output")
    private Card outputCard = null;
    @JsonProperty("output")
    private ArrayList<Card> outputCards = null;
    @JsonProperty("output")
    private Integer outputInteger = null;

    private String command;
    private Integer playerIdx;

    /**
     * Constructor for the getPlayerDeck command.
     * @param command the command to be executed
     * @param playerIndex the index of the player
     * @param cards the cards to be output
     */
    public OutputConstructor(final String command, final Integer playerIndex,
                             final ArrayList<Card> cards) {
        this.command = command;
        this.playerIdx = playerIndex;
        this.outputCards = new ArrayList<>();
        for (Card card : cards) {
            this.outputCards.add(cardHelper.createCard(card));
        }
    }

    /**
     * Constructor for the GetPlayerTurn command.
     * @param command the command to be executed
     * @param integer the index of the player
     */
    public OutputConstructor(final String command, final Integer outputInteger) {
        this.command = command;
        this.outputInteger = outputInteger;
    }

    /**
     * Constructor for the GetPlayerHero command.
     * @param command the command to be executed
     * @param playerIndex the index of the player
     */
    public OutputConstructor(final String command, final Card heroCard,
                             final Integer playerIndex) {
        this.command = command;
        this.playerIdx = playerIndex;
        this.outputCard = cardHelper.createCard(heroCard);
    }

    @JsonProperty("output")
    public Object returnOutput() {
        if (outputCards != null) {
            return outputCards;
        }
        if (outputCard != null) {
            return outputCard;
        }
        return outputInteger;
    }
}
