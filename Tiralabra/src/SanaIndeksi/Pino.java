/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.Serializable;

/**
 * Luokka huolehtii hakusanojen hakutermien järjestyksestä.
 * @author heidi
 */
public class Pino implements Serializable {
    /**
     * Pinon päällimmäinen solmu.
     */
    public PinoSolmu huippu;

    /**
     * Konstruktori luo tyhjän pinon.
     */
    public Pino() {
        huippu = null;
    }
    
    /**
     * Metodi lisää pinon päälle uuden solmun.<p>
     * Käytännössä toinen parametreista on aina null.
     * Luo uuden solmun, jolla annetut parametrit, ja merkitsee sen pinon päällimmäisenä olevaksi.
     * @param sana String-muotinen hakutermi.
     * @param rivit Int-taulukko jossa lista riveistä.
     */
    public void push(String sana, int[] rivit) {
        PinoSolmu uusi = new PinoSolmu(sana, rivit, huippu);
        huippu = uusi;
    }
    
    /**
     * Metodi poistaa pinosta päällimmäiset solmun.<p>
     * Asettaa poistettavan solmun edellisen päällimmäiseksi.
     * @return PinosSolmu, joka poistettiin pinosta.
     */
    public PinoSolmu pop() {
        PinoSolmu popattava = huippu;
        huippu = popattava.edellinen;
        return popattava;
    }
    
    /**
     * Metodi kertoo onko pino tyhjä.
     * @return true jos pino on tyhjä.
     */
    public boolean empty() {
        if (huippu == null) {
            return true;
        }
        return false;
    }
    
}
