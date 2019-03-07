package ood.srp;


import java.util.Scanner;

public class AppCalc {
    public static void main(String[] args) {
        InterCalc calc = new EngineerInteractCalc(new SimpleInteractCalc(new Calculator()));
        Scanner scanner = new Scanner(System.in);
        Operation operation = null;
        boolean start = true;
        boolean next = false;
        while (start) {
            while (!next) {
                calc.showMenu();
                System.out.println("Выберете пункт меню или '0' для выхода:");
                var check = scanner.nextInt();
                if (check == 0) {
                    System.out.println("Вы вышли из программы...");
                    start = false;
                    break;
                }
                try {
                    operation = calc.getOperation(check);
                    next = true;
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
            }
            while (next) {
                operation.execute(scanner);
                System.out.println("Сохранить результат? (y)");
                var oldValue = scanner.next();
                if (!"y".equalsIgnoreCase(oldValue)) {
                    calc.resetResult();
                }
                next = false;
            }
        }
    }
}
