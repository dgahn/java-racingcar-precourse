package me.dgahn.racingcar.view;

import me.dgahn.racingcar.domain.Car;
import me.dgahn.racingcar.domain.Round;
import me.dgahn.racingcar.domain.ScoreBoard;
import me.dgahn.racingcar.util.StringUtils;

public class CarViewModel {

	private final ScoreBoard scoreBoard;
	private String output = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private Round totalRound;

	public CarViewModel(final ScoreBoard scoreBoard) {
		this.scoreBoard = scoreBoard;
	}

	public Round getRound() {
		return totalRound;
	}

	public void setTotalRound(final String totalRound) {
		this.totalRound = new Round(totalRound);
	}

	public void setTotalRound(final int totalRound) {
		this.totalRound = new Round(totalRound);
	}

	public void setCars(final String csvCarNames) {
		final String[] carNames = StringUtils.deleteWhitespace(csvCarNames).split(",");
		for (final String name : carNames) {
			scoreBoard.addCar(new Car(name));
		}
		output = "시도할 회수는 몇회인가요?";
	}

	public String getOutput() {
		return output;
	}

	public void play() {
		scoreBoard.giveScore();
		if(isCompleted()) {
			output = scoreBoard.toString();
		}
	}

	public boolean isCompleted() {
		return totalRound.getValue() == scoreBoard.getCurrentRound().getValue();
	}

}
