/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package SanaIndeksi;

import java.io.InputStream;
import java.io.Serializable;
import java.util.StringTokenizer;

/**
 * Luokka huolehtii sanojen muotoilusta niitä tallennettaessa. 
 * @author heidi
 */
public class Muotoilija implements Serializable {

    /**
     * Konstruktori luo Muotoilija-olion
     */
    public Muotoilija() {
    }
    
    /**
     * Metodi poistaa annetun sanan alusta ja lopusta mahdolliset merkit, jotka eivät ole kirjaimia.
     * <p>Tarkistaakseen onko merkki kirjain, metodi kutsuu eiKirjain-metodia.
     * @param sana String-muotoinen sana, jota halutaan muotoilla.
     * @return Palauttaa sanan ilman alussa tai lopussa olevia erikoismerkkejä.
     */
    public String trimmaa(String sana) {
        String karsittuSana = sana.trim();
        int alku = 0;
        while (karsittuSana.length()>0 && eiKirjain(karsittuSana.charAt(0))) {
            karsittuSana = karsittuSana.substring(1);
        }
        while (karsittuSana.length()>0 && eiKirjain(karsittuSana.charAt(karsittuSana.length()-1))) {
            karsittuSana = karsittuSana.substring(0, karsittuSana.length()-1);
        }
        return karsittuSana;
    }
    
    /**
     * Metodi tarkastaa onko annettu merkki kirjain.
     * @param merkki Char-merkki, josta halutaan tietää onko se kirjain vai ei.
     * @return true jos merkki ei ole kirjain.
     */
    private boolean eiKirjain(char merkki) {
        return ("abcdefghijklmnopqrstuvwxyzåäö".indexOf(merkki) ==-1);
    }
    
    
    
}
