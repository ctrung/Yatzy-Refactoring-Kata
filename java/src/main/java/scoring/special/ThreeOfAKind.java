package scoring.special;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'un brelan.
 */
public class ThreeOfAKind extends Scoring {

    @Override
    public Type getType() {
        return Type.THREE_OF_A_KIND;
    }

    /**
     * @return la somme des trois dés du brelan.
     */
    @Override
    protected int computeScore(int[] dices) {
        return numberOfAkindScore(dices,
            valueAtLeast(3),
            keyTimes(3));
    }
}
