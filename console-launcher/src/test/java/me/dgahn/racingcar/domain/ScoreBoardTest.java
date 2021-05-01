package me.dgahn.racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

class ScoreBoardTest {

	@Test
	void ScoreBoard에_자동차를_각각_붙일_수_있다() {
		final var scoreBoard = createScoreBoard();
		final Map<Car, List<Integer>> scores = scoreBoard.getScores();
		scores.forEach((car, scoreList) -> assertThat(scoreList).isEmpty());
	}

	@Test
	void 게임이_실행되면_횟수만큼_각_자동차가_점수를_갖는다() {
		final var scoreBoard = createScoreBoard();
		final int times = 5;

		for (int i = 0; i < times; i++) {
			scoreBoard.giveScore();
		}

		final Map<Car, List<Integer>> scores = scoreBoard.getScores();
		scores.forEach((car, scoreList) -> {
			assertThat(scoreList).isNotEmpty();
			assertThat(scoreList.size()).isEqualTo(times);
		});
	}

	private ScoreBoard createScoreBoard() {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		final var scoreBoard = new ScoreBoard(generator);
		Arrays.asList(new Car("단군"), new Car("온조왕")).forEach(car -> scoreBoard.addCar(car));
		return scoreBoard;
	}

}
