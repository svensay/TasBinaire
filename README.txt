Projet réalisé par Sven Say et Marc Bannout du groupe 3.

Conclusion pour l'exercice 4:
Pour conclure on remarque que le temps d'exécution pour TBDQueue est plus long que celui de TBQueue mais on a pas de limite de taille pour ce dernier.
TBDQueue; Bien quand on ne veut pas avoir une limite de taille mais lent lors de l'execution.
TBQueue; Rapide lors de l'execution mais une limite de taille est imposé.

Pour compiler et exécuter: javac *.java && java Main
Résultat en fin d'exécution lors d'une execution a 1million de valeur(le programme prend quelques minutes à s'exécuter):
$ javac *.java && java Main
Tas(int) aprés ajout : ["100" , "19" , "36" , "17" , "3" , "25" , "1" , "2" , "7"]
100
36
Tas(int) aprés suppression : ["25" , "19" , "7" , "17" , "3" , "2" , "1"]
Test d'iteration : 25 19 7 17 3 2 1 
Filre : 3 2 1 
Map : 30 24 12 22 8 7 6 
Trouve : Optional[3]
Reduit : 74
Tas(int) aprés ajout : ["zb" , "cb" , "bb" , "a"]
zb
cb
bb
a
Pas de racine = null
Tas(int) aprés suppression : Pas d'élément a afficher
Tas(int) aprés ajout : ["100" "19" "36" "17" "3" "25" "1" "2" "7" ]
Test d'iteration (Exo3) : 100 19 36 17 3 25 1 2 7
100
36
25
19
17
7
3
2
1
Tas(int) aprés suppression : Pas d'élément a afficher
Temps d'éxecution pour LinkedList<Integer> : 
Ajouter = 200 ms
Carre = 305 ms
Addition = 18 ms
haveFive = 12 ms
headSup42 = 0 ms
Vider = 15 ms
Temps d'éxecution pour ArrayDeque<Integer> : 
Ajouter = 136 ms
Carre = 86 ms
Addition = 82 ms
haveFive = 14 ms
headSup42 = 0 ms
Vider = 7 ms
Temps d'éxecution pour TBQueue<Integer> : 
Ajouter = 280 ms
Carre = 236 ms
Addition = 72 ms
haveFive = 9 ms
headSup42 = 0 ms
Vider = 5 ms
Temps d'éxecution pour TBDQueue<Integer> : 
Ajouter = 46208 ms
Carre = 128505 ms
Addition = 17 ms
haveFive = 23 ms
headSup42 = 0 ms
Vider = 0 ms