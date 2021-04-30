package me.dgahn.racingcar.domain;

import static me.dgahn.racingcar.domain.RandomNumberGenerator.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {

	@Test
	void 임의의_수는_0과9_사이의_값을_가진다() {
		final var generator = new RandomNumberGenerator(new Random());
		for (int i = 0; i < 1_000; i++) {
			assertThat(generator.generateUnderTen()).isBetween(RANDOM_MIN, RANDOM_MAX);
		}
	}

}
