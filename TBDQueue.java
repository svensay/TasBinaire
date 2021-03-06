
import java.util.*;

public class TBDQueue<E extends Object> extends AbstractQueue<E> {

    private ArrayList<E[]> tas;
    private Comparator<? super E> comp;
    private int courant = 0;
    private int niveau = 0;

    @SuppressWarnings("unchecked")//"E extends Object" donc on peut cast un tableau Object
    public TBDQueue(Comparator<? super E> c) throws ClassCastException {
        comp = c;
        tas = new ArrayList<E[]>();
        tas.add((E[]) new Object[tailleDuTableauAuNiveau(niveau)]);
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int n = -1;
            private int lvl = 0;
            private int ind = -1;

            public boolean hasNext() {
                n++;
                ind++;
                return (n < courant);
            }

            public E next() {
                if (ind > tas.get(lvl).length - 1) {
                    lvl++;
                    ind = 0;
                }
                return tas.get(lvl)[ind];
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
        return courant;
    }

    /**
     * On verifie si le tas est rempli
     *
     * @return retourne un boolean
     */
    public boolean tasFull() {
        for (int i = 0; i < tas.get(tas.size()-1).length; i++) {
            if (tas.get(tas.size()-1)[i] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Donne la taille du niveau en argument
     *
     * @param niveauDonne Le niveau donne
     *
     * @return Retourne le nombre d'element que peut contenir ve niveau
     */
    public int tailleDuTableauAuNiveau(int niveauDonne) {
        int taille = 1;
        for (int i = 0; i < niveauDonne; i++) {
            taille *= 2;
        }
        return taille;
    }

    /**
     *
     * Enleve la racine, puis place le dernier élément de l'arbre a la racine et le tasmine. 
     * @param e
     * @return true si ajouter a la queue
     */
    @SuppressWarnings("unchecked")//"E extends Object" donc on peut cast un tableau Object
    public boolean offer(E e) {
        if (tasFull()) {
            niveau++;
            tas.add((E[]) new Object[tailleDuTableauAuNiveau(niveau)]);
        }
        if(e == null){
            return false;
        }
        courant++;
        int indiceDuDernierElement = courant - tailleDuTableauAuNiveau(niveau);
        tas.get(niveau)[indiceDuDernierElement] = e;
        int niveauTmp = niveau;
        int indiceDuFils = indiceDuDernierElement;
        while (niveauTmp > 0 && indiceDuFils >= 0 && (indiceDuFils / 2) >= 0 && tas.get(niveauTmp-1)[indiceDuFils / 2] != null && (comp.compare(tas.get(niveauTmp-1)[indiceDuFils / 2], tas.get(niveauTmp)[indiceDuFils]) < 0)) {
            E tmp = tas.get(niveauTmp-1)[indiceDuFils/2];
            tas.get(niveauTmp-1)[indiceDuFils/2] = tas.get(niveauTmp)[indiceDuFils];
            tas.get(niveauTmp)[indiceDuFils] = tmp;
            niveauTmp--;
            indiceDuFils = (indiceDuFils / 2);
        }
        return true;
    }

    /**
     * Enleve la racine, puis place le dernier élément de l'arbre a la racine et le tasmine. 
     * @return null si l'abre est vide et la valeur de la racine sinon
     */
    public E poll() {
        if (niveau < 0) {
            System.out.println("Vide");
            return null;
        }
        E res = tas.get(0)[0];
        int ind = courant - tailleDuTableauAuNiveau(niveau);
        if (ind < 0) {
            ind *= (-1);
        }
        tas.get(0)[0] = tas.get(niveau)[ind];
        tas.get(niveau)[ind] = null;
        courant--;
        ind--;
        if (ind < 0) {
            niveau--;
        }
        if ((tas.size() - niveau) >= 2) {
            tas.remove(tas.size() - 1);
        }
        int pere = 0;
        int lvl = 0;
        while (lvl < niveau && (tas.get(lvl + 1)[2 * pere] != null || tas.get(lvl + 1)[(2 * pere) + 1] != null)) {
            E tmp = tas.get(lvl)[pere];

            if ((tas.get(lvl + 1)[2 * pere] != null && tas.get(lvl + 1)[(2 * pere) + 1] == null)) {
                if (tas.get(lvl + 1)[2 * pere] != null && tas.get(lvl)[pere] != null && comp.compare(tas.get(lvl + 1)[2 * pere], tas.get(lvl)[pere]) > 0) {
                    tas.get(lvl)[pere] = tas.get(lvl + 1)[2 * pere];
                    tas.get(lvl + 1)[2 * pere] = tmp;
                    pere = 2 * pere;
                    lvl++;
                } else {
                    break;
                }
            } else if ((tas.get(lvl + 1)[2 * pere] == null && tas.get(lvl + 1)[(2 * pere) + 1] != null)) {
                if (tas.get(lvl + 1)[(2 * pere) + 1] != null && tas.get(lvl)[pere] != null && comp.compare(tas.get(lvl + 1)[(2 * pere) + 1], tas.get(lvl)[pere]) > 0) {
                    tas.get(lvl)[pere] = tas.get(lvl + 1)[(2 * pere) + 1];
                    tas.get(lvl + 1)[(2 * pere) + 1] = tmp;
                    pere = (2 * pere) + 1;
                    lvl++;
                } else {
                    break;
                }
            } else {
                if (comp.compare(tas.get(lvl + 1)[2 * pere], tas.get(lvl + 1)[(2 * pere) + 1]) > 0) {
                    tas.get(lvl)[pere] = tas.get(lvl + 1)[2 * pere];
                    tas.get(lvl + 1)[2 * pere] = tmp;
                    pere = (2 * pere);
                    lvl++;
                } else {
                    tas.get(lvl)[pere] = tas.get(lvl + 1)[(2 * pere) + 1];
                    tas.get(lvl + 1)[(2 * pere) + 1] = tmp;
                    pere = (2 * pere) + 1;
                    lvl++;
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
        if(courant <= 0){
            return null;
        }
        return tas.get(0)[0];
    }

    /**
     * Affiche le tas
     */
    public void affiche() {
        if (courant <= 0) {
            System.out.println("Pas d'élément a afficher");
            return;
        }
        int tmp = courant;
        System.out.print("[");
        for (int i = 0; i < niveau; i++) {
            for (int j = 0; j < tas.get(i).length; j++) {
                System.out.print("\"" + (tas.get(i))[j] + "\" ");
            }
        }
        for (int i = 0; i <= tmp - tas.get(niveau).length; i++) {
            System.out.print("\"" + (tas.get(niveau))[i] + "\" ");
        }
        System.out.println("]");
    }
}
