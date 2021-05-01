package me.dgahn.racingcar.domain;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.assertj.core.util.Lists;
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

	@Test
	void 게임을_실행하면_현재까지_부여한_점수의_출력값을_확인할_수_있다() {
		final var generator = mock(RandomNumberGenerator.class);
		when(generator.generateUnderTen()).thenReturn(10);
		final var scoreBoard = createScoreBoard(generator);
		for (int i = 0; i < 2; i++) {
			scoreBoard.giveScore();
		}
		assertThat(scoreBoard.toString()).contains("실행결과\n");
		assertThat(scoreBoard.toString()).contains("단군 : 10\n");
		assertThat(scoreBoard.toString()).contains("혁거세 : 10\n");
		assertThat(scoreBoard.toString()).contains("온조왕 : 10\n");
	}

	private ScoreBoard createScoreBoard() {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		return createScoreBoard(generator);
	}

	private ScoreBoard createScoreBoard(final RandomNumberGenerator generator) {
		final var scoreBoard = new ScoreBoard(generator);
		final List<Car> cars = Lists.newArrayList(new Car("단군"), new Car("온조왕"), new Car("혁거세"));
		cars.forEach(scoreBoard::addCar);

		return scoreBoard;
	}

}
