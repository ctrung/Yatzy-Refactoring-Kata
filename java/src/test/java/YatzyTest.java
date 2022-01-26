import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * TUs de la classe @{@link Yatzy} pour vérifier le calcul des scores.
 */
class YatzyTest {

    private static final String PARAM_TEST_NAME = "{0}, {1}, {2}, {3}, {4} => {5}";

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "2, 3, 4, 5, 1, 15",
        "3, 3, 4, 5, 1, 16",
    })
    void chance_scores_sum_of_all_dice(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).chance());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 4, 4, 50",
        "6, 6, 6, 6, 6, 50",
        "6, 6, 6, 6, 3,  0",
    })
    void yatzy_scores_50(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).yatzy());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "6, 2, 2, 4, 5, 0",
        "1, 2, 1, 4, 5, 2",
        "1, 2, 3, 4, 5, 1",
        "1, 2, 1, 1, 1, 4",
    })
    void ones_scores_the_number_of_one(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).ones());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 3, 3, 4, 6, 0",
        "1, 2, 3, 2, 6, 4",
        "2, 2, 2, 2, 2, 10",
    })
    void twos_scores_two_times_the_number_of_two(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).twos());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 4, 2, 5, 0",
        "1, 2, 3, 2, 3, 6",
        "2, 3, 3, 3, 3, 12",
    })
    void threes_scores_three_times_the_number_of_three(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).threes());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 3, 5, 6, 0",
        "4, 5, 5, 5, 5, 4",
        "4, 4, 5, 5, 5, 8",
        "4, 4, 4, 5, 5, 12",
    })
    void fours_scores_four_times_the_number_of_four(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).fours());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 1, 2, 0",
        "4, 4, 4, 5, 5, 10",
        "4, 4, 5, 5, 5, 15",
        "4, 5, 5, 5, 5, 20",
    })
    void fives_scores_five_times_the_number_of_five(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).fives());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "4, 4, 4, 5, 5, 0",
        "4, 4, 6, 5, 5, 6",
        "6, 5, 6, 6, 5, 18",
    })
    void sixes_scores_six_times_the_number_of_six(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).sixes());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 4, 1, 5, 6, 0",
        "3, 4, 3, 5, 6, 6",
        "5, 3, 3, 3, 5, 10",
        "5, 3, 6, 6, 5, 12",
    })
    void one_pair_scores_the_value_of_the_greatest_pair(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).onePair());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 4, 5, 0",
        "3, 3, 5, 4, 5, 16",
        "3, 3, 5, 5, 5, 16",
    })
    void two_pairs_scores_the_value_of_two_pairs(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).twoPairs());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 4, 5, 0",
        "3, 3, 3, 4, 5, 9",
        "3, 3, 3, 3, 5, 9",
        "5, 3, 5, 4, 5, 15",
    })
    void three_of_a_kind_scores_the_sum_of_three_equal_dices(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).threeOfAKind());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "3, 3, 2, 3, 5, 0",
        "3, 3, 3, 3, 5, 12",
        "3, 3, 3, 3, 3, 12",
        "5, 5, 5, 4, 5, 20",
    })
    void four_of_a_kind_scores_the_sum_of_four_equal_dices(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).fourOfAKind());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 2, 4, 5, 0",
        "1, 2, 3, 4, 5, 15",
        "2, 3, 4, 5, 1, 15",
    })
    void small_straight_scores_15_if_one_to_five_present(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).smallStraight());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "1, 2, 2, 4, 5, 0",
        "6, 2, 3, 4, 5, 20",
        "2, 3, 4, 5, 6, 20",
    })
    void large_straight_scores_20_if_two_to_six_present(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).largeStraight());
    }

    @ParameterizedTest(name = PARAM_TEST_NAME)
    @CsvSource({
        "2, 3, 4, 5, 6, 0",
        "6, 2, 2, 2, 6, 18",
    })
    void full_house_scores_the_sum_of_a_pair_and_three_of_a_kind(int dice1, int dice2, int dice3, int dice4, int dice5, int score) {
        assertEquals(score, new Yatzy(dice1, dice2, dice3, dice4, dice5).fullHouse());
    }
}
