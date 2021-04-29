package me.dgahn.racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CountTest {

	@ParameterizedTest
	@ValueSource(strings = {"abc", "!@#!@#", "-1", "10.7"})
	void Count를_생성할_때_양수가_아니면_IllegalException이_발생한다(final String value) {
		assertThatThrownBy(() -> new Count(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("양수를 입력해주세요. : " + value);
	}

	@Test
	void Count_를_양수로_생성_할_수_있다() {
		final var value = "10";
		final var count = new Count(value);
		assertThat(count.getValue()).isEqualTo(10);
	}

}
