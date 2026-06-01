import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Калькулятор задач ===");
        System.out.println("Доступные операции: +, -, *, /");
        System.out.println("=========================");
        
        // Если аргументы переданы через командную строку
        if (args.length == 3) {
            calculate(args[0], args[1], args[2]);
        } 
        // Интерактивный режим
        else {
            try {
                System.out.print("Введите первое число: ");
                double a = scanner.nextDouble();
                
                System.out.print("Введите оператор (+, -, *, /): ");
                String op = scanner.next();
                
                System.out.print("Введите второе число: ");
                double b = scanner.nextDouble();
                
                double result = calculate(a, op, b);
                System.out.printf("\n📊 Результат: %.2f %s %.2f = %.2f%n", a, op, b, result);
            } catch (Exception e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    private static void calculate(String aStr, String op, String bStr) {
        try {
            double a = Double.parseDouble(aStr);
            double b = Double.parseDouble(bStr);
            double result = calculate(a, op, b);
            System.out.printf("\n📊 Результат: %.2f %s %.2f = %.2f%n", a, op, b, result);
        } catch (NumberFormatException e) {
            System.out.println("❌ Ошибка: Введите корректные числа");
        }
    }
    
    private static double calculate(double a, String op, double b) {
        switch(op) {
            case "+": 
                return a + b;
            case "-": 
                return a - b;
            case "*": 
                return a * b;
            case "/": 
                if (b == 0) {
                    throw new ArithmeticException("Деление на ноль!");
                }
                return a / b;
            default:
                throw new IllegalArgumentException("Неизвестный оператор: " + op);
        }
    }
}