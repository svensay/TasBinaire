import java.util.*;
import java.util.function.*;
import java.lang.*;

public class TBQueue<E> extends AbstractQueue<E>  implements QueueExt<E>{

    /*
      Racine indice 0
      fils gauche de l'indice i => 2i+1 et son fils droit => 2i+2
      i > 0 pere gauche => i-1/2
    */
    private E[] tas;
    private Comparator<? super E> comp;
    private int courant = 0;
    private int max;
        
    public TBQueue(Comparator<? super E> c,int max) {
	comp = c;
		tas = (E[]) new Object[max];	
	
		System.out.println("ICIII" + tas.getClass().getSimpleName() + "FINI");
	this.max = max;
    }

    //@Override
    public Iterator<E> iterator() {
	return new Iterator<E>() {
	    private int n = -1;

	    public boolean hasNext() {
		n++;
		return (n < courant);
	    }

	    public E next() {
		return tas[n];
	    }

	    public void remove() {
	    }
	};
    }

    /**
     *
     * @return le nombre d'element
     */
    //@Override
    public int size() {
	return courant;
    }

    /**
     *
     * @param e
     * @return true si ajouter a la queue
     */
    //@Override
    public boolean offer(E e) {
	if (courant >= max) {
	    System.out.println("Plus de place: nombre élement = " + courant + "; capacité = " + max);
	    return false;
	}
	tas[courant] =  e;
	int i = courant;
	while (i >= 0 && ((i - 1) / 2) >= 0 && tas[(i - 1) / 2] != null && (comp.compare(tas[(i - 1) / 2],tas[i]) < 0)) {
	    E tmp = tas[(i - 1) / 2];
	    tas[(i - 1) / 2] = tas[i];
	    tas[i] = tmp;
	    i = (i - 1) / 2;
	}
	courant++;
	return true;
    }

    /**
     *
     * @return la tete de la queue et l'enleve
     */
    //@Override
    public E poll() {
	if (courant <= 0 || max <= 0) {
	    System.out.println("Pas de racine");
	    return null;
	}
	E res = tas[0];
	tas[0] = tas[courant - 1];
	tas[courant - 1] = null;
	courant--;
	int i = 0;
	while (((((2 * i) + 1) < max && tas[(2 * i) + 1] != null)
		|| (((2 * i) + 2) < max && tas[(2 * i) + 2] != null))){
	    E tmp = tas[i];
	    if ((tas[(2 * i) + 1] != null && tas[(2 * i) + 2] == null)) {
		if (comp.compare(tas[(2 * i) + 1],tas[i]) > 0) {
		    tas[i] = tas[(2 * i) + 1];
		    tas[(2 * i) + 1] = tmp;
		    i = (2 * i) + 1;
		}else{
		    break;
		}
	    } else if ((tas[(2 * i) + 1] == null && tas[(2 * i) + 2] != null)) {
		if (comp.compare(tas[(2 * i) + 2],tas[i]) > 0) {
		    tas[i] = tas[(2 * i) + 2];
		    tas[(2 * i) + 2] = tmp;
		    i = (2 * i) + 2;
		}else{
		    break;
		}
	    } else {
		if (comp.compare(tas[(2 * i) + 1],tas[(2 * i) + 2]) > 0) {
		    tas[i] = tas[(2 * i) + 1];
		    tas[(2 * i) + 1] = tmp;
		    i = (2 * i) + 1;
		} else {
		    tas[i] = tas[(2 * i) + 2];
		    tas[(2 * i) + 2] = tmp;
		    i = (2 * i) + 2;
		}
	    }
	}
	return res;
    }

    /**
     *
     * @return la tete de la queue
     */
    //@Override
    public E peek() {
	return tas[0];
    }

    /**
     * @return une nouvelle liste consistant en les éléments de tab qui satisfont le prédicat cond.
     */
    //@Override
    public QueueExt<E> filtre(Predicate<E> cond){
	ArrayList<E> tmp = new ArrayList<E>();
	for(E x:this){
	    if(cond.test(x)) tmp.add(x);
	}
	QueueExt<E> res = new TBQueue<E>(comp,tmp.size());
        
	for(E x:tmp){
	    res.offer(x);
	}
	return res;
    }
    
    /**
     * @return une liste dont les éléments sont tous les éléments de tab auxquels on a appliqué la fonction f.
     */
    //@Override
    public <U> QueueExt<U> map(Function<E, U> f){
        QueueExt<U> res = new TBQueue<U>(new Comparator<U>(){
            public int compare(U x, U y){
            	System.out.println("X:"+x.getClass().getSimpleName()+":X Y:"+y.getClass().getSimpleName()+":Y");
                try{
                	return comp.compare((E)x,(E)y);
                } catch(ClassCastException e){
                	throw e;
                }
            }
        },max);
        for(E x: this){
            res.offer(f.apply(x));
        }
	return res;
    }
    
    /**
     * @return un optionnel contenant un élément de la liste satisfaisant le prédicat cond, s’il en existe, sinon l’optionnel vide.
     */
    //@Override
    public Optional<E> trouve(Predicate<E> cond){
        for (E x:this) {
            if(cond.test(x)) return Optional.of(x);
        }
	return Optional.empty();
    }

    /**
     * Initialise un accumulateur a avec z, puis, pour chaque élément x de this, calcule a = f (a, x).
     * @return a
     */

    //@Override
    public <U> U reduit(U z, BiFunction<U, E, U> f){
        U acc = z;
        for (E x:this) {
            acc = f.apply(acc, x);
        }
	return acc;
    }

    public void affiche() {
	if(courant <= 0 || max <= 0){
	    System.out.println("Pas d'élément a afficher");
	    return;
	}
	System.out.print("[");
	for (int i = 0; i < courant - 1; i++) {
	    System.out.print("\""+tas[i]+"\"" + " , ");
	}
	System.out.println("\""+tas[courant - 1]+"\"" + "]");
    }
}
