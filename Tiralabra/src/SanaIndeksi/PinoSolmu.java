/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.Serializable;

/**
 * Luokka huolehtii pinon solmuihin liittyvistä sanoista ja rivinumero-taulukoista.
 * @author heidi
 */
public class PinoSolmu implements Serializable {
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
     * Konstruktori luo solmun, jolla on hakusana ja rivi-taulukko sekä tieto edellisestä solmusta.<p>
     * Käytännössä solmuilla on joko hakutermi (and, or, ( ), jolloin solmu on hakutermipinossa 
     * tai haetun sanan rivitaulukko (sanat-pinossa). Toinen on aina null.
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


