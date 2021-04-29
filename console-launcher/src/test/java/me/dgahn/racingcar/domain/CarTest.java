package me.dgahn.racingcar.domain;

import static me.dgahn.racingcar.domain.Position.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarTest {

	@Test
	void 자동차는_생성되면_처음에_생성되면_0_이라는_위치를_갖는다() {
		final var name = "단군";
		final var car = new Car(name);
		assertThat(car.getPosition().getValue()).isEqualTo(POSITION_INITIAL_VALUE);
		assertThat(car.getName().getValue()).isEqualTo(name);
	}

}
