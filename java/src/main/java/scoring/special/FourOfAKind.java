package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score d'un carré.
 */
public class FourOfAKind extends Scoring {

    @Override
    public Type getType() {
        return Type.FOUR_OF_A_KIND;
    }

    /**
     * @return la somme des quatre dés d'un carré
     */
    @Override
    public int computeScore(int[] dice) {
        Map<Integer, Long> counts = countByValue(dice);
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastFour(counts, value)) {
                return value * 4;
            }
        }
        return NO_POINTS;
    }
}
