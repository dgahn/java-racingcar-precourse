package me.dgahn.racingcar.domain;

public class Car {

	public static final int MOVE_MIN = 4;

	private final Position position;
	private final Name name;

	public Car(final String name) {
		this.position = new Position();
		this.name = new Name(name);
	}

	public Position getPosition() {
		return position;
	}

	public Name getName() {
		return name;
	}

	public void move(final int number) {
		if(number >= MOVE_MIN) {
			position.plusOne();
		}
	}

}
