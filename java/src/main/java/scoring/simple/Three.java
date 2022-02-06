package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie de trois.
 */
public class Three extends Scoring {
    @Override
    public Type getType() {
        return Type.THREE;
    }

    /**
     * @return la somme des trois.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 3);
    }
}
