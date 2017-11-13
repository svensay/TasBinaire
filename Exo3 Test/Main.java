
import java.util.function.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //TEST AVEC UN TAS D'INTEGER
        TBDQueue<Integer> t = new TBDQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                return x.compareTo(y);
            }
        });
        System.out.println(t.offer(100));
        System.out.println(t.offer(19));
        System.out.println(t.offer(36));
        System.out.println(t.offer(17));
        System.out.println(t.offer(3));
        System.out.println(t.offer(25));
        System.out.println(t.offer(1));
        System.out.println(t.offer(2));
        System.out.println(t.offer(7));
        System.out.print("Tas(int) aprés ajout : ");
        t.affiche();
        for (Integer x:t) {
            System.out.print(x + " ");
        }
        System.out.println("");
        System.out.println(t.poll());
        System.out.println(t.poll());
        System.out.println(t.poll());
        System.out.print("Tas(int) aprés suppression : ");
        t.affiche();
    }
}
