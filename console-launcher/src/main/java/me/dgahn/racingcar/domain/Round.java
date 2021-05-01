package me.dgahn.racingcar.domain;

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
			throw new IllegalArgumentException("양수를 입력해주세요. : " + value);
		}
	}

	public int getValue() {
		return value;
	}

	public void increase() {
		value++;
	}

}