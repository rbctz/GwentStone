package game;

import cards.Card;
import cards.CardHelper;
import cards.MinionCard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import fileio.Coordinates;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
public final class OutputConstructor {

    @JsonIgnore
    private final CardHelper cardHelper = new CardHelper();


    @JsonProperty("output")
    private Card outputCard = null;
    @JsonProperty("output")
    private ArrayList<Card> outputList = null;
    @JsonProperty("output")
    private Integer outputInteger = null;
    @JsonProperty("output")
    private ArrayList<ArrayList<Card>> outputTable = null;
    @JsonProperty("output")
    private String outputString = null;

    private String command = null;
    private String error = null;

    @JsonProperty("playerIdx")
    private Integer playerIndex;
    @JsonProperty("handIdx")
    private Integer handIndex;

    private Coordinates attackCoordinates;
    private Coordinates targetCoordinates;
    private Integer x;
    private Integer y;

    private String gameEnded;

    /**
     * Constructor for the getPlayerDeck and getCardsInHand command.
     * @param command the command to be executed
     * @param playerIndex the index of the player
     * @param cards the cards to be output
     */
    public OutputConstructor(final String command, final int playerIndex,
                             final ArrayList<Card> cards) {
        this.command = command;
        this.playerIndex = playerIndex;
        this.outputList = new ArrayList<>();
        for (Card card : cards) {
            this.outputList.add(cardHelper.createCard(card));
        }
    }

    /**
     * Constructor for the cardUsesAttack command.
     * @param command the command to be executed
     * @param attackCoordinates the coordinates of the attacking card
     * @param targetCoordinates the coordinates of the target card
     * @param error the error message
     */
    public OutputConstructor(final String command, final Coordinates attackCoordinates,
                             final Coordinates targetCoordinates, final String error) {
        this.command = command;
        this.error = error;
        this.attackCoordinates = attackCoordinates;
        this.targetCoordinates = targetCoordinates;
    }

    /**
     * Constructor for the useHeroAttack command.
     * @param command the command to be executed
     * @param attackCoordinates the coordinates of the attacking card
     * @param error the error message
     */
    public OutputConstructor(final String command, final Coordinates attackCoordinates,
                             final String error) {
        this.command = command;
        this.attackCoordinates = attackCoordinates;
        this.error = error;
    }


    /**
     * Constructor for the useHeroAttack command
     *  when the hero dies
     * @param gameEnded the command to be executed
     *
     */
    public OutputConstructor(final String gameEnded) {
        this.gameEnded = gameEnded;
    }

    /**
     * Constructor for the GetPlayerTurn command.
     * @param command the command to be executed
     * @param outputInteger the integer
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
                             final int playerIndex) {
        this.command = command;
        this.playerIndex = playerIndex;
        this.outputCard = cardHelper.createCard(heroCard);
    }

    /**
     * Constructor for the placeCard command.
     * @param command the command to be executed
     * @param handIndex the index of the card in the hand
     * @param error the error message
     */
    public OutputConstructor(final String command, final Integer handIndex, final String error) {
        this.command = command;
        this.handIndex = handIndex;
        this.error = error;
    }

    /**
     * Constructor for the getCardsOnTable command.
     * @param command the command to be executed
     * @param outputTable the 2D ArrayList of cards
     */
    public OutputConstructor(final String command,
                             final ArrayList<ArrayList<MinionCard>> outputTable) {
        this.command = command;
        this.outputTable = new ArrayList<>();
        for (int i = 0; i < GameBoard.getBoardRows(); ++i) {
            this.outputTable.add(new ArrayList<>());
        }
        for (int i = 0; i < GameBoard.getBoardRows(); ++i) {
            for (int j = 0; j < outputTable.get(i).size(); ++j) {
                MinionCard minionCard = outputTable.get(i).get(j);
                this.outputTable.get(i).add(cardHelper.createCard(minionCard));
            }
        }
    }

    /**
     * Constructor for the getPlayerMana command.
     * @param command the command to be executed
     * @param playerIndex the index of the player
     * @param outputInteger the value returned
     */
    public OutputConstructor(final String command,
                             final int playerIndex,
                             final Integer outputInteger) {
        this.command = command;
        this.playerIndex = playerIndex;
        this.outputInteger = outputInteger;
    }

    /**
     * Constructor for the getCardAtPosition command,
     * but for when the method returns the error(no card at pos).
     * @param command the command to be executed
     * @param x the x coordinate
     * @param y the y coordinate
     * @param error the error message
     */
    public OutputConstructor(final String command,
                             final Integer x,
                             final Integer y,
                             final String error) {
        this.command = command;
        this.outputString = error;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor for the getCardAtPosition command.
     * @param command the command to be executed
     * @param x the x coordinate
     * @param y the y coordinate
     * @param card the card at the position
     */
    public OutputConstructor(final String command,
                             final Integer x,
                             final Integer y,
                             final Card card) {
        this.command = command;
        this.outputCard = cardHelper.createCard(card);
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return the main part of the output
     */
    @JsonProperty("output")
    public Object returnOutput() {
        if (outputList != null) {
            return outputList;
        }
        if (outputCard != null) {
            return outputCard;
        }
        if (outputTable != null) {
            return outputTable;
        }
        if (outputString != null) {
            return outputString;
        }
        return outputInteger;
    }

    public String getCommand() {
        return command;
    }

    public String getError() {
        return error;
    }

    public Integer getPlayerIndex() {
        return playerIndex;
    }

    public Integer getHandIndex() {
        return handIndex;
    }

    public Coordinates getAttackCoordinates() {
        return attackCoordinates;
    }

    public Coordinates getTargetCoordinates() {
        return targetCoordinates;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getGameEnded() {
        return gameEnded;
    }
}
