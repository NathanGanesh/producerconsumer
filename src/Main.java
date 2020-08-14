import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> questioNlist = new ArrayList<>();

        Thread t1 = new Thread(new Producer(questioNlist));
        Thread t2 = new Thread(new Consumer(questioNlist));

        t1.start();
        t2.start();
    }
}
