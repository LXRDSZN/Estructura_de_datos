import java.util.Scanner;
public class cola {
    public  void cola(){
        Scanner sc = new Scanner(System.in);
        System.out.print("┌─[DIGITA LA CAPACIDAD DE LA COLA]─[~]\n└──╼ ");
        int capacidadcola = sc.nextInt();
        colacircular cola = new colacircular(capacidadcola);
        //menu de la cola 
        while(true){
            System.out.println("Operaciones disponibles:");
            System.out.println("1: Encolar");
            System.out.println("2: Desencolar");
            System.out.println("3: Ver Frente");
            System.out.println("4: Tamaño Actual");
            System.out.println("5: Ver Elementos");
            System.out.println("6. Salir");
            System.out.print("┌─[SELECCIONA UNA OPCION─[~]\n└──╼ ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el elemento a encolar: ");
                    int elemento = sc.nextInt();
                    cola.encolar(elemento);
                    break;
                case 2:
                    int elementoDesencolado = cola.desencolar();
                    if (elementoDesencolado != -1) {
                        System.out.println("Elemento desencolado: " + elementoDesencolado);
                    }
                    break;
                case 3:
                    int elementoFrente = cola.verFrente();
                    if (elementoFrente != -1) {
                        System.out.println("Frente de la cola: " + elementoFrente);
                    }
                    break;
                case 4:
                    System.out.println("Tamaño actual de la cola: " + cola.tamañoActual());
                    break;
                case 5:
                    cola.verElementos();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Operación no válida.");
            }
        }
    }
}
 
class colacircular{
    private int capacidad;
    private int cola[];
    private int frente;
    private int tamaño;

    public colacircular(int capacidad) {
        this.capacidad = capacidad;
        this.cola = new int[capacidad];
        this.frente = 0;
        this.tamaño = 0;

    }



    public boolean estaVacia() {
        return tamaño == 0;
    }

    public boolean estaLlena() {
        return tamaño == capacidad;
    }

    public void encolar(int elemento) {
        if (!estaLlena()) {
            int posicion = (frente + tamaño) % capacidad;
            cola[posicion] = elemento;
            tamaño++;
        } else {
            System.out.println("La cola está llena. No se puede encolar el elemento.");
        }
    }

    public int desencolar() {
        if (!estaVacia()) {
            int elemento = cola[frente];
            cola[frente] = 0; // Opcional: Limpiar la posición desencolada
            frente = (frente + 1) % capacidad;
            tamaño--;
            return elemento;
        } else {
            System.out.println("La cola está vacía. No se puede desencolar.");
            return -1; // Valor sentinela para indicar cola vacía
        }
    }

    public int verFrente() {
        if (!estaVacia()) {
            return cola[frente];
        } else {
            System.out.println("La cola está vacía.");
            return -1; // Valor sentinela para indicar cola vacía
        }
    }

    public int tamañoActual() {
        return tamaño;
    }
  public void verElementos() {
        if (!estaVacia()) {
            System.out.println("Elementos de la cola:");
            for (int i = 0; i < tamaño; i++) {
                int indice = (frente + i) % capacidad;
                System.out.print(cola[indice] + " ");
            }
            System.out.println();
        } else {
            System.out.println("La cola está vacía.");
        }
    }
  
}