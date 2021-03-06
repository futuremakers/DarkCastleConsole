package com.yaa.rpg.playit.theme.color;

import static com.yaa.rpg.playit.theme.color.Color.*;

public class ColorFormatter {

	public static void main(String[] args) {
		System.out.println(yellow("this will be yellow") + " white");
		System.out.println(green("and this should be " + GREEN) + " white");
		System.out.println(red("and this should be " + RED) + " white");
		System.out.println(underlinedBlue("underlined blue") + " white");
		System.out.println(boldMagenta("bold magenta") + " white");
	}

	public static String red(String input) {
		return RED.format(input);
	}

	public static String blue(String input) {
		return BLUE.format(input);
	}

	public static String green(String input) {
		return GREEN.format(input);
	}

	public static String yellow(String input) {
		return YELLOW.format(input);
	}

	public static String cyan(String input) {
		return CYAN.format(input);
	}

	public static String bold(String input) {
		return BOLD.format(input);
	}

	public static String boldMagenta(String input) {
		return bold(MAGENTA.format(input));
	}

	public static String underlinedBlue(String input) {
		return UNDERLINE.format(BLUE.format(input));
	}

}
