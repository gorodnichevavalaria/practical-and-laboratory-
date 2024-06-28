import java.io.*;
import java.util.Scanner;

public class Calculator {
    private static final String HISTORY_FILE = "history.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите действие: ");
        System.out.println("1. Выполнить вычисление");
        System.out.println("2. Показать историю");
        int choice = scanner.nextInt();

        if (choice == 1) {
            performCalculation(scanner);
        } else if (choice == 2) {
            showHistory();
        } else {
            System.out.println("Ошибка! Неверный выбор.");
        }
    }

    private static void performCalculation(Scanner scanner) {
        System.out.println("Введите выражение: ");
        scanner.nextLine();
        String expression = scanner.nextLine();

        try {
            double result = evaluate(expression);
            System.out.println("Результат: " + result);
            saveToHistory(expression + " = " + result);
        } catch (Exception e) {
            System.out.println("Ошибка в выражении: " + e.getMessage());
        }
    }

    private static void saveToHistory(String equation) {
        try (FileWriter writer = new FileWriter(HISTORY_FILE, true)) {
            writer.write(equation + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при сохранении истории: " + e.getMessage());
        }
    }

    private static void showHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HISTORY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении истории: " + e.getMessage());
        }
    }

    private static double evaluate(String expression) throws Exception {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() throws Exception {
                nextChar();
                double x = parseExpression();
                if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            double parseExpression() throws Exception {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() throws Exception {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor();
                    else if (eat('/')) {
                        nextChar();
                        if (ch == '/') {
                            nextChar();
                            x = (int) x / (int) parseFactor();
                        } else {
                            x /= parseFactor();
                        }
                    } else if (eat('%')) x %= parseFactor(); 
                    else return x;
                }
            }

            double parseFactor() throws Exception {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(expression.substring(startPos, this.pos));
                } else if (ch == '|') {
                    nextChar();
                    x = parseFactor();
                    eat('|');
                    x = Math.abs(x);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
}