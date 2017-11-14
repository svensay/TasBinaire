import java.util.*;

public class ALTBQueue<E extends Object> extends AbstractQueue<E> {

    private ArrayList<E> tas;
    private Comparator<? super E> comp;

    @SuppressWarnings("unchecked")//"E extends Object" donc on peut cast un tableau Object
    public ALTBQueue(Comparator<? super E> c) throws ClassCastException {
        comp = c;
        tas = new ArrayList<E>();
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int n = -1;

            public boolean hasNext() {
                n++;
                return (n < size());
            }

            public E next() {
                return tas.get(n);
            }

            public void remove() {
            }
        };
    }

    /**
     *
     * @return le nombre d'element
     */
    public int size() {
        return tas.size();
    }

    /**
     *
     * Enleve la racine, puis place le dernier élément de l'arbre a la racine et le tasmine. 
     * @param e
     * @return true si ajouter a la queue
     */
    @SuppressWarnings("unchecked")//"E extends Object" donc on peut cast un tableau Object
    public boolean offer(E e) {
        if(e == null){
            return false;
        }
        tas.add(e);
        int i = size()-1;
        while (i >= 0 && ((i-1) / 2) >= 0 && tas.get((i-1)/2) != null && comp.compare(tas.get((i-1)/2), tas.get(i)) < 0) {
	       E poubelle = tas.set((i-1)/2, tas.get(i));
	       poubelle = tas.set(i, poubelle);
	       i = (i-1)/2;
        }
        return true;
    }

    /**
     * Enleve la racine, puis place le dernier élément de l'arbre a la racine et le tasmine. 
     * @return null si l'abre est vide et la valeur de la racine sinon
     */
    public E poll() {
    	if (size() <= 0) {
            System.out.print("Pas de racine = ");
    		return null;
    	}
        E res = tas.get(0);
        E poubelle = tas.set(0, tas.get(size()-1));
        poubelle = tas.remove(size()-1);
        int i = 0;
        while (((2*i)+1 < size() && tas.get((2*i)+1) != null) || ( (2*i)+2 < size() && tas.get((2*i)+2) != null) ) {
            E tmp = tas.get(i);
            if ((2*i)+2 < size() && (tas.get((2*i)+1) != null && tas.get((2*i)+2) == null)) {
                if (comp.compare(tas.get((2 * i)+1), tas.get(i)) > 0) {
                	poubelle = tas.set(i, tas.get((2 * i) + 1));
                    poubelle = tas.set((2*i)+1, tmp);
                    i = ((2 * i)+1);
                } else {
                    break;
                }
            } else if ((tas.get((2 * i)+1) == null && tas.get((2 * i) + 2)  != null)) {
                if (comp.compare(tas.get((2 * i) + 2), tas.get(i)) > 0) {
                	poubelle = tas.set(i, tas.get((i*2)+2));
                	poubelle = tas.set((i*2)+2, tmp);
                    i = (2 * i) + 2;
                } else {
                    break;
                }
            } else {
                if ((2*i)+2 < size() && comp.compare(tas.get((2 * i)+1), tas.get((2 * i) + 2)) > 0) {
                	poubelle = tas.set(i, tas.get((2 * i)+1));
                    poubelle = tas.set((2*i)+1, tmp);
                    i = ((2 * i)+1);
                } else if((2*i)+2 < size()){
                	poubelle = tas.set(i, tas.get((i*2)+2));
                	poubelle = tas.set((i*2)+2, tmp);
                    i = (2 * i) + 2;
                }else{
                	break;
                }
            }
        }
        return res;
    }

    /**
     *
     * @return la tete de la queue
     */
    public E peek() {
        if(size() <= 0){
            return null;
        }
        return tas.get(0);
    }

    /**
     * Affiche le tas
     */
    public void affiche() {
        if (size() <= 0) {
            System.out.println("Pas d'élément a afficher");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < size(); i++) {
            System.out.print("\"" + (tas.get(i)) + "\" ");
        }
        System.out.println("]");
    }
}
