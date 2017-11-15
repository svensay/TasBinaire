
import java.util.*;

public class Main {

	public static void ajouter(Queue<Integer> q){
		long debut = System.currentTimeMillis();
		for (int i = 0; i < 200000; i++) {
			q.add(i);
		}
		System.out.println(System.currentTimeMillis() - debut + " ms");
	}	
	public static void vider(Queue<Integer> q){
		long debut = System.currentTimeMillis();
		for (int i = 0; i < q.size(); i++) {
			q.remove();
		}
		System.out.println(System.currentTimeMillis() - debut + " ms");
	}

	public static void carree(Queue<Integer> q) {
		long debut = System.currentTimeMillis();
		Integer x = 0;
		for (int i = 0; i < q.size(); i++) {
			x = q.poll();
			q.offer(x * x);
		}
        //affiche la durée d'exéctuion en milliseconds
		System.out.println(System.currentTimeMillis() - debut + " ms");
	}

	public static int addition(Queue<Integer> q) {
		long debut = System.currentTimeMillis();
		Integer res = 0;
		for (Integer x : q) {
			res += x;
		}
        //affiche la durée d'exéctuion en milliseconds
		System.out.println(System.currentTimeMillis() - debut + " ms");
		return res;
	}

	public static boolean haveFive(Queue<Integer> q) {
		long debut = System.currentTimeMillis();
		boolean res = q.contains(5);
		System.out.println(System.currentTimeMillis() - debut + " ms");
		return res;
	}	
	public static boolean headSup42(Queue<Integer> q) {
		long debut = System.currentTimeMillis();
		boolean res = (q.peek() != null && q.peek() > 42);
		System.out.println(System.currentTimeMillis() - debut + " ms");
		return res;
	}

	public static void main(String[] args) {
        //Exercice 1
        //TEST AVEC UN TAS D'INTEGER
		TBQueue<Integer> t = new TBQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		}, 20);
		t.offer(100);
		t.offer(19);
		t.offer(36);
		t.offer(17);
		t.offer(3);
		t.offer(25);
		t.offer(1);
		t.offer(2);
		t.offer(7);
		System.out.print("Tas(int) aprés ajout : ");
		t.affiche();
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

        //Exercice 2
        //TEST DE LA METHODE "FILTRE"
		QueueExt<Integer> fil = t.filtre((Integer x) -> x.intValue() >= 1 && x.intValue() <= 5);
		System.out.print("Filre : ");
		for (Object x : fil) {
			System.out.print(x + " ");
		}
		System.out.println();

        //TEST DE LA METHODE "MAP"
		QueueExt<Integer> map = t.map((Integer x) -> {
			return x + 5;
		},new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		});
		System.out.print("Map : ");
		for (Object x : map) {
			System.out.print(x + " ");
		}
		System.out.println();
        //TEST DE LA METHODE "TROUVE"
		System.out.println("Trouve : " + t.trouve((Integer x) -> x <= 4));

        //TEST DE LA METHODE "REDUIT"
		System.out.println("Reduit : " + t.reduit(0, (Integer x, Integer y) -> x + y));

        // TEST AVEC UN TAS DE STRING
		TBQueue<String> g = new TBQueue<String>(new Comparator<String>() {
			public int compare(String x, String y) {
				return x.compareTo(y);
			}
		}, 10);
		g.offer("a");
		g.offer("bb");
		g.offer("cb");
		g.offer("zb");
		System.out.print("Tas(int) aprés ajout : ");
		g.affiche();
		System.out.println(g.poll());
		System.out.println(g.poll());
		System.out.println(g.poll());
		System.out.println(g.poll());
		System.out.println(g.poll());
		System.out.print("Tas(int) aprés suppression : ");
		g.affiche();

        //Exercice 3
		TBDQueue<Integer> d = new TBDQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		});
		d.offer(100);
		d.offer(19);
		d.offer(36);
		d.offer(17);
		d.offer(3);
		d.offer(25);
		d.offer(1);
		d.offer(2);
		d.offer(7);
		System.out.print("Tas(int) aprés ajout : ");
		d.affiche();//TEST D'ITERATION
		System.out.print("Test d'iteration (Exo3) : ");
		for (Integer x : d) {
			System.out.print(x + " ");
		}
		System.out.println("");
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.println(d.poll());
		System.out.print("Tas(int) aprés suppression : ");
		d.affiche();

		//Exercice 4 facultatif

		ALTBQueue<Integer> al = new ALTBQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		});
		al.offer(100);
		al.offer(19);
		al.offer(36);
		al.offer(17);
		al.offer(3);
		al.offer(25);
		al.offer(1);
		al.offer(2);
		al.offer(7);
		System.out.print("Tas(int) aprés ajout : ");
		al.affiche();//TEST D'ITERATION
		System.out.print("Test d'iteration (Exo4 facultatif) : ");
		for (Integer x : al) {
			System.out.print(x + " ");
		}
		System.out.println("");
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.println(al.poll());
		System.out.print("Tas(int) aprés suppression : ");
		al.affiche();

        //Exercice 4
		LinkedList<Integer> LL = new LinkedList<Integer>();
		ArrayDeque<Integer> AD = new ArrayDeque<Integer>();
		TBQueue<Integer> TBQ = new TBQueue<Integer>(new Comparator<Integer>() {
			public int
			compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		}, 1000000);       
		TBDQueue<Integer> TBDQ = new TBDQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		});
		ALTBQueue<Integer> ALTB = new ALTBQueue<Integer>(new Comparator<Integer>() {
			public int compare(Integer x, Integer y) {
				return x.compareTo(y);
			}
		});

		System.out.println("Temps d'éxecution pour LinkedList<Integer> : ");
		System.out.print("Ajouter = ");
		ajouter(LL);        
		System.out.print("Carre = ");
		carree(LL);
		System.out.print("Addition = ");
		addition(LL);
		System.out.print("haveFive = ");
		haveFive(LL);		
		System.out.print("headSup42 = ");
		headSup42(LL);
		System.out.print("Vider = ");
		vider(LL);        
		System.out.println("Temps d'éxecution pour ArrayDeque<Integer> : ");
		System.out.print("Ajouter = ");
		ajouter(AD);
		System.out.print("Carre = ");
		carree(AD);
		System.out.print("Addition = ");
		addition(AD);
		System.out.print("haveFive = ");
		haveFive(AD);
		System.out.print("headSup42 = ");
		headSup42(AD);
		System.out.print("Vider = ");
		vider(AD);
		System.out.println("Temps d'éxecution pour TBQueue<Integer> : ");
		System.out.print("Ajouter = ");
		ajouter(TBQ);
		System.out.print("Carre = ");
		carree(TBQ);
		System.out.print("Addition = ");
		addition(TBQ);
		System.out.print("haveFive = ");
		haveFive(TBQ);      
		System.out.print("headSup42 = ");
		headSup42(TBQ);      
		System.out.print("Vider = ");
		vider(TBQ);       
		System.out.println("Temps d'éxecution pour TBDQueue<Integer> : ");
		System.out.print("Ajouter = ");
		ajouter(TBDQ);
		System.out.print("Carre = ");
		carree(TBDQ);
		System.out.print("Addition = ");
		addition(TBDQ);
		System.out.print("haveFive = ");
		haveFive(TBDQ);
		System.out.print("headSup42 = ");
		headSup42(TBDQ);
		System.out.print("Vider = ");
		vider(TBDQ);
		System.out.println("Temps d'éxecution pour ALTBQueue<Integer> : ");
		System.out.print("Ajouter = ");
		ajouter(ALTB);
		System.out.print("Carre = ");
		carree(ALTB);
		System.out.print("Addition = ");
		addition(ALTB);
		System.out.print("haveFive = ");
		haveFive(ALTB);
		System.out.print("headSup42 = ");
		headSup42(ALTB);
		System.out.print("Vider = ");
		vider(ALTB); 
	}
}
