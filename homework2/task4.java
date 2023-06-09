// 4) К калькулятору из предыдущего ДЗ добавить логирование.
// 4+2=6
// 6-1=5

import java.util.Scanner;
import java.util.logging.*;
import java.io.IOException;
public class task4 {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(task4.class.getName());
        logger.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("homework2/task4_log.txt", true);
        logger.addHandler(fh);

        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите первое число: ");
        double a = iScanner.nextDouble();
        System.out.printf("Введите знак операции (+,-,*,/): ");
        char ch = iScanner.next().charAt(0);
        System.out.printf("Введите второе число: ");
        double b = iScanner.nextDouble();
        if(ch == '+'){
            double result = a + b;
            System.out.println(String.format("Результат: %f + %f = %f", a,b,result));
            logger.info(String.format("%f + %f = %f", a,b,result));
        }else if(ch == '-'){
            double result = a - b;
            System.out.println(String.format("Результат: %f - %f = %f", a,b,result));
            logger.info(String.format("%f - %f = %f", a,b,result));
        }else if(ch == '*'){
            double result = a * b;
            System.out.println(String.format("Результат: %f * %f = %f", a,b,result));
            logger.info(String.format("%f * %f = %f", a,b,result));
        }else if(ch == '/'){
            double result = a / b;
            System.out.println(String.format("Результат: %f / %f = %f", a,b,result));
            logger.info(String.format("%f / %f = %f", a,b,result));
        }else{
            System.out.println(String.format("Неизвестный знак операции: %s", ch));
        }
        iScanner.close();
    }
}