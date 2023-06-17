// Пусть дан произвольный список целых чисел.
// 1) Нужно удалить из него чётные числа
// 2) Найти минимальное значение
// 3) Найти максимальное значение
// 4) Найти среднее значение

package homework3;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;

public class task {

    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите количество элементов списка: ");
        int number = iScanner.nextInt();

        //Создаем список целых чисел
        ArrayList<Integer> list = GenerateArrayListInteger(number);
        System.out.println("Список чисел:" + System.lineSeparator()); 
        System.out.println(list.toString());

        // Удаляем четные числа
        Iterator<Integer> col = list.iterator();
        while (col.hasNext()) {         
            if(col.next() % 2 == 0)
                col.remove();
        }
        System.out.println("Список после удаления четных элементов:" + System.lineSeparator()); 
        System.out.println(list.toString());

        //Ищем мин.и макс.значение и среднее арифметическое
        int min = list.get(0), max = list.get(0);
        double sum=0;
        col = list.iterator();
        while (col.hasNext()) {    
            int value = col.next();   
            sum +=   value;
            if(value > max) max = value;
            if(value < min) min = value;
        }        
        System.out.println(String.format("Мин.элемент списка: %d", min));
        System.out.println(String.format("Макс.элемент списка: %d", max));
        System.out.println(String.format("Среднее арифметическое в спиcке: %f", sum / list.size()));
        
        iScanner.close();
    }
    
    // Generate random integers in range 0 to 99
    public static ArrayList<Integer> GenerateArrayListInteger(int number){
        if (number <= 0)
            return new ArrayList<Integer>();
        ArrayList<Integer> list = new ArrayList<Integer>(number<0 ? 0:number);
        Random rand = new Random();        
        for (int i=0; i<number; i++)
            list.add(rand.nextInt(100));
        return list;
    }
}