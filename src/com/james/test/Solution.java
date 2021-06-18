package com.james.test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) {
        System.out.println("****************************************************");
        System.out.println("Do you want to read file for initialization?(yes/no)");

        String input = "";
        boolean hasShowQuitHint = false;
        StringBuilder sb = new StringBuilder();
        while (true) {
            Scanner scan = new Scanner(System.in);
            input = scan.nextLine();
            if(isYesStr(input)) {
                showInitialHint();
            }

            if(isQuitCmd(input)) {
                break;
            }

            if(triggerExit(input)) {
                if(!hasShowQuitHint) {
                    outputResult(sb.toString());
                }

                showQuitHint();
                hasShowQuitHint = true;
            }

            if(isFormatValid(input)) {
                sb = sb.append(input).append("\n");
                showInputMoreHint();
            }
        }
    }

    private static boolean isYesStr(String input) {
        return "yes".equalsIgnoreCase(input);
    }

    private static boolean isNoStr(String input) {
        return "no".equalsIgnoreCase(input);
    }

    private static boolean isQuitStr(String input) {
        return "quit".equalsIgnoreCase(input);
    }

    private static boolean triggerExit(String input) {
        return isNoStr(input) || !isFormatValid(input) && !isYesStr(input) && !isQuitStr(input);
    }

    private static boolean isQuitCmd(String input) {
        return isQuitStr(input);
    }

    private static boolean isFormatValid(String input) {
        String pattern = "[A-Z]{3}\\s[1-9]{1}[0-9]{2}";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return m.matches();
    }

    private static void outputResult(String result) {
        System.out.println("Output:");
        System.out.println(result);
    }

    private static void showInitialHint() {
        System.out.println("Initial payment inputs");
    }

    private static void showInputMoreHint() {
        System.out.println("Please input more payments if needed (Ex: USD 123), please enter no if you don't want to input");
    }

    private static void showQuitHint() {
        System.out.println("Type quit anytime if you want to quit the program");
    }
}
