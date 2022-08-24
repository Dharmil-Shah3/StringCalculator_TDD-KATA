package stringCalculator.test;

import stringCalculator.main.StringCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestStringCalculator{

    StringCalculator calculator = new StringCalculator();

    @Test
    public void emptyStringInput(){
        // single value input should return 0
        assertEquals(0, calculator.add(""));
        assertEquals(0, calculator.add(",,,,,   , , , "));
    }

    @Test
    public void singleValueInput(){
        // single value input should return value itself
        assertEquals(4, calculator.add("4"));
        assertEquals(36781, calculator.add(" 36 781 "));
    }

    @Test
    public void multiValueInputWithDelimiter(){
        // multiple values enterd with delimiter ',' and should return sum of all values
        assertEquals(3, calculator.add("1,2"));
        assertEquals(15, calculator.add("1,2,3,4, 5"));
    }

    @Test
    public void alphabeticValueInput(){
        // a=1, b=2... z=16.   i.e) a+5+b = 8
        assertEquals(7, calculator.add("1,2,a,c"));
        assertEquals(26, calculator.add("z"));
        assertEquals(27, calculator.add("a,z"));
    }

    @Test
    public void negativeValueThrowsException(){
        //  Throws exception and display those negative numbers

        var e = assertThrows(
            IllegalArgumentException.class, 
            ()->{ calculator.add("//-\n-1-8--2"); }
        );
        assertEquals("\n Negative values are not allowed: [ -1 -2 ]", e.getMessage());

        e = assertThrows(
            IllegalArgumentException.class, 
            ()->{ calculator.add("a,-1,8, 69, -7,-79"); }
        );
        assertEquals("\n Negative values are not allowed: [ -1 -7 -79 ]", e.getMessage());
    }

    @Test
    public void valueGreaterThan1000ShouldBeIgnored(){
        assertEquals(2, calculator.add("2,1001"));
        assertEquals(0, calculator.add("2022,1234"));
    }

    @Test
    public void acceptNewlineAsADelimiter(){
        // Accepts '\n' as a delimiter like ','
        assertEquals(6, calculator.add("1\n2,3"));
        assertEquals(555,calculator.add("50\n500\n5"));
    }

    @Test
    public void inputWithCustomDelimiter(){
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(15, calculator.add("//[***]\n5***5***5"));
    }

    @Test
    public void additionOfOddIndices(){
        assertEquals(4, calculator.add("0//;\n1;2;3,4"));
        assertEquals(10, calculator.add("0//[**]\n6**2**4"));
    }

    @Test
    public void additionOfEvenIndices(){
        assertEquals(6, calculator.add("1//;\n1;2;3,4"));
        assertEquals(20, calculator.add("1//6,8,10\n12"));
    }
}