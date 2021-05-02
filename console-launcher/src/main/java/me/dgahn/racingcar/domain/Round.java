package me.dgahn.racingcar.domain;

import static me.dgahn.racingcar.view.CarViewModel.*;

public class Round {

	private int value;

	public Round(final String value) {
		validCount(value);
		this.value = Integer.parseInt(value);
	}

	public Round(final int value) {
		this.value = value;
	}

	private void validCount(final String value) {
		if(!value.chars().allMatch(Character::isDigit)) {
			throw new IllegalArgumentException(ROUND_INPUT_ERROR_MESSAGE);
		}
	}

	public int getValue() {
		return value;
	}

	public void increase() {
		value++;
	}

}
