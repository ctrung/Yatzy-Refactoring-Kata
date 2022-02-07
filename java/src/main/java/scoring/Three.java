package scoring;

import api.Type;

/**
 * Calcule le score d'une partie de trois.
 */
public final class Three extends Scoring {
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
