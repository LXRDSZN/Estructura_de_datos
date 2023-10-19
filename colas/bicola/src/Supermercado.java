import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Supermercado {
    private LinkedList<Integer> carritosDeCompra = new LinkedList<>();
    private List<LinkedList<Integer>> cajas = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Supermercado supermercado = new Supermercado();
        supermercado.iniciarSimulacion();
    }

    public void iniciarSimulacion() {
        for (int i = 1; i <= 25; i++) {
            carritosDeCompra.add(i);
        }

        for (int i = 0; i < 3; i++) {
            cajas.add(new LinkedList<>());
        }

        while (true) {
            System.out.println("SUPERMERCADO");
            System.out.println("1) Cliente llega al supermercado");
            System.out.println("2) Ver colas");
            System.out.println("3) Limpiar consola");
            System.out.println("4) Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    clienteLlega();
                    break;
                case 2:
                    verColas();
                    break;
                case 3:
                    limpiarConsola();
                    break;
                case 4:
                    sc.close();
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
            }
        }
    }

    public void clienteLlega() {
        if (carritosDeCompra.isEmpty()) {
            System.out.println("No hay carritos disponibles. Los clientes deben esperar.");
            return;
        }

        int cliente = carritosDeCompra.poll();
        System.out.println("Cliente " + cliente + " ha llegado al supermercado y está esperando en la cola.");

        // Seleccionar la caja con la menor cantidad de clientes
        int cajaSeleccionada = seleccionarCaja();
        cajas.get(cajaSeleccionada).add(cliente);
        System.out.println("Cliente " + cliente + " se coloca en la cola de la caja " + (cajaSeleccionada + 1) + ".");
    }

    public int seleccionarCaja() {
        int cajaMenosGente = 0;
        int cantidadMenosGente = Integer.MAX_VALUE;

        for (int i = 0; i < cajas.size(); i++) {
            if (cajas.get(i).size() < cantidadMenosGente) {
                cantidadMenosGente = cajas.get(i).size();
                cajaMenosGente = i;
            }
        }

        return cajaMenosGente;
    }

    public void verColas() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Cola de la caja " + (i + 1) + ": " + cajas.get(i).toString());
        }
    }

    public void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
