package stringCalculator.main;

import java.util.StringTokenizer;

public class StringCalculator{
    
    private String trimAccordingOddEvenDelimiter(String inputString){
        char check = inputString.charAt(3);
        if( Character.isDigit(check) || Character.isLetter(check) ) {
            // if it's not delimiter then trim '0//' or '1//'
            return inputString.substring(3);
        }
        else{
            // if it's delimiter then just trim 0 or 1, keep '//' as is.
            return inputString.substring(1);
        }
    }

    private int singleValueEval(String token) throws IllegalArgumentException {
        if( (int)token.charAt(0) >= 97 && (int)token.charAt(0) <= 122 ){
            // if number is alphabet then return according values. i.e) a=1...z=26
            return (int)token.charAt(0) - 96;
        }
        else if(Integer.parseInt(token) < 0){
            throw new IllegalArgumentException("\n Negative values are not allowed: "+Integer.parseInt(token));
        }
        return Integer.parseInt(token);
    }

    private int multiValueEval(StringTokenizer tokens, boolean odd, boolean even){
        int sum = 0;
        String negativeNumbers = "";
        short count=0;  // for '0//' and '1//' (odd and even indices)

        while(tokens.hasMoreTokens()){ // for multiple values
            
            count++;
            if((odd && count%2==0) || (even && count%2==1)){
                // skip iteration according to odd/even '0//' '1//'
                tokens.nextToken();
                continue;
            }

            String number = tokens.nextToken();
            
            if( (int)number.charAt(0) >= 97 && (int)number.charAt(0) <= 122 ){
                // if value is an alphabet then convert into according values a=1...z=26
                sum += (int)number.charAt(0) - 96;
            }
            else if(Integer.parseInt(number) < 0){
                negativeNumbers += Integer.parseInt(number) + ", ";
            }
            else if(Integer.parseInt(number)>1000){
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
        return sum;
    }

    public int add(String inputString){
        
        inputString = inputString.replace(" ", ""); // Removing whitespaces from string
    
        String delimiter = ",\n";
        boolean odd = false;        // for '0//' odd indices's addition
        boolean even = false;       // for '1//' even indices's addition
    
        if(inputString.startsWith("0//")){
            odd = true;
        }
        else if(inputString.startsWith("1//")){
            even = true;
        }
    
        if(odd || even){
            // if '0//' OR '1//' is there so check if next character is delimiter i.e) "0//;\n1;2" or not i.e) "0//1,2,3,4"
            inputString = trimAccordingOddEvenDelimiter(inputString);
        }
    
        if(inputString.startsWith("//"))
        {
            // getting '\n's postion to know end of the delimiter
            int end = inputString.indexOf("\n");
            delimiter += inputString.substring(2, end);
            // after getting delimiter, remove that part from string to get only numbers
            inputString = inputString.substring(end+1);
        }
        
        StringTokenizer tokens = new StringTokenizer(inputString, delimiter);
    
        if(inputString.length()==0 || tokens.countTokens()==0 ){ // for empty string
            return 0;
        }
        else if(tokens.countTokens() == 1){ // for single value
            return singleValueEval(tokens.nextToken());
        }
        else{ // for multiple value
            return multiValueEval(tokens, odd, even);
        }
    }
}