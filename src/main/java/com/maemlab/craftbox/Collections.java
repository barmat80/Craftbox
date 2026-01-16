package com.maemlab.craftbox;

public final class Collections {

	private Collections() {}

	public static boolean contains(String what, String... args) {
        for (String arg : args) {
            if (what.equals(arg)) {
                return true;
            }
        }
		return false;
	}

	public static boolean contains(int what, int... args) {
        for (int arg : args) {
            if (what == arg) {
                return true;
            }
        }
		return false;
	}

	public static boolean contains(double what, double... args) {
        for (double arg : args) {
            if (what == arg) {
                return true;
            }
        }
		return false;
	}
}
