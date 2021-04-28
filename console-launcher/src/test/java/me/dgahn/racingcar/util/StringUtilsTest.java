package me.dgahn.racingcar.util;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringUtilsTest {

	@Test
	void 문자열의_공백을_제거할_수_있다() {
		final var str = "A   B C \n \t";
		assertThat(StringUtils.deleteWhitespace(str)).isEqualTo("ABC");
	}

}
