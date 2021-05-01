package me.dgahn.racingcar.view;

import me.dgahn.racingcar.domain.Car;
import me.dgahn.racingcar.domain.Count;
import me.dgahn.racingcar.domain.ScoreBoard;
import me.dgahn.racingcar.util.StringUtils;

public class CarViewModel {

	private final ScoreBoard scoreBoard;
	private Count count;
	private boolean completed = false;
	private String output = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";

	public CarViewModel(final ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public void setCars(final String csvCarNames) {
		final String[] carNames = StringUtils.deleteWhitespace(csvCarNames).split(",");
		for (final String name : carNames) {
			scoreBoard.addCar(new Car(name));
		}
		output = "시도할 회수는 몇회인가요?";
	}

	public void setCount(final String count) {
		this.count = new Count(count);
	}

	public void play() {
		for (var i = 0; i < count.getValue(); i++) {
			scoreBoard.giveScore();
		}
		completed = true;
	}

	public boolean isCompleted() {
		return completed;
	}

	public String getOutput() {
		return output;
	}

}
