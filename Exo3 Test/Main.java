
import java.util.function.*;
import java.util.*;

public class Main {

    // public static void carree(Queue<Integer> q) {
    //     long debut = System.currentTimeMillis();
    //     Integer x = 0;
    //     for (int i = 0; i < q.size(); i++) {
    //         x = q.poll();
    //         q.offer(x*x);
    //     }
    //     //affiche la durée d'exéctuion en milliseconds
    //     System.out.println(System.currentTimeMillis() - debut +" ms");
    // }

    // public static int addition(Queue<Integer> q) {
    //     long debut = System.currentTimeMillis();
    //     Integer res = 0;
    //     for (int i = 0; i < q.size(); i++) {
    //         res += q.poll();
    //     }
    //     //affiche la durée d'exéctuion en milliseconds
    //     System.out.println(System.currentTimeMillis() - debut + " ms");
    //     return res;
    // }

    // public static boolean haveFive(Queue<Integer> q) {
    //     long debut = System.currentTimeMillis();
    //     for (int i = 0; i < q.size(); i++) {
    //         if (q.peek().compareTo(5) == 0) {
    //             System.out.println(System.currentTimeMillis() - debut);
    //             return true;
    //         }
    //     }
    //     System.out.println(System.currentTimeMillis() - debut+ " ms");
    //     return false;
    // }

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

        // //TEST D'ITERATION
        // System.out.print("Test d'iteration : ");
        // for (Object i : t) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        // //TEST DE LA METHODE "FILTRE"
        // QueueExt<Integer> fil = t.filtre((Integer x) -> x.intValue() >= 1 && x.intValue() <= 5);
        // System.out.print("Filre : ");
        // for (Object x : fil) {
        //     System.out.print(x + " ");
        // }
        // System.out.println();

        // //TEST DE LA METHODE "MAP"
        // QueueExt<Integer> map = t.map((Integer x) -> {
        //     return x + 5;
        // });
        // System.out.print("Map : ");
        // for (Object x : map) {
        //     System.out.print(x + " ");
        // }
        // System.out.println();
        // //TEST DE LA METHODE "TROUVE"
        // System.out.println("Trouve : " + t.trouve((Integer x) -> x <= 4));

        // //TEST DE LA METHODE "REDUIT"
        // System.out.println("Reduit : " + t.reduit(0, (Integer x, Integer y) -> x + y));

        // // TEST AVEC UN TAS DE STRING
        // TBQueue<String> g = new TBQueue<String>(new Comparator<String>() {
        //     public int compare(String x, String y) {
        //         return x.compareTo(y);
        //     }
        // }, 10);
        // System.out.println(g.offer("a"));
        // System.out.println(g.offer("bb"));
        // System.out.println(g.offer("cb"));
        // System.out.println(g.offer("zb"));
        // System.out.print("Tas(int) aprés ajout : ");
        // g.affiche();
        // System.out.println(g.poll());
        // System.out.println(g.poll());
        // System.out.println(g.poll());
        // System.out.println(g.poll());
        // System.out.println(g.poll());
        // System.out.print("Tas(int) aprés suppression : ");
        // g.affiche();
        
        // LinkedList<Integer> LL = new LinkedList<Integer>();
        // ArrayDeque<Integer> AD = new ArrayDeque<Integer>();
        // TBQueue<Integer> TBQ = new TBQueue<Integer>(new Comparator<Integer>() {
        //     public int
        //             compare(Integer x, Integer y) {
        //         return x.compareTo(y);
        //     }
        // }, 1000000);
        // for (int i = 0; i < 1000000; i++) {
        //     LL.add(i);
        //     AD.add(i);
        //     TBQ.add(i);
        // }
        // System.out.println("Temps d'éxecution pour LinkedList<Integer> : ");
        // System.out.print("Carre = ");
        // carree(LL);
        // System.out.print("Addition = ");
        // addition(LL);
        // System.out.print("haveFive = ");
        // haveFive(LL);
        // System.out.println("Temps d'éxecution pour ArrayDeque<Integer> : ");
        // System.out.print("Carre = ");
        // carree(AD);
        // System.out.print("Addition = ");
        // addition(AD);
        // System.out.print("haveFive = ");
        // haveFive(AD);
        // System.out.println("Temps d'éxecution pour TBQueue<Integer> : ");
        // System.out.print("Carre = ");
        // carree(TBQ);
        // System.out.print("Addition = ");
        // addition(TBQ);
        // System.out.print("haveFive = ");
        // haveFive(TBQ);
    }
}
