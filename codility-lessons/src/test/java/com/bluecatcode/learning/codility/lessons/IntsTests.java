package com.bluecatcode.learning.codility.lessons;

import static com.bluecatcode.learning.codility.lessons.Ints.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@SuiteClasses({ 
	IntsTests.CountTest.class,
	IntsTests.SumTest.class,
	IntsTests.GetDigitRightTest.class,
	IntsTests.GetDigitRightPeconditionsTest.class,
	IntsTests.GetDigitLeftTest.class,
	IntsTests.GetDigitLeftPeconditionsTest.class,
	IntsTests.DigitArrayTest.class,
	IntsTests.FromDigitArrayTest.class,
	IntsTests.ReverseDigitArrayTest.class,
	IntsTests.FromReverseDigitArrayTest.class,
	IntsTests.NumberToWordTest.class
})
public class IntsTests {
	
	@RunWith(value = Parameterized.class)	
	public static class CountTest {
	
		private final int input;
	    private final int expected;
	    
	    public CountTest(final int input, final int expected) {
	        this.input = input;
	        this.expected = expected;
	    }
	
	    @Parameters
	    public static Collection<Integer[]> data() {
	    	return Arrays.asList(new Integer[][] {
	            { 0, 1 }, /* input, expected */
	            { -1, 1 },
	            { 900003245, 9 },
	        });
	    }
	
	    @Test
		public void shouldCountDigits() {
			assertThat(digitCount(input), is(expected));
		}
	}
	
	@RunWith(value = Parameterized.class)
	public static class SumTest {
	
		private final int input;
	    private final int expected;
	    
	    public SumTest(final int input, final int expected) {
	        this.input = input;
	        this.expected = expected;
	    }
	
	    @Parameters
	    public static Collection<Integer[]> data() {
	    	return Arrays.asList(new Integer[][] {
	            { 0, 0 }, /* input, expected */
	            { -1, 1 },
	            { 1001, 2 },
	            { 1234, 1+2+3+4 }
	        });
	    }
	
	    @Test
		public void shouldSumDigits() {
			assertThat(digitSum(input), is(expected));
		}
	}
	
	@RunWith(value = Parameterized.class)
	public static class GetDigitRightTest {
	
		private final int input;
		private final int index;
		private final int expected;
	    
	    public GetDigitRightTest(final int input, final int index, final int expected) {
	        this.input = input;
	        this.index = index;
	        this.expected = expected;
	    }
	
	    @Parameters
	    public static Collection<Integer[]> data() {
	    	return Arrays.asList(new Integer[][] {
	            { 0, 0, 0 }, /* input, expected */
	            { -1004, 0, 4 },
	            { 1234, 1, 3 },
	            { -1234, 2, 2 },
	            { 1234, 3, 1 },
	        });
	    }
	
	    @Test
		public void shouldGetDigits() {
			assertThat(digitFromRight(input, index), is(expected));
		}
	}
	
	public static class GetDigitRightPeconditionsTest {
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void shouldNotAllowNegativeIndex() {
			digitFromRight(1234, -1);
		}
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void shouldNotAllowToBigIndex() {
			digitFromRight(1234, 4);
		}
	}
	
	@RunWith(value = Parameterized.class)
	public static class GetDigitLeftTest {
	
		private final int input;
		private final int index;
		private final int expected;
	    
	    public GetDigitLeftTest(final int input, final int index, final int expected) {
	        this.input = input;
	        this.index = index;
	        this.expected = expected;
	    }
	
	    @Parameters
	    public static Collection<Integer[]> data() {
	    	return Arrays.asList(new Integer[][] {
	            { 0, 0, 0 }, /* input, expected */
	            { -1004, 0, 1 },
	            { 1234, 1, 2 },
	            { -1234, 2, 3 },
	            { 1234, 3, 4 },
	        });
	    }
	
	    @Test
		public void shouldGetDigits() {
			assertThat(digitFromLeft(input, index), is(expected));
		}
	}
	
	public static class GetDigitLeftPeconditionsTest {
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void shouldNotAllowNegativeIndex() {
			digitFromLeft(1234, -1);
		}
		
		@Test(expected=IndexOutOfBoundsException.class)
		public void shouldNotAllowToBigIndex() {
			digitFromLeft(1234, 4);
		}
	}
	
	public static class DigitArrayTest {
		
		@Test
		public void shouldGetAllDigits() {
			final int[] result = digits(-1234);
			final int[] expected = new int[] {1,2,3,4};
			assertThat(result, is(expected));
		}
	}
	
	public static class FromDigitArrayTest {
		
		@Test
		public void shouldConvertDigits() {
			final int result = fromDigits(new int[] {1,2,3,4});
			final int expected = 1234;
			assertThat(result, is(expected));
		}
	}
	
	public static class ReverseDigitArrayTest {
		
		@Test
		public void shouldGetAllDigits() {
			final int[] result = digitsReversed(-1234);
			final int[] expected = new int[] {4,3,2,1};
			assertThat(result, is(expected));
		}
	}
	
	public static class FromReverseDigitArrayTest {
		
		@Test
		public void shouldConvertDigits() {
			final int result = fromDigitsReversed(new int[] {4,3,2,1});
			final int expected = 1234;
			assertThat(result, is(expected));
		}
	}
	
	@RunWith(value = Parameterized.class)
	public static class NumberToWordTest {

	    private final int input;
	    private final String expected;
	    
	    public NumberToWordTest(final int input, final String expected) {
	        this.input = input;
	        this.expected = expected;
	    }

	    @Parameters
	    public static Collection<Object[]> data() {
	    	return Arrays.asList(new Object[][] {
     			{ 0, "zero" },
	        	{ 1, "one" },
	        	{ 10, "ten" },
	        	{ 15, "fifteen" },
	        	{ 60, "sixty" },
	        	{ 67, "sixty-seven" },
	        	{ 72, "seventy-two" },
	        	{ 101, "one hundred one" },
	        	{ 205, "two hundred five" },
	            { 4589, "four thousand five hundred eighty-nine" },
	            { 3333, "three thousand three hundred thirty-three" },
	            { 67500, "sixty-seven thousand five hundred" },
	            { 100000, "one hundred thousand" },
	            { 100567, "one hundred thousand five hundred sixty-seven" },
	            { 172346, "one hundred seventy-two thousand three hundred forty-six" },
	            { 600700, "six hundred thousand seven hundred" },
	            { 678900, "six hundred seventy-eight thousand nine hundred" },
	            { 890000, "eight hundred ninety thousand" },
	            { 999999, "nine hundred ninety-nine thousand nine hundred ninety-nine" },
	            { 999999999, "nine hundred ninety-nine million nine hundred ninety-nine thousand nine hundred ninety-nine" },
	            { 1999999999, "one billion nine hundred ninety-nine million nine hundred ninety-nine thousand nine hundred ninety-nine" },
	            { -21239, "minus twenty-one thousand two hundred thirty-nine"},
	        });
	    }

	    @Test
	    public void test() {
	        assertEquals(expected, numberInWords(input));
	    }

	}
}