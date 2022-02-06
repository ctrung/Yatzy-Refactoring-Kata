package scoring.special;

import api.Type;
import scoring.Scoring;

/**
 * Calcule le score d'une paire.
 */
public class OnePair extends Scoring {

    @Override
    public Type getType() {
        return Type.ONE_PAIR;
    }

    /**
     * @return la somme des deux dés de la plus grande paire.
     */
    @Override
    public int computeScore(int[] dices) {
        return numberOfAkindScore(dices,
            valueAtLeast(2),
            keyTimes(2));
    }
}
