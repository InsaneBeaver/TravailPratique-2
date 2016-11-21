/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prjtp2;

/**
 *
 * @author 1628908
 */
public class Messages {
    public static String SEPARATEUR      = "-----------------------------------------------------------------";
    public static String CONTINUER       = "(Faites entrée pour continuer)";
    public static String AFFICHERCAPITAL = "Vous avez %d$!";
    public static String AFFICHERJEU1   = "Vos cartes: ";
    public static String AFFICHERJEU2   = "Vos nouvelles cartes: ";
    public static String REQUETECARTES = "Veuillez entrer jusqu'à 4 positions de 1 à 5 des cartes que vous désirez changer, séparées d'un espace: ";
    public static String POSITIONERREUR1 = "Trop de positions. Le maximum est 4. ";
    public static String ERREURREPETITION = "Vous ne pouvez pas entrer la même carte plusieurs fois. ";
    public static String POSITIONERREUR2 = "La position no. %d que vous avez fournie (%s) est invalide. ";
    public static String POSITIONERREUR3 = "La position no. %d que vous avez fournie (%s) n'est pas entre 1 et 5. ";
    public static String CARTESACHANGER  = "Voici les cartes que vous vous apprêtez à changer: ";
    public static String CONFIRMATION    = "Voulez-vous bien changer ces cartes?";
    public static String INFOCOMBINAISON        = "Vous avez %s.";
    public final static String[] COMBINAISONS = {"des cartes toutes différentes", "une paire", "deux paires", "un brelan", "un carré", "un full"};
    public final static String COULEUR     = "De plus, toutes les cartes sont de même couleur";
    public final static String QUINTE = "Vous avez une quinte!";
    public final static String QUINTEROYALE = "Vous avez une quinte royale!!!";
    
    public static String INFOGAIN   = "Vous gagnez %d$!";
    public static String INFOPERTE  = "Vous perdez %d$...";
    public static String INFORIEN   = "Vous ne gagnez rien.";
    public static String FIN             = "C'est fini. Vous n'avez plus d'argent. ";
    
    public static String RECOMMENCER     = "Voulez-vous recommencer?";
    public static String BIENVENUE       = "Bonjour! Bienvenue dans cette simulation de poker! ";
    public static String EXPLICATION1    = "Le jeu fonctionne comme suit. Vous recevez 5 cartes. Vous avez le droit d'en changer jusqu'à 4. ";
    public static String EXPLICATION2    = "Les critères d'évaluation sont les suivants: ";
    public static String REGLES          = "\tLes cartes sont toutes différentes: -10$\n" +
"\tUne paire (2 cartes de mêmes valeurs et de sortes différentes): 0$\n" +
"\tDeux paires: 20$\n" +
"\tUn brelan (3 cartes de mêmes valeurs): 35 $\n" +
"\tUne quinte (5 cartes qui se suivent dans l’ordre des valeurs): 50$\n" +
"\tUn full (un brelan + une paire): 75 $\n" +
"\tUne couleur (5 cartes de la même sorte): 100 $\n" +
"\tUn carré (4 cartes identiques): 150 $\n" +
"\tUne quinte royale (5 cartes qui se suivent dans la même sorte): 500 $\n";
    public static String CAPITAL         = "Vous commencez toujours avec 100$.";
    
}
