// Реализуйте структуру телефонной книги с помощью HashMap.
// Программа также должна учитывать, что во входной структуре 
// будут повторяющиеся имена с разными телефонами, 
// их необходимо считать, как одного человека с разными телефонами. 
// Вывод должен быть отсортирован по убыванию числа телефонов.
// Пример:
// Иванов 1231234
// Иванов 3456345
// Иванов 5676585
// Петров 12345
// Петров 82332

package homework5;

import java.util.*;

/**
 * task
 */
public class task {
    
    // для заданной фамилии выводим отсортированный список телефонов
    public static void printData(Map<String, ArrayList<Integer>> map, String name){
        if(!map.containsKey(name)) return;
        ArrayList<Integer> phones = map.get(name);
        for(int one : phones)
            System.out.printf(name + " " + one + System.lineSeparator());             
    }

    public static void main(String[] args) {
        
        Map<String, ArrayList<Integer>> map = new HashMap<>();       

        //исходные данные (фамилия и телефон через пробел)
        String[] source = {"Иванов 1231234",
        "Иванов 3456345",
        "Иванов 5676585",
        "Петров 12345",
        "Петров 82332",
        "Сидоров 23123135"};
        
        //Формируем map
        for(String one : source){
            String[] splitted = one.split(" ");
            String Name = splitted[0]; 
            int Phone = Integer.parseInt(splitted[1]);

            if(map.containsKey(Name)){
                ArrayList<Integer> phones = map.get(Name);
                for(int phone : phones)
                    if(Phone==phone)
                        continue;    //если телефон уже имеется в списке, не добавляем его                                
                phones.add(Phone);
                Collections.sort(phones);   //сортируем список телефонов по возрастанию
                map.put(Name, phones);                
            }else{
                ArrayList<Integer> phones = new  ArrayList<Integer>();
                phones.add(Phone);
                map.put(Name, phones);              
            }
        }   
        
        //Выводим телефонную книгу. 
        // Вывод должен быть отсортирован по убыванию числа телефонов.
        Set<String> keyset  = map.keySet();
        //System.out.printf(keyset.toString());             

        while(!keyset.isEmpty()){
            //Ищем в списке фамилию с наибольшим количеством телефонов 
            int max=0; String smax="";
            for (String s : keyset) {
                if(map.get(s).size() > max){
                    max = map.get(s).size();
                    smax = s;
                }
            }  
            printData(map, smax);
            keyset.remove(smax);
        }
    }
}