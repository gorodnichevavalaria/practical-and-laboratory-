public class StringAddition {
    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "456";
        String result = addStrings(num1, num2);
        System.out.println("Сумма: " + result);
    }

    public static String addStrings(String num1, String num2) {
        int number1 = Integer.parseInt(num1);
        int number2 = Integer.parseInt(num2);
        int sum = number1 + number2;
        return Integer.toString(sum);
    }
}