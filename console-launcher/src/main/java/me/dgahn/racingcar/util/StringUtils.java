package me.dgahn.racingcar.util;

public class StringUtils {

	private StringUtils() {}

	public static final String BLANK = " ";
	public static final String TAB = "\t";
	public static final String NEW_LINE = "\n";
	public static final String QUOTES = "";

	public static String deleteWhitespace(final String str) {
		return str.replace(BLANK, QUOTES).replace(NEW_LINE, QUOTES).replace(TAB, QUOTES);
	}

}
