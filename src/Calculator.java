import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator {

    public static void main(String[] args) {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String expression = reader.readLine();
            System.out.println(calc(expression));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String calc(String input) throws Exception {
        String[] arab2 = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayList<String> arab = new ArrayList<>(Arrays.asList(arab2));
        String result = null;
        boolean isArabic = false;
        boolean isRoman = false;
        int operandFirst = 0;
        int operandSecond = 0;

        String[] operands = input.split("\\+|\\*|/|-");

        if (operands.length > 2) {
            throw new Exception();
        }

        if (arab.contains(operands[0]) && arab.contains(operands[1])) {
            isArabic = true;
        }

        if (isArabic) {
            operandFirst = Integer.valueOf(operands[0]);
            operandSecond = Integer.valueOf(operands[1]);}

        else {
        for (RomanNumber romanNumber : RomanNumber.values()) {
            if (romanNumber.name().equals(operands[0])) {
                operandFirst = Integer.valueOf(transformToArabic(operands[0]));
                if (operandFirst > 10){throw new Exception();}
            }
            if (romanNumber.name().equals(operands[1])) {
                operandSecond = Integer.valueOf(transformToArabic(operands[1]));
                if (operandSecond > 10){throw new Exception();}
            }
        }

            if (operandFirst !=0 && operandSecond !=0){
                isRoman = true;
            }
            else if (operandFirst != 0 || operandSecond != 0) {
                throw new Exception();
            }
        }

        if (!isArabic && !isRoman) {
            throw new Exception();
        }

        if (input.contains("+")) {
            result = "" + (operandFirst + operandSecond);
        } else if (input.contains("-")) {
            result = "" + (operandFirst - operandSecond);
        } else if (input.contains("/")) {
            result = "" + (operandFirst / operandSecond);
        } else if (input.contains("*")) {
            result = "" + (operandFirst * operandSecond);
        }
        if(isRoman){
           result = transformToRoman(result);
        }
        return result;
    }

    public static String transformToArabic(String operand) {
        String value = null;
        for (RomanNumber romanNumber : RomanNumber.values()) {
            if (operand.equals(romanNumber.name())) {
                value = romanNumber.getValue() + "";
                break;
            }
        }
        return value;
    }

    public static String transformToRoman(String result) throws Exception {
        int finalNumber = Integer.parseInt(result);
        if (finalNumber < 0 || finalNumber == 0) {
            throw new Exception();
        }
        String output = null;
        for (RomanNumber romanNumber : RomanNumber.values()) {
            if (romanNumber.getValue() == finalNumber) {
                output = romanNumber.name();
            }
        }
        String strDozens = null;
        String strRemainder = null;
        if (output == null) {
            for (RomanNumber romanNumber : RomanNumber.values()) {
                int remainder = finalNumber % 10;
                int dozens = finalNumber / 10;
                if (romanNumber.getValue() == remainder) {
                    strRemainder = romanNumber.name();
                }
                if (romanNumber.getValue() == dozens * 10) {
                    strDozens = romanNumber.name();
                }
                output = strDozens + strRemainder;
            }
        }
        return output;
    }
}
