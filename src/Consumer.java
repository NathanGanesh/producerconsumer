import java.util.List;

public class Consumer implements Runnable {
    List<Integer> products = null;
    final int LIMIT = 5;
    private int productNummer;

    public Consumer(List<Integer> products) {
        this.products = products;
    }

    public void answerQuestion() throws InterruptedException {
        synchronized (products) {
            while (products.isEmpty()) {
                System.out.println("No question to answer .. waiting!!!");

                products.wait();

            }

            synchronized (products) {
                Thread.sleep(5000);
                System.out.println("answered question: " + products.remove(0));

                products.notify();
            }
        }
    }

    @Override
    public void run() {
        while (true){

            try {
                answerQuestion();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
