package me.dgahn.racingcar.domain;

import java.util.Random;

public class RandomNumberGenerator {

	public static final int RANDOM_MIN = 0;
	public static final int RANDOM_MAX = 10;

	private final Random random;

	public RandomNumberGenerator(final Random random) {
		this.random = random;
	}

	public int generateUnderTen() {
		return random.nextInt(RANDOM_MAX);
	}

}
