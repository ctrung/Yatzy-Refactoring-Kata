import api.YatzyGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import scoring.simple.Five;
import scoring.simple.Four;
import scoring.simple.One;
import scoring.simple.Six;
import scoring.simple.Three;
import scoring.simple.Two;
import scoring.special.Chance;
import scoring.special.FourOfAKind;
import scoring.special.FullHouse;
import scoring.special.OnePair;
import scoring.special.ThreeOfAKind;
import scoring.special.TwoPairs;
import scoring.special.Yatzy;
import scoring.straight.LargeStraight;
import scoring.straight.SmallStraight;

import java.util.List;

import static api.Type.CHANCE;
import static api.Type.FIVE;
import static api.Type.FOUR;
import static api.Type.FOUR_OF_A_KIND;
import static api.Type.FULL_HOUSE;
import static api.Type.LARGE_STRAIGHT;
import static api.Type.ONE;
import static api.Type.ONE_PAIR;
import static api.Type.SIX;
import static api.Type.SMALL_STRAIGHT;
import static api.Type.THREE;
import static api.Type.THREE_OF_A_KIND;
import static api.Type.TWO;
import static api.Type.TWO_PAIRS;
import static api.Type.YATZY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * TUs de la classe @{@link YatzyGame} pour vérifier le calcul des scores.
 */
class YatzyGameTest {

    private static final String PARAM_TEST_NAME = "{0}, {1}, {2}, {3}, {4} => {5}";

    private YatzyGame yatzyGame;

    @BeforeEach
    void beforeEach() {
        yatzyGame = new YatzyGame(List.of(
            new Chance(),
            new Yatzy(),
            new One(),
            new Two(),
            new Three(),
            new Four(),
            new Five(),
            new Six(),
            new OnePair(),
            new TwoPairs(),
            new ThreeOfAKind(),
            new FourOfAKind(),
            new FullHouse(),
            new SmallStraight(),
            new LargeStraight()
        ));
    }

    @Test
    void missing_scoring_is_rejected() {
        // given
        YatzyGame partiallyConfiguredGame = new YatzyGame(List.of(new Chance()));

        // then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
            () -> partiallyConfiguredGame.score(YATZY, 1, 1, 1, 1, 1));
        // and
        assertEquals("Scoring inconnu.\n" +
            "Vérifiez que le scoring YATZY a été paramétré lors de l'instanciation de cet objet.",
            ex.getMessage());
    }

    @ParameterizedTest(name = "{0}, {1}, {2}, {3}, {4} => error")
    @CsvSource({
        "0, 1, 2, 3, 4",
        "-1, 1, 2, 3, 4",
        "1, 2, 3, 4, 7",
    })
    void invalid_dices_are_rejected(int dice1, int dice2, int dice3, int dice4, int dice5) {
        // la combinaison importe peu
        assertThrows(IllegalArgumentException.class,
            () -> yatzyGame.score(YATZY, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "2, 3, 4, 5, 1, 15",
        "3, 3, 4, 5, 1, 16",
    })
    void chance_scores_sum_of_all_dice(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(CHANCE, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 4, 4, 50",
        "6, 6, 6, 6, 6, 50",
        "6, 6, 6, 6, 3,  0",
    })
    void yatzy_scores_50(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(YATZY, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "6, 2, 2, 4, 5, 0",
        "1, 2, 1, 4, 5, 2",
        "1, 2, 3, 4, 5, 1",
        "1, 2, 1, 1, 1, 4",
    })
    void ones_scores_the_number_of_one(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(ONE, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 3, 3, 4, 6, 0",
        "1, 2, 3, 2, 6, 4",
        "2, 2, 2, 2, 2, 10",
    })
    void twos_scores_two_times_the_number_of_two(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(TWO, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 4, 2, 5, 0",
        "1, 2, 3, 2, 3, 6",
        "2, 3, 3, 3, 3, 12",
    })
    void threes_scores_three_times_the_number_of_three(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(THREE, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 3, 5, 6, 0",
        "4, 5, 5, 5, 5, 4",
        "4, 4, 5, 5, 5, 8",
        "4, 4, 4, 5, 5, 12",
    })
    void fours_scores_four_times_the_number_of_four(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(FOUR, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 1, 2, 0",
        "4, 4, 4, 5, 5, 10",
        "4, 4, 5, 5, 5, 15",
        "4, 5, 5, 5, 5, 20",
    })
    void fives_scores_five_times_the_number_of_five(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(FIVE, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 5, 5, 0",
        "4, 4, 6, 5, 5, 6",
        "6, 5, 6, 6, 5, 18",
    })
    void sixes_scores_six_times_the_number_of_six(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(SIX, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 4, 1, 5, 6, 0",
        "3, 4, 3, 5, 6, 6",
        "5, 3, 3, 3, 5, 10",
        "5, 3, 6, 6, 5, 12",
    })
    void one_pair_scores_the_value_of_the_greatest_pair(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(ONE_PAIR, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 4, 5, 0",
        "3, 3, 5, 4, 5, 16",
        "3, 3, 5, 5, 5, 16",
    })
    void two_pairs_scores_the_value_of_two_pairs(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(TWO_PAIRS, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 4, 5, 0",
        "3, 3, 3, 4, 5, 9",
        "3, 3, 3, 3, 5, 9",
        "5, 3, 5, 4, 5, 15",
    })
    void three_of_a_kind_scores_the_sum_of_three_equal_dices(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(THREE_OF_A_KIND, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 3, 5, 0",
        "3, 3, 3, 3, 5, 12",
        "3, 3, 3, 3, 3, 12",
        "5, 5, 5, 4, 5, 20",
    })
    void four_of_a_kind_scores_the_sum_of_four_equal_dices(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(FOUR_OF_A_KIND, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 2, 4, 5, 0",
        "1, 2, 3, 4, 5, 15",
        "2, 3, 4, 5, 1, 15",
    })
    void small_straight_scores_15_if_one_to_five_present(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(SMALL_STRAIGHT, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 2, 4, 5, 0",
        "6, 2, 3, 4, 5, 20",
        "2, 3, 4, 5, 6, 20",
    })
    void large_straight_scores_20_if_two_to_six_present(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(LARGE_STRAIGHT, dice1, dice2, dice3, dice4, dice5));
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "2, 3, 4, 5, 6, 0",
        "1, 2, 2, 2, 6, 0",
        "1, 2, 2, 3, 6, 0",
        "6, 2, 2, 2, 6, 18",
        "6, 2, 2, 1, 6, 0",
    })
    void full_house_scores_the_sum_of_a_pair_and_three_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, yatzyGame.score(FULL_HOUSE, dice1, dice2, dice3, dice4, dice5));
    }
}
