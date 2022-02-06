package scoring.straight;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score d'une petite suite.
 */
public class SmallStraight extends Scoring {

    private static final int POINTS = 15;

    @Override
    public Type getType() {
        return Type.SMALL_STRAIGHT;
    }

    /**
     * @return quinze points si petite suite (1 2 3 4 5).
     */
    @Override
    protected int computeScore(int[] dices) {
        Map<Integer, Long> counts = countByValue(dices);
        return hasAtLeastOne(counts, 1) &&
            hasAtLeastOne(counts, 2) &&
            hasAtLeastOne(counts, 3) &&
            hasAtLeastOne(counts, 4) &&
            hasAtLeastOne(counts, 5) ? POINTS : POINTS_IF_NOT_FOUND;
    }
}
