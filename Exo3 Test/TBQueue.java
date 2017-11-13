
import java.util.*;
import java.util.function.*;
import java.lang.*;
import java.lang.Class.*;
//https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html

public class TBQueue<E extends Object> extends AbstractQueue<E> implements QueueExt<E> {

    /*
      Racine indice 0
      fils gauche de l'indice i => 2i+1 et son fils droit => 2i+2
      i > 0 pere gauche => i-1/2
     */
	private ArrayList<E[]> tas;
    private Comparator<? super E> comp;
    private int courant = 0;
	private int niveau = 0;

    public TBQueue(Comparator<? super E> c) throws ClassCastException {
        comp = c;
        // Class<E> aux[];
        // Object tmp[] = new Object[max];
        // tas = tmp.getComponentType();
		tas = new ArrayList<E[]>();
        tas.add((E[]) new Object[tailleDuTableauAuNiveau(niveau)]);
        System.out.println("ICIII" + tas.getClass().getSimpleName() + "FINI");
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
                // return tas[n];
                return tas.get(niveau)[n - tailleDuTableauAuNiveau(niveau)];
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
     * On verifie si le tas est rempli
     *
     * @return     retourne un boolean
     */
    public boolean tasFull(){
		int tmp = 1;
		for (int i = 0; i <= niveau; i++) {
			tmp*=2;
		}
        System.out.println("TASFULL: tmp-1: " + (tmp-1) + " ,courant " + courant);
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
        // if (courant >= max) {
        //     System.out.println("Plus de place: nombre élement = " + courant + "; capacité = " + max);
        //     return false;
        // }
        
    	int emplacement = 1;	// Pour trouver le dernier emplacement de libre dans la liste de tableau
    	for (int i = 0; i < niveau; i++) {
    		emplacement*=2;
    	}

        System.out.println("emplacement: " + emplacement + " ,courant: " + courant);
    	emplacement-=courant+1;	// On recupere l'indice de l'emplacement libre celui juste apres ou se trouve courant

        if (emplacement < 0) {
            emplacement *=(-1);
        }
        System.out.println(emplacement);
    	(tas.get(niveau))[emplacement] = e;

        //tas[courant] = e;
       	int niveauTmp = niveau;	// pour remonter dans la liste
        int i = courant;
        // while (i >= 0 && ((i - 1) / 2) >= 0 && tas[(i - 1) / 2] != null && (comp.compare(tas[(i - 1) / 2], tas[i]) < 0)) {
        System.out.println("1TEST-->"+(i-tailleDuTableauAuNiveau(niveauTmp))/2);
        if (niveauTmp-1 >= 0) {
            System.out.println("2TEST-->"+(tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2] + "  niveauTmp = " + niveauTmp + ", i = "+i+" tailleDuTableauAu(Niveau) = "+ tailleDuTableauAuNiveau(niveauTmp));
            if (i - tailleDuTableauAuNiveau(niveauTmp) < 0) {                
                System.out.println("3TEST-->"+(tas.get(niveauTmp))[(i - tailleDuTableauAuNiveau(niveauTmp)) * (-1) -1]);    // L'adresse du fils
            }else{
                System.out.println("3TEST--> niveauTmp: " + niveauTmp + ", i - tailleDuTableauAuNiveau(niveauTmp)) -1 = " + ((i - tailleDuTableauAuNiveau(niveauTmp) -1)*(-1)));
                if ((i - tailleDuTableauAuNiveau(niveauTmp)) -1 < 0) {
                    System.out.println("3TEST-->"+(tas.get(niveauTmp))[((i - tailleDuTableauAuNiveau(niveauTmp)) -1)*(-1)]);    // L'adresse du fils                  
                }else{
                    System.out.println("3TEST-->"+(tas.get(niveauTmp))[((i - tailleDuTableauAuNiveau(niveauTmp)) -1)]);    // L'adresse du fils                  
                }  
            }
            // && (comp.compare((tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2], tas.get(niveau)[i - tailleDuTableauAuNiveau(niveauTmp)]) < 0)
        }
        while (niveauTmp > 0 && i >= 0 && ((i - 1) / 2) >= 0 && (tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2] != null) {
        	// La formule pour trouver le pere dans une liste de tableau : Pere = (courant-tailleDuTableauAuNiveau(niveau)) / 2; Exemple: courant = 11, tailleDu...(niveau) = 8 -> (11-8)/2 = 1 et 1 etant l'indice dans lequel se trouve le pere dans le niveau au dessus.
            int indiceDuFils = i - tailleDuTableauAuNiveau(niveauTmp);
            if (indiceDuFils < 0) {
                indiceDuFils*=(-1);
                indiceDuFils--;
                if (indiceDuFils < 0) {
                    indiceDuFils*= (-1);
                }
            }
            System.out.println("3.5TEST-->indiceDuFils-1 = " + (indiceDuFils));
            System.out.println("4TEST-->" + tas.get(niveauTmp)[indiceDuFils]);
            System.out.println("(tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2] -> " + (tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2] + "\ntas.get(niveauTmp)[indiceDuFils] -> " + tas.get(niveauTmp)[indiceDuFils] + "\ncomp.compare((tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2], tas.get(niveauTmp)[indiceDuFils]) --> " comp.compare((tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2], tas.get(niveauTmp)[indiceDuFils]));
            if ((comp.compare((tas.get(niveauTmp-1))[(i-tailleDuTableauAuNiveau(niveauTmp))/2], tas.get(niveauTmp)[indiceDuFils]) < 0)) {
                
                // E tmp = tas[(i - 1) / 2];
                // tas[(i - 1) / 2] = tas[i];
                // tas[i] = tmp;
                // i = (i - 1) / 2;
                
                
                E tmp = tas.get(niveauTmp-1)[(i-tailleDuTableauAuNiveau(niveauTmp))/2];
                tas.get(niveauTmp-1)[(i-tailleDuTableauAuNiveau(niveauTmp))/2] = tas.get(niveauTmp)[indiceDuFils];
                tas.get(niveauTmp)[indiceDuFils] = tmp;


                // E[] tmp = tas.set((i-tailleDuTableauAuNiveau(niveauTmp))/2, tas.get(tailleDuTableauAuNiveau(niveauTmp)-i));
                // tmp = tas.set(i-tailleDuTableauAuNiveau(niveauTmp), tmp);
            }
    			i = (i - 1) / 2;
                niveauTmp--;	// On remonte
        }
        courant++;
        return true;
    }

    public E poll(){
        return tas.get(0)[0];
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

    // /**
    //  * @return une nouvelle liste consistant en les éléments de tab qui
    //  * satisfont le prédicat cond.
    //  */
    // //@Override
    // public QueueExt<E> filtre(Predicate<E> cond) {
    //     ArrayList<E> tmp = new ArrayList<E>();
    //     for (E x : this) {
    //         if (cond.test(x)) {
    //             tmp.add(x);
    //         }
    //     }
    //     QueueExt<E> res = new TBQueue<E>(comp, tmp.size());

    //     for (E x : tmp) {
    //         res.offer(x);
    //     }
    //     return res;
    // }

    // /**
    //  * @return une liste dont les éléments sont tous les éléments de tab
    //  * auxquels on a appliqué la fonction f.
    //  */
    // //@Override
    // public <U> QueueExt<U> map(Function<E, U> f) {
    //     QueueExt<U> res = new TBQueue<U>(new Comparator<U>() {
    //         public int compare(U x, U y) {
    //             // E tmp;
    //             // if ((tmp).isAssignableFrom(x.getClass()) && (tmp).isAssignableFrom(y.getClass()) ) {
    //             return comp.compare((E) x, (E) y);
    //             // }else{
    //             // System.out.println("Cast pas possible");
    //             // return 0;

    //             // }
    //         }
    //     }, max);
    //     for (E x : this) {
    //         res.offer(f.apply(x));
    //     }
    //     return res;
    // }

    // /**
    //  * @return un optionnel contenant un élément de la liste satisfaisant le
    //  * prédicat cond, s’il en existe, sinon l’optionnel vide.
    //  */
    // //@Override
    // public Optional<E> trouve(Predicate<E> cond) {
    //     for (E x : this) {
    //         if (cond.test(x)) {
    //             return Optional.of(x);
    //         }
    //     }
    //     return Optional.empty();
    // }

    /**
     * Initialise un accumulateur a avec z, puis, pour chaque élément x de this,
     * calcule a = f (a, x).
     *
     * @return a
     */
    //@Override
    public <U> U reduit(U z, BiFunction<U, E, U> f) {
        U acc = z;
        for (E x : this) {
            acc = f.apply(acc, x);
        }
        return acc;
    }

    public void affiche() {
        if (courant <= 0) {
            System.out.println("Pas d'élément a afficher");
            return;
        }
        System.out.print("[");
        for (int i = 0; i <= niveau; i++) {
        	for (int j = 0; j <  tas.get(i).length; j++) {
                if ((tas.get(i))[j] != null) {      
	               System.out.print("\"" + (tas.get(i))[j] + "\" ");   		
                }
        	}
        }
        System.out.println("]");
    }
}
