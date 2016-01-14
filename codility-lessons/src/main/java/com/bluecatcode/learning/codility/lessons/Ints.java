package com.bluecatcode.learning.codility.lessons;

import static com.google.common.base.Optional.*;
import static com.google.common.base.Preconditions.*;
import static java.lang.Math.*;

import java.util.Arrays;
import java.util.LinkedList;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

public final class Ints {
	
	public static final int BASE_10 = 10;
	
	public static final String MINUS = "minus";	
	
	public static final ImmutableMap<Integer, String> NUMBERS = ImmutableMap.<Integer, String>builder()
			.put(0,"zero")
    		.put(1,"one")
    		.put(2,"two")
    		.put(3,"three")
    		.put(4,"four")
    		.put(5,"five")
    		.put(6,"six")
    		.put(7,"seven")
    		.put(8,"eight")
    		.put(9,"nine")
    		.put(10,"ten")
    		.put(11,"eleven")
    		.put(12,"twelve")
    		.put(13,"thirteen")
    		.put(14,"fourteen")
    		.put(15,"fifteen")
    		.put(16,"sixteen")
    		.put(17,"seventeen")
    		.put(18,"eighteen")
    		.put(19,"nineteen")
    		.put(20,"twenty")
    		.put(30,"thirty")
    		.put(40,"forty")
    		.put(50,"fifty")
    		.put(60,"sixty")
    		.put(70,"seventy")
    		.put(80,"eighty")
    		.put(90,"ninety")
    		.put(100,"hundred")
    		.put(1000,"thousand")
    		.put(1000000,"million")
    		.put(1000000000,"billion")
    		.build();

	private Ints() { /* disabled */ }

	public static boolean allZeros(int[] range) {
		for (int n : range) {
			if (n != 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Counts digits in an integer
	 * @param number
	 * @return digits count in the number
	 */
	public static int digitCount(final int number) {
		return (int) ((number == 0) ? 1 : log10(abs(number)) + 1);
	}
	
	/**
	 * Sums digits in an integer
	 * @param number number
	 * @return sum of the n's digits
	 */
	public static int digitSum(final int number) {
		int n = abs(number);
		int sum = 0;
		do {
			sum += n % BASE_10;
		} while ((n /= BASE_10) > 0);
		return sum;
	}

	/**
	 * Gets digit index in an integer, counting from right 
	 * @param number number
	 * @param index (from right)
	 * @return index-th digit from n
	 */
	public static int digitFromRight(final int number, final int index) {
		checkElementIndex(index, digitCount(number));
		return (int) ((abs(number) / pow(BASE_10, index)) % BASE_10);
	}

	/**
	 * Gets digit of index in an integer, counting from left 
	 * @param number
	 * @param index (from left)
	 * @return index-th digit from n
	 */
	public static int digitFromLeft(final int number, final int index) {
		checkElementIndex(index, digitCount(number));
		return digitFromRight(number, digitCount(number) - index - 1);
	}
	
	/**
	 * Converts a number to the array of it's digits
	 * @param number number
	 * @return array of digits
	 */
	public static int[] digits(final int number) {
		return digits(number, digitCount(number));
	}
	
	/**
	 * Converts a number to a reversed array of it's digits
	 * @param number number
	 * @return reversed array of digits
	 */
	public static int[] digitsReversed(final int number) {
		return digitsReversed(number, digitCount(number));
	}

	private static int[] digits(final int number, final int digitCount) {
		final int[] digits = new int[digitCount];
	
		int _n = abs(number);
		int i = digitCount - 1;
		do {
			digits[i--] = _n % BASE_10;
		} while ((_n /= BASE_10) > 0);
		return digits;
	}

	/**
	 * Converts a number to a reversed array of it's digits
	 * @param number number
	 * @param digitCount digit count
	 * @return reversed array of digits
	 */
	private static int[] digitsReversed(final int number, final int digitCount) {
		final int[] reversedDigits = new int[digitCount];
	
		int _n = abs(number);
		int i = 0;
		do {
			reversedDigits[i++] = _n % BASE_10;
		} while ((_n /= BASE_10) > 0);
		return reversedDigits;
	}

	public static int fromDigits(final int[] digits) {
		int n = 0;
		int i = 0;
		do {		
			n += digits[i++];
			if (i >= digits.length) {
				break;
			}
			n *= BASE_10;
		} while (true);
		return n;
	}

	public static int fromDigitsReversed(final int[] reversedDigits) {
		int n = 0;
		int i = reversedDigits.length;
		do {		
			n += reversedDigits[--i];
			if (i <= 0) {
				break;
			}
			n *= BASE_10;
		} while (true);
		return n;
	}

	@Beta
	public static String numberInWords(final int number) {
		Optional<String> name = fromNullable(NUMBERS.get(number));
		if (name.isPresent()) {
			return name.get();
		} else {
			return numberToName(number);
		} 
	}

	private static String numberToName(int number) {		
		final LinkedList<String> words = Lists.newLinkedList();
		final int[] digits = digitsReversed(number);		
		for (int factor = 0; factor < digits.length; factor += 3) {
			final int[] range = Arrays.copyOfRange(digits, factor, factor + 3);
			if (allZeros(range)) {
				continue;
			}
							
			switch (factor) {
			case 0: /* nothing */
				break;
			case 3: /* thousand */
				words.addFirst(NUMBERS.get((int) pow(BASE_10, 3)));
				break;
			case 6: /* million */
				words.addFirst(NUMBERS.get((int) pow(BASE_10, 6)));
				break;
			case 9: /* billion */
				words.addFirst(NUMBERS.get((int) pow(BASE_10, 9)));
				break;
			default:
				throw new IllegalStateException("Unknown factor: " + factor);
			}
			
			String part = tripletToWords(range);
			words.addFirst(part);
		}

		if (number < 0) { // negative
			words.addFirst(MINUS);
		}
		
		return Joiner.on(' ').join(words);
	}
	
	private static String tripletToWords(final int[] reversedDigits) {
		checkArgument(reversedDigits.length == 3, "This is not a triplet of digits, size: " + reversedDigits.length);
		
		final int number = fromDigitsReversed(reversedDigits);

		final int[] range = Arrays.copyOfRange(reversedDigits, 0, 2);
		final String dubletWords = dubletToWords(range);
		
		if (number >= 100) {
			final int thirdDigit = reversedDigits[2];
			final int factor = BASE_10 * BASE_10;
			final String dublet = allZeros(range) ? null : dubletWords;
			return Joiner.on(' ').skipNulls().join(
					NUMBERS.get(thirdDigit),
					NUMBERS.get(factor),
					dublet);
		} else {
			return dubletWords;
		}
	}
	
	private static String dubletToWords(final int[] reversedDigits) {
		checkArgument(reversedDigits.length == 2, "This is not a dublet of digits, size: " + reversedDigits.length);
		
		final int number = fromDigitsReversed(reversedDigits);
		
		Optional<String> name = fromNullable(NUMBERS.get(number));
		if (name.isPresent()) {
			return name.get();
		} else {
			final int firstDigit = reversedDigits[0];
			final int secondDigit = reversedDigits[1];
			final int tens = BASE_10 * secondDigit;
			return Joiner.on('-').join(
					NUMBERS.get(tens),
					NUMBERS.get(firstDigit));
		}
	}
}
