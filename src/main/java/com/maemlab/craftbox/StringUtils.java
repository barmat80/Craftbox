package com.maemlab.craftbox;

public final class StringUtils {

	private StringUtils() {}

	/**
	 * Pads an integer with leading zeros to create a 3-digit string
	 *
	 * @param toPad the integer to pad
	 * @return a zero-padded string (e.g., 5 becomes "005")
	 */
	public static String zeroPadding(int toPad) {
		return String.format("%03d", toPad);
	}

	/**
	 * Pads a string with leading zeros to create a 3-digit string
	 *
	 * @param toPad the string to pad
	 * @return a zero-padded string (e.g., "5" becomes "005")
	 */
	public static String zeroPadding(String toPad) {
		return String.format("%03d", toPad);
	}

	/**
	 * Counts the number of words in a string based on a separator
	 *
	 * @param what the string to analyze
	 * @param separator the separator used to split words
	 * @return the number of words found
	 */
	public static double getWordCount(String what, String separator) {
		String[] str = what.split(separator);
		return str.length;
	}

	/**
	 * Retrieves a specific word from a string by its position (1-indexed)
	 *
	 * @param what the string to analyze
	 * @param index the position of the word to retrieve (starting from 1)
	 * @param separator the separator used to split words
	 * @return the word at the specified position, or empty string if index is out of bounds
	 */
	public static String getWordNum(String what, int index, String separator) {
		String[] str = what.split(separator);
		if (index > 0 && index <= str.length) {
			return str[index - 1];
		} else {
			return "";
		}
	}
	// TODO
	// empty
	// left
	// right
	// len
	// strtran
	// trim
	// lrtrim
}
