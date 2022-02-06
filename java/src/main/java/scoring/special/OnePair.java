package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score d'une paire.
 */
public class OnePair extends Scoring {

    @Override
    public Type getType() {
        return Type.ONE_PAIR;
    }

    /**
     * @return la somme des deux dés de la plus grande paire.
     */
    @Override
    public int computeScore(int[] dice) {
        Map<Integer, Long> counts = countByValue(dice);
        // NB : ordre décroissant pour trouver la plus grande paire
        for (int i = DICE_MAX; i >= DICE_MIN; i--) {
            if (hasAtLeastTwo(counts, i)) {
                return i * 2;
            }
        }
        return NO_POINTS;
    }
}
