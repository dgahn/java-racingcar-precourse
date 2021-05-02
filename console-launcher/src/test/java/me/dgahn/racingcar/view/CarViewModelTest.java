package me.dgahn.racingcar.view;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import me.dgahn.racingcar.domain.RandomNumberGenerator;
import me.dgahn.racingcar.domain.ScoreBoard;

class CarViewModelTest {

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

	@Test
	void 게임을_몇_라운드_진행할_수_있는지_설정_할_수_있다() {
		final var viewModel = getViewModel();
		final String round = "10";
		viewModel.setTotalRound(round);
		assertThat(viewModel.getRound().getValue()).isEqualTo(Integer.parseInt(round));
	}

	@Test
	void 게임을_실행하면_각_라운드마다_자동차_갯수만큼_점수가_부여된다() {
		final RandomNumberGenerator generator = mock(RandomNumberGenerator.class);
		when(generator.generateUnderTen()).thenReturn(10);
		final var viewModel = getViewModel(10, "단군,온조왕,혁거세", generator);

		viewModel.play();

		verify(generator, times(3)).generateUnderTen();
	}

	@Test
	void 게임의_총_라운드만큼_수행하면_output은_결과가_된다() {
		final RandomNumberGenerator generator = mock(RandomNumberGenerator.class);
		when(generator.generateUnderTen()).thenReturn(10);
		final var viewModel = getViewModel(1, "단군,온조왕", generator);
		viewModel.play();
		assertThat(viewModel.getOutput()).startsWith("실행결과");
	}

	@Test
	void 자동차의_이름을_1대_이하로_입력하면_예외가_발생한다() {
		final CarViewModel viewModel = getViewModel();
		assertThatThrownBy(() -> viewModel.setCars("단군"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("자동차의 이름을 2대 이상 입력해야 합니다.");
	}

	@Test
	void 자동차의_이름이_중복되면_예외가_발생한다() {
		final CarViewModel viewModel = getViewModel();
		assertThatThrownBy(() -> viewModel.setCars("단군,단군"))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("자동차의 이름이 중복되면 안됩니다.");
	}

	@ParameterizedTest
	@ValueSource(ints = {0, -1, -2})
	void 총_횟수에_0이하를_입력하면_예외가_발생한다(final int input) {
		final CarViewModel viewModel = getViewModel();
		assertThatThrownBy(() -> viewModel.setTotalRound(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("총 횟수는 1이상 입력해야 합니다.");
	}

	private CarViewModel getViewModel() {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		final var scoreBoard = new ScoreBoard(generator);
		return new CarViewModel(scoreBoard);
	}

	private CarViewModel getViewModel(final RandomNumberGenerator generator) {
		final var scoreBoard = new ScoreBoard(generator);
		return new CarViewModel(scoreBoard);
	}

	private CarViewModel getViewModel(
		final int count,
		final String csvCarNames,
		final RandomNumberGenerator generator
	) {
		final var viewModel = getViewModel(generator);
		viewModel.setCars(csvCarNames);
		viewModel.setTotalRound(count);
		return viewModel;
	}

}
