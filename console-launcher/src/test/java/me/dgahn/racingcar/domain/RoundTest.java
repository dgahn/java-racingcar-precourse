package me.dgahn.racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RoundTest {

	@ParameterizedTest
	@ValueSource(strings = {"abc", "!@#!@#", "-1", "10.7"})
	void Round_를_생성할_때_양수가_아니면_IllegalException이_발생한다(final String value) {
		assertThatThrownBy(() -> new Round(value))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("총 횟수는 1이상 입력해야 합니다.");
	}

	@Test
	void Round_를_양수로_생성_할_수_있다() {
		final var value = "10";
		final var count = new Round(value);
		assertThat(count.getValue()).isEqualTo(10);
	}

}
