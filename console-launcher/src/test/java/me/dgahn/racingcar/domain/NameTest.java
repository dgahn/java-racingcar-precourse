package me.dgahn.racingcar.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class NameTest {

	@ParameterizedTest
	@ValueSource(strings = {"", "우리나라만세"})
	void 이름이_한_글자_이하거나_다섯_글자_이상이면_IllegalException이_발생한다(final String input) {
		assertThatThrownBy(() -> new Name(input))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("자동차의 이름은 1글자 이상 5글자 이하입니다. (name: \"" + input + "\")");
	}

	@ParameterizedTest
	@ValueSource(strings = {"절", "객체지향형"})
	void 이름은_한글자_이상_다섯_글자_이하로_지을_수_있다(final String input) {
		final var name = new Name(input);
		assertThat(name.getValue()).isEqualTo(input);
	}

}
