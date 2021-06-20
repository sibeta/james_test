package com.james.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    public static void main(String[] args) {
        doTrade();
    }

    private static void doTrade() {
        String input = "";
        boolean outputResultFinished = false;
        Scanner scanner = new Scanner(System.in);;
        HashMap<String, Integer> resultMap = new HashMap<>();

        showWelcomeHint();
        showIfInitialHint();

        while (true) {
            input = scanner.nextLine();
            if(isYesStr(input)) {
                showInitialHint();
                String initialData = initTradeFromFile(resultMap);
                showInitialData(initialData);
                showInputMoreHint();
            }

            if(isQuitCmd(input)) {
                if (scanner != null) {
                    scanner.close();
                }
                break;
            }

            if(triggerExit(input)) {
                if(!outputResultFinished) {
                    showOutputHint();
                    outputResult(resultMap);
                    outputResultFinished = true;
                }

                showQuitHint();
            }

            if(isFormatValid(input)) {
                putToResultMap(resultMap, input);
                showInputMoreHint();
            }
        }
    }

    private static String initTradeFromFile(HashMap<String, Integer> resultMap) {
        Scanner scanner = null;
        StringBuilder sb = new StringBuilder();
        try {
            scanner = new Scanner(new File("./trade.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line = "";
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            sb.append(line).append("\n");
            putToResultMap(resultMap, line);
        }

        if (scanner != null) {
            scanner.close();
        }

        return sb.toString();
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
        String pattern = "[A-Z]{3}\\s-?[1-9][0-9]*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(input);
        return m.matches();
    }

    private static void putToResultMap(HashMap<String, Integer> resultMap, String line) {
        String[] lineArray = line.split(" ");
        if(lineArray.length == 2) {
            String key = lineArray[0];
            Integer val = Integer.parseInt(lineArray[1]);
            if(resultMap.containsKey(key)) {
                val = resultMap.get(key) + val;
            }
            resultMap.put(key, val);
        }
    }

    private static void outputResult(HashMap<String, Integer> resultMap) {
        String result = transferMapToString(resultMap);
        System.out.println(result);
    }

    private static String transferMapToString(HashMap<String, Integer> resultMap) {
        StringBuilder sb = new StringBuilder();
        for(String key : resultMap.keySet()) {
            sb = sb.append(key).append(" ").append(resultMap.get(key)).append("\n");
        }
        return sb.toString();
    }

    private static void showWelcomeHint() {
        System.out.println("****************************************************");
        System.out.println("***********Welcome to the trade center!*************");
        System.out.println("****************************************************");
    }

    private static void showIfInitialHint() {
        System.out.println("Do you want to read file for initialization?(yes/no)");
    }

    private static void showInitialHint() {
        System.out.println("Initial payment inputs");
    }

    private static void showInitialData(String data) {
        System.out.println(data);
    }

    private static void showInputMoreHint() {
        System.out.println("Please input more payments if needed (Ex: USD 123), please enter no if you don't want to input");
    }

    private static void showQuitHint() {
        System.out.println("Type quit anytime if you want to quit the program");
    }

    private static void showOutputHint() {
        System.out.println("Output:");
    }

}
