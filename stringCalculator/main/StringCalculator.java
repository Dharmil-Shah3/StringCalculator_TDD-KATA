package stringCalculator.main;

import java.util.StringTokenizer;

public class StringCalculator{

    public int add(String inputString){
        
        inputString = inputString.replace(" ", "");
        
        StringTokenizer inputNumber = new StringTokenizer(inputString,",");

        if(inputString.length()==0 || inputNumber.countTokens()==0 ){
            return 0;
        }
        else if(inputNumber.countTokens() == 1){
            return Integer.parseInt(inputString);
        }

        int sum = 0;
        while(inputNumber.hasMoreTokens()){
            //trimmed token and added to 'sum' as an Integer
            String number = inputNumber.nextToken().replace(" ","");
            sum += Integer.parseInt(number);
        }
        return sum; // returning answer
        
    }
}