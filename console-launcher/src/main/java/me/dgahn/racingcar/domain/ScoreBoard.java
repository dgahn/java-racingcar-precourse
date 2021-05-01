package me.dgahn.racingcar.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreBoard {

	private final RandomNumberGenerator generator;
	private final Map<Car, List<Integer>> scores = new HashMap<>();

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
	}

}
