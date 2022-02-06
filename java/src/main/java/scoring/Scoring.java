package scoring;

import api.Type;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * Parent de tous les scoring du jeu.
 */
public abstract class Scoring {

    protected static final int DICE_MIN = 1;
    protected static final int DICE_MAX = 6;

    protected static final int NO_POINTS = 0;

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
     * @return le score de la combinaison en fontion de {@code dice}.
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
     * Utilitaire pour calculer les occurences des valeurs des dés.
     *
     * @return Map&lt;Valeur dé, Décompte&gt;
     */
    protected Map<Integer, Long> countByValue(int[] dices) {
        return Arrays.stream(dices)
            .boxed()
            .collect(groupingBy(Function.identity(), counting()));
    }

    /**
     * @param counts map des décomptes
     * @param value  valeur recherchée
     * @return {@code true} si il y a <b>au moins un dé {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    protected boolean hasAtLeastOne(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 1);
    }


    /**
     * @param counts map des décomptes
     * @param value  valeur recherchée
     * @return {@code true} si il y a <b>au moins deux dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    protected boolean hasAtLeastTwo(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 2);
    }

    /**
     * @param counts map des décomptes
     * @param value  valeur recherchée
     * @return {@code true} si il y a <b>au moins trois dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    protected boolean hasAtLeastThree(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 3);
    }

    /**
     * @param counts map des décomptes
     * @param value  valeur recherchée
     * @return {@code true} si il y a <b>au moins quatre dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    protected boolean hasAtLeastFour(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 4);
    }

    /*
     * -------
     * Interne
     * -------
     */

    /**
     * @param counts map des décomptes
     * @param value  valeur recherchée
     * @return {@code true} si il y a <b>au moins un nombre {@code count} de dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeast(Map<Integer, Long> counts, int value, int count) {
        return counts.getOrDefault(value, 0L) >= count;
    }
}
