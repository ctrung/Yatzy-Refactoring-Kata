package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie de six.
 */
public class Six extends Scoring {
    @Override
    public Type getType() {
        return Type.SIX;
    }

    /**
     * @return la somme des six.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 6);
    }
}
