/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controleur;


/**
 *
 * @author laurillau
 */
public interface Observateur {
    public void traiterMessage(Message msg);
    public void traiterMessageAction(MessageAction msg);
}