package me.dgahn.racingcar.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

	private static final int INIT_VALUE = 0;
	private static final String RESULT_START_MESSAGE = "실행결과";
	private static final String COLON = " : ";

	private final RandomNumberGenerator generator;
	private final Map<Car, List<Integer>> scores = new HashMap<>();
	private final Round currentRound = new Round(INIT_VALUE);

	public ScoreBoard(final RandomNumberGenerator generator) {
		this.generator = generator;
	}

	public void addCar(final Car car) {
		scores.putIfAbsent(car, new ArrayList<>());
	}

	public Map<Car, List<Integer>> getScores() {
		return scores;
	}

	public void giveScore() {
		scores.forEach((car, scoreList) -> scoreList.add(generator.generateUnderTen()));
		currentRound.increase();
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	@Override
	public String toString() {
		final String newLine = System.lineSeparator();
		final var builder = new StringBuilder(RESULT_START_MESSAGE);
		for (var round = INIT_VALUE; round < currentRound.getValue(); round++) {
			append(builder, newLine, round);
		}
		return builder.toString();
	}

	private void append(final StringBuilder builder, final String newLine, final int round) {
		builder.append(newLine);
		scores.forEach((car, integers) -> builder.append(car.getName().getValue())
			.append(COLON)
			.append(integers.get(round))
			.append(newLine));
	}
}
