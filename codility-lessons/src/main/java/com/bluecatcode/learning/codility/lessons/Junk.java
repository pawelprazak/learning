package com.bluecatcode.learning.codility.lessons;

public class Junk {

    public static int digits(int n) {
        return (int) ((n == 0) ? 1 : Math.log10(Math.abs(n)) + 1);
    }

    public static int sumOfDigits(int n) {
        int sum = 0;
        do {
            sum += n % 10;
        } while ((n /= 10) > 0);
        return sum;
    }

    public static int getDigit(int x, int n) {
        return (int) ((Math.abs(x) / Math.pow(10, n)) % 10);
    }

    public static int getDigitLeft(int x, int n) {
        return getDigit(x, digits(x) - n - 1);
    }

}
