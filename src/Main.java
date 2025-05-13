import java.util.Scanner;
import java.util.Stack;
import Clases.Operaciones;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.print("Ingrese una expresión infija: ");
            String expr = sc.nextLine().replaceAll(" ", "");

            Stack<Character> postfijo = Operaciones.infijoAPostfijo(expr);
            Stack<Character> prefijo = Operaciones.infijoAPrefijo(expr);

            System.out.print("Postfijo: " + postfijo);
            System.out.print("\nPrefijo: " + prefijo);

            System.out.println("\n¿Desea ingresar otra expresión? (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase();
            if (!respuesta.equals("s")) {
                continuar = false;
            }
        }

        sc.close();

    }
}
