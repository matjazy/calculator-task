# Stack-Based Calculator

This project provides a simple stack-based calculator that can parse and evaluate basic infix expressions using integer arithmetic.

## Features

- Supports the following operators: `+`, `-`, `*`, `/`
- Handles operator precedence, ensuring multiplication and division are evaluated before addition and subtraction
- Throws exceptions for malformed expressions and unsupported operators
- Provides user-friendly error messages and handles division by zero gracefully

## Requirements

- Java 23 or later
- Maven 4.0.0 or later

## How to Use

### Building and Running the Calculator

**Step 1: Build the Project**  
Run the following command to compile and package the project:
```
mvn clean package
```
This will produce a JAR file in the target directory.

**Step 2: Run the Application**
After packaging, you can run the calculator using:

```
java -jar target/stack-based-calculator-1.0-SNAPSHOT.jar
```

Upon running, the application will display a welcome message and demonstrate a series of sample calculations. These examples show how various infix expressions are evaluated. No user interaction is required—just observe the output.

Examples demonstrated by the program:

2 + 3 returns 5

3 * 2 + 1 returns 7

10 / 2 returns 5

2 + 3 * 4 returns 14 (since * is evaluated before +)

6 - 6 / 3 returns 4

3 * -2 + 6 returns 0


## Handling Errors and Exceptions ##
If you integrate or modify this calculator in your own code, you may encounter errors in certain scenarios:

**Empty or Null Input:**

If you provide an empty string or null, the calculator will throw:

Expression cannot be null or empty

**Unsupported Operators:**

If you use an unsupported operator, you'll get:

Unsupported operator

**Malformed Expressions:**

If the expression is structurally incorrect (e.g., missing an operand or operator), you'll see:

Malformed expression

**Division by Zero:**

Attempting division by zero results in:

Division by zero

**Additional Examples**

You can experiment by modifying the code in Main.java to evaluate other expressions. For example:

3 * 5 - 4 / 2 should return 13

1000000000 + 1000000000 should return 2000000000

## Test Suite ##
To run the provided JUnit tests, use:

```
mvn test
```
If all tests pass successfully, you will see a "BUILD SUCCESS" message.

Enjoy using the Stack-Based Calculator!




