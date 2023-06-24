// Даны два Deque, представляющие два целых числа. Цифры хранятся в обратном порядке и каждый из их узлов содержит одну цифру.
// 1) Умножьте два числа и верните произведение в виде связанного списка.
// 2) Сложите два числа и верните сумму в виде связанного списка.
// Одно или два числа могут быть отрицательными.

// Даны два Deque, цифры в обратном порядке.
// [3,2,1,-] - пример Deque
// [5,4,3]- пример второго Deque
// 1) -123 * 345 = -42 435
// Ответ всегда - связный список, в обычном порядке
// [-,4,2,4,3,5] - пример ответа

package homework4;

import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * task
 */
public class task {

    // Парсим очередь в число тип int
    public static int getValue(Deque<Character> d){     
        StringBuilder sb = new StringBuilder();
        while (!d.isEmpty()) {            
            sb.append(d.pollLast());
        }
        return Integer.parseInt(sb.toString());
    }

    //Преобразуем число тип int в связный список
    public static LinkedList<Character> int2linkedList(int number){
        LinkedList<Character> list = new LinkedList<>();
        String string = Integer.toString(number);
        for(int i=0; i<string.length(); i++){
            list.add(string.charAt(i));
        }
        return list;
    }

    public static void main(String[] args) {
        // Создаем исходные очереди
        Deque<Character> deque1 = new ArrayDeque<>();
        deque1.add('3');
        deque1.add('2');
        deque1.add('1');
        deque1.add('-');

        ArrayDeque<Character> deque2 = new ArrayDeque<>();
        deque2.add('5');
        deque2.add('4');
        deque2.add('3');

        int firstNumber = getValue(deque1);
        System.out.println(String.format("Первое число: %d", firstNumber));
        
        int secondNumber = getValue(deque2);
        System.out.println(String.format("Второе число: %d", secondNumber));

        LinkedList<Character> list = int2linkedList(firstNumber * secondNumber);
        System.out.println("Результат: " + list.toString());
    }
}