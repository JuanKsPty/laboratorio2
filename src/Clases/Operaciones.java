package Clases;

import java.util.Stack;

public class Operaciones {

    public static Stack<Character> infijoAPostfijo(String expr) {
        Stack<Character> operadores = new Stack<>();
        Stack<Character> salida = new Stack<>();

        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);

            if (c == ' ') continue;

            if (esOperando(c)) {
                salida.push(c);
            } else if (c == '(') {
                operadores.push(c);
            } else if (c == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    salida.push(operadores.pop());
                }
                if (!operadores.isEmpty()) operadores.pop(); // eliminar '('
            } else if (esOperador(c)) {
                while (!operadores.isEmpty() && prioridad(operadores.peek()) >= prioridad(c)) {
                    salida.push(operadores.pop());
                }
                operadores.push(c);
            }
        }

        while (!operadores.isEmpty()) {
            salida.push(operadores.pop());
        }

        return salida;
    }

    public static Stack<Character> infijoAPrefijo(String expr) {
        String invertida = invertir(expr);
        Stack<Character> postfijoInvertido = infijoAPostfijo(invertida);
        Stack<Character> prefijo = new Stack<>();

        while (!postfijoInvertido.isEmpty()) {
            prefijo.push(postfijoInvertido.pop());
        }

        return prefijo;
    }

    private static String invertir(String expr) {
        String resultado = "";
        for (int i = expr.length() - 1; i >= 0; i--) {
            char c = expr.charAt(i);
            if (c == '(') resultado += ')';
            else if (c == ')') resultado += '(';
            else resultado += c;
        }
        return resultado;
    }

    private static boolean esOperando(char c) {
        return Character.isLetterOrDigit(c);
    }

    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static int prioridad(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }
}
