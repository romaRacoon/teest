enum RomanNumber {
    I(1),II(2),III(3),IV(4),V(5),VI(6),VII(7),VIII(8),IX(9),X(10),
    XX(20),XXX(30),XL(40),L(50),LX(60),LXX(70),LXXX(80),XC(90),C(100);

    private int value;

    RomanNumber(int newValue) {
        value=newValue;
    }

    public static RomanNumber getNameByValue(int findingValue){
        for(RomanNumber romanNumber:RomanNumber.values()){
            if(romanNumber.value==findingValue){
                return romanNumber;
            }
        }

        return null;
    }

    public int getValue(){return value;}
}
