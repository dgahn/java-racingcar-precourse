package me.dgahn.racingcar.view;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import me.dgahn.racingcar.domain.RandomNumberGenerator;
import me.dgahn.racingcar.domain.ScoreBoard;

class CarViewModelTest {

	@Test
	void 게임이_완료되면_isCompleted는_true다() {
		final var viewModel = getViewModel("1", "단군,동명왕,온조왕,혁거세");
		viewModel.play();
		assertThat(viewModel.isCompleted()).isTrue();
	}

	@Test
	void 자동차_이름을_입력_받기_전에는_output이_자동차_입력을_위한_Message다() {
		final var expected = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
		final var viewModel = getViewModel();
		final var actual = viewModel.getOutput();
		assertThat(actual).isEqualTo(expected);
	}

	@Test
	void 자동차_이름을_입력_받으면_output이_횟수를_입력_받기_위한_Message가_된다() {
		final var viewModel = getViewModel();
		viewModel.setCars("단군,동명왕,온조왕,혁거세");
		assertThat(viewModel.getOutput()).isEqualTo("시도할 회수는 몇회인가요?");
	}

	private CarViewModel getViewModel() {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		final var scoreBoard = new ScoreBoard(generator);
		return new CarViewModel(scoreBoard);
	}

	private CarViewModel getViewModel(final String count, final String csvCarNames) {
		final var viewModel = getViewModel();
		viewModel.setCars(csvCarNames);
		viewModel.setCount(count);
		return viewModel;
	}

}
