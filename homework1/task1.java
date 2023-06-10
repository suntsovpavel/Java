//1) Вычислить n-ое треугольного число (сумма чисел от 1 до n), n! (произведение чисел от 1 до n)
package homework1;

import java.util.Scanner;

public class task1 {
    public static void main(String[] args) {

        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите целое число: ");
        int n = iScanner.nextInt();
        int sum = 0, product = 1;
        for (int i = 1; i <= n; i++) {
            sum += i;
            product *= i;
        }
        System.out.printf("сумма чисел от 1 до %d: %d\n", n, sum);
        System.out.printf("произведение чисел от 1 до %d: %d\n", n, product);
        iScanner.close();
    }
}
