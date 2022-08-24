package stringCalculator.main;

import java.util.ArrayList;

public class StringCalculator{
    
    private String trimAccordingOddEvenDelimiter(String inputString){
        char check = inputString.charAt(3);
        if( Character.isDigit(check) || Character.isLetter(check) ) {
            // if it's not delimiter then trim '0//' or '1//'
            return inputString.substring(3);
        }
        else{
            // if it's delimiter then just trim 0 or 1, keep '//' as it is.
            return inputString.substring(1);
        }
    }

    private String [] splitAndRemoveEmptyTokens(String inputString, String delimiter){
        // this function removes empty elements from splitted array and also hepls in case of negative numbers
        String tempTokens[] = inputString.split(delimiter);
        ArrayList <String> tokens = new ArrayList<String>();
    
        for(int i=0 ; i<tempTokens.length ; i++){
            if(i>0 && !tempTokens[i].isBlank() && delimiter.equals("-") && tempTokens[i-1]==""){
                tokens.add("-"+tempTokens[i]); // add '-' at start if last element was empty
            }
            else if(!tempTokens[i].isBlank()){
                tokens.add(tempTokens[i]);     // add value as it is if last element was not empty
            }
        }
        return tokens.toArray(new String[0]);
    }

    private int singleValueEval(String token) throws IllegalArgumentException {
        if( (int)token.charAt(0) >= 97 && (int)token.charAt(0) <= 122 ){
            // if number is alphabet then return according values. i.e) a=1...z=26
            return (int)token.charAt(0) - 96;
        }
        else if(Integer.parseInt(token) < 0){
            throw new IllegalArgumentException("\n Negative values are not allowed: [ "+Integer.parseInt(token)+" ]");
        }
        return Integer.parseInt(token);
    }

    private int multiValueEval(String tokens[], boolean odd, boolean even){
        int sum = 0;
        String negativeNumbers = "";
        short count=0;  // for '0//' and '1//' (odd and even indices)
        
        for(short i=0 ; i<tokens.length ; i++){ // for multiple values
    
            count++;
            if((odd && count%2==0) || (even && count%2==1)){
                // skip iteration according to odd/even '0//' '1//'
                continue;
            }
            if( (int)tokens[i].charAt(0) >= 97 && (int)tokens[i].charAt(0) <= 122 ){
                // if value is an alphabet then convert into according values a=1...z=26
                sum += (int)tokens[i].charAt(0) - 96;
            }
            else if(Integer.parseInt(tokens[i]) < 0){
                negativeNumbers += Integer.parseInt(tokens[i]) + " ";
            }
            else if(Integer.parseInt(tokens[i])>1000){
                continue;
            }
            else{
                sum += Integer.parseInt(tokens[i]);
            }
        }
        if(negativeNumbers != ""){
            throw new IllegalArgumentException("\n Negative values are not allowed: [ "+negativeNumbers+"]");
        }
        return sum;
    }

    public int add(String inputString){
        
        inputString = inputString.replace(" ", "");
    
        String delimiter = ",";
        boolean odd = false;  // for '0//' odd indices's addition
        boolean even = false; // for '1//' even indices's addition
    
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
            // get '\n' postion to know end of the delimiter
            int end = inputString.indexOf("\n");
            delimiter = inputString.substring(2, end);
            // after getting delimiter, remove that part from string to get only numbers
            inputString = inputString.substring(end+1);
            inputString = inputString.replace(",", delimiter);
        }
        
        inputString = inputString.replace("\n", delimiter); // making delimiter unified by replacing \n(if any) with delimiter
        
        String [] tokens = splitAndRemoveEmptyTokens(inputString, delimiter);
        
        if(inputString.length()==0 || tokens.length==0 ){
            return 0;
        }
        else if(tokens.length == 1){
            return singleValueEval(tokens[0]);
        }
        else{
            return multiValueEval(tokens, odd, even);
        }
    }
}