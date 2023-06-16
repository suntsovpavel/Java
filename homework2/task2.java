//2) Реализуйте алгоритм сортировки пузырьком числового массива,
//        результат после каждой итерации запишите в лог-файл.

import java.io.IOException;
import java.util.logging.*;
public class task2 {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(task2.class.getName());
        logger.setLevel(Level.INFO);
        FileHandler fh = new FileHandler("homework2/task2_log.txt");
        logger.addHandler(fh);

        SimpleFormatter sFormat = new SimpleFormatter();
        fh.setFormatter(sFormat);

        //Задаем какой-то несортированный массив
        int[] arr = new int[] {91, 1, 5, 8, 34, 17, 9};
        logger.info(arr2String(arr));
        
        int len = arr.length;
        //алгоритм пузырьковой сортировки:
        for (int out=len-1; out>=1; out--){
            for (int in=0;  in < out; in++){
                if(arr[in] > arr[in+1]) {
                    //swap elements:
                    int tmp = arr[in];
                    arr[in] = arr[in+1];
                    arr[in+1] = tmp;
                    logger.info(arr2String(arr));
                }
            }
        }
    }

    public static String arr2String(int[] arr){
        StringBuilder result = new StringBuilder();
        for (int i=0; i<arr.length; i++){
            result.append(Integer.toString(arr[i]));
            if(i<arr.length-1)
                result.append(',');
        }
        return result.toString();
    }
}