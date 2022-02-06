package scoring.simple;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une partie de quatre.
 */
public class Four extends Scoring {

    @Override
    public Type getType() {
        return Type.FOUR;
    }

    /**
     * @return la somme des quatres.
     */
    @Override
    protected int computeScore(int[] dices) {
        return sumByDice(dices, 4);
    }
}
