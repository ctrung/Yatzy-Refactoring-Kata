package scoring.special;

import api.Type;
import scoring.Scoring;

import java.util.Map;

/**
 * Calcule le score de deux paires.
 */
public class TwoPairs extends Scoring {

    @Override
    public Type getType() {
        return Type.TWO_PAIRS;
    }

    /**
     * @return la somme des quatre dés des deux paires.
     */
    @Override
    protected int computeScore(int[] dices) {
        Map<Integer, Long> counts = countByValue(dices);
        int n = 0;
        int score = 0;
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastTwo(counts, value)) {
                n++;
                score += value;
            }
        }
        return n == 2 ? score * 2 : NO_POINTS;
    }
}
