/**
 * Implémentation du jeu du <a href="https://fr.wikipedia.org/wiki/Yahtzee">Yatzy</a>.
 * <br/><br/>
 * Cette classe contient des méthodes de calcul de points des combinaisons du jeu.
 */
public class Yatzy {

    /**
     * Les joueurs doivent obtenir le plus grand nombre de points / ils marquent la somme de la valeur des dés.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return la somme des valeurs des dés.
     */
    public static int chance(int d1, int d2, int d3, int d4, int d5)
    {
        int total = 0;
        total += d1;
        total += d2;
        total += d3;
        total += d4;
        total += d5;
        return total;
    }

    /**
     * Les joueurs doivent obtenir 5 dés de même valeur / ils marquent 50 points.
     * @param dice les (5) dés.
     * @return 0 ou 50 points selon qu'il y ait 5 dés de même valeur.
     */
    public static int yatzy(int... dice)
    {
        int[] counts = new int[6];
        for (int die : dice)
            counts[die-1]++;
        for (int i = 0; i != 6; i++)
            if (counts[i] == 5)
                return 50;
        return 0;
    }

    /**
     * Partie de 1 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 1 /
     * ils marquent 1 fois le nombre de dés de valeur 1.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return Un score entre 0 et 5 en fonction du nombre de dés avec la face 1.
     */
    public static int ones(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 1) sum++;
        if (d2 == 1) sum++;
        if (d3 == 1) sum++;
        if (d4 == 1) sum++;
        if (d5 == 1) 
            sum++;

        return sum;
    }

    /**
     * Partie de 2 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 2 /
     * ils marquent 2 fois le nombre de dés de valeur 2.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return Un score entre 0 et 10 en fonction du nombre de dés avec la face 2.
     */
    public static int twos(int d1, int d2, int d3, int d4, int d5) {
        int sum = 0;
        if (d1 == 2) sum += 2;
        if (d2 == 2) sum += 2;
        if (d3 == 2) sum += 2;
        if (d4 == 2) sum += 2;
        if (d5 == 2) sum += 2;
        return sum;
    }

    /**
     * Partie de 3 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 3 /
     * ils marquent 3 fois le nombre de dés de valeur 3.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return Un score entre 0 et 15 en fonction du nombre de dés avec la face 3.
     */
    public static int threes(int d1, int d2, int d3, int d4, int d5) {
        int s;    
        s = 0;
        if (d1 == 3) s += 3;
        if (d2 == 3) s += 3;
        if (d3 == 3) s += 3;
        if (d4 == 3) s += 3;
        if (d5 == 3) s += 3;
        return s;
    }

    protected int[] dice;

    /**
     * Initialisation de l'état des 5 dés.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     */
    public Yatzy(int d1, int d2, int d3, int d4, int d5)
    {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    /**
     * Partie de 4 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 4 /
     * ils marquent 4 fois le nombre de dés de valeur 4.
     * @return Un score entre 0 et 20 en fonction du nombre de dés avec la face 4.
     */
    public int fours()
    {
        int sum;    
        sum = 0;
        for (int at = 0; at != 5; at++) {
            if (dice[at] == 4) {
                sum += 4;
            }
        }
        return sum;
    }

    /**
     * Partie de 5 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 5 /
     * ils marquent 5 fois le nombre de dés de valeur 5.
     * @return Un score entre 0 et 25 en fonction du nombre de dés avec la face 5.
     */
    public int fives()
    {
        int s = 0;
        int i;
        for (i = 0; i < dice.length; i++) 
            if (dice[i] == 5)
                s = s + 5;
        return s;
    }

    /**
     * Partie de 6 : les joueurs doivent obtenir le plus grand nombre de dés avec la face 6 /
     * ils marquent 6 fois le nombre de dés de valeur 6.
     * @return Un score entre 0 et 30 en fonction du nombre de dés avec la face 6.
     */
    public int sixes()
    {
        int sum = 0;
        for (int at = 0; at < dice.length; at++) 
            if (dice[at] == 6)
                sum = sum + 6;
        return sum;
    }

    /**
     * Partie de paires : les joueurs doivent obtenir la plus grande paire /
     * ils marquent la valeur de la plus grande paire (ex. paire de 6 = 12, paire de 5 = 10, etc...)
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return La valeur de la plus grande paire si présente, 0 sinon.
     */
    public static int score_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        int at;
        for (at = 0; at != 6; at++)
            if (counts[6-at-1] >= 2)
                return (6-at)*2;
        return 0;
    }

    /**
     * Partie de doubles paires : les joueurs doivent obtenir deux paires /
     * ils marquent la valeur des deux paires (ex. paire de 3 et 5 = 16, etc...)
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return La valeur des deux paires cumulées si présentes, 0 sinon.
     */
    public static int two_pair(int d1, int d2, int d3, int d4, int d5)
    {
        int[] counts = new int[6];
        counts[d1-1]++;
        counts[d2-1]++;
        counts[d3-1]++;
        counts[d4-1]++;
        counts[d5-1]++;
        int n = 0;
        int score = 0;
        for (int i = 0; i < 6; i += 1)
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }        
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    /**
     * Carré : les joueurs doivent obtenir 4 dés de même valeur / ils marquent 4 fois la valeur des dés identiques.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return 4 fois la valeur des dés identiques en présence d'un carré, 0 sinon.
     */
    public static int four_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1]++;
        tallies[d2-1]++;
        tallies[d3-1]++;
        tallies[d4-1]++;
        tallies[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (tallies[i] >= 4)
                return (i+1) * 4;
        return 0;
    }

    /**
     * Brelan : les joueurs doivent obtenir 3 dés de même valeur / ils marquent 3 fois la valeur des dés identiques.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return 3 fois la valeur des dés identiques en présence d'un brelan, 0 sinon.
     */
    public static int three_of_a_kind(int d1, int d2, int d3, int d4, int d5)
    {
        int[] t;
        t = new int[6];
        t[d1-1]++;
        t[d2-1]++;
        t[d3-1]++;
        t[d4-1]++;
        t[d5-1]++;
        for (int i = 0; i < 6; i++)
            if (t[i] >= 3)
                return (i+1) * 3;
        return 0;
    }

    /**
     * Petite suite : les joueurs doivent obtenir 5 dés qui se suivent jusqu'à 5 (1-2-3-4-5) / ils marquent 15 points.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return 15 points si présence d'une petite suite, 0 sinon.
     */
    public static int smallStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[0] == 1 &&
            tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1)
            return 15;
        return 0;
    }

    /**
     * Grande suite : les joueurs doivent obtenir 5 dés qui se suivent jusqu'à 6 (2-3-4-5-6) / ils marquent 20 points.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return 20 points si présence d'une grance suite, 0 sinon.
     */
    public static int largeStraight(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;
        if (tallies[1] == 1 &&
            tallies[2] == 1 &&
            tallies[3] == 1 &&
            tallies[4] == 1
            && tallies[5] == 1)
            return 20;
        return 0;
    }

    /**
     * Les joueurs doivent obtenir 3 dés de même valeur et 2 dés de même valeur – (brelan + paire) /
     * ils marquent la somme des valeurs des dés.
     * @param d1 dé 1.
     * @param d2 dé 2.
     * @param d3 dé 3.
     * @param d4 dé 4.
     * @param d5 dé 5.
     * @return La somme des valeurs de tous les dés si présence d'un full house, 0 sinon.
     */
    public static int fullHouse(int d1, int d2, int d3, int d4, int d5)
    {
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;




        tallies = new int[6];
        tallies[d1-1] += 1;
        tallies[d2-1] += 1;
        tallies[d3-1] += 1;
        tallies[d4-1] += 1;
        tallies[d5-1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i+1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i+1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }
}



