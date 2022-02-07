package scoring;

import api.Type;

/**
 * Calcule le score d'une partie de quatre.
 */
public final class Four extends Scoring {

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
