package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import enums.Constants;
import fileio.CardInput;

public final class GetHeroHelper {
    private GetHeroHelper() { }
    /**
     *
     * @param hero
     * @return
     */
    public static ObjectNode getHeroHelper(final CardInput hero) {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode returnHero = objectMapper.createObjectNode();
        returnHero.put(enums.Card.MANA.getField(), hero.getMana());
        returnHero.put(enums.Card.DESCRIPTION.getField(), hero.getDescription());
        ArrayNode colors = objectMapper.createArrayNode();
        for (String color : hero.getColors()) {
            colors.add(color);
        }
        returnHero.set(enums.Card.COLORS.getField(), colors);
        returnHero.put(enums.Card.NAME.getField(), hero.getName());
        returnHero.put(enums.Card.HEALTH.getField(), Constants.HERO_HEALTH.getValue());
        return returnHero;
    }
}
