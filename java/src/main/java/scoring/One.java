package scoring;

import api.Type;

/**
 * Calcule le score d'une partie d'un.
 */
public final class One extends Scoring {

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
