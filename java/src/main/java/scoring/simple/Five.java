package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie de cinq.
 */
public class Five extends Scoring {

    @Override
    public Type getType() {
        return Type.FIVE;
    }

    /**
     * @return la somme des cinqs.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 5);
    }
}
