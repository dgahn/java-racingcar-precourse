package me.dgahn.racingcar.view;

import java.util.Arrays;
import java.util.HashSet;

import me.dgahn.racingcar.domain.Car;
import me.dgahn.racingcar.domain.Round;
import me.dgahn.racingcar.domain.ScoreBoard;
import me.dgahn.racingcar.util.StringUtils;

public class CarViewModel {

	private static final String ROUND_INPUT_MESSAGE = "시도할 회수는 몇회인가요?";
	public static final String ROUND_INPUT_ERROR_MESSAGE = "총 횟수는 1이상 입력해야 합니다.";
	private static final String CAR_INPUT_MESSAGE = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
	private static final String CAR_DUPLICATION_ERROR_MESSAGE = "자동차의 이름이 중복되면 안됩니다.";
	private static final String CAR_NUMBERS_ERROR_MESSAGE = "자동차의 이름을 2대 이상 입력해야 합니다.";
	private static final String RESULT_END_MESSAGE = "가 우승했습니다.";

	private final ScoreBoard scoreBoard;
	private String output = CAR_INPUT_MESSAGE;
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
			output = CAR_INPUT_MESSAGE;
			throw e;
		}
	}

	public void setTotalRound(final int totalRound) {
		this.setTotalRound(Integer.toString(totalRound));
	}

	private void validTotalRound() {
		if (totalRound.getValue() <= 0) {
			throw new IllegalArgumentException(ROUND_INPUT_ERROR_MESSAGE);
		}
	}

	public void setCars(final String csvCarNames) {
		try {
			final String[] carNames = StringUtils.deleteWhitespace(csvCarNames).split(",");
			validCarNames(carNames);
			for (final String name : carNames) {
				scoreBoard.addCar(new Car(name));
			}
			output = ROUND_INPUT_MESSAGE;
		} catch (IllegalArgumentException e) {
			scoreBoard.clearCarScoreMap();
			output = CAR_INPUT_MESSAGE;
			throw e;
		}
	}

	private void validCarNames(final String[] carNames) {
		if (carNames.length <= 1) {
			throw new IllegalArgumentException(CAR_NUMBERS_ERROR_MESSAGE);
		}
		if (checkDuplication(carNames)) {
			throw new IllegalArgumentException(CAR_DUPLICATION_ERROR_MESSAGE);
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
			output = scoreBoard.getResult() + System.lineSeparator() + scoreBoard.getWinner() + RESULT_END_MESSAGE;
		}
	}

	public boolean isCompleted() {
		return totalRound.getValue() == scoreBoard.getCurrentRound().getValue();
	}

}
