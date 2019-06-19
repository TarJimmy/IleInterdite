
package Controleur;

/**
 *
 * @author laurillau
 */
public class Observe {
    private Observateur observateur;
    
    public void addObservateur(Observateur o) {
        this.observateur = o;
    }
    
    public void notifierMessage(Message m) {
        if (observateur != null) {
            observateur.traiterMessage(m);
        }
    }
    public void notifierMessageAction(MessageAction msg){
        if (observateur != null) {
            observateur.traiterMessageAction(msg);
        }
    }
}
