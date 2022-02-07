package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Arrays;

/**
 * Calcule le score de Chance.
 */
public final class Chance extends Scoring {

    @Override
    public Type getType() {
        return Type.CHANCE;
    }

    /**
     * @return la somme de tous les dés.
     */
    @Override
    protected int computeScore(int[] dices) {
        return Arrays.stream(dices).sum();
    }
}
