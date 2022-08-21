package stringCalculator.test;

import stringCalculator.main.StringCalculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestStringCalculator{

    StringCalculator calculator = new StringCalculator();
    String inputString;
    int expected;

    @Test
    public void emptyStringInput(){
        // single value input should return 0
        inputString = "";
        expected = 0;
        assertEquals(expected, calculator.add(inputString));
        
        inputString = ", ,, ,,,";
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void singleValueInput(){
        // single value input should return value itself
        inputString = "4";
        expected = 4;
        assertEquals(expected, calculator.add(inputString));
        
        inputString = " 36 781 ";
        expected = 36781;
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void multiValueInputWithDelimiter(){
        // multiple values enterd with delimiter ',' and should return sum of all values
        inputString = "1,2";
        expected = 3;
        assertEquals(expected, calculator.add(inputString));

        inputString = "1,2,3,4, 5";
        expected = 15;
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void alphabeticValueInput(){
        // a=1, b=2... z=16.   i.e) a+5+b = 8
        inputString = "1,2,a,c";
        expected = 7;
        assertEquals(expected, calculator.add(inputString));

        inputString = "z";
        expected = 26;
        assertEquals(expected, calculator.add(inputString));

        inputString = "a,z";
        expected = 27;
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void negativeValueThrowsException(){
        //  Throws exception and display those negative numbers
        inputString = "-1";
        var e = assertThrows(
            IllegalArgumentException.class, 
            ()->{ calculator.add(inputString); }
        );
        assertEquals("\n Negative values are not allowed: -1", e.getMessage());

        inputString = "a,-1,8, 69, -7,-79";
        e = assertThrows(
            IllegalArgumentException.class, 
            ()->{ calculator.add(inputString); }
        );
        assertEquals("\n Negative values are not allowed: -1, -7, -79, ", e.getMessage());
    }

    @Test
    public void valueGreaterThan1000ShouldBeIgnored(){
        inputString = "2,1001";
        expected = 2;
        assertEquals(expected, calculator.add(inputString));

        inputString = "2022,1234";
        expected = 0;
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void acceptNewlineAsADelimiter(){
        // Accepts '\n' as a delimiter like ','
        inputString = "1\n2,3";
        expected = 6;
        assertEquals(expected, calculator.add(inputString));

        inputString = "50\n500\n5";
        expected = 555;
    }

    @Test
    public void inputWithCustomDelimiter(){
        inputString = "//;\n1;2";
        expected = 3;
        assertEquals(expected, calculator.add(inputString));

        inputString = "//***\n5***5***5";
        expected = 15;
        assertEquals(expected, calculator.add(inputString));
    }

    @Test
    public void additionOfOddIndices(){
        inputString = "0//;\n1;2;3,4";
        expected = 4;

        inputString = "0//***\n6***2,2***4";
        expected = 8;
    }

    @Test
    public void additionOfEvenIndices(){
        inputString = "1//;\n1;2;3,4";
        expected = 6;

        inputString = "1//***\n6***8,10***12";
        expected = 20;
    }
}