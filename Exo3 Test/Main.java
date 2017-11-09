
import java.util.function.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        //TEST AVEC UN TAS D'INTEGER
        TBQueue<Integer> t = new TBQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
               return x.compareTo(y);
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

        //TEST D'ITERATION
        System.out.print("Test d'iteration : ");
        for (Object i : t) {
            System.out.print(i + " ");
        }
        System.out.println();

        //TEST DE LA METHODE "FILTRE"
        QueueExt<Integer> fil = t.filtre((Integer x) -> x.intValue() >= 1 && x.intValue() <= 5);
        System.out.print("Filre : ");
        for (Object x : fil) {
            System.out.print(x + " ");
        }
        System.out.println();
        
        //TEST DE LA METHODE "MAP"
        QueueExt<Integer> map = t.map((Integer x) -> {return x+5;});
        System.out.print("Map : ");
        for (Object x : map) {
            System.out.print(x + " ");
        }
        System.out.println();
        //TEST DE LA METHODE "TROUVE"
        System.out.println("Trouve : "+t.trouve((Integer x) -> x <= 4));
        
        //TEST DE LA METHODE "REDUIT"
        System.out.println("Reduit : "+t.reduit(0, (Integer x,Integer y) -> x+y));
        
        // TEST AVEC UN TAS DE STRING
        TBQueue<String> g = new TBQueue<String>(new Comparator<String>() {
            public int compare(String x, String y) {
                return x.compareTo(y);
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
