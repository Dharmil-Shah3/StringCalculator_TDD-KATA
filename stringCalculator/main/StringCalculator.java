package stringCalculator.main;

import java.util.StringTokenizer;

public class StringCalculator{

    public int add(String inputString){
        
        inputString = inputString.replace(" ", "");
        
        StringTokenizer inputNumber = new StringTokenizer(inputString,",");

        if(inputString.length()==0 || inputNumber.countTokens()==0 ){
            // for empty string
            return 0;
        }
        else if(inputNumber.countTokens() == 1){
            // for single value
            String number = inputNumber.nextToken().replace(" ", "");
            char firstChar = number.charAt(0);
            if( (int)firstChar >= 97 && (int)firstChar <= 122 ){
                // if number is alphabet then return according values. i.e) a=1...z=26
                return (int)firstChar - 96;
            }
            else if(Integer.parseInt(inputString) < 0){
                // throws exception if number is negative
                throw new IllegalArgumentException(
                "\n Negative values are not allowed: "+Integer.parseInt(inputString));
            }
            return Integer.parseInt(inputString);
        }

        int sum = 0;
        String negativeNumbers = ""; // to check whether input has any negative values or not

        while(inputNumber.hasMoreTokens()){ // for multiple values
            
            // trimmed token and added to 'sum' as an Integer            
            String number = inputNumber.nextToken().replace(" ","");
            
            if( (int)number.charAt(0) >= 97 && (int)number.charAt(0) <= 122 ){
                // if value is an alphabet then convert into according values
                sum += (int)number.charAt(0) - 96;
            }
            else if(Integer.parseInt(number) < 0){ 
                // if it's negative then add to negativeNumbers String
                negativeNumbers += Integer.parseInt(number) + ", ";
            }
            else{
                sum += Integer.parseInt(number);
            }
        }
        if(negativeNumbers != ""){
            throw new IllegalArgumentException(
            "\n Negative values are not allowed: "+negativeNumbers);
        }
        return sum; // returning answer
        
    }
}