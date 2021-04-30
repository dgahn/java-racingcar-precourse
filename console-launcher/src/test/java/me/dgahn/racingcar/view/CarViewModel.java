package me.dgahn.racingcar.view;

import java.util.ArrayList;
import java.util.List;

import me.dgahn.racingcar.domain.Car;
import me.dgahn.racingcar.domain.Count;
import me.dgahn.racingcar.domain.RandomNumberGenerator;
import me.dgahn.racingcar.util.StringUtils;

public class CarViewModel {

	private final List<Car> cars = new ArrayList<>();
	private final Count count;
	private final RandomNumberGenerator generator;
	private boolean completed = false;

	public CarViewModel(final RandomNumberGenerator generator, final String count, final String csvCarNames) {
		this.setCars(csvCarNames);
		this.count = new Count(count);
		this.generator = generator;
	}

	private void setCars(final String csvCarNames) {
		final String[] carNames = StringUtils.deleteWhitespace(csvCarNames).split(",");
		for (final String name : carNames) {
			cars.add(new Car(name));
		}
	}

	public List<Car> getCars() {
		return cars;
	}

	public void play() {
		for (int i = 0; i < count.getValue(); i++) {
			moveCars();
		}
		completed = true;
	}

	private void moveCars() {
		for (final Car car : cars) {
			car.move(generator.generateUnderTen());
		}
	}

	public boolean isCompleted() {
		return completed;
	}

}
