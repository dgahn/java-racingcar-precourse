package me.dgahn.racingcar.domain;

import static me.dgahn.racingcar.domain.Position.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PositionTest {

	@Test
	void Position을_생성하면_0의_값을_가진다() {
		final var position = new Position();
		assertThat(position.getValue()).isEqualTo(POSITION_INITIAL_VALUE);
	}

}
