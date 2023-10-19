import java.util.Scanner;


public class fibonacci {
    public  void fubonacci() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese un número para calcular su Fibonacci: ");
        int numero = sc.nextInt();
        sc.close();

        if (numero < 0) {
            System.out.println("Ingrese un valor válido (n >= 0).");
        } else {
            System.out.println("El número de Fibonacci en la posición " + numero + " es: " + fibu(numero));
        }
    }

    public static int fibu(int numero) {
        if (numero <= 1) {
            return numero;
        } else {
            return fibu(numero - 1) + fibu(numero - 2);
        }
    }
}
