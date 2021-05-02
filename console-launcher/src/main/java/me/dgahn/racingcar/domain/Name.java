package me.dgahn.racingcar.domain;

public class Name {

	private static final int NAME_LENGTH_MIN = 1;
	private static final int NAME_LENGTH_MAX = 5;
	private static final String CAR_NAME_ERROR_MESSAGE = "자동차의 이름은 1글자 이상 5글자 이하입니다. ";

	private final String value;

	public Name(final String value) {
		validName(value);
		this.value = value;
	}

	private void validName(final String value) {
		final var nameLength = value.length();
		if (nameLength > NAME_LENGTH_MAX || nameLength < NAME_LENGTH_MIN) {
			throw new IllegalArgumentException(CAR_NAME_ERROR_MESSAGE + "(name: \"" + value + "\")");
		}
	}

	public String getValue() {
		return value;
	}

}
