package stringCalculator.main;

import java.util.StringTokenizer;

public class StringCalculator{

    public int add(String inputString){
        
        inputString = inputString.replace(" ", ""); // Removing whitespaces from string

        String delimiter = ",\n";   // defining default delimiters
        boolean odd = false;    // for '0//' odd indices's addition
        boolean even = false;   // for '1//' even indices's addition

        if(inputString.startsWith("0//")){
            odd = true;
        }
        else if(inputString.startsWith("1//")){
            even = true;
        }
        if(odd || even){
            // if '0//' OR '1//' is there so check if next character is delimiter i.e) "0//;\n1;2" or not i.e) "0//1,2,3,4"
            int check = (int)inputString.charAt(3);
            if( 
            ( check>=48 && check<=57) || 
            ( check>=65 && check<=90) || 
            ( check>=97 && check<=122)) {
                // if it's not delimiter then trim '0//' or '1//'
                inputString = inputString.substring(3);
            }
            else{
                // if it's delimiter then just trim 0 or 1
                inputString = inputString.substring(1);
            }
        }

        if(inputString.startsWith("//"))
        {
            // getting '\n's postion to know end of the delimiter
            int end = inputString.indexOf("\n");
            //setting new delimiter including '\n'
            delimiter += inputString.substring(2, end) + "\n";
            // after getting delimiter, remove that part from string to get only numbers
            inputString = inputString.substring(end+1);
        }
        StringTokenizer inputNumber = new StringTokenizer(inputString, delimiter);

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
        short count=0; // for '0//' and '1//' (odd and even indices)

        while(inputNumber.hasMoreTokens()){ // for multiple values
            
            count++;
            if((odd && count%2==0) || (even && count%2==1)){
                // skip iteration according to odd/even '0//' '1//'
                inputNumber.nextToken();
                continue;
            }

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
            else if(Integer.parseInt(number)>1000){
                // value greater than 1000 should be ingnored
                continue;
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