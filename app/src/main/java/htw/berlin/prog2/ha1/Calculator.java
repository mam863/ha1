package htw.berlin.prog2.ha1;

/**
 * Eine Klasse, die das Verhalten des Online Taschenrechners imitiert, welcher auf
 * https://www.online-calculator.com/ aufgerufen werden kann (ohne die Memory-Funktionen)
 * und dessen Bildschirm bis zu zehn Ziffern plus einem Dezimaltrennzeichen darstellen kann.
 * Enthält mit Absicht noch diverse Bugs oder unvollständige Funktionen.
 */
public class Calculator {

    private String screen = "0";
    private double latestValue;
    private String latestOperation = "";

    public String readScreen() {
        return screen;
    }

    public void pressDigitKey(int digit) {
        if (digit > 9 || digit < 0) throw new IllegalArgumentException("Digit must be between 0 and 9");
        if (screen.equals("0") || latestValue == Double.parseDouble(screen)) {
            screen = "";
        }
        screen += digit;
    }

    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressBinaryOperationKey(String operation) {
        if (!isValidOperation(operation)) {
            displayError("Invalid operation");
            return;
        }
        latestValue = Double.parseDouble(screen);
        latestOperation = operation;
        screen = "0";
    }

    private boolean isValidOperation(String operation) {
        return operation.equals("+") || operation.equals("-") || operation.equals("x") || operation.equals("/");
    }

    public void pressUnaryOperationKey(String operation) {
        if (!operation.equals("√") && !operation.equals("%") && !operation.equals("1/x")) {
            displayError("Invalid operation");
            return;
        }
        double currentValue = Double.parseDouble(screen);
        double result = switch (operation) {
            case "√" -> Math.sqrt(currentValue);
            case "%" -> currentValue / 100;
            case "1/x" -> 1 / currentValue;
            default -> throw new IllegalArgumentException("Invalid operation");
        };
        if (Double.isNaN(result)) {
            displayError("Error");
        } else {
            screen = formatResult(result);
        }
    }

    public void pressDotKey() {
        if (!screen.contains(".")) {
            screen += ".";
        }
    }

    public void pressNegativeKey() {
        if (screen.startsWith("-")) {
            screen = screen.substring(1);
        } else if (!screen.equals("0")) {
            screen = "-" + screen;
        }
    }

    public void pressEqualsKey() {
        if (latestOperation.isEmpty()) {
            displayError("No operation set");
            return;
        }
        double result = calculateResult(Double.parseDouble(screen));
        screen = formatResult(result);
        if (screen.equals("Infinity")) {
            displayError("Cannot divide by zero");
        }
    }

    private double calculateResult(double current) {
        return switch (latestOperation) {
            case "+" -> latestValue + current;
            case "-" -> latestValue - current;
            case "x" -> latestValue * current;
            case "/" -> latestValue / current;
            default -> throw new IllegalStateException("Unexpected operation: " + latestOperation);
        };
    }

    private void displayError(String message) {
        screen = "Error: " + message;
        latestOperation = "";
        latestValue = 0.0;
    }

    private String formatResult(double result) {
        String formatted = String.format("%.10f", result);
        formatted = formatted.replaceAll("\\.0+$", "");
        if (formatted.length() > 10) {
            formatted = formatted.substring(0, 10);
        }
        return formatted;
    }
}
