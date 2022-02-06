package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie d'un.
 */
public class One extends Scoring {

    @Override
    public Type getType() {
        return Type.ONE;
    }

    /**
     * @return la somme des uns.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 1);
    }
}
