package scoring.straight;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score d'une grande suite.
 */
public final class LargeStraight extends Scoring {

    private static final int POINTS = 20;

    @Override
    public Type getType() {
        return Type.LARGE_STRAIGHT;
    }

    /**
     * @return vingt points si grande suite (2 3 4 5 6).
     */
    @Override
    protected int computeScore(int[] dices) {
        Map<Integer, Long> counts = countByValue(dices);
        return hasAtLeastOne(counts, 2) &&
            hasAtLeastOne(counts, 3) &&
            hasAtLeastOne(counts, 4) &&
            hasAtLeastOne(counts, 5) &&
            hasAtLeastOne(counts, 6) ? POINTS : POINTS_IF_NOT_FOUND;
    }
}
