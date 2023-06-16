//1) Дана строка sql-запроса "select * from students WHERE ". Сформируйте часть WHERE этого запроса, используя StringBuilder.
// Данные для фильтрации приведены ниже в виде json-строки.
//        Если значение null, то параметр не должен попадать в запрос.
//        String input_str = "{"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}"
//        Ввод данных: {"name":"Ivanov", "country":"Russia", "city":"Moscow", "age":"null"}
//        вывод: select * from students WHERE name=Ivanov AND country=Russia AND city=Moscow

public class task1 {
    public static boolean isError=false;

    public static void main(String[] args) {
        //Исходная json-строка:
        String input_str = "{\"name\":\"Ivanov\", \"country\":\"Russia\", \"city\":\"Moscow\", \"age\":\"null\"}";
    
        StringBuilder sql_queye = form_sql_queye(input_str);
        if(!isError)
            System.out.println(String.format("Результат: %s", sql_queye));              
        else
            System.out.println("!!! Программа завершила работу с ошибкой");                                  
    }

    public static StringBuilder form_sql_queye(String json_str){
        StringBuilder result = new StringBuilder();
        result.append("select * from students WHERE ");

        //Разбиваем json-строку на пары ключ-значение:
        String[] pairs = json_str.split(",");

        boolean isAddFirst = true; //первое добавление в sql-запрос без 'AND '

        //Для каждой пары парсим ключ String key и значение String value
        for(String elem : pairs){            
            String[] splitted = elem.split(":");
            if(splitted.length != 2){
                isError=true;
                System.out.println(String.format("!!! wrong format json in '%s'", elem));
                return result;
            }
            String value = getWordInsideQuotes(splitted[1]);  if(isError) return result;

            if(!value.equals("null")){
                String key = getWordInsideQuotes(splitted[0]);  if(isError) return result;                                                         
                //первое добавление без 'AND ':
                result.append(isAddFirst ? String.format("%s=%s ", key, value) : String.format("AND %s=%s ", key, value));
                isAddFirst = false;
            }
        }
        return result;
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