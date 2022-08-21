package stringCalculator.test;

import stringCalculator.main.StringCalculator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestStringCalculator{

    StringCalculator calculator = new StringCalculator();
    String inpuString;
    int expected;

    @Test
    public void emptyStringInput(){
        inpuString = "";
        expected = 0;
        assertEquals(expected, calculator.add(inpuString));
    }
}