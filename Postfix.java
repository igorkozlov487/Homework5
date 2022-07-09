import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {
        getPostfix();
    }
    /**
     * Метод переводит инфиксное выражение в постфиксное
     * @return очередь Queue<String> с постфиксным выражением
     */
    public static Queue<String> getPostfix() {

        String infixExpression = getInfix();

        Queue<String> queue = new LinkedList<>();
        Stack<String> st = new Stack<>();
        String keepNum = "";

        for (int i = 0; i < infixExpression.length(); i++) {

            String infix = Character.toString(infixExpression.charAt(i));

            if  (isLetter(infix)) {
                queue.add(infix);
            }
            else if (isDigit(infix)) {
                while (infixExpression.length() - 1 != i){
                    if (isDigit(Character.toString(infixExpression.charAt(i + 1))) == false){
                        keepNum = keepNum + infix;
                        queue.add(keepNum);
                        keepNum = "";
                        break;
                    }
                    else if (isDigit(Character.toString(infixExpression.charAt(i + 1)))){
                        keepNum = keepNum + infix;
                        break;
                    }
                    else {
                        queue.add(infix);
                        break;
                    }
                }    
                if (infixExpression.length() - 1 == i){
                    keepNum = keepNum + infix;
                    queue.add(keepNum);
                }
            }
            else if (isSign(infix)) {

                if (st.isEmpty() || "(".contains(st.peek())) {
                    st.push(infix);
                }
                else if (priority(infix) > priority(st.peek())){
                    st.push(infix);
                }
                else if (priority(infix) <= priority(st.peek())){
                    queue.add(st.pop());
                    st.push(infix);
                }
            } 
            else if ("(".contains(infix)){
                st.push(infix);
            } 
            else if (")".contains(infix)){
                while (st.isEmpty() == false && "(".contains(st.peek()) == false){
                    queue.add(st.pop());
                }
                st.remove("(");
            }
            
        }
        while (st.isEmpty() == false){
            queue.add(st.pop());
        }
    
        System.out.printf("Постфиксная запись: ");
        for (String item: queue) {
            System.out.print(item);
            System.out.printf(" ");
        }
        return queue;
        
    }

    /**
     * Метод проверяет, является ли символ строки цифрой
     * @param s - входящий символ строки
     * @return true, если символ - цифра
     * @throws NumberFormatException
     */
    public static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Метод определяет приоритет знака математической операции
     * @param s - входящий символ строки
     * @return цифру, в зависимости от величины приоритета математической операции
     */
    public static int priority(String s){
            if ("^".contains(s))
            {
                return 3;
            }
            else if ("*".contains(s) || "/".contains(s))
            {
                return 2;
            }
            else if ("+".contains(s) || "-".contains(s))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    
    /**
     * Метод проверяет, является ли символ строки знаком математической операции
     * @param s - входящий символ строки
     * @return true, если символ - знак математической операции
     */    
    public static boolean isSign(String s){
        if ("^/*+-".contains(s)){
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Метод проверяет, является ли символ строки буквой
     * @param s - входящий символ строки
     * @return true, если символ - буква
     */
    public static boolean isLetter(String s){
        Character ch = s.charAt(0);
        if (Character.isLetter(ch)){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Метод запрашивает данные из консоли и убирает пробелы из строки
     * @return строку без пробелов
     */
    public static String getInfix() {
        Scanner iScanner = new Scanner(System.in);
        System.out.printf("Введите выражение в инфиксной записи, отделяя каждый символ пробелом:\n");
        String infix = iScanner.nextLine();
        infix = infix.replaceAll("\\s", "");
        iScanner.close();
        return infix;
    }
}
