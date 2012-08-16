/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

/**
 * Luokka huolehtii pinon solmuihin liittyvistä sanoista ja rivinumero-taulukoista.
 * @author heidi
 */
public class PinoSolmu {
    /**
     * Hakusana
     */
    String sana;
    /**
     * Edellinen solmu pinossa
     */
    PinoSolmu edellinen;
    /**
     * Rivinumerot-taulukko
     */
    int[] rivit;

    /**
     * Konstruktori luo solmun, jolla on hakusana ja rivi-taulukko sekä tieto edellisestä solmusta.
     * @param sana
     * @param rivit
     * @param edellinen 
     */
    public PinoSolmu(String sana, int[] rivit, PinoSolmu edellinen) {
        this.sana = sana;
        this.edellinen = edellinen;
        this.rivit = rivit;
    }
    
    
}


