import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

/**
 * Implémentation du jeu du <a href="https://fr.wikipedia.org/wiki/Yahtzee">Yatzy</a>.
 * <br/><br/>
 * Cette classe contient des méthodes de calcul de points des combinaisons du jeu.
 */
public class Yatzy {

    private static final int NO_POINTS = 0;
    private static final int YATZY_POINTS = 50;
    private static final int SMALL_STRAIGHT_POINTS = 15;
    private static final int LARGE_STRAIGHT_POINTS = 20;

    /**
     * Etat des dés.
     */
    private final int[] dice;

    /**
     * Initialisation de l'état des 5 dés.
     *
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     */
    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        validateDice(d1, d2, d3, d4, d5);
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    /**
     * Les joueurs doivent obtenir le plus grand nombre de points / ils marquent la somme de la valeur des dés.
     *
     * @return la somme des valeurs des dés.
     */
    public int chance() {
        return stream(dice).sum();
    }

    /**
     * Les joueurs doivent obtenir 5 dés de même valeur / ils marquent 50 points.
     *
     * @return 0 ou 50 points selon qu'il y ait 5 dés de même valeur.
     */
    public int yatzy() {
        return stream(dice).distinct().count() == 1 ? YATZY_POINTS : NO_POINTS;
    }

    /**
     * Partie de 1 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 1 /
     * ils marquent 1 fois le nombre de dés de valeur 1.
     *
     * @return Un score entre 0 et 5 en fonction du nombre de dés avec la face 1.
     */
    public int ones() {
        return countAndMultiply(1);
    }

    /**
     * Partie de 2 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 2 /
     * ils marquent 2 fois le nombre de dés de valeur 2.
     *
     * @return Un score entre 0 et 10 en fonction du nombre de dés avec la face 2.
     */
    public int twos() {
        return countAndMultiply(2);
    }

    /**
     * Partie de 3 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 3 /
     * ils marquent 3 fois le nombre de dés de valeur 3.
     *
     * @return Un score entre 0 et 15 en fonction du nombre de dés avec la face 3.
     */
    public int threes() {
        return countAndMultiply(3);
    }

    /**
     * Partie de 4 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 4 /
     * ils marquent 4 fois le nombre de dés de valeur 4.
     *
     * @return Un score entre 0 et 20 en fonction du nombre de dés avec la face 4.
     */
    public int fours() {
        return countAndMultiply(4);
    }

    /**
     * Partie de 5 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 5 /
     * ils marquent 5 fois le nombre de dés de valeur 5.
     *
     * @return Un score entre 0 et 25 en fonction du nombre de dés avec la face 5.
     */
    public int fives() {
        return countAndMultiply(5);
    }

    /**
     * Partie de 6 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 6 /
     * ils marquent 6 fois le nombre de dés de valeur 6.
     *
     * @return Un score entre 0 et 30 en fonction du nombre de dés avec la face 6.
     */
    public int sixes() {
        return countAndMultiply(6);
    }

    /**
     * Partie de paires : les joueurs doivent obtenir la plus grande paire /
     * ils marquent la valeur de la plus grande paire (ex. paire de 6 = 12, paire de 5 = 10, etc...)
     *
     * @return La valeur de la plus grande paire si présente, 0 sinon.
     */
    public int onePair() {
        Map<Integer, Long> counts = countByValue();
        // NB : parcours en ordre décroissant pour trouver la plus grande paire possible
        for (int i = 6; i > 0; i--) {
            if (hasAtLeastTwo(counts, i)) {
                return i * 2;
            }
        }
        return NO_POINTS;
    }

    /**
     * Partie de doubles paires : les joueurs doivent obtenir deux paires /
     * ils marquent la valeur des deux paires (ex. paire de 3 et 5 = 16, etc...)
     *
     * @return La valeur des deux paires cumulées si présentes, 0 sinon.
     */
    public int twoPairs() {
        Map<Integer, Long> counts = countByValue();
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

    /**
     * Carré : les joueurs doivent obtenir 4 dés de même valeur / ils marquent 4 fois la valeur des dés identiques.
     *
     * @return 4 fois la valeur des dés identiques en présence d'un carré, 0 sinon.
     */
    public int fourOfAKind() {
        Map<Integer, Long> counts = countByValue();
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastFour(counts, value)) {
                return value * 4;
            }
        }
        return NO_POINTS;
    }

    /**
     * Brelan : les joueurs doivent obtenir 3 dés de même valeur / ils marquent 3 fois la valeur des dés identiques.
     *
     * @return 3 fois la valeur des dés identiques en présence d'un brelan, 0 sinon.
     */
    public int threeOfAKind() {
        Map<Integer, Long> counts = countByValue();
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastThree(counts, value)) {
                return value * 3;
            }
        }
        return NO_POINTS;
    }

    /**
     * Petite suite : les joueurs doivent obtenir 5 dés qui se suivent jusqu'à 5 (1-2-3-4-5) / ils marquent 15 points.
     *
     * @return 15 points si présence d'une petite suite, 0 sinon.
     */
    public int smallStraight() {
        Map<Integer, Long> counts = countByValue();
        return hasAtLeastOne(counts, 1) &&
            hasAtLeastOne(counts, 2) &&
            hasAtLeastOne(counts, 3) &&
            hasAtLeastOne(counts, 4) &&
            hasAtLeastOne(counts, 5) ? SMALL_STRAIGHT_POINTS : NO_POINTS;
    }

    /**
     * Grande suite : les joueurs doivent obtenir 5 dés qui se suivent jusqu'à 6 (2-3-4-5-6) / ils marquent 20 points.
     *
     * @return 20 points si présence d'une grance suite, 0 sinon.
     */
    public int largeStraight() {
        Map<Integer, Long> counts = countByValue();
        return hasAtLeastOne(counts, 2) &&
            hasAtLeastOne(counts, 3) &&
            hasAtLeastOne(counts, 4) &&
            hasAtLeastOne(counts, 5) &&
            hasAtLeastOne(counts, 6) ? LARGE_STRAIGHT_POINTS : NO_POINTS;
    }

    /**
     * Les joueurs doivent obtenir 3 dés de même valeur et 2 dés de même valeur – (brelan + paire) /
     * ils marquent la somme des valeurs des dés.
     *
     * @return La somme des valeurs de tous les dés si présence d'un full house, 0 sinon.
     */
    public int fullHouse() {
        Map<Integer, Long> counts = countByValue();
        int three = 0;
        int pair = 0;
        for (Map.Entry<Integer, Long> e : counts.entrySet()) {
            int value = e.getKey();
            if (hasAtLeastThree(counts, value)) {
                three = value;
                continue;
            }
            if (hasAtLeastTwo(counts, value)) {
                pair = value;
            }
        }
        return pair > 0 && three > 0 ? pair * 2  + three * 3 : NO_POINTS;
    }

    /*
     * -------------------
     * Interne/utilitaire
     * -------------------
     */

    /**
     * Valide que tous les dés sont entre 1..6.
     * @param dices valeurs des dés.
     */
    private void validateDice(int... dices) {
        if (stream(dices).filter(d -> d < 1 || d > 6).count() > 0) {
            throw new IllegalArgumentException(Arrays.toString(dices)
                + " contient au moins un dé qui n'est pas dans l'intervalle 1..6");
        }
    }

    /**
     * Mulitplie le nombre d'occurences de {@code value} dans {@code dice} par {@code value}.
     * @param value valeur recherchée
     * @return Le nombre d'occurences de {@code value} dans {@code dice} par {@code value}.
     */
    private int countAndMultiply(int value) {
        return (int) stream(dice).filter(i -> i == value).count() * value;
    }

    /**
     * Utilitaire pour calculer les occurences des valeurs des dés.
     * @return Map&lt;Valeur dé, Décompte&gt;
     */
    private Map<Integer, Long> countByValue() {
        return stream(dice)
            .boxed()
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting())
            );
    }

    /**
     * @param counts map des décomptes
     * @param value valeur recherchée
     * @return {@code true} si il y a <b>au moins un dé {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeastOne(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 1);
    }


    /**
     * @param counts map des décomptes
     * @param value valeur recherchée
     * @return {@code true} si il y a <b>au moins deux dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeastTwo(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 2);
    }

    /**
     * @param counts map des décomptes
     * @param value valeur recherchée
     * @return {@code true} si il y a <b>au moins trois dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeastThree(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 3);
    }

    /**
     * @param counts map des décomptes
     * @param value valeur recherchée
     * @return {@code true} si il y a <b>au moins quatre dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeastFour(Map<Integer, Long> counts, int value) {
        return hasAtLeast(counts, value, 4);
    }

    /**
     * @param counts map des décomptes
     * @param value valeur recherchée
     * @return {@code true} si il y a <b>au moins un nombre {@code count} de dés {@code value}</b>
     * d'après la map des décomptes {@code counts}
     */
    private boolean hasAtLeast(Map<Integer, Long> counts, int value, int count) {
        return counts.getOrDefault(value, 0L) >= count;
    }
}



