package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Map;

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
        Map<Integer, Long> counts = countByValue(dices);
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastThree(counts, value)) {
                return value * 3;
            }
        }
        return NO_POINTS;
    }
}
