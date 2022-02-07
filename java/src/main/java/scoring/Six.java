package scoring;

import api.Type;

/**
 * Calcule le score d'une partie de six.
 */
public final class Six extends Scoring {
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
