package scoring;

import api.Type;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Parent de tous les scoring du jeu.
 */
public abstract sealed class Scoring permits Chance, Five, Four, FourOfAKind,
    FullHouse, LargeStraight, One, OnePair, Six, SmallStraight, Three,
    ThreeOfAKind, Two, TwoPairs, Yatzy {

    /**
     * Valeur min d'un dé
     */
    protected static final int DICE_MIN = 1;

    /**
     * Valeur max d'un dé
     */
    protected static final int DICE_MAX = 6;

    /**
     * Nombre de points quand la combinaison n'est pas trouvée
     */
    protected static final int POINTS_IF_NOT_FOUND = 0;

    /*
     * ------
     * API
     * ------
     */

    /**
     * @return le type de combinaison pour lequel cette classe doit calculer
     * un score.
     */
    public abstract Type getType();

    /**
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return le score en fontion des dés.
     */
    public int compute(int d1, int d2, int d3, int d4, int d5) {

        int[] dices = new int[]{d1, d2, d3, d4, d5};

        if (Arrays.stream(dices)
            .filter(d -> d < DICE_MIN || d > DICE_MAX).count() > 0) {
            throw new IllegalArgumentException(Arrays.toString(dices) +
                " contient au moins un dé qui n'est pas dans l'intervalle 1..6");
        }

        return computeScore(dices);
    }

    /**
     * @param dices les dés.
     * @return le score de la combinaison définie par {@link #getType()}.
     */
    protected abstract int computeScore(int[] dices);

    /*
     * ------------------------------
     * Méthodes pour les sous classes
     * ------------------------------
     */

    /**
     * @param searchedDice valeur recherchée
     * @return La somme de tous les dés {@code searchedDice}.
     */
    protected int sumByDice(int[] dices, int searchedDice) {
        return Arrays.stream(dices).filter(i -> i == searchedDice).sum();
    }

    /**
     * Utilitaire pour calculer le décompte des dés.
     *
     * @return Map&lt;Valeur dé, Décompte&gt;
     */
    protected Map<Integer, Long> countByValue(int[] dices) {
        return Arrays.stream(dices)
            .boxed()
            .collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * Génère en interne le décompte décroissant des dés (Map&lt;Valeur dé, Décompte&gt;),
     * puis filtre et enfin mappe pour obtenir le score d'une combinaison
     * d'au moins N dés identiques.
     *
     * @param dices           les dés
     * @param filterPredicate fonction de filtrage des {@link java.util.Map.Entry}
     *                        des décomptes
     * @param mapper          fonction de mapping des {@link java.util.Map.Entry}
     *                        des décomptes
     * @return le score de N dés identiques
     * @see #valueAtLeast(int)
     * @see #keyTimes(int)
     */
    protected Integer numberOfAkindScore(int[] dices,
                                         Predicate<Map.Entry<Integer, Long>> filterPredicate,
                                         Function<Map.Entry<Integer, Long>, Integer> mapper) {

        return countByValue(dices).entrySet().stream()
            .filter(filterPredicate)
            .max(Map.Entry.comparingByKey()) // plus grand dé
            .map(mapper)
            .orElse(POINTS_IF_NOT_FOUND);
    }

    /**
     * @param threshold le seuil min
     * @return fonction de filtrage des {@link java.util.Map.Entry} des décomptes
     * @see #numberOfAkindScore(int[], Predicate, Function)
     */
    protected Predicate<Map.Entry<Integer, Long>> valueAtLeast(int threshold) {
        return e -> e.getValue() >= threshold;
    }

    /**
     * @param factor le coefficient
     * @return fonction de mapping des {@link java.util.Map.Entry} des décomptes
     * @see #numberOfAkindScore(int[], Predicate, Function)
     */
    protected Function<Map.Entry<Integer, Long>, Integer> keyTimes(int factor) {
        return e -> e.getKey() * factor;
    }

    /**
     * @param counts le décompte des dés (Map&lt;Valeur dé, Décompte&gt;)
     * @param dice  dé recherché
     * @return {@code true} si le dé recherché apparait au moins une fois dans
     * {@code counts}
     */
    protected boolean hasAtLeastOne(Map<Integer, Long> counts, int dice) {
        return counts.get(dice) != null;
    }
}
