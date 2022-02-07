package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie de deux.
 */
public final class Two extends Scoring {
    @Override
    public Type getType() {
        return Type.TWO;
    }

    /**
     * @return la somme des deux.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 2);
    }
}
