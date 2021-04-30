package me.dgahn.racingcar;

import java.util.Random;
import java.util.Scanner;

import me.dgahn.racingcar.domain.RandomNumberGenerator;
import me.dgahn.racingcar.view.CarConsoleView;
import me.dgahn.racingcar.view.CarViewModel;

public class Launcher {

	public static void main(final String[] args) {
		final var random = new Random();
		final var generator = new RandomNumberGenerator(random);
		final var viewModel = new CarViewModel(generator);
		final var scanner = new Scanner(System.in);
		final var view = new CarConsoleView(scanner);

		setGame(view, viewModel);
	}

	private static void setGame(final CarConsoleView view, final CarViewModel viewModel) {
		try {
			final String csvCarNames = view.input(viewModel.getOutput());
			viewModel.setCars(csvCarNames);
			final String count = view.input(viewModel.getOutput());
			viewModel.setCount(count);
		} catch(IllegalArgumentException e) {
			view.error(e.getMessage());
		}
	}

}
