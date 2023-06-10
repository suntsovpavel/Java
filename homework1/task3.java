
// 3) Реализовать простой калькулятор
// + - / *
// Введите первое число: A \n
// Введите знак: *
// Введите второе число: B
// Результат: A*B
// sc.next() -- считывает символ
// sc.nextLine() -- считывает всю строчку
// sc.nextInt() -- считывает число, но без перехода на след. строчку
package homework1;

import java.util.Scanner;

/**
 * task3
 */
public class task3 {

    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);   
        System.out.printf("Введите первое число: ");
        double a = iScanner.nextDouble();
        System.out.printf("Введите знак операции (+,-,*,/): ");
        char ch = iScanner.next().charAt(0); 
        System.out.printf("Введите второе число: ");
        double b = iScanner.nextDouble();            
        if(ch == '+'){
            System.out.printf("Результат: %f + %f = %f", a,b,a+b);          
        }else if(ch == '-'){
            System.out.printf("Результат: %f - %f = %f", a,b,a-b);     
        }else if(ch == '*'){
            System.out.printf("Результат: %f * %f = %f", a,b,a*b);          
        }else if(ch == '/'){
            System.out.printf("Результат: %f / %f = %f", a,b,a/b);          
        }else{
            System.out.printf("Неизвестный знак операции: %s", ch);                
        }    
        iScanner.close();
    }
}