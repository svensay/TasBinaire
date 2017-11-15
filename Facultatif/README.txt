Projet réalisé par Sven Say et Marc Bannout du groupe 3.

Conclusion pour l'exercice 4:
Pour conclure on remarque que le temps d'exécution pour TBDQueue est plus long que celui de TBQueue mais on a pas de limite de taille pour ce dernier.
TBDQueue; Bien quand on ne veut pas avoir une limite de taille mais lent lors de l'execution.
TBQueue; Rapide lors de l'execution mais une limite de taille est imposé.

Question facultatif de l'exercice 4:
On remarque que ALTBQueue a les avantages de TBDQueue et TBQueue, il est rapide et n'a pas de limite de taille.
On conclue donc que ALTBQueue est le plus optimisé pour implémenter un tas binaire.

Pour compiler et exécuter: javac *.java && java Main
Résultat en fin d'exécution lors d'une execution a 200000 de valeur(le programme prend quelques minutes à s'exécuter):
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
Tas(int) aprés ajout : ["100" "19" "36" "17" "3" "25" "1" "2" "7" ]
Test d'iteration (Exo4 facultatif) : 100 19 36 17 3 25 1 2 7 
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
Ajouter = 18 ms
Carre = 66 ms
Addition = 12 ms
haveFive = 4 ms
headSup42 = 0 ms
Vider = 4 ms
Temps d'éxecution pour ArrayDeque<Integer> : 
Ajouter = 11 ms
Carre = 47 ms
Addition = 29 ms
haveFive = 7 ms
headSup42 = 0 ms
Vider = 2 ms
Temps d'éxecution pour TBQueue<Integer> : 
Ajouter = 139 ms
Carre = 122 ms
Addition = 49 ms
haveFive = 10 ms
headSup42 = 0 ms
Vider = 62 ms
Temps d'éxecution pour TBDQueue<Integer> : 
Ajouter = 1057 ms
Carre = 2786 ms
Addition = 4 ms
haveFive = 11 ms
headSup42 = 0 ms
Vider = 140 ms
Temps d'éxecution pour ALTBQueue<Integer> : 
Ajouter = 76 ms
Carre = 90 ms
Addition = 29 ms
haveFive = 5 ms
headSup42 = 0 ms
Vider = 56 ms
