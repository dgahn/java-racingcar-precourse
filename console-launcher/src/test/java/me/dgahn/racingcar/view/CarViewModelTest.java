package me.dgahn.racingcar.view;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

import me.dgahn.racingcar.domain.RandomNumberGenerator;

class CarViewModelTest {

	@Test
	void 이름을_입력_받으면_쉼표를_기준으로_자동차_이름을_구분_한다() {
		constructorTest("단군,동명왕,온조왕,혁거세", Arrays.asList("단군", "동명왕", "온조왕", "혁거세"));
	}

	@Test
	void 이름을_분리할_때_여백은_삭제한다() {
		constructorTest("단군\n,동명왕\t       ,온조왕  ,혁거세", Arrays.asList("단군", "동명왕", "온조왕", "혁거세"));
	}

	@Test
	void 게임이_완료되면_isCompleted는_true다() {
		final var viewModel = getViewModel("1", "단군,동명왕,온조왕,혁거세");
		viewModel.play();
		assertThat(viewModel.isCompleted()).isTrue();
	}

	private CarViewModel getViewModel(final String count, final String csvCarNames) {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		return new CarViewModel(generator, count, csvCarNames);
	}

	private void constructorTest(final String csvCarNames, final List<String> carNames) {
		final var viewModel = getViewModel("1", csvCarNames);
		final var cars = viewModel.getCars();
		for (int i = 0; i < cars.size(); i++) {
			assertThat(cars.get(i).getName().getValue()).isEqualTo(carNames.get(i));
		}
	}

}
