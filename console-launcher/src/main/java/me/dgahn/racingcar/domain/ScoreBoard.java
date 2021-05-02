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
	private final Map<Car, List<Integer>> carScoreMap = new HashMap<>();
	private final Round currentRound = new Round(INIT_VALUE);

	public ScoreBoard(final RandomNumberGenerator generator) {
		this.generator = generator;
	}

	public void addCar(final Car car) {
		carScoreMap.putIfAbsent(car, new ArrayList<>());
	}

	public Map<Car, List<Integer>> getCarScoreMap() {
		return carScoreMap;
	}

	public void giveScore() {
		carScoreMap.forEach((car, scoreList) -> scoreList.add(generator.generateUnderTen()));
		currentRound.increase();
	}

	public Round getCurrentRound() {
		return currentRound;
	}

	public String getResult() {
		final String newLine = System.lineSeparator();
		final var builder = new StringBuilder(RESULT_START_MESSAGE);
		for (var round = INIT_VALUE; round < currentRound.getValue(); round++) {
			append(builder, newLine, round);
		}
		return builder.toString();
	}

	private void append(final StringBuilder builder, final String newLine, final int round) {
		builder.append(newLine);
		carScoreMap.forEach((car, integers) -> builder.append(car.getName().getValue())
			.append(COLON)
			.append(integers.get(round))
			.append(newLine));
	}

	public String getWinner() {
		final var winners = new ArrayList<String>();
		var currentMaxPosition = INIT_VALUE;
		for (var carScoreEntry : carScoreMap.entrySet()) {
			final var car = carScoreEntry.getKey();
			final var scores = carScoreEntry.getValue();
			car.move(scores);
			currentMaxPosition = measureMaxPosition(car, currentMaxPosition, winners);
		}
		return String.join(",", winners);
	}

	public int measureMaxPosition(final Car car, int currentMaxPosition, final List<String> winnerBuilder) {
		int carPositionValue = car.getPosition().getValue();
		if (currentMaxPosition == carPositionValue) {
			winnerBuilder.add(car.getName().getValue());
		}
		if (currentMaxPosition < carPositionValue) {
			winnerBuilder.clear();
			winnerBuilder.add(car.getName().getValue());
			currentMaxPosition = carPositionValue;
		}
		return currentMaxPosition;
	}

	public void clearCarScoreMap() {
		carScoreMap.clear();
	}
}
