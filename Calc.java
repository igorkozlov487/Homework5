import java.util.Queue;
import java.util.Stack;
import java.lang.Math;

public class Calc extends Postfix {
    public static void main(String[] args) {
        Calculate();
    }

    /**
     * Метод производит вычисление арифметического выражения
     */
    public static void Calculate() {

        Queue<String> exp = getExpression();
        String strArray[] = exp.toArray(new String[exp.size()]);
        Stack<Double> st = new Stack<>();
        double res = 0;
        for (int i = 0; i < strArray.length; i++) {

            if (isDigit(strArray[i])) {
                st.push(Double.parseDouble(strArray[i]));
            } else {
                switch (strArray[i]) {
                    case "+":
                        res = st.pop() + st.pop();
                        st.push(res);
                        break;
                    case "-":
                        res = -st.pop() + st.pop();
                        st.push(res);
                        break;
                    case "*":
                        res = st.pop() * st.pop();
                        st.push(res);
                        break;
                    case "/":
                        res =  1/st.pop() * st.pop();
                        st.push(res);
                        break;
                    case "^":
                        Double degree = st.pop();
                        Double base = st.pop();
                        res =  Math.pow(base,degree);
                        st.push(res);
                        break;
                    default:
                        break;
                }
            }
        }
        System.out.printf("\nРезультат выражения: %.0f\n", st.pop());

    }

    /**
     * Метод вызывает класс Postfix, который запрашивает в консоли арифметическое
     * выражение в инфиксной записи и выводит его в консоль в постфиксной записи
     */
    public static Queue<String> getExpression() {
        Postfix newExpression = new Postfix();
        return newExpression.getPostfix();        
    }

}
