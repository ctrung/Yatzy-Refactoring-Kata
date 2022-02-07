package scoring;

import api.Type;

/**
 * Calcule le score d'une partie de cinq.
 */
public final class Five extends Scoring {

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
