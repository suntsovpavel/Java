// 4) (дополнительное задание) Задано уравнение вида q + w = e, q, w, e >= 0.
// Некоторые цифры могут быть заменены знаком вопроса, например, 2? + ?5 = 69.
// Требуется восстановить выражение до верного равенства.
// Знаки вопроса - одинаковые цифры.
// Предложить хотя бы одно решение или сообщить, что его нет.
package homework1;

import java.util.Scanner;

public class task4 {
    public static void main(String[] args) {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите выражение: ");
        String sourceString = iScanner.nextLine();

        //Проверяем, содержит ли строка знак вопроса 
        boolean isContainsQMark = sourceString.contains("?");        
        if(isContainsQMark){                         
            //Строка содержит знаки вопроса '?'
            //Поочередно пробуем подставить цифры от 0 до 9, 
            //с тем, чтобы обеспечить правильность выражения
            for (int i=0; i<10; i++){  
                //Делаем замену знаков вопроса на число(в виде символа) и проверяем получившееся выражение                
                String str = sourceString.replace('?', String.valueOf(i).charAt(0));

                if(checkExpression(str)){
                    System.out.printf("Для '%s' найдено верное выражение: %s", sourceString, str);
                    iScanner.close();     
                    return;
                } 
            }
            //Если добрались сюда, ни одна замена '?' на число не дала верное выражение
            System.out.printf("Для '%s' не найдено верное выражение",  sourceString);            
        }else{
            if(checkExpression(sourceString)){                
                System.out.printf("Исходное выражение '%s' является верным", sourceString);          
            }else{
                System.out.printf("Исходное выражение '%s' не является верным", sourceString);     
            } 
        }            
        iScanner.close();     
    }

    //Проверяем равенство выражения вида  q + w = e
    public static boolean checkExpression(String str){
        //делаем сплит на 3 подстроки по символам '+' и '='
        String[] splitted = str.split("[+|=]");
        try{
            int a = Integer.parseInt(splitted[0]);
            int b = Integer.parseInt(splitted[1]);
            int c = Integer.parseInt(splitted[2]);
            return (a + b == c);           
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return false;   
        }          
    }
}
