// 3) Дана строка (сохранить в файл и читать из файла)
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"},{"фамилия":"Петрова","оценка":"4","предмет":"Информатика"},{"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Написать метод(ы), который распарсит json и, используя StringBuilder, создаст строки вида: Студент [фамилия] получил [оценка] по предмету [предмет].
// Пример вывода:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
public class task3 {
    public static boolean isError=false;

    public static void main(String[] args) {        
        //1. Считываем строку из файла task3_source.txt
        String line = parseStringFromFile("homework2/task3_source.txt");
        if(isError){
            System.out.println("Ошибка чтения файла"); 
            return;
        }
        System.out.println(String.format("Исходная json-строка: %s", line));                         
        
        //2. Обрабатываем строку line и делаем запись в файл
        String fileResult = "task3_result.txt";
        workWithString(line, "homework2/" + fileResult);   
        if(!isError)
            System.out.println(String.format("Файл %s создан", fileResult));   
        else
            System.out.println("Программа завершила работу с ошибкой");   
    }

    public static String parseStringFromFile(String namefile){        
        try{
            String pathProject = System.getProperty ("user.dir");        
            String pathFile = pathProject.concat("/" + namefile);
            File file = new File(pathFile);        
            FileReader fr = new FileReader(file);          
            BufferedReader reader = new BufferedReader(fr);  
            String result =  reader.readLine();            
            reader.close();  
            return  result;
        }catch(IOException e){
            isError=true;    
            return "";
        }   
    }

    public static void workWithString(String line, String nameFileOut){
        try{
            String pathProject = System.getProperty ("user.dir");  
            String pathFile = pathProject.concat("/" + nameFileOut);
            File file = new File(pathFile);        
            FileWriter fw = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fw);

            boolean searchOpenBrace=true;  
            while(true){    //завершение оператором break
                int index = line.indexOf(searchOpenBrace ? '{' : '}');
                if(index == -1) break;
                if(!searchOpenBrace){   
                    //Найдена замыкающая скобка
                    //Парсим содержимое текущего блока внутри фигурных скобок
                    String forParse = line.substring(0, index);
                    String[] splitted = forParse.split("[,|:]");
                    if(splitted.length != 6){ 
                        System.out.println(String.format("!!! Количество элементов \"...\" в блоке '%s' не равно шести", forParse));       
                        return;
                    }

                    //Делаем запись в файл 
                    StringBuilder result = new StringBuilder();
                    result.append("Студент ");
                    String lastName =  getWordInsideQuotes(splitted[1]); if(isError) return;                
                    result.append(lastName + " получил ");
                    String score = getWordInsideQuotes(splitted[3]); if(isError) return;            
                    result.append(score + " по предмету ");
                    String subject = getWordInsideQuotes(splitted[5]); if(isError) return;     
                    result.append(subject);         
                    writer.write(result.toString() + System.lineSeparator());                           
                }
                //Если найдена открывающая скобка, ищем закрывающую, и наоборот:
                searchOpenBrace = !searchOpenBrace;
                line = line.substring(index+1);              
            }     
            writer.close();   
        }catch(IOException e){
            isError=true;    
        }   
    }

    //Получаем подстроку, находящуюся внутри кавычек
    public static String getWordInsideQuotes(String _str){
        String result = "";
        int indexFirstQuote = _str.indexOf('"');  
        if(indexFirstQuote == -1){
            isError=true;
        }else{
            int indexSecondQuote = _str.lastIndexOf('"'); 
            if(indexSecondQuote == indexFirstQuote){
                isError=true;
            }else{
                result = _str.substring(indexFirstQuote+1, indexSecondQuote);
                if(result.indexOf('"') != -1)
                    isError=true;                                    
            }
        }   
        if(isError)                 
            System.out.println(String.format("!!! неверный формат json, '%s' должно содержать две кавычки", _str));
        return result;        
    }
}