package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score d'un full house.
 */
public class FullHouse extends Scoring {

    @Override
    public Type getType() {
        return Type.FULL_HOUSE;
    }

    /**
     * @return la somme des dés du full house.
     */
    @Override
    public int computeScore(int[] dice) {
        Map<Integer, Long> counts = countByValue(dice);
        int three = 0;
        int pair = 0;
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastThree(counts, value)) {
                three = value;
                continue;
            }
            if (hasAtLeastTwo(counts, value)) {
                pair = value;
            }
        }
        return pair > 0 && three > 0 ? pair * 2  + three * 3 : NO_POINTS;
    }
}
