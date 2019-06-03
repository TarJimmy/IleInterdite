package Modele;


import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Gholbin
 */
public class DemoConsole {
    
    
    
    public static void Assecher(Grille grille,Aventurier av){
        System.out.println("L'aventurier est sur la tuile : " + av.getTuile() 
                + "\nde coordonnées : {" + av.getTuile().getCoords()[0] + " , " + av.getTuile().getCoords()[1] + "}");
        
        ArrayList<Tuile> assechements = av.getAssechement(grille);
        System.out.println("Vous pouvez assecher à " + assechements.size() + " endroits");
        int i = 1;
        for(Tuile tui : assechements){
            System.out.println(i +") nom : " + tui);
            System.out.println("coordonnées : '" + tui.getCoords()[0] + " , " + tui.getCoords()[1] + "}");
            i++;
        }
        System.out.println("\nQuelle est votre choix ?");
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        av.deplacer(assechements.get(nb-1));
    }
    
    
    public static void Deplacer(Grille grille,Aventurier av){
        System.out.println("L'aventurier est sur la tuile : " + av.getTuile() 
                + "\nde coordonnées : {" + av.getTuile().getCoords()[0] + " , " + av.getTuile().getCoords()[1] + "}");
        
        ArrayList<Tuile> deplacements = av.getDeplacement(grille);
        System.out.println("Vous pouvez vous deplacer à " + deplacements.size() + " endroits");
        int i = 1;
        for(Tuile tui : deplacements){
            System.out.println(i +") nom : " + tui);
            System.out.println("coordonnées : '" + tui.getCoords()[0] + " , " + tui.getCoords()[1] + "}");
            i++;
        }
        System.out.println("\nQuelle est votre choix ?");
        Scanner sc = new Scanner(System.in);
        int nb = sc.nextInt();
        av.deplacer(deplacements.get(nb-1));
    }
    
    
    
    
    public static void main(String[]args ){
        Scanner sc = new Scanner(System.in);
        int num;
        Grille grille = new Grille(0);
        Tuile depart = grille.getTuile(3,3);
        Aventurier[] avs = new Aventurier[] {new Ingenieur(depart),new Pilote(depart),new Explorateur(depart),new Plongeur(depart)};
        grille.getTuile(3,3).Inonder();                 //cette tuile est sous le joueur au depart
        grille.getTuile(2,3).Inonder();                 //cette tuile est adjacente
        grille.getTuile(3,4).Inonder();                 
        grille.getTuile(3,4).Inonder();                 //cette tuile est coulée et adjacente
        grille.getTuile(4,4).Inonder();                 //cette tuile est en diagonale au debut
        grille.getTuile(2,2).Inonder();                 //celle là aussi
        
        
        
        
        
        System.out.println("Quelle type d'aventurier ?");
        System.out.println("Ingenieur : 1");
        System.out.println("Pilote : 2");
        System.out.println("Explorateur : 3");
        System.out.println("Plongeur : 4");
        
        num = sc.nextInt() -1;
        
        avs[num].DebutTour();
        System.out.println("Vous avez " + avs[num].getActionsRestantes() + " actions");
        while(avs[num].getActionsRestantes() != 0){
            System.out.println("Assecher : 1" + "\nDeplacer : 2");
            int rep  = sc.nextInt();
            if(rep == 1){
                DemoConsole.Assecher(grille, avs[num]);
            }else if(rep == 1){
                DemoConsole.Deplacer(grille, avs[num]);
            }else{
                System.out.println("Action non reconnue, recommencez.");
            }
            System.out.println("Il vous reste " + avs[num].getActionsRestantes() + " actions");
        }
        System.out.println("Merci d'avoir joué");
    }
}
