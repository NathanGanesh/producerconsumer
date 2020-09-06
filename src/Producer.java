import java.util.List;
import java.util.concurrent.Semaphore;

public class Producer implements Runnable {
    List<Integer> products;
     int limit ;
    private int productNummer;
    private Semaphore semaphore = new Semaphore(limit);
    public Producer(List<Integer> products, int limit) {
        this.limit = limit;
        this.products = products;
    }


    public void readProductNummer(int productNummer) throws InterruptedException {
        semaphore.acquire();
        synchronized (products) {
            while (products.size() == limit) {
                System.out.println("Product nummers have pilled up... waiting!!!");
                products.wait();
            }


            synchronized (products) {
                System.out.println("adding new product: " + productNummer);
                semaphore.release();
                products.add(productNummer);
                Thread.sleep(150);
                products.notify();
            }
        }

        semaphore.release();
    }

    @Override
    public void run() {
        while (true){
        try {
            readProductNummer(productNummer++);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        }
}
