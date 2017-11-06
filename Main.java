
import java.util.function.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        TBQueue<Integer> t = new TBQueue<Integer>(new Comparator() {
            public int compare(Object x, Object y) {
                if ((Integer) x > (Integer) y) {
                    return 1;
                } else if ((Integer) x < (Integer) y) {
                    return -1;
                }else{
                    return 0;
                }
            }
        }, 10);
        System.out.println(t.offer(2));
        System.out.println(t.offer(6));
        System.out.println(t.offer(20));
        System.out.println(t.offer(85));
        System.out.println(t.offer(7));
        System.out.println(t.offer(47));
        System.out.println(t.offer(4));
        System.out.println(t.offer(54));
        System.out.print("Tas(int) aprés ajout : ");
        t.affiche();
        System.out.println(t.poll());
        System.out.println(t.poll());
        System.out.println(t.poll());
        System.out.print("Tas(int) aprés suppression : ");
        t.affiche();

        System.out.print("Test d'iteration : ");
        for (Object i : t) {
            System.out.print(i + " ");
        }
        System.out.println();

        QueueExt<Integer> fil = t.filtre((Integer x) -> x.intValue() >= 1 && x.intValue() <= 5);
        System.out.println("Filre : ");
        for (Object x : fil) {
            System.out.print(x + " ");
        }
        System.out.println();

        TBQueue<String> g = new TBQueue<String>(new Comparator() {
            public int compare(Object x, Object y) {
                return ((String) x).compareTo((String) y);
            }
        }, 10);
        System.out.println(g.offer("a"));
        System.out.println(g.offer("bb"));
        System.out.println(g.offer("cb"));
        System.out.println(g.offer("zb"));
        System.out.print("Tas(int) aprés ajout : ");
        g.affiche();
        System.out.println(g.poll());
        System.out.println(g.poll());
        System.out.println(g.poll());
        System.out.println(g.poll());
        System.out.println(g.poll());
        System.out.print("Tas(int) aprés suppression : ");
        g.affiche();
    }
}
