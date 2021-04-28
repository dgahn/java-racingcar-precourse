package me.dgahn.racingcar.view;

import me.dgahn.racingcar.util.StringUtils;

public class CatViewModel {

	public String[] createCar(final String input) {
		return StringUtils.deleteWhitespace(input).split(",");
	}

}
