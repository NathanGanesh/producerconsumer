import java.util.List;

public class Producer implements Runnable {
    List<Integer> products = null;
    final int LIMIT = 5;
    private int productNummer;

    public Producer(List<Integer> products) {
        this.products = products;
    }

    public void readProductNummer(int productNummer) throws InterruptedException {
        synchronized (products) {
            while (products.size() == LIMIT) {
                System.out.println("Product nummers have pilled up... waiting!!!");

                products.wait();

            }

            synchronized (products) {
                System.out.println("adding new product: " + productNummer);
                products.add(productNummer);
                Thread.sleep(150);
                products.notify();
            }
        }
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
