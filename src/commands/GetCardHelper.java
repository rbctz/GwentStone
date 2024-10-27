package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

public final class GetCardHelper {
    private GetCardHelper() { }
    /**
     *
     * @param card
     * @return
     */
    public static ObjectNode getCardHelper(final CardInput card) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnCard = objectMapper.createObjectNode();
        returnCard.put(enums.Card.MANA.getField(), card.getMana());
        returnCard.put(enums.Card.ATTACK_DAMAGE.getField(), card.getAttackDamage());
        returnCard.put(enums.Card.HEALTH.getField(), card.getHealth());
        returnCard.put(enums.Card.DESCRIPTION.getField(), card.getDescription());
        ArrayNode colors = objectMapper.createArrayNode();
        for (String color : card.getColors()) {
            colors.add(color);
        }
        returnCard.set(enums.Card.COLORS.getField(), colors);
        returnCard.put(enums.Card.NAME.getField(), card.getName());
        return returnCard;
    }
}
