package me.dgahn.racingcar.domain;

public class Count {

	private final int value;

	public Count(final String value) {
		validCount(value);
		this.value = Integer.parseInt(value);
	}

	private void validCount(final String value) {
		if(!value.chars().allMatch(Character::isDigit)) {
			throw new IllegalArgumentException("양수를 입력해주세요. : " + value);
		}
	}

	public int getValue() {
		return value;
	}

}
