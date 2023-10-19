import java.util.Scanner;
public class factorial {
    public void factorial(){
        Scanner sc = new Scanner(System.in);
        System.out.print("┌─[DIGITA UN NUMERO PARA SABER SU FACTORIAL]─[~]\n└──╼ ");
        int numero = sc.nextInt();
        long resultado = calcularFactorialRecursivo(numero);
        System.out.println("Factorial de " + numero + " es " + resultado);
    }
    public long calcularFactorialRecursivo(int numero) {
        if (numero == 0) {
            return 1; // Caso base: el factorial de 0 es 1.
        } else {
            return numero * calcularFactorialRecursivo(numero - 1); // Llamada recursiva.
        }
    }
}