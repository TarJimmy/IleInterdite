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
public class Message {
    TypeMessage type;
    Message(TypeMessage type){
        setType(type);
    }

    private void setType(TypeMessage type) {
        this.type = type;
    }
}
