package api;

import scoring.Scoring;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

/**
 * Calcule le score d'une combinaison {@link Type} donnée du jeu
 * du <a href="https://fr.wikipedia.org/wiki/Yahtzee">Yatzy</a>.
 */
public class YatzyGame {

    private final Map<Type, Scoring> scoringByType;

    public YatzyGame(List<Scoring> scorings) {
        scoringByType = scorings.stream()
            .collect(toMap(Scoring::getType, Function.identity()));
    }

    /**
     * @return le score en fonction de la combinaison et des dés donnés.
     */
    public int score(Type type, int d1, int d2, int d3, int d4, int d5) {
        if (!scoringByType.containsKey(type)) {
            String error = """
                Scoring inconnu.
                Vérifiez que le scoring %s a été paramétré lors de l'instanciation de cet objet."""
                .formatted(type);
            throw new IllegalArgumentException(error);
        }
        return scoringByType.get(type).compute(d1, d2, d3, d4, d5);
    }

}