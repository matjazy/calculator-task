package org.example;

import org.example.service.StackBasedCalculator;

public class ExampleCalculatorPresenter {
    public static void main(String[] args) {
        StackBasedCalculator calculator = new StackBasedCalculator();

        System.out.println("Welcome to the Stack-Based Calculator Demo!");
        System.out.println("Below are a few sample calculations:");

        String[] examples = {
                "2 + 3",
                "3 * 2 + 1",
                "10 / 2",
                "2 + 3 * 4",
                "6 - 6 / 3",
                "3 * -2 + 6"
        };

        for (String expression : examples) {
            try {
                int result = calculator.calculate(expression);
                System.out.println(expression + " = " + result);
            } catch (Exception e) {
                System.out.println(expression + " caused an error: " + e.getMessage());
            }
        }

        System.out.println("\nThese were just a few examples showing the calculator's functionality.");
        System.out.println("For more complex usage, integrate the calculator into your application or provide dynamic input.");
    }


}
