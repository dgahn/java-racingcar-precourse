package me.dgahn.racingcar.view;

import java.util.Arrays;
import java.util.HashSet;

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
		try {
			this.totalRound = new Round(totalRound);
			validTotalRound();
		} catch (IllegalArgumentException e) {
			output = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
			throw e;
		}
	}

	public void setTotalRound(final int totalRound) {
		this.setTotalRound(Integer.toString(totalRound));
	}

	private void validTotalRound() {
		if (totalRound.getValue() <= 0) {
			throw new IllegalArgumentException("총 횟수는 1이상 입력해야 합니다.");
		}
	}

	public void setCars(final String csvCarNames) {
		try {
			final String[] carNames = StringUtils.deleteWhitespace(csvCarNames).split(",");
			validCarNames(carNames);
			for (final String name : carNames) {
				scoreBoard.addCar(new Car(name));
			}
			output = "시도할 회수는 몇회인가요?";
		} catch (IllegalArgumentException e) {
			scoreBoard.clearCarScoreMap();
			output = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
			throw e;
		}
	}

	private void validCarNames(final String[] carNames) {
		if (carNames.length <= 1) {
			throw new IllegalArgumentException("자동차의 이름을 2대 이상 입력해야 합니다.");
		}
		if (checkDuplication(carNames)) {
			throw new IllegalArgumentException("자동차의 이름이 중복되면 안됩니다.");
		}
	}

	private boolean checkDuplication(final String[] carNames) {
		final var carNameSet = new HashSet<>(Arrays.asList(carNames));
		return carNames.length != carNameSet.size();
	}

	public String getOutput() {
		return output;
	}

	public void play() {
		scoreBoard.giveScore();
		if (isCompleted()) {
			output = scoreBoard.getResult() + System.lineSeparator() + scoreBoard.getWinner() + "가 우승했습니다.";
		}
	}

	public boolean isCompleted() {
		return totalRound.getValue() == scoreBoard.getCurrentRound().getValue();
	}

}
