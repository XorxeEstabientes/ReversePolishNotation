/*
A simple infix to postfix expression converter.
Uses a String as input, with every number or character ( *, /, + , - ( and )) separated by a single whitespace.
Doesn't apply power(^), only parentheses ( and ), adding, subtraction, multiplying and dividing.
Examples:
7 + 13 / ( 12 - 4 ) -> 7 13 12 4 - / +
x * y / ( 5 * z ) + 10 -> x y * 5 z * / 10 +
 */

package com.xorxeestabientes;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = readLine(scanner).split("\\s+");
        StringBuilder result = new StringBuilder();

        Deque<String> stack = new ArrayDeque<>();

        Map<String, Integer> priority = new HashMap<>();
        priority.put("*", 3);
        priority.put("/", 3);
        priority.put("+", 2);
        priority.put("-", 2);
        priority.put("(", 1);

        for (String str : input) {
            if (isNumber(str)) {
                result.append(str).append(" ");
            } else {
                if (stack.isEmpty()) {
                    stack.push(str);
                } else if (str.equals("(")) {
                    stack.push(str);
                } else if (!str.equals(")")) {
                    if (priority.get(str) > priority.get(stack.peek())) {
                        stack.push(str);
                    } else if (priority.get(str) <= priority.get(stack.peek())) {
                        while (!stack.isEmpty() && priority.get(str) <= priority.get(stack.peek())) {
                            result.append(stack.pop()).append(" ");
                        }
                        stack.push(str);
                    }
                } else {
                    while (!stack.peek().equals("(")) {
                        result.append(stack.pop()).append(" ");
                    }
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()){
            result.append(stack.pop()).append(" ");
        }

        System.out.println(result.toString().trim());

    }

    private static boolean isNumber(String str) {
        return (str.charAt(0) >= 48 && str.charAt(0) <= 57) || (str.charAt(0) >= 97 && str.charAt(0) <= 122);
    }

    private static String readLine(Scanner scanner) {
        return scanner.nextLine();
    }
}