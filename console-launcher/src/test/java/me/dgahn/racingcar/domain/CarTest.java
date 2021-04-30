package me.dgahn.racingcar.domain;

import static me.dgahn.racingcar.domain.Position.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarTest {

	@Test
	void 자동차는_생성되면_처음에_생성되면_0_이라는_위치를_갖는다() {
		final var name = "단군";
		final var car = new Car(name);
		assertThat(car.getPosition().getValue()).isEqualTo(POSITION_INITIAL_VALUE);
		assertThat(car.getName().getValue()).isEqualTo(name);
	}

	@ParameterizedTest
	@ValueSource(ints = {4, 9})
	void 자동차는_4이상의_숫자를_부여_받으면_Position의_값이_1만큼_증가한다(int input) {
		final var name = "단군";
		final var car = new Car(name);
		car.move(input);
		assertThat(car.getPosition().getValue()).isEqualTo(1);
	}

	@ParameterizedTest
	@ValueSource(ints = {0, 3})
	void 자동차는_3이하의_숫자를_부여_받으면_Position의_값이_변하지_않는다(int input) {
		final var name = "단군";
		final var car = new Car(name);
		car.move(input);
		assertThat(car.getPosition().getValue()).isEqualTo(0);
	}

}
