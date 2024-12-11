package org.example.service;

import java.util.Map;
import java.util.Stack;

public class StackBasedCalculator implements Calculator{

    private static final Map<String, Integer> PRECEDENCE = initPrecedence();

    @Override
    public int calculate(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException("Expression cannot be null or empty");
        }

        Stack<Integer> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = expression.split(" ");

        boolean expectOperand = true;

        for (String token : tokens) {
            if (isNumber(token)) {
                if (!expectOperand) {
                    throw new IllegalStateException("Malformed expression");
                }
                operands.push(Integer.parseInt(token));
                expectOperand = false;
            } else {
                if (expectOperand) {
                    throw new IllegalStateException("Malformed expression");
                }

                if (!PRECEDENCE.containsKey(token)) {
                    throw new IllegalArgumentException("Unsupported operator: " + token);
                }

                while (!operators.isEmpty() &&
                        PRECEDENCE.get(token) <= PRECEDENCE.get(operators.peek())) {
                    applyOperator(operands, operators.pop());
                }
                operators.push(token);
                expectOperand = true;
            }
        }

        if (expectOperand) {
            throw new IllegalStateException("Malformed expression");
        }

        while (!operators.isEmpty()) {
            applyOperator(operands, operators.pop());
        }

        if (operands.size() != 1) {
            throw new IllegalStateException("Malformed expression");
        }

        return operands.pop();
    }

    private boolean isNumber(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static Map<String, Integer> initPrecedence() {
        return Map.of(
                "+", 1,
                "-", 1,
                "*", 2,
                "/", 2
        );
    }

    private void applyOperator(Stack<Integer> operands, String operator) {
        // Check if we have enough operands
        if (operands.size() < 2) {
            throw new IllegalStateException("Malformed expression");
        }

        int b = operands.pop();
        int a = operands.pop();

        switch (operator) {
            case "+" -> operands.push(a + b);
            case "-" -> operands.push(a - b);
            case "*" -> operands.push(a * b);
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Division by zero");
                operands.push(a / b);
            }
            default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

}

