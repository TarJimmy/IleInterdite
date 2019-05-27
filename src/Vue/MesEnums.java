/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

/**
 *
 * @author groott
 */
public class MesEnums {
    public enum typeAction {
	ASSECHER,
	GAGNERCARTE,
	DONNERCARTE,
	DEPLACER
    }
    
    public enum typeMessage {
	ACTION,
	ENLEVERCARTE
    }
    public enum tresor {
        
    }
    
    public enum nomTuile {
        ;
	private MesEnums.tresor tresor;
	private String nomLieu;

    }
}
