package org.example.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StackBasedCalculatorTest {

    private final StackBasedCalculator calculator = new StackBasedCalculator();

    @Test
    public void testSimpleAddition() {
        assertEquals(5, calculator.calculate("2 + 3"), "Simple addition failed");
    }

    @Test
    public void testSimpleSubtraction() {
        assertEquals(1, calculator.calculate("3 - 2"), "Simple subtraction failed");
    }

    @Test
    public void testSimpleMultiplication() {
        assertEquals(6, calculator.calculate("2 * 3"), "Simple multiplication failed");
    }

    @Test
    public void testSimpleDivision() {
        assertEquals(5, calculator.calculate("10 / 2"), "Simple division failed");
    }

    @Test
    public void testComplexExpression() {
        assertEquals(7, calculator.calculate("3 * 2 + 1"), "Complex expression failed");
    }

    @Test
    public void testExpressionWithNegativeNumbers() {
        assertEquals(0, calculator.calculate("3 * -2 + 6"), "Expression with negative numbers failed");
    }

    @Test
    public void testExpressionWithMultipleOperators() {
        assertEquals(4, calculator.calculate("6 - 4 / 2"), "Expression with multiple operators failed");
    }

    @Test
    public void testDivisionByZero() {
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.calculate("4 / 0"));
        assertEquals("Division by zero", exception.getMessage(), "Division by zero handling failed");
    }

    @Test
    public void testLargeNumbers() {
        assertEquals(2000000000, calculator.calculate("1000000000 + 1000000000"), "Large numbers failed");
    }

    @Test
    public void testPrecedenceMultiplicationOverAddition() {
        assertEquals(14, calculator.calculate("2 + 3 * 4"), "Operator precedence failed");
    }

    @Test
    public void testPrecedenceDivisionOverSubtraction() {
        assertEquals(4, calculator.calculate("6 - 6 / 3"), "Operator precedence for division failed");
    }

    @Test
    public void testMalformedExpressionMissingOperand() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> calculator.calculate("3 +"));
        assertTrue(exception.getMessage().contains("Malformed expression"), "Malformed expression handling failed");
    }

    @Test
    public void testMalformedExpressionExtraOperand() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> calculator.calculate("2 3 +"));
        assertTrue(exception.getMessage().contains("Malformed expression"), "Malformed expression with extra operand failed");
    }

    @Test
    public void testUnsupportedOperator() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate("2 ^ 3"));
        assertTrue(exception.getMessage().contains("Unsupported operator"), "Unsupported operator handling failed");
    }

    @Test
    public void testEmptyInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(""));
        assertEquals("Expression cannot be null or empty", exception.getMessage(), "Empty input handling failed");
    }

    @Test
    public void testNullInput() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.calculate(null));
        assertEquals("Expression cannot be null or empty", exception.getMessage(), "Null input handling failed");
    }

}
