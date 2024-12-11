package org.example.service;

import java.util.Map;
import java.util.Stack;

public class StackBasedCalculator implements Calculator{

    private static final Map<String, Integer> PRECEDENCE = initPrecedence();

    @Override
    public int calculate(String expression) {
        Stack<Integer> operands = new Stack<>();
        Stack<String> operators = new Stack<>();

        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (isNumber(token)) {
                operands.push(Integer.parseInt(token));
            } else if (PRECEDENCE.containsKey(token)) {
                while (!operators.isEmpty() &&
                        PRECEDENCE.get(token) <= PRECEDENCE.get(operators.peek())) {
                    applyOperator(operands, operators.pop());
                }
                operators.push(token);
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(operands, operators.pop());
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

        private static Map<String, Integer> initPrecedence(){
            return Map.of(
                    "+", 1,
                    "-", 1,
                    "*", 2,
                    "/", 2
            );
        }

        private void applyOperator(Stack<Integer> operands, String operator) {
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
            }
        }

}
