package me.dgahn.racingcar.domain;

public class Position {

	public static final int POSITION_INITIAL_VALUE = 0;

	private int value = POSITION_INITIAL_VALUE;

	public int getValue() {
		return value;
	}

	public void plusOne() {
		value++;
	}

}
