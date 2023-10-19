public class SupermarketSimulation {
    public static void main(String[] args) {
        Supermarket supermarket = new Supermarket(25);

        System.out.println("Inicio del programa:");
        supermarket.showQueueStatus();

        Thread[] threads = new Thread[10];

        // Simulación de llegada de clientes y pagos
        for (int i = 1; i <= 10; i++) {
            final int customerNumber = i;
            Thread customerThread = new Thread(() -> {
                System.out.println("Cliente " + customerNumber + " ha llegado.");
                supermarket.customerArrives();

                // Simulación de tiempo de compra
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                int checkoutNumber = (customerNumber % 3) + 1; // Elige una caja de salida
                supermarket.customerPays(checkoutNumber);
            });

            threads[i - 1] = customerThread;
            customerThread.start();
        }

        // Esperar a que todos los subprocesos terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Fin del programa:");
        supermarket.showQueueStatus();
    }
}