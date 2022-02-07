package scoring;

import api.Type;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Calcule le score de deux paires.
 */
public final class TwoPairs extends Scoring {

    @Override
    public Type getType() {
        return Type.TWO_PAIRS;
    }

    /**
     * @return la somme des quatre dés des deux paires.
     */
    @Override
    protected int computeScore(int[] dices) {
        List<Map.Entry<Integer, Long>> found = countByValue(dices).entrySet().stream()
            .filter(valueAtLeast(2))
            .collect(Collectors.toList());

        return found.size() == 2 ?
            (found.get(0).getKey() + found.get(1).getKey()) * 2 : POINTS_IF_NOT_FOUND;
    }
}
