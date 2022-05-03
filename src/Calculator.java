import java.util.Scanner;

class Calculator {
    private final int EXIT_CODE = 0;

    private String[] operations = {"+", "-", "*", "/"};
    private Number[] numbers = {new Number(), new Number()};

    private String message = "";
    private char operator;
    private Scanner scanner = new Scanner(System.in);
    private int operationIndex;
    private int operatorSymbolAmount = 0;
    private RomanNumber romanNumber=null;

    private void getMessage(String input) {
        message=input;
    }

    private void findOperationSymbol() {
        int template = -1;

        for (int i = 0; i < operations.length; i++) {
            if (message.indexOf(operations[i]) != template) {
                operationIndex = message.indexOf(operations[i]);
                operator = message.charAt(operationIndex);
                break;
            }
        }
    }

    private void breakMessage() {
        for (int i = 0; i < message.length(); i++) {
            if (i < operationIndex) {
                numbers[0].increaseStringNumber(message.charAt(i));
            }
            else if (i > operationIndex) {
                numbers[1].increaseStringNumber(message.charAt(i));
            }
        }
    }

    private void reset() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i].reset();
        }

        operatorSymbolAmount=0;
        romanNumber=null;
        message="";
    }

    private void convertNumbers() {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i].convert();
        }
    }

    private void findAllOperatorSymbolAmount() {
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == operator) {
                operatorSymbolAmount++;
            }
        }
    }

    private boolean isAllNumbersRoman(){
        if(numbers[0].getIsRoman()==numbers[1].getIsRoman()&&numbers[0].getIsRoman()==false){
            return false;
        }
        else if(numbers[0].getIsRoman()==numbers[1].getIsRoman()&&numbers[1].getIsRoman()==true){
            return true;
        }
        else if (numbers[0].getIsRoman()!=numbers[1].getIsRoman()) {
            System.out.println("Используются разные системы счисления");
            Runtime.getRuntime().exit(EXIT_CODE);
        }
        return false;
    }

    private String convertResultToRoman(int result){
        if(result<=10){
            romanNumber=RomanNumber.getNameByValue(result);
            System.out.println(romanNumber);
        }
        else if(result>10){
            if(result%10==0){
                return RomanNumber.getNameByValue(result).toString();
            }
            else{
                int firstPart=result/10;
                int secondPart=result%10;
                RomanNumber firstNumber=RomanNumber.getNameByValue(firstPart*10);
                RomanNumber secondNumber=RomanNumber.getNameByValue(secondPart);

                return firstNumber.name().toString() + secondNumber.name().toString();
            }
        }

        return "";
    }

    private String doOperate() {
        int result=0;
        findOperationSymbol();
        findAllOperatorSymbolAmount();

        if(operatorSymbolAmount>1){
            System.out.println("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            Runtime.getRuntime().exit(EXIT_CODE);
        }
        else if(operatorSymbolAmount==0){
            System.out.println("строка не является математической операцией");
            Runtime.getRuntime().exit(EXIT_CODE);
        }
        else if(operatorSymbolAmount==1){
            convertNumbers();

            if(isAllNumbersRoman() == false){
                switch (operator) {
                    case '+':
                        result=(numbers[0].getValue() + numbers[1].getValue());
                        break;
                    case '-':
                        result=(numbers[0].getValue() - numbers[1].getValue());
                        break;
                    case '*':
                        result=numbers[0].getValue() * numbers[1].getValue();
                        break;
                    case '/':
                        result=numbers[0].getValue() / numbers[1].getValue();
                        break;
                    default:
                        System.out.println("Такого оператора нет");
                        Runtime.getRuntime().exit(EXIT_CODE);
                        break;
                }

                return Integer.toString(result);
            }
            else if(isAllNumbersRoman()){
                switch (operator) {
                    case '+':
                        result=numbers[0].getValue() + numbers[1].getValue();
                        break;
                    case '-':
                        result=numbers[0].getValue() - numbers[1].getValue();

                        if(result<=0){
                            System.out.println("Такого римского числа не существует");
                            Runtime.getRuntime().exit(EXIT_CODE);
                        }
                        break;
                    case '*':
                        result=numbers[0].getValue() * numbers[1].getValue();
                        break;
                    case '/':
                        result=numbers[0].getValue() / numbers[1].getValue();
                        break;
                    default:
                        System.out.println("Такого оператора нет");
                        Runtime.getRuntime().exit(EXIT_CODE);
                        break;
                }

                String answer=convertResultToRoman(result);
                return answer;
            }
        }

        return Integer.toString(result);
    }

    public String doCalculate(String input) {
        getMessage(input);
        findOperationSymbol();
        breakMessage();
        String result=doOperate();
        reset();

        return result;
    }
}
