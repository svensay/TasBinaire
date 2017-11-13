
import java.util.*;
import java.util.function.*;
import java.lang.*;
import java.lang.Class.*;
//https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html

public class TBDQueue<E extends Object> extends AbstractQueue<E>{

    /*
      Racine indice 0
      fils gauche de l'indice i => 2i+1 et son fils droit => 2i+2
      i > 0 pere gauche => i-1/2
     */
	private ArrayList<E[]> tas;
    private Comparator<? super E> comp;
    private int courant = 0;
	private int niveau = 0;

    public TBDQueue(Comparator<? super E> c) throws ClassCastException {
        comp = c;
		tas = new ArrayList<E[]>();
        tas.add((E[]) new Object[tailleDuTableauAuNiveau(niveau)]);
    }

    //@Override
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
                if(ind > tas.get(lvl).length-1){
                    lvl++;
                    ind = 0;
                }
                return tas.get(lvl)[ind];
            }

            public void remove() {}
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
     * On verifie si le tas est rempli
     *
     * @return     retourne un boolean
     */
    public boolean tasFull(){
		int tmp = 1;
		for (int i = 0; i <= niveau; i++) {
			tmp*=2;
		}
        // System.out.println("TASFULL: tmp-1: " + (tmp-1) + " ,courant " + courant);
		return tmp - 1 == courant;
    }

    /**
     * Donne la taille du niveau en argument
     *
     * @param      niveauDonne  Le niveau donne
     *
     * @return     Retourne le nombre d'element que peut contenir ve niveau
     */
    public int tailleDuTableauAuNiveau(int niveauDonne){
		int taille = 1;
		for (int i = 0; i < niveauDonne; i++) {
			taille*=2;
		}
    	return taille;
    }

    /**
     *
     * @param e
     * @return true si ajouter a la queue
     */
    //@Override
    public boolean offer(E e) {
    	if (tasFull()) { // On recupere la taille du dernier tableau de la liste
    		niveau++;
    		tas.add((E[]) new Object[tailleDuTableauAuNiveau(niveau)]);
    	}
        
    	int emplacement = 1;	// Pour trouver le dernier emplacement de libre dans la liste de tableau
    	for (int i = 0; i < niveau; i++) {
    		emplacement*=2;
    	}

    	emplacement-=courant+1;	// On recupere l'indice de l'emplacement libre celui juste apres ou se trouve courant

        if (emplacement < 0) {
            emplacement *=(-1);
        }
    	(tas.get(niveau))[emplacement] = e;

       	int niveauTmp = niveau;	// pour remonter dans la liste
        int i = courant+1;

        int indiceDuFils = (i - tailleDuTableauAuNiveau(niveauTmp));

        while (niveauTmp > 0 && i >= 0 && ((i - 1) / 2) >= 0 && (tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2] != null) {
            if ((comp.compare((tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2], tas.get(niveauTmp)[indiceDuFils]) < 0)) {
                E tmp = tas.get(niveauTmp-1)[((i-tailleDuTableauAuNiveau(niveauTmp))/2)];
                tas.get(niveauTmp-1)[((i-tailleDuTableauAuNiveau(niveauTmp))/2)] = tas.get(niveauTmp)[indiceDuFils];
                tas.get(niveauTmp)[indiceDuFils] = tmp;
                indiceDuFils = ((i - tailleDuTableauAuNiveau(niveauTmp))/2);
    			i = (i - 1) / 2;
                niveauTmp--;	// On remonte
            }else{
                break;
            }
        }
        courant++;
        return true;
    }

    public E poll(){
              if(niveau <= 0){
            System.out.println("Vide");
            return null;
        }
        E res = tas.get(0)[0];
        tas.get(0)[0] = tas.get(niveau)[courant];
        tas.get(niveau)[courant] = null;
        courant--;
        if(courant < 0){
            niveau--;
            courant = tas.get(niveau).length-1;
        }
        if((tas.size()-niveau) >= 2){
            tas.remove(tas.size());
        }
        return res;
    }

    // /**
    //  *
    //  * @return la tete de la queue et l'enleve
    //  */
    // //@Override
    // public E poll() {
    //     if (courant <= 0 || max <= 0) {
    //         System.out.println("Pas de racine");
    //         return null;
    //     }
    //     E res = tas[0];
    //     tas[0] = tas[courant - 1];
    //     tas[courant - 1] = null;
    //     courant--;
    //     int i = 0;
    //     while (((((2 * i) + 1) < max && tas[(2 * i) + 1] != null)
    //             || (((2 * i) + 2) < max && tas[(2 * i) + 2] != null))) {
    //         E tmp = tas[i];
    //         if ((tas[(2 * i) + 1] != null && tas[(2 * i) + 2] == null)) {
    //             if (comp.compare(tas[(2 * i) + 1], tas[i]) > 0) {
    //                 tas[i] = tas[(2 * i) + 1];
    //                 tas[(2 * i) + 1] = tmp;
    //                 i = (2 * i) + 1;
    //             } else {
    //                 break;
    //             }
    //         } else if ((tas[(2 * i) + 1] == null && tas[(2 * i) + 2] != null)) {
    //             if (comp.compare(tas[(2 * i) + 2], tas[i]) > 0) {
    //                 tas[i] = tas[(2 * i) + 2];
    //                 tas[(2 * i) + 2] = tmp;
    //                 i = (2 * i) + 2;
    //             } else {
    //                 break;
    //             }
    //         } else {
    //             if (comp.compare(tas[(2 * i) + 1], tas[(2 * i) + 2]) > 0) {
    //                 tas[i] = tas[(2 * i) + 1];
    //                 tas[(2 * i) + 1] = tmp;
    //                 i = (2 * i) + 1;
    //             } else {
    //                 tas[i] = tas[(2 * i) + 2];
    //                 tas[(2 * i) + 2] = tmp;
    //                 i = (2 * i) + 2;
    //             }
    //         }
    //     }
    //     return res;
    // }

    /**
     *
     * @return la tete de la queue
     */
    //@Override
    public E peek() {
        return tas.get(0)[0];
        // return tas[0];
    }

    public void affiche() {
        if (courant <= 0) {
            System.out.println("Pas d'élément a afficher");
            return;
        }
        int tmp = courant;
        System.out.print("[");
        for (int i = 0; i < niveau; i++) {
        	for (int j = 0; j <  tas.get(i).length; j++) {
                System.out.print("\"" + (tas.get(i))[j] + "\" ");   		
        	}
        }
        for (int i = 0;i <= tmp-tas.get(niveau).length; i++) {
            System.out.print("\"" + (tas.get(niveau))[i] + "\" ");
        }
        System.out.println("]");
    }
}
