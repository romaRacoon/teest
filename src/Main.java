import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        boolean isWork=true;
        String input;
        String answer;
        Scanner scanner=new Scanner(System.in);

        while (isWork){
            System.out.println("Введите математическую операцию");
            input=scanner.nextLine();
            System.out.println(calc(input));

            System.out.println("Хотите продолжить пользоваться калькулятором? 1 - Да, 2 - Нет");
            answer=scanner.nextLine();

            switch (answer){
                case "2":
                    isWork=false;
                    break;
                default:
                    break;
            }
        }
    }

    public static String calc(String input){
        Calculator calculator=new Calculator();
        return calculator.doCalculate(input);
    }
}
