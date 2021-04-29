package me.dgahn.racingcar.domain;

public class Car {

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

}
