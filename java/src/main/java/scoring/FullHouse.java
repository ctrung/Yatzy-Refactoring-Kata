package scoring;

import api.Type;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Calcule le score d'un full house.
 */
public final class FullHouse extends Scoring {

    @Override
    public Type getType() {
        return Type.FULL_HOUSE;
    }

    /**
     * @return la somme des dés du full house.
     */
    @Override
    public int computeScore(int[] dice) {
        List<Map.Entry<Integer, Long>> found = countByValue(dice).entrySet().stream()
            .filter(valueAtLeast(2))
            .sorted(Map.Entry.comparingByValue()) // order by count asc
            .collect(Collectors.toList());

        boolean fullHouseFound = found.size() == 2 && found.get(1).getValue() == 3;
        return fullHouseFound ?
            found.get(0).getKey() * 2 + found.get(1).getKey() * 3 : POINTS_IF_NOT_FOUND;
    }
}
