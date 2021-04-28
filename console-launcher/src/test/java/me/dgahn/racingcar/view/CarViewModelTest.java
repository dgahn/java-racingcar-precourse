package me.dgahn.racingcar.view;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarViewModelTest {

	@Test
	void 이름을_입력_받으면_쉼표를_기준으로_자동차_이름을_구분_한다() {
		final var viewModel = new CatViewModel();
		final String[] carNames = viewModel.createCar("단군,동명왕,온조왕,혁거세");
		assertThat(carNames).isEqualTo(new String[] {"단군", "동명왕", "온조왕", "혁거세"});
	}

	@Test
	void 이름을_분리할_때_여백은_삭제한다() {
		final var viewModel = new CatViewModel();
		final String[] carNames = viewModel.createCar("단군\n,동명왕\t       ,온조왕  ,혁거세");
		assertThat(carNames).isEqualTo(new String[] {"단군", "동명왕", "온조왕", "혁거세"});
	}

}
