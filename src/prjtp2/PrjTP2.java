package prjtp2;
import java.util.Random;
import java.util.Scanner;


public class PrjTP2 {


    public static void main(String[] args) {
        test();
        Afficher_les_règles();
        int ronde = 1;
        while(true)
        {
            // Compteur de rondes.
            System.out.println("Ronde " + ronde + ": ");
            faireTour();
            
            // On demande à l'utilisateur s'il veut recommencer.
            if(!demanderOuiOuNon(Messages.RECOMMENCER)) break;
            
            ronde++;
        }
    }
    public static void faireTour()
    {
        int capital = 100;
        while(capital > 0)
        {
            System.out.println("====================================================================");
            System.out.println("");
            System.out.println(String.format(Messages.AFFICHERCAPITAL, capital));
            
            // Initialisation et affichage du jeu
            int[] paquet = initialiser();
            Mélanger_Paquet(paquet);
            int[] jeu = Donner_Cartes(paquet, 5);
            trier(jeu);
            System.out.println(Messages.AFFICHERJEU1);
            AfficherCartes(jeu);
            
            // Changement des cartes
            int[] positionsAChanger;
            do
            {
                System.out.println(Messages.SEPARATEUR);
                positionsAChanger = ObtenirNoCartesValides();

            
            } while(!confirmerPositions(positionsAChanger, jeu));
            
            ChangerCartes(jeu, paquet, positionsAChanger);
            trier(jeu);
            System.out.println(Messages.SEPARATEUR);
            System.out.println(Messages.AFFICHERJEU2);
            AfficherCartes(jeu);
            System.out.println(Messages.SEPARATEUR);
            
            // Évaluation du jeu
            int gain = evaluerJeu(jeu);
            afficher$(gain);
            capital += gain;
            
            continuer();
        }
        System.out.println(Messages.FIN);
    }
    
    public static void test()
    {
        int[] jeu1 = {0, 6, 23, 44, 50};
        int[] jeu2 = {0, 6, 23, 45, 50};
        int[] jeu3 = {0, 13, 22, 48, 50};
        int[] jeu4 = {2, 15, 17, 28, 49};
        int[] jeu5 = {0, 13, 26, 39, 40};
        int[] jeu6 = {1, 14, 27, 10, 23};
        int[] jeu7 = {0, 14, 15, 29, 30};
        int[] jeu8 = {13, 15, 19, 23, 24};
        int[] jeu9 = {39, 40, 41, 42, 43};
        
        System.out.println("Tester un jeu tout différent...");
        testJeu(jeu1);
        
        System.out.println("Tester un jeu avec une paire...");
        testJeu(jeu2);
        
        System.out.println("Tester un jeu avec deux paires...");
        testJeu(jeu3);
        
        System.out.println("Tester un jeu avec un brelan....");
        testJeu(jeu4);
        
        System.out.println("Tester un jeu avec un carré...");
        testJeu(jeu5);
        
        System.out.println("Tester un jeu avec un full...");
        testJeu(jeu6);
        
        System.out.println("Tester un jeu avec une quinte...");
        testJeu(jeu7);
        
        System.out.println("Tester un jeu avec une couleur...");
        testJeu(jeu8);
        
        System.out.println("Tester un jeu avec une quinte royale...");
        testJeu(jeu9);
        
        continuer();
        
        
    }
    public static void testJeu(int[] jeu)
    {
        AfficherCartes(jeu);
        int gain = evaluerJeu(jeu);
        afficher$(gain);
        System.out.println(Messages.SEPARATEUR);
    }
    
    public static void continuer()
    {
        System.out.println(Messages.CONTINUER);
        Scanner foo = new Scanner(System.in);
        foo.nextLine();
    }
    public static boolean demanderOuiOuNon(String message)
    {
        Scanner clavier = new Scanner(System.in);
        boolean resultat;
        while(true)
        {
            System.out.print(message);
            System.out.println(" (Entrez o/O pour oui et n/N pour non)");
            String chaine = clavier.nextLine().toLowerCase();
            char premier = chaine.charAt(0);
            if(premier == 'o')
            {
                resultat = true;
                break;
            }
            else if(premier == 'n')
            {
                resultat = false;
                break;
            }

        }
        return resultat;

    }
    
    public static int[] initialiser()
    {
        int[] paquet = new int[52];
        for(int i = 0; i < paquet.length; i++) paquet[i] = i;
        return paquet;
    }
    
    public static void Afficher_les_règles() 
    {
        System.out.println(Messages.BIENVENUE);
        System.out.println(Messages.EXPLICATION1);
        System.out.println(Messages.EXPLICATION2);
        System.out.println(Messages.REGLES);
        System.out.println(Messages.CAPITAL);
    }
    
    public static char[] Chercher_Sorte(int[] cartes)
    {
        // Trouve toutes les sortes (pique, trèfle, carreau, coeur) et range leurs symboles dans un tableau.
        char[] sortes = new char[cartes.length];
        int iSortes = 0;
        for(int carte : cartes)
        {
            sortes[iSortes] = Constantes.CHARSORTES[carte / 13];
            iSortes++;

        }
        return sortes;
    }
    
    public static String[] Chercher_Valeur(int[] cartes)
    {
        // Trouve toutes les valeurs (as, 2, 3, ..., dame, roi)
        String[] valeurs = new String[cartes.length];
        int iValeurs = 0;
        for(int carte : cartes)
        {
            valeurs[iValeurs] = Constantes.STRVALEURS[carte % 13];
            iValeurs++;
        }
        return valeurs;
    }
        
    public static void trier(int[] tableau)
    {
        // Trie un tableau d'entiers par bubble sort
        for(int i = tableau.length - 1; i >= 0; i--)
            for(int j = 0; j < i; j++)
            {
                if(tableau[j] > tableau[j+1])
                {
                    int temp = tableau[j];
                    tableau[j] = tableau[j+1];
                    tableau[j+1] = temp;
                }
            }
    }
    
    
    public static int[] Donner_Cartes(int[] cartes, int nb)
    {
        // Prend nb cartes du paquet de cartes.
        int[] nouvellesCartes = new int[nb];
        for(int i = 0; i < nb; i++)
        {
            nouvellesCartes[i] = Donner_Carte(cartes);
        }
        return nouvellesCartes;
    }
    
    public static int Donner_Carte(int[] cartes) 
    {
        int carte = -1;
        for(int i = 0; i < cartes.length; i++)
        {
            // Les cartes qui sont choisies sont remplacées par -1 pour éviter qu'elles soient utilisées une autre fois. 
            if(cartes[i] != -1)
            {
                carte = cartes[i];
                cartes[i] = -1;
                break;
            }
        }
        if(carte == -1)
            throw new RuntimeException("Toutes les cartes du paquet ont été prises");
        
        return carte;
    }
    
    public static void Mélanger_Paquet(int[] cartes)
    {
        // Mélange le paquet à l'aide du mélange de Fisher-Yates
        Random r = new Random();
        
        for(int i = cartes.length - 1; i > 0; i--)
        {
            // i et j sont des index
            int j = r.nextInt(i + 1);
            
            int temp = cartes[i];
            cartes[i] = cartes[j];
            cartes[j] = temp;
        }
    }
    public static void AfficherCartes(int[] jeu)
    {
        // Affiche toutes les cartes
        char[] sortes = Chercher_Sorte(jeu);
        String[] valeurs = Chercher_Valeur(jeu);
        // Affiche les valeurs
        for(String valeur : valeurs)
            System.out.print(valeur + "\t");
        
        System.out.println();
        // Affiche les sortes
        for(char sorte : sortes)
            System.out.print(sorte + "\t");
        
        System.out.println();

    }
    
    public static void ChangerCartes(int[] jeu, int[] cartes, int[] positions)
    {
        int[] nouvellesCartes = Donner_Cartes(cartes, positions.length);
        int iNouvellesCartes = 0;
        for(int position : positions)
        {
            jeu[position] = nouvellesCartes[iNouvellesCartes];
            iNouvellesCartes++;
        }
    }
    
    public static int[] ObtenirNoCartesValides()
    {
        int[] positions;
        Scanner clavier = new Scanner(System.in);
        
        boucle:
        while(true)
        {
            System.out.println(Messages.REQUETECARTES);
            String[] positionsChaines = decouperChaine(clavier.nextLine());
            
            // Pas plus que 4 permises
            if(positionsChaines.length > 4)
            {
                System.out.println(Messages.POSITIONERREUR1);
                continue;
            }
            
            positions = new int[positionsChaines.length];
            int iPositions = 0;
            
            for(String positionChaine : positionsChaines)
            {
                int nouvellePosition;
                try
                {
                    nouvellePosition = Integer.parseInt(positionChaine);
                }
                catch(NumberFormatException e)
                {
                    // Pas lisible
                    System.out.println(String.format(Messages.POSITIONERREUR2, iPositions + 1, positionChaine));
                    break;
                }

                // 1 2 3 4 5 permis seulement
                if(nouvellePosition < 1 || nouvellePosition > 5)
                {
                    System.out.println(String.format(Messages.POSITIONERREUR3, iPositions + 1, positionChaine));
                    break;
                }

                // Vérifier que la même carte ne se répète pas
                boolean unique = true;
                for(int j = 0; j < iPositions; j++)
                {
                    unique &= positions[j] != nouvellePosition - 1;
                }

                if(!unique)
                {
                    System.out.println(Messages.ERREURREPETITION);
                    break;
                }

                positions[iPositions] = nouvellePosition - 1;
                iPositions++;
            }
            // Si toutes les positions ont été évaluées, cette condition est vraie.
            if(iPositions == positions.length) break;
        }
        
        return positions;
    }
    public static boolean confirmerPositions(int[] positions, int[] jeu)
    {
        // Confirmer les positions obtenues auprès de l'utilisateur
        int[] cartesAChanger = new int[positions.length];
        for(int index = 0; index < cartesAChanger.length; index++)
            cartesAChanger[index] = jeu[positions[index]];

        trier(cartesAChanger);

        System.out.println(Messages.CARTESACHANGER);
        AfficherCartes(cartesAChanger);
        return (demanderOuiOuNon(Messages.CONFIRMATION));
    }
    
    public static String[] decouperChaine(String str)
    {
        // En plus de découper la chaîne en fonction des espaces, elle supprime toutes les chaînes vides qui viennent avec.
        String[] decoupageDeDepart = str.split("\\s+");
        int len = 0;
        for (String chaine : decoupageDeDepart) {
            if (!chaine.equals("")) {
                len++;
            }
        }
        
        String[] vraiDecoupage = new String[len];
        int vraiDecoupageIndex = 0;
        
        for (String chaine : decoupageDeDepart) {
            if (!chaine.equals("")) {
                vraiDecoupage[vraiDecoupageIndex] = chaine;
                vraiDecoupageIndex++;
            }
        }
        
        return vraiDecoupage;
    }
    
    public static int trouverCombinaison(int[] jeu)
    {
        int combinaison = Constantes.CARTESTOUTESDIFFERENTES; // Cartes toutes différentes

        for(int valeur = 0; valeur < 13; valeur++)
        {
            int occurences = 0; // Nombre de cartes i dans le jeu
            for(int carte : jeu) 
                if(carte % 13 == valeur)
                    occurences++;
            
            int nouvelleCombinaison = Constantes.CARTESTOUTESDIFFERENTES;
            switch(occurences)
            {
                case 2:
                    nouvelleCombinaison = Constantes.UNEPAIRE;
                    break;
                case 3:
                    nouvelleCombinaison = Constantes.BRELAN;
                    break;
                case 4:
                    nouvelleCombinaison = Constantes.CARRE;
                    break;
            }
            
            // Tester pour des combinaisons (deux paires et full)
            switch(nouvelleCombinaison)
            {
                case Constantes.UNEPAIRE:
                    if(combinaison == Constantes.UNEPAIRE)
                        combinaison = Constantes.DEUXPAIRES;
                    
                    else if(combinaison == Constantes.BRELAN)
                        combinaison = Constantes.FULL;
                    
                    break;
                    
                case Constantes.BRELAN:
                    if(combinaison == Constantes.DEUXPAIRES)
                        combinaison = Constantes.FULL;
                    break;
            }
            
            if(combinaison == Constantes.CARTESTOUTESDIFFERENTES)
                combinaison = nouvelleCombinaison;
        }
        return combinaison;
    }
    
    public static boolean estQuinte(int[] jeu)
    {
        // On assume que le jeu est trié
        boolean resultat = true;
        int premiereValeur = jeu[0] % 13;
        
        for(int i = 1; i < jeu.length && resultat; i++)
            resultat &= (premiereValeur + i == jeu[i] % 13);
        
        
        return resultat;
    }
    
    public static boolean sontToutesMemeCouleur(int[] jeu)
    {
        // Vérifie si le jeu contient une couleur
        boolean resultat = true;
        int couleur = jeu[0] / 13;
        
        for(int i = 1; i < jeu.length && resultat; i++)
            resultat &= (jeu[i] / 13 == couleur);
        
        return resultat;
    }
    
    public static int evaluerJeu(int[] jeu)
    {
        int gain = 0;
        boolean estMemeCouleur = sontToutesMemeCouleur(jeu);
        boolean estQuinte      = estQuinte(jeu);
        
        // Si c'est une quinte, ça ne sert à rien de tester la combinaison
        if(estQuinte)
        {
            // Quinte + couleur -> Quinte royale
            if(estMemeCouleur)
            {
                gain = Constantes.QUINTEROYALEVAL;
                System.out.println(Messages.QUINTEROYALE);
            }
            
            // Quinte + !couleur -> Quinte
            else
            {
                gain = Constantes.QUINTEVAL;
                System.out.println(Messages.QUINTE);
            }
        }
        else 
        {
            //!quinte -> peut-être qu'il y a une combinaison (paire, deux paires, etc. )
            int combinaison = trouverCombinaison(jeu);
            System.out.println(String.format(Messages.INFOCOMBINAISON, Messages.COMBINAISONS[combinaison]));
            gain = Constantes.COMBOVALEURS[combinaison];
            
            if(estMemeCouleur)
            {
                // Pas de pertes si même couleur. Même s'il n'y a pas de combinaison.
                if(gain == -10) 
                    gain = 0;
                
                gain += Constantes.COULEURVAL;
                System.out.println(Messages.COULEUR);
            }
        }

        return gain;
                
    }
    
    public static void afficher$(int gain)
    {
        if(gain == 0)
            System.out.println(Messages.INFORIEN);

        else if(gain > 0)
            System.out.println(String.format(Messages.INFOGAIN, gain));

        else
            System.out.println(String.format(Messages.INFOPERTE, -gain));
        
    }

}
