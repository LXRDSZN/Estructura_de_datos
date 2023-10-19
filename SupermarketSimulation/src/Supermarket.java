import java.util.Queue;
import java.util.LinkedList;

class Supermarket {
    private Queue<Integer> shoppingCarts; // Cola de carritos de compra
    private Queue<Integer> checkout1; // Cola de la caja 1
    private Queue<Integer> checkout2; // Cola de la caja 2
    private Queue<Integer> checkout3; // Cola de la caja 3

    public Supermarket(int numCarts) {
        shoppingCarts = new LinkedList<>();
        checkout1 = new LinkedList<>();
        checkout2 = new LinkedList<>();
        checkout3 = new LinkedList<>();

        // Inicializa la lista de carritos de compra
        for (int i = 1; i <= numCarts; i++) {
            shoppingCarts.add(i);
        }
    }

    public void customerArrives() {
        int cartNumber;

        synchronized (shoppingCarts) {
            while (shoppingCarts.isEmpty()) {
                try {
                    shoppingCarts.wait(); // Espera si no hay carritos disponibles
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            cartNumber = shoppingCarts.poll();
        }

        // El cliente selecciona la cola con menos gente
        Queue<Integer> chosenCheckout = checkout1;
        if (checkout2.size() < chosenCheckout.size()) {
            chosenCheckout = checkout2;
        }
        if (checkout3.size() < chosenCheckout.size()) {
            chosenCheckout = checkout3;
        }

        synchronized (chosenCheckout) {
            chosenCheckout.add(cartNumber);
        }
    }

    public void customerPays(int checkoutNumber) {
        Queue<Integer> chosenCheckout = null;
        if (checkoutNumber == 1) {
            chosenCheckout = checkout1;
        } else if (checkoutNumber == 2) {
            chosenCheckout = checkout2;
        } else if (checkoutNumber == 3) {
            chosenCheckout = checkout3;
        }

        synchronized (chosenCheckout) {
            if (!chosenCheckout.isEmpty()) {
                int cartNumber = chosenCheckout.poll();
                System.out.println("Cliente en caja " + checkoutNumber + " ha pagado. Carrito " + cartNumber + " disponible.");
                synchronized (shoppingCarts) {
                    shoppingCarts.add(cartNumber); // Carrito disponible
                    shoppingCarts.notify(); // Notificar a los clientes esperando carritos
                }
            }
        }
    }

    public void showQueueStatus() {
        System.out.println("Estado de las colas:");
        System.out.println("Cola de carritos: " + shoppingCarts);
        System.out.println("Cola de la caja 1: " + checkout1);
        System.out.println("Cola de la caja 2: " + checkout2);
        System.out.println("Cola de la caja 3: " + checkout3);
    }
}