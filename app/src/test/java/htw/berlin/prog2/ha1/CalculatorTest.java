package htw.berlin.prog2.ha1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
/**
 * Testklasse für den Retro-Taschenrechner.
 *
 * <p>Diese Klasse enthält verschiedene Tests zur Überprüfung der Funktionalität
 * der Implementierung des Taschenrechners. Jeder Tes Digitis nichts mehrBerechnungen
 * oder Fehlerfälle, die der Taschenrechner korrekt handhaben sollte.</p>
 */
@DisplayName("Retro calculator")
class CalculatorTest {

    /**
     * Testet die Addition von zwei positiven, mehrstelligen Zahlen.
     *
     * <p>Überprüft, ob der Taschenrechner nach der Addition von "20" und "20"
     * das erwartete Ergebnis "40" anzeigt.</p>
     */

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        // Testcode hier...

        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Quadratwurzelberechnung der Zahl "2".
     *
     * <p>Überprüft, ob der Taschenrechner nach der Berechnung der Quadratwurzel von "2"
     * das erwartete Ergebnis "1.41421356" anzeigt.</p>
     */
    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Division durch Null.
     *
     * <p>Überprüft, ob der Taschenrechner bei der Division der Zahl "7" durch "0"
     * eine Fehlermeldung ("Error") anzeigt.</p>
     */
    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Quadratwurzelberechnung einer negativen Zahl.
     *
     * <p>Überprüft, ob der Taschenrechner bei der Berechnung der Quadratwurzel von "-7"
     * eine Fehlermeldung ("Error") anzeigt.</p>
     */
    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Testet die Eingabe mehrerer Dezimalpunkte.
     *
     * <p>Überprüft, ob der Taschenrechner nicht mehrere Dezimalpunkte zulässt
     * und das erwartete Ergebnis "1.78" korrekt anzeigt.</p>
     */

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    //TODO hier weitere Tests erstellen

    /**
     * Testet die Löschfunktion.
     *
     * <p>Überprüft, ob der Taschenrechner nach Betätigung der Löschtaste ("C")
     * den Bildschirm auf "0" zurücksetzt.</p>
     */
    @Test
    @DisplayName("should clear the screen when the clear key is pressed")
    void testClearKey() {
        Calculator calc = new Calculator();
        calc.pressDigitKey(5);
        calc.pressDigitKey(3);
        calc.pressClearKey();
        String expected = "0";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Testet die maximale Anzahl an darstellbaren Ziffern.
     *
     * <p>Überprüft, ob der Taschenrechner nicht mehr als 10 Ziffern auf dem Bildschirm anzeigt,
     * wenn elf Ziffern eingegeben werden.</p>
     */
    @Test
    @DisplayName("should not display more than 10 digits")
    void testMaxDigitLength() {
        Calculator calc = new Calculator();

        for (int i = 0; i < 11; i++) {
            calc.pressDigitKey(1);
        }

        String expected = "1111111111";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Eingabe eines Dezimalpunkts nach einer Operation.
     *
     * <p>Überprüft, ob der Taschenrechner nach der Eingabe einer binären Operation
     * (z.B. Addition) weiterhin die korrekte Eingabe eines Dezimalpunkts ermöglicht.</p>
     */
    @Test

    @DisplayName("should allow decimal point input correctly after an operation")
    void testDecimalInputAfterOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(3);
        calc.pressDotKey();

        String expected = "3.";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Funktionalität von pressDigitKey().
     *
     * <p>Überprüft, ob die Eingabe von Ziffern korrekt funktioniert,
     * auch nach einer binären Operation.</p>
     */
        void testWeirdFunctionalityOfPressDigitKey() {
            Calculator calc = new Calculator();

            calc.pressDigitKey(5);
            calc.pressBinaryOperationKey("+");
            calc.pressDigitKey(5);
            calc.pressDigitKey(3);
            String expected = "53";
            String actual = calc.readScreen();

            assertEquals(expected, actual);
        }

    /**
     * Testet die Subtraktion von zwei positiven Zahlen.
     *
     * <p>Überprüft, ob der Taschenrechner nach der Subtraktion von "5" und "3"
     * das erwartete Ergebnis "2" anzeigt.</p>
     */
    @Test
    @DisplayName("should display result after subtracting two positive numbers")
    void testPositiveSubtraction() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("-");
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "2";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }
    /**
     * Testet die Behandlung ungültiger Operationen.
     *
     * <p>Überprüft, ob der Taschenrechner bei einer ungültigen Operation (z.B. "%")
     * eine Fehlermeldung ("Error") anzeigt.</p>
     */
    @Test
    @DisplayName("should display error when trying to perform an invalid operation")
    void testInvalidOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(5);
        calc.pressBinaryOperationKey("%"); // افترض أن "%" غير مدعوم
        calc.pressDigitKey(3);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    /**
     * Testet die Eingabe von "=" ohne vorherige Operation.
     *
     * <p>Überprüft, ob der Taschenrechner eine Fehlermeldung ("Error") anzeigt,
     * wenn "=" direkt gedrückt wird.</p>
     */
    @Test
    @DisplayName("should display error when pressing equals without an operation")
    void testEqualsWithoutOperation() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressEqualsKey(); // Directly pressing equals

        String expected = "Error: No operation set";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }



    }
