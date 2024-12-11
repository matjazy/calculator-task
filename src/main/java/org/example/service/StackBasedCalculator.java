package org.example.service;

import java.util.Map;
import java.util.Stack;


public class StackBasedCalculator implements Calculator {

    private static final Map<String, Integer> PRECEDENCE = initPrecedence();
    private static final String ERR_EMPTY_EXPRESSION = "Expression cannot be null or empty";
    private static final String ERR_UNSUPPORTED_OPERATOR = "Unsupported operator: ";
    private static final String ERR_MALFORMED_EXPRESSION = "Malformed expression";

    @Override
    public int calculate(String expression) {
        validateInput(expression);

        Stack<Integer> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = expression.trim().split(" ");
        boolean shouldExpectOperand = true;

        for (String token : tokens) {
            if (isNumber(token)) {
                processOperand(token, operands, shouldExpectOperand);
                shouldExpectOperand = false;
            } else {
                processOperator(token, operands, operators, shouldExpectOperand);
                shouldExpectOperand = true;
            }
        }

        if (shouldExpectOperand) {
            throwMalformedExpression();
        }

        resolveRemainingOperators(operands, operators);

        if (operands.size() != 1) {
            throwMalformedExpression();
        }

        return operands.pop();
    }

    private void validateInput(String expression) {
        if (expression == null || expression.trim().isEmpty()) {
            throw new IllegalArgumentException(ERR_EMPTY_EXPRESSION);
        }
    }

    private void processOperand(String token, Stack<Integer> operands, boolean shouldExpectOperand) {
        if (!shouldExpectOperand) {
            throwMalformedExpression();
        }
        operands.push(Integer.parseInt(token));
    }

    private void processOperator(String token, Stack<Integer> operands, Stack<String> operators, boolean shouldExpectOperand) {
        if (shouldExpectOperand) {
            throwMalformedExpression();
        }
        if (!PRECEDENCE.containsKey(token)) {
            throw new IllegalArgumentException(ERR_UNSUPPORTED_OPERATOR + token);
        }

        while (!operators.isEmpty() && PRECEDENCE.get(token) <= PRECEDENCE.get(operators.peek())) {
            applyOperator(operands, operators.pop());
        }
        operators.push(token);
    }

    private void resolveRemainingOperators(Stack<Integer> operands, Stack<String> operators) {
        while (!operators.isEmpty()) {
            applyOperator(operands, operators.pop());
        }
    }

    private void applyOperator(Stack<Integer> operands, String operator) {
        if (operands.size() < 2) {
            throwMalformedExpression();
        }
        int b = operands.pop();
        int a = operands.pop();
        operands.push(computeOperation(a, b, operator));
    }

    private int computeOperation(int a, int b, String operator) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                yield a / b;
            }
            default -> throw new IllegalArgumentException(ERR_UNSUPPORTED_OPERATOR + operator);
        };
    }

    private static void throwMalformedExpression() {
        throw new IllegalStateException(ERR_MALFORMED_EXPRESSION);
    }

    private static boolean isNumber(String token) {
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
}
