package scoring.special;

import api.Type;
import scoring.Scoring;

import static java.util.Arrays.stream;

/**
 * Calcule le score de Yatzy.
 */
public class Yatzy extends Scoring {

    private static final int POINTS = 50;

    @Override
    public Type getType() {
        return Type.YATZY;
    }

    /**
     * @return cinquante points si Yatzi.
     */
    @Override
    protected int computeScore(int[] dices) {
        return stream(dices).distinct().count() == 1 ? POINTS : POINTS_IF_NOT_FOUND;
    }
}
