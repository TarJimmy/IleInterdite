/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 *
 * @author tardy
 */
public class MessageAction extends Message {
    private MesEnums.typeAction typeact;
    MessageAction(MesEnums.typeAction act){
        super(TypeMessage.ACTION);
        setTypeact(act);
    }

    public void setTypeact(MesEnums.typeAction typeact) {
        this.typeact = typeact;
    }
}
