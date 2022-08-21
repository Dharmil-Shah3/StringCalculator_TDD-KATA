package stringCalculator.test;

import stringCalculator.main.StringCalculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.io.InputStream;

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
}