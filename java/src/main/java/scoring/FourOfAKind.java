package scoring;

import api.Type;

/**
 * Calcule le score d'un carré.
 */
public final class FourOfAKind extends Scoring {

    @Override
    public Type getType() {
        return Type.FOUR_OF_A_KIND;
    }

    /**
     * @return la somme des quatre dés d'un carré
     */
    @Override
    public int computeScore(int[] dices) {
        return numberOfAkindScore(dices,
            valueAtLeast(4),
            keyTimes(4));
    }
}
