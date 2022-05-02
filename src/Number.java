class Number {
    private final int EXIT_CODE=0;
    private final int MIN_VALUE=1;
    private final int MAX_VALUE=10;

    private String stringValue ="";
    private int value;
    private boolean isRoman;
    private RomanNumber romanNumber=null;

    private void deleteSpaces(){
        stringValue = stringValue.replaceAll(" ","");
    }

    private boolean isExceededInterval(){
        if(value<MIN_VALUE||value>MAX_VALUE){
            return false;
        }

        return true;
    }

    private void checkRomanNumber(){
        try{
            romanNumber=RomanNumber.valueOf(stringValue);

            if(romanNumber!=null){
                isRoman=true;
            }
            else{
                isRoman=false;
            }
        }
        catch (IllegalArgumentException exception){
            isRoman=false;
        }
    }

    private void convertFromRomanToInteger(){
        try{
            RomanNumber template=RomanNumber.valueOf(stringValue);
            value= template.getValue();
        }
        catch (IllegalArgumentException exception){
            System.out.println("Число находится за границами 0< число <=10");
            Runtime.getRuntime().exit(EXIT_CODE);
        }
    }

    public void increaseStringNumber(char symbol){
        stringValue +=symbol;
    }

    public void convert(){
        deleteSpaces();
        checkRomanNumber();

        if(isRoman){
            convertFromRomanToInteger();
            if(isExceededInterval() == false){
                System.out.println("Число находится за границами (0< число<=10)");
                Runtime.getRuntime().exit(EXIT_CODE);
            }
        }
        else if(isRoman==false){
            try{
                value=Integer.parseInt(stringValue);

                if(isExceededInterval()==false){
                    System.out.println("Число находится за границами (0< число<=10)");
                    Runtime.getRuntime().exit(EXIT_CODE);
                }
            }
            catch (Exception exception){
                System.out.println("Такого числа не существует");
                Runtime.getRuntime().exit(EXIT_CODE);
            }
        }
    }

    public void reset(){
        stringValue ="";
    }

    public String getStringValue(){
        return stringValue;
    }

    public int getValue(){
        return value;
    }

    public boolean getIsRoman(){
        return isRoman;
    }
}
